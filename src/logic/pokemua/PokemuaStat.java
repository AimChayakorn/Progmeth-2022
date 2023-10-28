package logic.pokemua;

public class PokemuaStat {

    /**
     * Max HP of pokemua
     */
    private int maxHp;

    /**
     * Attack of pokemua
     */
    private int attack;

    /**
     * Defense of pokemua
     */
    private int defense;

    /**
     * ID of pokemua
     */
    private int id;

    /**
     * Initialize pokemua stat from Pokemon template and level
     */
    public PokemuaStat(PokemuaTemplate template, int level) {
        id = template.getId();
        setLevel(level);
    }

    /**
     * Initialize pokemua stat from Pokemon
     */
    public PokemuaStat(Pokemua pokemua) {
        id = pokemua.getId();
        attack = pokemua.getAttack();
        defense = pokemua.getDefense();
        maxHp = pokemua.getMaxHp();
    }

    /**
     * Set pokemua level
     */
    public void setLevel(int level) {
        PokemuaTemplate baseStats = PokemuaLoader.load(id);
        double factor = 1 + level * 0.02f;
        maxHp = (int) (factor * baseStats.getMaxHp());
        attack = (int) (factor * baseStats.getAttack());
        defense = (int) (factor * baseStats.getDefense());
    }

    /**
     * Get pokemua max HP
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * Set Pokemua max HP
     */
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    /**
     * Get pokemua attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Set pokemua attack
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * Get pokemua defense
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Set pokemua defense
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }
}
