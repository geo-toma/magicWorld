package magi.geo.com;

public class Wizard extends Character {

    public Wizard(String name, int level, int strength, int agility, int intelligence) {
        super(name, level, strength, agility, intelligence);
    }

    @Override
    public int basicAttack() {
        System.out.println(getName() + " uses basic attack (FIREBALL) and inflicts " + getIntelligence() + " damage");
        // We test on the name of the players to know who is lunch the attack. Players must have different name
        if (!Accessory.character1.getName().equals(getName()))
            System.out.println(Accessory.character1.getName() + " lost " + getIntelligence() + " life");
        else
            System.out.println(Accessory.character2.getName() + " lost " + getIntelligence() + " life");
        return getIntelligence();
    }

    @Override
    public int specialAttack() {
        setLife(getLife() + 2 * getIntelligence());
        System.out.println(getName() + " uses special attack (LIFE CARE) and increase his life by " + getIntelligence() * 2);
        return 0;
    }
}
