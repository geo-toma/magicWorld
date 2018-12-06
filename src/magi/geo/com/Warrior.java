package magi.geo.com;

public class Warrior extends Character {

    public Warrior(String name, int level, int strength, int agility, int intelligence) {
        super(name, level, strength, agility, intelligence);
    }

    @Override
    public int basicAttack() {
        System.out.println(getName() + " uses basic attack (SWORD STROKE) and inflicts " + getStrength() + " damage");
        // We test on the name of the players to know who is lunch the attack. Players must have different name.
        if (!Accessory.character1.getName().equals(getName()))
            System.out.println(Accessory.character1.getName() + " lost " + getStrength() + " life");
        else
            System.out.println(Accessory.character2.getName() + " lost " + getStrength() + " life");
        return getStrength();
    }

    @Override
    public int specialAttack() {
        setLife(getLife() - getStrength() / 2);
        System.out.println(getName() + " uses special attack (RAGE STROKE) and inflicts " + getStrength() * 2 + " damage");
        // We test on the name of the players to know who is lunch the attack. Players must have different name
        if (!Accessory.character1.getName().equals(getName()))
            System.out.println(Accessory.character1.getName() + " lost " + getStrength() * 2 + " life");
        else
            System.out.println(Accessory.character2.getName() + " lost " + getStrength() * 2 + " life");
        System.out.println(getName() + " lost " + getStrength() / 2 + " life");
        return getStrength() * 2;
    }
}
