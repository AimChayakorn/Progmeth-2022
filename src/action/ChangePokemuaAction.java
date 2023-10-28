package action;

import logic.fighter.Fighter;
import logic.pokemua.Pokemua;

public class ChangePokemuaAction extends Action {

    /**
     * Fighter that change pokemua
     */
    private Fighter fighter;

    /**
     * Pokemua that fighter change to
     */
    private Pokemua pokemua;

    /**
     * Initialize change pokemua action
     */
    public ChangePokemuaAction(Fighter fighter, Pokemua pokemua) {
        this.fighter = fighter;
        this.pokemua = pokemua;
    }

    /**
     * Get fighter that change pokemua
     */
    public Fighter getFighter() {
        return fighter;
    }

    /**
     * Set fighter that change pokemua
     */
    public void setFighter(Fighter fighter) {
        this.fighter = fighter;
    }

    /**
     * Get pokemua that fighter change to
     */
    public Pokemua getPokemua() {
        return pokemua;
    }

    /**
     * Set pokemua that fighter change to
     */
    public void setPokemua(Pokemua pokemua) {
        this.pokemua = pokemua;
    }

    /**
     * Change action to string
     */
    @Override
    public String toString() {
        return "ChangePokemuaAction{" +
                "fighter=" + fighter +
                ", pokemua=" + pokemua +
                '}';
    }

    /**
     * Get type of action
     */
    @Override
    public Type getType() {
        return Type.CHANGE_POKEMUA;
    }
}
