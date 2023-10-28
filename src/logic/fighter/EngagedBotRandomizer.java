package logic.fighter;

import logic.Fight;
import logic.pokemua.Pokemua;
import logic.pokemua.PokemuaRandomizer;

import java.util.ArrayList;

public class EngagedBotRandomizer {

    /**
     * Generate random bot
     */
    public static EngagedBot random(String botName, Fight fight) {
        ArrayList<Pokemua> botPokemuas = new ArrayList<>();
        int numberOfPokemuas = (int) (Math.random() * 3) + 1;

        int playerPokemonLevel = 0;
        for (Pokemua p : fight.getEngagedPlayer().getAllPokemuas()) {
            playerPokemonLevel += p.getLevel();
        };

        int minPokemuaLevel = Math.max(1, playerPokemonLevel / fight.getEngagedPlayer().getAllPokemuas().size() - 30);
        int maxPokemuaLevel = playerPokemonLevel / fight.getEngagedPlayer().getAllPokemuas().size() + 30;

        for (int j = 0; j < numberOfPokemuas; j++) {
            botPokemuas.add(PokemuaRandomizer.random(minPokemuaLevel, maxPokemuaLevel));
        }

        return new EngagedBot(botName, botPokemuas, fight);
    }
}
