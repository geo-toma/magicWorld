package magi.geo.com;

public class Ranger extends Character {

    public Ranger(String name, int level, int strength, int agility, int intelligence) {
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
