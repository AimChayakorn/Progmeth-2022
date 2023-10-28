package action;

public abstract class Action {

    /**
     * Get type of action
     */
    public abstract Type getType();

    /**
     * Type of action
     */
    public enum Type {
        USE_SKILL,
        CHANGE_POKEMUA,
    }
}
