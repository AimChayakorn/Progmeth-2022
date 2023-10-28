package controllers;

import event.pokemua.PokemuaEvent;
import event.pokemua.PokemuaSkillChangeEvent;
import logic.pokemua.Pokemua;
import logic.skill.Skill;

public class PokemuaController extends EventController<PokemuaEvent> {

    /**
     * Initialize pokemua controller
     */
    public PokemuaController(Controller parent) {
        super(parent);
    }

    /**
     * Unlearn Pokemua skill
     */
    public void unlearnSkill(Pokemua pokemua, Skill skill) {
        pokemua.unlearnSkill(skill);

        emit(new PokemuaSkillChangeEvent(pokemua));
    }
}
