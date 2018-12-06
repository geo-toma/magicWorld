package magi.geo.com;

public class Wizard extends Character {

    public Wizard(String name, int level, int strength, int agility, int intelligence) {
        super(name, level, strength, agility, intelligence);
    }

    @Override
    public int basicAttack() {
        return 0;
    }

    @Override
    public int specialAttack() {
        return 0;
    }
}
