package event.pokemua;

import logic.pokemua.Pokemua;

public class PokemuaSkillChangeEvent extends PokemuaEvent {

    /**
     * Pokemua in the event
     */
    private Pokemua pokemua;

    /**
     * Initialize event
     */
    public PokemuaSkillChangeEvent(Pokemua pokemua) {
        this.pokemua = pokemua;
    }

    /**
     * Get pokemua
     */
    public Pokemua getPokemua() {
        return pokemua;
    }

    /**
     * Get type of event
     */
    @Override
    public Type getType() {
        return Type.POKEMUA_SKILL_CHANGE;
    }
}
