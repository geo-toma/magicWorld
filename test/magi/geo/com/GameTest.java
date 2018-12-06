package magi.geo.com;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    void Given_goodValue_When_characterCreationIsRun_Then_createCorrectCharacter() {
        System.setIn(new ByteArrayInputStream("2\nuser\n10\n0\n10\n0\n".getBytes()));
        Game game = new Game();
        game.characterCreation(1);
        assertTrue(Accessory.character1 instanceof Ranger);
    }

    @Test
    void Given_badValue_When_characterCreationIsRun_Then_askAgainFormAndCreateCorrectCharacter() {
        System.setIn(new ByteArrayInputStream("3\nuser\n10\n5\n0\n10\n3\nuser\n10\n0\n0\n10\n".getBytes()));
        Game game = new Game();
        game.characterCreation(2);
        String[] output = outContent.toString().replace("\r\t","\n").split("\n");
        assertEquals("The information not correspond! Please reconfigure your player",output[7]);
        assertTrue(Accessory.character2 instanceof Wizard);
    }

    @Test
    void Given_twoCharacter_When_characterUseBasicAttackAction_Then_useMatchedAction() {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        Game game = new Game();
        Accessory ad = new Accessory();
        Warrior user1 = new Warrior("user1",10,10,0,0);
        Wizard user2 = new Wizard("user2",10,0,0,10);
        ad.setCharacter(1,user1);
        ad.setCharacter(2,user2);
        game.characterAction(user1,user2);
        assertEquals(40,user2.getLife());
    }

    @Test
    void Given_twoCharacter_When_characterUseSpecialAttackAction_Then_useMatchedAction() {
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));
        Game game = new Game();
        Accessory ad = new Accessory();
        Ranger user1 = new Ranger("user1",10,0,10,0);
        Wizard user2 = new Wizard("user2",10,0,0,10);
        ad.setCharacter(1,user1);
        ad.setCharacter(2,user2);
        game.characterAction(user1,user2);
        assertEquals(50,user2.getLife());
        assertEquals(15,user1.getAgility());
    }

    @Test
    void Given_allInformation_When_GameIsRun_Then_runGameCorrectly() {
        System.setIn(new ByteArrayInputStream("1\nuser1\n10\n10\n0\n0\n3\nuser2\n10\n0\n0\n10\n1\n1\n2\n2\n2\n1\n2".getBytes()));
        Game game = new Game();
        game.runGame();
        String[] output = outContent.toString().replace("\r\n","\n").split("\n");
        assertEquals("user1 (life : 50) Please enter your action (1 - basic attack or 2 - special attack)",output[22]);
        assertEquals("user1 uses basic attack (SWORD STROKE) and inflicts 10 damage",output[23]);
        assertEquals("user2 lost 10 life",output[24]);
        assertEquals("user2 (life : 40) Please enter your action (1 - basic attack or 2 - special attack)",output[26]);
        assertEquals("user2 uses basic attack (FIREBALL) and inflicts 10 damage",output[27]);
        assertEquals("user1 lost 10 life",output[28]);
        assertEquals("user1 (life : 40) Please enter your action (1 - basic attack or 2 - special attack)",output[34]);
        assertEquals("user1 uses special attack (RAGE STROKE) and inflicts 20 damage",output[35]);
        assertEquals("user2 lost 20 life",output[36]);
        assertEquals("user1 lost 5 life",output[37]);
        assertEquals("user2 (life : 20) Please enter your action (1 - basic attack or 2 - special attack)",output[39]);
        assertEquals("user2 uses special attack (LIFE CARE) and increase his life by 20",output[40]);
        assertEquals("user2 lost the game",output[69]);
        assertEquals(0,Accessory.character2.getLife());
    }

}