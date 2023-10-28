package controllers;

import event.fight.*;
import javafx.application.Platform;
import logic.Fight;
import logic.FightReward;
import action.Action;
import action.ChangePokemuaAction;
import action.UseSkillAction;
import logic.fighter.EngagedPlayer;
import logic.fighter.Fighter;
import logic.pokemua.Pokemua;
import logic.pokemua.PokemuaLoader;
import logic.pokemua.PokemuaStat;
import logic.skill.Skill;
import logic.skill.SkillTemplate;

import java.util.ArrayList;

public class FightController extends EventController<FightEvent> {

    /**
     * Tick rate of fight
     */
    public static final int TICK_RATE = 64;

    /**
     * Tick interval of fight
     */
    private static final long TICK_INTERVAL = 1000 / TICK_RATE;

    /**
     * Thread that tick fight
     */
    private Thread ticker;

/**
     * Initialize fight controller
     */
    public FightController(Controller parent) {
        super(parent);
    }

    /**
     * Act on action
     */
    public void act(Action action) {
        switch (action.getType()) {
            case USE_SKILL -> {
                act((UseSkillAction) action);
            }
            case CHANGE_POKEMUA -> {
                act((ChangePokemuaAction) action);
            }
        }
    }

    /**
     * Act from change pokemua action
     */
    private void act(ChangePokemuaAction action) {
        Pokemua pokemua = action.getPokemua();
        Fighter fighter = action.getFighter();

        fighter.setCurrentPokemua(pokemua);
        emit(new ChangePokemuaEvent(fighter));
    }

    /**
     * Act from use skill action
     */
    private void act(UseSkillAction action) {
        Fighter attacker = action.getAttacker();
        Fighter target = action.getTarget();
        Skill skill = action.getSkill();

        emit(new ActionStartEvent(attacker, target, skill));
        attacker.useSkill(target, skill);
        Fight.UpdateStatus status = getFight().updateFighter(target);

        switch (status) {
            case NONE -> {

            }
            case POKEMUA_DEAD -> {
                emit(new ChangePokemuaEvent(target));
            }
            case FIGHTER_DEAD -> {
                emit(new FighterDeadEvent(target));
            }
        }

        emit(new ActionEndEvent(attacker, target, skill));
    }

    /**
     * Get fight
     */
    public Fight getFight() {
        return getController().getGameInstance().getFight();
    }

    /**
     * Get tick
     */
    public int getTick() {
        return getFight().getTick();
    }

    /**
     * Start ticker
     */
    protected void startTicker() {
        if (ticker != null) {
            return;
        }

        ticker = new Thread(() -> {
            while (!getFight().isGameEnd()) {
                try {

                    Action action = getFight().tick();

                    if (action != null) {
//                        System.out.println("Random action: " + action.toString());
                        Platform.runLater(() -> {
                            act(action);
                        });
                    }

                    emit(new TickEvent(getFight().getTick()));

                    Thread.sleep(TICK_INTERVAL);
                } catch (Exception e) {
                    return;
                }
            }

            Platform.runLater(() -> {
                endFight(true);
            });
        });

        ticker.setDaemon(true);
        ticker.start();
    }

    /**
     * Stop ticker
     */
    protected void stopTicker() {
        if (ticker == null) {
            return;
        }
        ticker.interrupt();
        ticker = null;
    }

    /**
     * End fight
     */
    public void endFight(boolean playerWin) {
        EngagedPlayer player = (EngagedPlayer) getFight().getEngagedPlayer();

        FightReward reward = getFight().endFight();

        stopTicker();

        if (playerWin) {
            FightEndEvent event = new FightEndEvent();

            int oldPlayerLevel = player.getPlayerLevel();
            event.setOldPlayerLevel(oldPlayerLevel);
            player.getPlayer().addExperience(reward.getExperience());
            int newPlayerLevel = player.getPlayerLevel();
            event.setNewPlayerLevel(newPlayerLevel);

            player.getSelectedPokemua().forEach(pokemua -> {
                PokemuaStat oldStat = new PokemuaStat(pokemua);
                int oldLevel = pokemua.getLevel();
                if (pokemua.addExperience(reward.getExperience())) {
                    ArrayList<SkillTemplate> newSkills = new ArrayList<>();

                    for (int l = oldLevel + 1; l <= pokemua.getLevel(); l++) {
                        SkillTemplate template = PokemuaLoader.getSkill(pokemua.getId(), l);
                        if (template != null) {
                            newSkills.add(template);
                            pokemua.learnSkill(template.toSkill(pokemua));
                        }
                    }

                    event.addPokemonLevelChange(oldStat, pokemua, newSkills);
                }
            });

            reward.getPokemuas().forEach(pokemua -> {
                getController().getPlayerController().addPokemua(pokemua);
            });

            event.setNewPokemuas(reward.getPokemuas());

            emit(event);
        }
    }
}
