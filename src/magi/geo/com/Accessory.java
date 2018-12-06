package magi.geo.com;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Accessory {

    Scanner sc = new Scanner(System.in);

    static Character character1; // represent the first player.
    static Character character2; // represent the second player.
    // Both character are static because they must be unique for any object when the game is run

    // These variables below describe the full information about one player and they allowed us to return the value give
    // in the form.
    private int nbClass, level, life, strength, agility, intelligence;
    private String name;

    public int getNbClass() {
        return nbClass;
    }

    public int getLevel() {
        return level;
    }

    public int getLife() {
        return life;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public String getName() {
        return name;
    }

    /**
     * This method take in parameter one minimal and maximal value.
     * It allows to control and manage what the user enter.
     * It end and return the good value when the user finally enter the good value.
     *
     * @param minValue is the minimal value
     * @param maxValue is the maximal value
     * @param label    the label that show what to enter
     * @return
     */
    public int supportOfForm(int minValue, int maxValue, String label) {
        AtomicInteger value = new AtomicInteger(maxValue + 1); // initialise value at maxValue + 1.
        do {
            System.out.println(label);
            try {
                value.set(sc.nextInt());
            } catch (InputMismatchException e) {
                sc.next(); // destroy what the user enter if it is different to integer and let the value at
                // his initial value.
            }
            if (value.get() < minValue || value.get() > maxValue) {
                System.out.println("The value doesn't meet the standard! Please reenter");
                System.out.println("The value must be between " + minValue + " and " + maxValue + "\n");
            }
        } while (value.get() < minValue || value.get() > maxValue);
        return value.get();
    }

    /**
     * Display the form to create character and recover the enter values.
     */
    public void formToCreateCharacter() {
        nbClass = supportOfForm(1, 3, "Please choose the class of your character (1 - Warrior, 2 - Ranger, 3 - Wizard)");
        System.out.println("Enter the name of your character (Both Player must have different name)");
        name = sc.next();
        level = supportOfForm(1, 100, "Character level ?");
        strength = supportOfForm(0, 100, "Character strength ?");
        agility = supportOfForm(0, 100, "Character agility ?");
        intelligence = supportOfForm(0, 100, "Character intelligence ?");
    }

    /**
     * Take in parameters the number identifying the player and one character and then
     * the attribute the character to the correct player.
     * As the player don't have access to choose his identifying number, we can
     * test the (if) condition on 1 only because it will be manage by ourselves.
     * The first who fill the form will be the player1 and the second will be the player2
     *
     * @param nbPlayer  player identifying manage by ourselves.
     * @param character the character to create.
     */
    public void setCharacter(int nbPlayer, Character character) {
        if (nbPlayer == 1) {
            character1 = character;
            life = character1.getLevel() * 5;
            character1.setLife(life);
        } else {
            character2 = character;
            life = character2.getLevel() * 5;
            character2.setLife(life);
        }
    }

    public void displayBeginningState(String name, int level, int life, int strength, int agility, int intelligence, String state) {
        System.out.print(state + " " + name + " : level(" + level + ") life(" + life + ") strength(" + strength);
        System.out.println(") agility(" + agility + ") intelligence(" + intelligence + ")");
    }

    public void displayAdvancement(Character character) {
        System.out.print(character.getName() + " : life(" + character.getLife() + "), strength(" + character.getStrength());
        System.out.println("), agility(" + character.getAgility() + "), intelligence(" + character.getIntelligence() + ")");
    }
}
