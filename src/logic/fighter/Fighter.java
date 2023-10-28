package logic.fighter;

import logic.Fight;
import logic.pokemua.Pokemua;
import logic.skill.Skill;

import java.util.List;
import java.util.stream.Stream;

public interface Fighter {

    /**
     * Get the current Pokemua
     */
    public Pokemua getCurrentPokemua();

    /**
     * Set the current Pokemua with the index
     */
    public void setCurrentPokemua(int index);

    /**
     * Set the current Pokemua with the Pokemua
     */
    public default void setCurrentPokemua(Pokemua pokemua) {
        int idx = getAllPokemuas().indexOf(pokemua);

        setCurrentPokemua(idx);
    }

    /**
     * Get all Pokemuas
     */
    public List<Pokemua> getAllPokemuas();

    /**
     * Get the fight
     */
    public Fight getFight();

    /**
     * Get the name
     */
    public String getDisplayName();

    /**
     * Use a skill
     */
    public default void useSkill(Fighter target, Skill skill) {
        skill.use(target);
        skill.setLastUsed(getFight().getTick());
    }

    /**
     * Get the alive Pokemuas
     */
    public default Stream<Pokemua> getAlivePokemuas() {
        return getAllPokemuas().stream().filter(pokemua -> !pokemua.isDead());
    }

    /**
     * Check if the fighter has some Pokemuas alive
     */
    public default boolean hasSomePokemuaAlive() {
        return getAlivePokemuas().count() != 0;
    }
}
