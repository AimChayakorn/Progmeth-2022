package logic.skill;

public class SkillLoader {

    /**
     * List of all skill data
     */
    public static final SkillTemplate[] skillData = {
            new AttackSkillTemplate(0, "Tackle", 35, 8),
            new AttackSkillTemplate(1, "Vine Whip", 35, 8),
            new AttackSkillTemplate(2, "Razor Leaf", 55, 8),
            new AttackSkillTemplate(3, "SolarBeam", 120, 8),
            new AttackSkillTemplate(4, "Scratch", 40, 8),
            new AttackSkillTemplate(5, "Ember", 40, 8),
            new AttackSkillTemplate(6, "Rage", 20, 8),
            new AttackSkillTemplate(7, "Slash", 70, 8),
            new AttackSkillTemplate(8, "Flamethrower", 95, 8),
            new AttackSkillTemplate(9, "Fire Spin", 15, 8),
            new AttackSkillTemplate(10, "Bubble", 20, 8),
            new AttackSkillTemplate(11, "Water Gun", 40, 8),
            new AttackSkillTemplate(12, "Bite", 60, 8),
            new AttackSkillTemplate(13, "Skull Bash", 100, 8),
            new AttackSkillTemplate(14, "Hydro Pump", 120, 8),
            new AttackSkillTemplate(15, "Confusion", 50, 8),
            new AttackSkillTemplate(16, "Psybeam", 65, 8),
            new AttackSkillTemplate(17, "Poison Sting", 15, 8),
            new AttackSkillTemplate(18, "Fury Attack", 15, 8),
            new AttackSkillTemplate(19, "Twineedle", 25, 8),
            new AttackSkillTemplate(20, "Pin Missile", 14, 8),
            new AttackSkillTemplate(21, "Gust", 40, 8),
            new AttackSkillTemplate(22, "Quick Attack", 40, 8),
            new AttackSkillTemplate(23, "Wing Attack", 35, 8),
            new AttackSkillTemplate(24, "Hyper Fang", 80, 8),
            new AttackSkillTemplate(25, "Super Fang", 20, 8),
            new AttackSkillTemplate(26, "Peck", 35, 8),
            new AttackSkillTemplate(27, "Drill Peck", 80, 8),
            new AttackSkillTemplate(28, "Wrap", 15, 8),
            new AttackSkillTemplate(29, "Acid", 40, 8),
            new AttackSkillTemplate(30, "ThunderShock", 40, 8),
            new AttackSkillTemplate(31, "Swift", 60, 8),
            new AttackSkillTemplate(32, "Thunder", 120, 8),
            new AttackSkillTemplate(33, "Fury Swipes", 18, 8),
            new AttackSkillTemplate(34, "Double Kick", 30, 8),
            new AttackSkillTemplate(35, "Body Slam", 85, 8),
            new AttackSkillTemplate(36, "Horn Attack", 65, 8),
            new AttackSkillTemplate(37, "Horn Drill", 20, 8),
            new AttackSkillTemplate(38, "Thrash", 90, 8),
            new AttackSkillTemplate(39, "Pound", 40, 8),
            new AttackSkillTemplate(40, "DoubleSlap", 15, 8),
            new AttackSkillTemplate(41, "Double-Edge", 100, 8),
            new AttackSkillTemplate(42, "Leech Life", 20, 8),
            new AttackSkillTemplate(43, "Absorb", 20, 8),
            new AttackSkillTemplate(44, "Petal Dance", 70, 8),
            new AttackSkillTemplate(45, "Psychic", 90, 8),
            new AttackSkillTemplate(46, "Dig", 100, 8),
            new AttackSkillTemplate(47, "Earthquake", 100, 8),
            new AttackSkillTemplate(48, "Pay Day", 40, 8),
            new AttackSkillTemplate(49, "Karate Chop", 50, 8),
            new AttackSkillTemplate(50, "Seismic Toss", 20, 8),
            new AttackSkillTemplate(51, "Take Down", 90, 8),
            new AttackSkillTemplate(52, "Low Kick", 50, 8),
            new AttackSkillTemplate(53, "Submission", 80, 8),
            new AttackSkillTemplate(54, "Slam", 80, 8),
            new AttackSkillTemplate(55, "Constrict", 10, 8),
            new AttackSkillTemplate(56, "Rock Throw", 50, 8),
            new AttackSkillTemplate(57, "Selfdestruct", 130, 8),
            new AttackSkillTemplate(58, "Explosion", 170, 8),
            new AttackSkillTemplate(59, "Stomp", 65, 8),
            new AttackSkillTemplate(60, "Headbutt", 70, 8),
            new AttackSkillTemplate(61, "SonicBoom", 20, 8),
            new AttackSkillTemplate(62, "Tri Attack", 80, 8),
            new AttackSkillTemplate(63, "Aurora Beam", 65, 8),
            new AttackSkillTemplate(64, "Ice Beam", 95, 8),
            new AttackSkillTemplate(65, "Sludge", 65, 8),
            new AttackSkillTemplate(66, "Clamp", 35, 8),
            new AttackSkillTemplate(67, "Spike Cannon", 20, 8),
            new AttackSkillTemplate(68, "Lick", 20, 8),
            new AttackSkillTemplate(69, "Night Shade", 20, 8),
            new AttackSkillTemplate(70, "Dream Eater", 100, 8),
            new AttackSkillTemplate(71, "Bind", 15, 8),
            new AttackSkillTemplate(72, "ViceGrip", 55, 8),
            new AttackSkillTemplate(73, "Guillotine", 20, 8),
            new AttackSkillTemplate(74, "Crabhammer", 90, 8),
            new AttackSkillTemplate(75, "Barrage", 15, 8),
            new AttackSkillTemplate(76, "Bone Club", 65, 8),
            new AttackSkillTemplate(77, "Bonemerang", 50, 8),
            new AttackSkillTemplate(78, "Rolling Kick", 60, 8),
            new AttackSkillTemplate(79, "Jump Kick", 70, 8),
            new AttackSkillTemplate(80, "Hi Jump Kick", 85, 8),
            new AttackSkillTemplate(81, "Mega Kick", 120, 8),
            new AttackSkillTemplate(82, "Comet Punch", 18, 8),
            new AttackSkillTemplate(83, "Fire Punch", 75, 8),
            new AttackSkillTemplate(84, "Ice Punch", 75, 8),
            new AttackSkillTemplate(85, "ThunderPunch", 75, 8),
            new AttackSkillTemplate(86, "Mega Punch", 80, 8),
            new AttackSkillTemplate(87, "Counter", 20, 8),
            new AttackSkillTemplate(88, "Smog", 20, 8),
            new AttackSkillTemplate(89, "Dizzy Punch", 70, 8),
            new AttackSkillTemplate(90, "Waterfall", 80, 8),
            new AttackSkillTemplate(91, "Blizzard", 120, 8),
            new AttackSkillTemplate(92, "Dragon Rage", 20, 8),
            new AttackSkillTemplate(93, "Hyper Beam", 150, 8),
            new AttackSkillTemplate(94, "Sky Attack", 140, 8),
    };

    /**
     * Loads a skill template from the skill data array.
     */
    public static SkillTemplate load(int index) {
        return skillData[index];
    }
}