package magi.geo.com;

public class Game {

    Accessory ad = new Accessory(); // Allowed us to use the Accessory methods.

    /**
     * Take in parameter the number identifying the player, display and assist the
     * form to create one character and attribute the values give in the form
     * to the correct character and player.
     *
     * @param nbPlayer number identifying the player. It's manage by ourselves.
     */
    public void characterCreation(int nbPlayer) {
        do {
            ad.formToCreateCharacter();
            if (ad.getStrength() + ad.getAgility() + ad.getIntelligence() != ad.getLevel()) {
                System.out.println("The information not correspond! Please reconfigure your player");
                System.out.println("Level must be equal of the sum of strength, agility and intelligence.\n");
            }
        } while (ad.getStrength() + ad.getAgility() + ad.getIntelligence() != ad.getLevel());
        switch (ad.getNbClass()) {
            case 1:
                ad.setCharacter(nbPlayer, new Warrior(ad.getName(), ad.getLevel(), ad.getStrength(), ad.getAgility(), ad.getIntelligence()));
                ad.displayBeginningState(ad.getName(), ad.getLevel(), ad.getLife(), ad.getStrength(), ad.getAgility(), ad.getIntelligence(), "I'm the Warrior");
                break;
            case 2:
                ad.setCharacter(nbPlayer, new Ranger(ad.getName(), ad.getLevel(), ad.getStrength(), ad.getAgility(), ad.getIntelligence()));
                ad.displayBeginningState(ad.getName(), ad.getLevel(), ad.getLife(), ad.getStrength(), ad.getAgility(), ad.getIntelligence(), "I'm the Ranger");
                break;
            case 3:
                ad.setCharacter(nbPlayer, new Wizard(ad.getName(), ad.getLevel(), ad.getStrength(), ad.getAgility(), ad.getIntelligence()));
                ad.displayBeginningState(ad.getName(), ad.getLevel(), ad.getLife(), ad.getStrength(), ad.getAgility(), ad.getIntelligence(), "I'm the Wizard");
                break;
                /*
                    default case isn't important because nbClass can't have value other than 1, 2 or 3; it is regulate by supportOfForm method in Accessory.
                 */
        }
    }

    /**
     * Take in parameters two character and run the correct action given by characterA
     * on characterB.
     *
     * @param characterA the player who do the action.
     * @param characterB the player who receive the action.
     */
    public void characterAction(Character characterA, Character characterB) {
        String label = ("\n" + characterA.getName() + " (life : " + characterA.getLife() + ") Please enter your action (1 - basic attack or 2 - special attack)");
        int attack = ad.supportOfForm(1, 2, label);
        if (attack == 1)
            characterB.setLife(characterB.getLife() - characterA.basicAttack());
        else
            characterB.setLife(characterB.getLife() - characterA.specialAttack());
    }

    /**
     * This method assure the logic of the Game within his progress.
     */
    public void runGame() {
        do {
            System.out.println("\nPlayer(1) character creation");
            characterCreation(1);
            System.out.println("\nPlayer(2) character creation");
            characterCreation(2);
            if (Accessory.character1.getName().equals(Accessory.character2.getName())){
                System.out.println("\nGAME CAN'T START PLAYERS HAVE SAME NAME. RECREATE YOUR CHARACTERS !!!");
            }
        } while (Accessory.character1.getName().equals(Accessory.character2.getName()));
        System.out.println("\n\t\t\t\tBATTLE !!!\n");

        while (Accessory.character1.getLife() != 0 && Accessory.character2.getLife() != 0) {
            if (Accessory.character1.getLife() != 0)
                characterAction(Accessory.character1, Accessory.character2);
            if (Accessory.character2.getLife() != 0 && Accessory.character1.getLife() != 0)
                characterAction(Accessory.character2, Accessory.character1);
            System.out.println("\n\t\t\tADVANCEMENT :");
            ad.displayAdvancement(Accessory.character1);
            ad.displayAdvancement(Accessory.character2);
        }

        if (Accessory.character2.getLife() == 0) {
            System.out.println("\n KO! " + Accessory.character2.getName() + " is dead");
            System.out.println(Accessory.character2.getName() + " lost the game");
        } else {
            System.out.println("\n KO! " + Accessory.character1.getName() + " is dead");
            System.out.println(Accessory.character1.getName() + " lost the game");
        }
    }
}
