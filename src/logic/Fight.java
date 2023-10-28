package logic;

import action.Action;
import action.ChangePokemuaAction;
import action.UseSkillAction;
import logic.fighter.EngagedBotRandomizer;
import logic.fighter.EngagedPlayer;
import logic.fighter.Fighter;
import logic.fighter.WildPokemua;
import logic.pokemua.Pokemua;
import logic.skill.Skill;

import java.util.ArrayList;
import java.util.List;

public class Fight {
    /**
     * Random chance for action
     */
    private static final double RANDOM_ACTION_CHANCE = 0.01;
    /**
     * Bot change Pokemua chance
     */
    private static final double CHANGE_POKEMUA_CHANCE = 0.05;
    /**
     * Minimum enemies
     */
    private static final int MIN_ENEMIES = 1;
    /**
     * Maximum enemies
     */
    private static final int MAX_ENEMIES = 2;
    /**
     * Minimum wild Pokemua
     */
    private static final int MIN_WILD_POKEMUA = 0;
    /**
     * Maximum wild Pokemua
     */
    private static final int MAX_WILD_POKEMUA = 3;
    /**
     * List of fighter
     */
    private ArrayList<Fighter> fighters;
    /**
     * tick in fight
     */
    private int tick;
    /**
     * reward after win the game
     */
    private FightReward reward;
    /**
     * Player that engage the game
     */
    private EngagedPlayer engagedPlayer;

    /**
     * -Initialize engagedPlayer list of fighters
     * -Add engaged player to the list
     * -Initialize tick
     * -Initialize bots, wild Pokemuas, and reward
     */
    public Fight(Player player, ArrayList<Pokemua> selectedPokemua) {

        engagedPlayer = new EngagedPlayer(player, selectedPokemua, this);

        fighters = new ArrayList<>();
        fighters.add(engagedPlayer);

        tick = 0;

        int numberOfEnemies = (int) (Math.random() * (MAX_ENEMIES - MIN_ENEMIES)) + MIN_ENEMIES;
        for (int i = 0; i < numberOfEnemies; i++) {
            String botName = String.format("Bot %d", i);
            fighters.add(EngagedBotRandomizer.random(botName, this));
        }

        int numberOfWildPokemua = (int) (Math.random() * (MAX_WILD_POKEMUA - MIN_WILD_POKEMUA)) + MIN_WILD_POKEMUA;
        for (int i = 0; i < numberOfWildPokemua; i++) {
            fighters.add(new WildPokemua(this));
        }

        reward = new FightReward();
    }

    /**
     * -Add tick by 1
     * -Random whether bot will change Pokemua or use skills or not
     */
    public Action tick() {
        tick += 1;

        if (Math.random() < RANDOM_ACTION_CHANCE) {
            double odd = Math.random();
            int fighterIdx = (int) (Math.random() * (fighters.size() - 1)) + 1;
            Fighter fighter = fighters.get(fighterIdx);
            if (odd < CHANGE_POKEMUA_CHANCE) {
                int availableIdx = (int) fighter.getAlivePokemuas().count();
                int pokemuaIdx = (int) (Math.random() * availableIdx);
                Pokemua chosen = fighter.getAlivePokemuas().toList().get(pokemuaIdx);

                return new ChangePokemuaAction(fighter, chosen);
            } else {
                int targetIdx = fighterIdx;
                while (targetIdx == fighterIdx) {
                    targetIdx = (int) (Math.random() * fighters.size());
                }

                Fighter target = fighters.get(targetIdx);
                List<Skill> skills = fighter.getCurrentPokemua().getSkills();
                List<Skill> availableSkills = skills.stream().filter(skill -> skill.isAvailable(tick)).toList();
                if (availableSkills.isEmpty()) {
                    return null;
                }

                int skillIdx = (int) (Math.random() * availableSkills.size());
                Skill skill = availableSkills.get(skillIdx);

                return new UseSkillAction(fighter, target, skill);
            }
        }

        return null;
    }

    /**
     * -Update status of the fighter
     * -Action to Pokemua status
     * -if there is no Pokemua left that fighter will lose
     */
    public UpdateStatus updateFighter(Fighter fighter) {
        if (fighter.getCurrentPokemua().isDead()) {
            if (fighter.hasSomePokemuaAlive()) {
                Pokemua chosen = fighter.getAlivePokemuas().toList().get(0);
                int idx = fighter.getAllPokemuas().indexOf(chosen);
                fighter.setCurrentPokemua(idx);
                return UpdateStatus.POKEMUA_DEAD;
            } else {
                fighters.remove(fighter);
                reward.addReward(fighter);
                fighter.getAllPokemuas().forEach(pokemua -> pokemua.reset());

                return UpdateStatus.FIGHTER_DEAD;
            }
        }
        return UpdateStatus.NONE;
    }

    /**
     * Get tick of the fight
     */
    public int getTick() {
        return tick;
    }

    /**
     * Get engaged player
     */
    public Fighter getEngagedPlayer() {
        return engagedPlayer;
    }

    /**
     * Get all fighter in the fight
     */
    public List<Fighter> getFighters() {
        return fighters;
    }

    /**
     * Check if game is end or not
     */
    public boolean isGameEnd() {
        return fighters.size() == 1;
    }

    /**
     * End the fight by reset the status of all Pokemuas and get the reward
     */
    public FightReward endFight() {
        getEngagedPlayer().getAllPokemuas().forEach(pokemua -> pokemua.reset());
        fighters.forEach(fighter -> {
            fighter.getAllPokemuas().forEach(pokemua -> pokemua.reset());
        });
        return reward;
    }

    /**
     * The status of Pokemua and fighter
     */
    public enum UpdateStatus {
        NONE,
        POKEMUA_DEAD,
        FIGHTER_DEAD,
    }
}
