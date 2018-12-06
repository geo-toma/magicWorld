package magi.geo.com;

public class Ranger extends Character {

    public Ranger(String name, int level, int strength, int agility, int intelligence) {
        super(name, level, strength, agility, intelligence);
    }

    @Override
    public int basicAttack() {
        System.out.println(getName() + " uses basic attack (SHOOT AT BOW) and inflicts " + getAgility() + " damage");
        // We test on the name of the players to know who is lunch the attack. Players must have different name
        if (!Accessory.character1.getName().equals(getName()))
            System.out.println(Accessory.character1.getName() + " lost " + getAgility() + " life");
        else
            System.out.println(Accessory.character2.getName() + " lost " + getAgility() + " life");
        return getAgility();
    }

    @Override
    public int specialAttack() {
        setAgility(getAgility() + getLevel() / 2);
        System.out.println(getName() + "uses special attack (CONCENTRATION) and increase his agility by " + getLevel() / 2);
        return 0;
    }
}
