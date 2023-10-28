package logic.pokemua;

public class PokemuaRandomizer {

    /**
     * Generate random Pokemua
     */
    public static Pokemua random() {
        int pokemuaId = (int) (Math.random() * PokemuaLoader.SIZE) + 1;
        int pokemuaLevel = (int) (Math.random() * 100);

        Pokemua p = PokemuaLoader.load(pokemuaId, pokemuaLevel);

        return p;
    }

    /**
     * Generate random Pokemua with level range
     */
    public static Pokemua random(int minLevel, int maxLevel) {
        int pokemuaId = (int) (Math.random() * PokemuaLoader.SIZE) + 1;
        int levelRange = maxLevel - minLevel;
        int pokemuaLevel = (int) ((Math.random() + Math.random()) / 2.0 * levelRange) + minLevel;

        Pokemua p = PokemuaLoader.load(pokemuaId, pokemuaLevel);

        return p;
    }
}
