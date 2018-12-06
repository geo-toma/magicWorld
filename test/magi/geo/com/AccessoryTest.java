package magi.geo.com;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class AccessoryTest {

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
    void Given_correctValue_When_supportOfFormIsRun_Then_returnCorrectValue() {
        System.setIn(new ByteArrayInputStream("3\n".getBytes()));
        Accessory ad = new Accessory();
        assertEquals(3,ad.supportOfForm(1,5,"choice"));
    }

    @Test
    void Given_unmatchedValue_When_supportOfFormIsRun_Then_askAgainTheValue(){
        System.setIn(new ByteArrayInputStream("2\n7\ntext\n4\n".getBytes()));
        Accessory ad = new Accessory();
        int correctValue = ad.supportOfForm(3,6,"yours choice");
        String[] output = outContent.toString().replace("\r\n","\n").split("\n");
        assertEquals("The value doesn't meet the standard! Please reenter",output[1]);
        assertEquals("The value doesn't meet the standard! Please reenter",output[5]);
        assertEquals("The value doesn't meet the standard! Please reenter",output[9]);
        assertEquals(4,correctValue);
    }

    @Test
    void Fill_CorrectlyForm_When_formToCreateCharacterIsRun_Then_attributeCorrectValueToEachVariable() {
        System.setIn(new ByteArrayInputStream("3\nname\n10\n0\n10\n0\n".getBytes()));
        Accessory ad = new Accessory();
        ad.formToCreateCharacter();
        assertEquals(3,ad.getNbClass());
        assertEquals("name",ad.getName());
        assertEquals(10,ad.getLevel());
        assertEquals(0,ad.getStrength());
        assertEquals(10,ad.getAgility());
        assertEquals(0,ad.getIntelligence());
    }

    @Test
    void Given_rankNumber_When_setCharacter_Then_attributeGoodParametersToCorrectCharacter() {
        Accessory ad = new Accessory();
        ad.setCharacter(1, new Warrior("user1",10,10,0,0));
        ad.setCharacter(2, new Ranger("user2",20,0,9,1));
        assertEquals("user1",Accessory.character1.getName());
        assertEquals(10,Accessory.character1.getLevel());
        assertEquals(10,Accessory.character1.getStrength());
        assertEquals(0,Accessory.character1.getAgility());
        assertEquals(0,Accessory.character1.getIntelligence());
        assertEquals(50,Accessory.character1.getLife());
        assertEquals("user2",Accessory.character2.getName());
        assertEquals(20,Accessory.character2.getLevel());
        assertEquals(0,Accessory.character2.getStrength());
        assertEquals(9,Accessory.character2.getAgility());
        assertEquals(1,Accessory.character2.getIntelligence());
        assertEquals(100,Accessory.character2.getLife());
    }

    @Test
    void Given_allInformationAboutCharacter_When_displayBeginningStateIsRun_Then_displayCorrectInformation() {
        Accessory ad =new Accessory();
        ad.displayBeginningState("user",10,50,10,0,0,"I'm the warrior");
        String[] output = outContent.toString().replace("\r\t","\n").split("\n");
        assertEquals("I'm the warrior user : level(10) life(50) strength(10) agility(0) intelligence(0)", output[0]);
    }

    @Test
    void Given_Character_When_displayAdvancementIsRun_Then_displayActualInformationOfCharacter() {
        Accessory ad = new Accessory();
        Ranger user = new Ranger("user",10,0,10,0);
        ad.setCharacter(1,user);
        ad.displayAdvancement(Accessory.character1);
        String[] output = outContent.toString().replace("\r\n","\n").split("\n");
        assertEquals("user : life(50), strength(0), agility(10), intelligence(0)", output[0]);
    }

}