package magi.geo.com;

public abstract class Character {

    private String name;
    private int level, strength, life, agility, intelligence;

    public Character(String name, int level, int strength, int agility, int intelligence) {
        this.name = name;
        this.level = level;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getStrength() {
        return strength;
    }

    public int getLife() {
        return life;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public abstract int basicAttack();

    public abstract int specialAttack();
}
