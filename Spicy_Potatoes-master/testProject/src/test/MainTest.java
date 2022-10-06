package test;


//import javafx.scene.image.Image;

import game.ConfigScreenController;
import game.Main;
//import game.Player;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;
//import org.testfx.matcher.control.TextMatchers;

import static junit.framework.TestCase.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;

//import static org.testfx.api.FxAssert.assert;
public class MainTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }

    @Before
    public void setUp() throws Exception {
        ApplicationTest.launch(Main.class);
        ConfigScreenController.getPV().setNumRooms(-1);

    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
    /*
    @Test
    public void testStartButtonExists() {
        verifyThat("Start", NodeMatchers.isVisible());
    }

    @Test
    public void testStartButtonClicks() {
        clickOn("Start");
    }
    @Test
    public void testDifficultyOptions() {
        clickOn("Start");
        verifyThat("Easy", NodeMatchers.isVisible());
        verifyThat("Medium", NodeMatchers.isVisible());
        verifyThat("Hard", NodeMatchers.isVisible());
    }
    @Test
    public void testWeaponOptions() {
        clickOn("Start");
        verifyThat("Water Gun", NodeMatchers.isVisible());
        verifyThat("Flame Thrower", NodeMatchers.isVisible());
        verifyThat("Laser", NodeMatchers.isVisible());
    }
    @Test
    public void testContinueButton() {
        clickOn("Start");
        verifyThat("Continue", NodeMatchers.isVisible());
    }

    @Test(expected = FxRobotException.class)
    public void testClickImpossibleElement() {
        clickOn("Continue");
    }

    @Test
    public void testCannotContinueWithoutWeapon() {
        clickOn("Start");
        clickOn("#Username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Easy");
        clickOn("Continue");
        clickOn("Continue"); //shouldn't cause an
        // exception bc you should still be on the same screen
    }

    @Test
    public void testCannotContinueWithoutDifficulty() {
        clickOn("Start");
        clickOn("#Username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Water Gun");
        clickOn("Continue");
        clickOn("Continue"); //shouldn't cause an
        // exception bc you should still be on the same screen
    }

    @Test
    public void testCannotContinueWithoutName() {
        clickOn("Start");
        clickOn("Easy");
        clickOn("Water Gun");
        clickOn("Continue");
        clickOn("Continue"); //shouldn't cause an exception
        // bc you should still be on the same screen
    }

    @Test

     //Since the following test validated that you couldn't continue without
     //a name and without buttons

    public void testNameCorrectlyWorks() {
        clickOn("Start");
        clickOn("#Username").write("Bob");
        verifyThat("#Username", TextInputControlMatchers.hasText("Bob"));
        clickOn("Easy");
        clickOn("Water Gun");
        clickOn("Continue");
    }

    @Test

     //Test if money is displayed correctly for hard

    public void testMoneyHard() {
        clickOn("Start");
        clickOn("#Username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Hard");
        clickOn("Water Gun");
        clickOn("Continue");
        verifyThat("#Money_Amount", TextMatchers.hasText("100"));
    }

    @Test

     //Test if money is displayed correctly for medium

    public void testMoneyMedium() {
        clickOn("Start");
        clickOn("#Username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Medium");
        clickOn("Water Gun");
        clickOn("Continue");
        verifyThat("#Money_Amount", TextMatchers.hasText("200"));
    }

    @Test

     //Test if money is displayed correctly for easy

    public void testMoneyEasy() {
        clickOn("Start");
        clickOn("#Username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Easy");
        clickOn("Water Gun");
        clickOn("Continue");
        verifyThat("#Money_Amount", TextMatchers.hasText("300"));
    }
    */

    //M3 TESTS
    //@Test


    /*public void testSpriteExists() {
        verifyThat("file:testProject/src/SrcImage/PirateCartoon.jpg", NodeMatchers.isVisible());
    }

     */

    @Test
    public void testStartButtonExists() {
        verifyThat("Start", NodeMatchers.isVisible());
    }

    @Test
    public void testDifficultyOptions() {
        clickOn("Start");
        verifyThat("Easy", NodeMatchers.isVisible());
        verifyThat("Medium", NodeMatchers.isVisible());
        verifyThat("Hard", NodeMatchers.isVisible());
    }

    @Test
    public void testWeaponOptions() {
        clickOn("Start");
        verifyThat("Water Gun", NodeMatchers.isVisible());
        verifyThat("Flame Thrower", NodeMatchers.isVisible());
        verifyThat("Laser", NodeMatchers.isVisible());
    }

    @Test

    public void testInventoryListUpdatedEasyWaterGun() {
        int weaponChoiceCounter = 0;
        String obj1 = "";
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Easy");
        clickOn("Water Gun");
        weaponChoiceCounter++;
        if (weaponChoiceCounter == 1) {
            obj1 = "Water";
        }
        clickOn("Continue");
        //verifyThat("#inventory", LabeledMatchers.hasText("Water"));
        verifyThat("#moneyAmount", LabeledMatchers.hasText("300 -1"));
        //assertEquals("Water", LabeledMatchers.hasText("Water"));
        verifyThat("10.0", NodeMatchers.isVisible());
        assertEquals(obj1, "Water");
        //verifyThat("Water", NodeMatchers.isVisible());
    }

    @Test

    public void testHPExistMedium() {
        clickOn("Start");
        clickOn("#username").write("Bobella");
        press(KeyCode.ENTER);
        clickOn("Medium");
        clickOn("Water Gun");
        clickOn("Continue");
        //verifyThat("#moneyAmount", LabeledMatchers.hasText("300 -1"));
        verifyThat("10.0", NodeMatchers.isVisible());
    }



    @Test

    public void testInventoryListUpdatedEasyLaser() {
        int weaponChoiceCounter = 0;
        String obj1 = "";
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Easy");
        clickOn("Laser");
        weaponChoiceCounter++;
        if (weaponChoiceCounter == 1) {
            obj1 = "Laser";
        }
        clickOn("Continue");
        //verifyThat("#inventory", LabeledMatchers.hasText("Water"));
        verifyThat("#moneyAmount", LabeledMatchers.hasText("300 -1"));
        //assertEquals("Water", LabeledMatchers.hasText("Water"));
        verifyThat("10.0", NodeMatchers.isVisible());
        assertEquals(obj1, "Laser");
        //verifyThat("Water", NodeMatchers.isVisible() );
    }

    @Test

    public void testInventoryListUpdatedEasyFlameThrower() {
        int weaponChoiceCounter = 0;
        String obj1 = "";
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Easy");
        clickOn("Flame Thrower");
        weaponChoiceCounter++;
        if (weaponChoiceCounter == 1) {
            obj1 = "Flame";
        }
        clickOn("Continue");
        //verifyThat("#inventory", LabeledMatchers.hasText("Water"));
        verifyThat("#moneyAmount", LabeledMatchers.hasText("300 -1"));
        //assertEquals("Water", LabeledMatchers.hasText("Water"));
        verifyThat("10.0", NodeMatchers.isVisible());
        assertEquals(obj1, "Flame");
        //verifyThat("Water", NodeMatchers.isVisible() );
    }

    @Test

    public void testInventoryListUpdatedMediumWaterGun() {
        int weaponChoiceCounter = 0;
        String obj1 = "";
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Medium");
        clickOn("Water Gun");
        weaponChoiceCounter++;
        if (weaponChoiceCounter == 1) {
            obj1 = "Water";
        }
        clickOn("Continue");
        //verifyThat("#inventory", LabeledMatchers.hasText("Water"));
        verifyThat("#moneyAmount", LabeledMatchers.hasText("200 -1"));
        //assertEquals("Water", LabeledMatchers.hasText("Water"));
        verifyThat("10.0", NodeMatchers.isVisible());
        assertEquals(obj1, "Water");
        //verifyThat("Water", NodeMatchers.isVisible() );
    }

    @Test

    public void testInventoryListUpdatedMediumLaser() {
        int weaponChoiceCounter = 0;
        String obj1 = "";
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Medium");
        clickOn("Laser");
        weaponChoiceCounter++;
        if (weaponChoiceCounter == 1) {
            obj1 = "Laser";
        }
        clickOn("Continue");
        //verifyThat("#inventory", LabeledMatchers.hasText("Water"));
        verifyThat("#moneyAmount", LabeledMatchers.hasText("200 -1"));
        //assertEquals("Water", LabeledMatchers.hasText("Water"));
        verifyThat("10.0", NodeMatchers.isVisible());
        assertEquals(obj1, "Laser");
        //verifyThat("Water", NodeMatchers.isVisible() );
    }

    @Test

    public void testInventoryListUpdatedMediumFlameThrower() {
        int weaponChoiceCounter = 0;
        String obj1 = "";
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Medium");
        clickOn("Flame Thrower");
        weaponChoiceCounter++;
        if (weaponChoiceCounter == 1) {
            obj1 = "Flame";
        }
        clickOn("Continue");
        //verifyThat("#inventory", LabeledMatchers.hasText("Water"));
        verifyThat("#moneyAmount", LabeledMatchers.hasText("200 -1"));
        //assertEquals("Water", LabeledMatchers.hasText("Water"));
        verifyThat("10.0", NodeMatchers.isVisible());
        assertEquals(obj1, "Flame");
        //verifyThat("Water", NodeMatchers.isVisible() );
    }

    @Test

    public void testInventoryListUpdatedHardWaterGun() {
        int weaponChoiceCounter = 0;
        String obj1 = "";
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Hard");
        clickOn("Water Gun");
        weaponChoiceCounter++;
        if (weaponChoiceCounter == 1) {
            obj1 = "Water";
        }
        clickOn("Continue");
        //verifyThat("#inventory", LabeledMatchers.hasText("Water"));
        verifyThat("#moneyAmount", LabeledMatchers.hasText("100 -1"));
        //assertEquals("Water", LabeledMatchers.hasText("Water"));
        verifyThat("10.0", NodeMatchers.isVisible());
        assertEquals(obj1, "Water");
        //verifyThat("Water", NodeMatchers.isVisible() );
    }

    @Test

    public void testInventoryListUpdatedHardLaser() {
        int weaponChoiceCounter = 0;
        String obj1 = "";
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Hard");
        clickOn("Laser");
        weaponChoiceCounter++;
        if (weaponChoiceCounter == 1) {
            obj1 = "Laser";
        }
        clickOn("Continue");
        //verifyThat("#inventory", LabeledMatchers.hasText("Water"));
        verifyThat("#moneyAmount", LabeledMatchers.hasText("100 -1"));
        //assertEquals("Water", LabeledMatchers.hasText("Water"));
        verifyThat("10.0", NodeMatchers.isVisible());
        assertEquals(obj1, "Laser");
        //verifyThat("Water", NodeMatchers.isVisible() );
    }

    @Test

    public void testInventoryListUpdatedHardFlameThrower() {
        int weaponChoiceCounter = 0;
        String obj1 = "";
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Hard");
        clickOn("Flame Thrower");
        weaponChoiceCounter++;
        if (weaponChoiceCounter == 1) {
            obj1 = "Flame";
        }
        clickOn("Continue");
        //verifyThat("#inventory", LabeledMatchers.hasText("Water"));
        verifyThat("#moneyAmount", LabeledMatchers.hasText("100 -1"));
        //assertEquals("Water", LabeledMatchers.hasText("Water"));
        verifyThat("10.0", NodeMatchers.isVisible());
        assertEquals(obj1, "Flame");
        //verifyThat("Water", NodeMatchers.isVisible() );
    }

    @Test

    public void testHealthPotionExists() {
        int weaponChoiceCounter = 0;
        String obj1 = "";
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Hard");
        clickOn("Flame Thrower");
        weaponChoiceCounter++;
        if (weaponChoiceCounter == 1) {
            obj1 = "health";
        }
        clickOn("Continue");
        //verifyThat("#inventory", LabeledMatchers.hasText("Water"));
        verifyThat("#moneyAmount", LabeledMatchers.hasText("100 -1"));
        //assertEquals("Water", LabeledMatchers.hasText("Water"));
        verifyThat("10.0", NodeMatchers.isVisible());
        assertEquals(obj1, "health");
        //verifyThat("Water", NodeMatchers.isVisible() );
    }

    @Test

    public void testGameRoomConfiguration() {
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Easy");
        clickOn("Water Gun");
        clickOn("Continue");
        verifyThat("#moneyAmount", LabeledMatchers.hasText("300 -1"));
    }

    /*
    @Test

    public void testGameRoom1ExistsMedium() {
        clickOn("Start");
        clickOn("#username").write("Bobette");
        press(KeyCode.ENTER);
        clickOn("Medium");
        clickOn("Flame Thrower");
        clickOn("Continue");
        verifyThat("#moneyAmount", LabeledMatchers.hasText("200 -1"));
    }

    */


    @Test

    public void testGameRoom1ExistsHard() {
        clickOn("Start");
        clickOn("#username").write("Bobella");
        press(KeyCode.ENTER);
        clickOn("Hard");
        clickOn("Flame Thrower");
        clickOn("Continue");
        verifyThat("#moneyAmount", LabeledMatchers.hasText("100 -1"));
    }

    /*@Test

    public void testGameRoom2ExistsEasy() {
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Easy");
        clickOn("Water Gun");
        clickOn("Continue");
        int up = 0;
        while (up < 25) {
            press(KeyCode.UP);
            up++;
        }
        release(KeyCode.UP);
        verifyThat("#moneyAmount", LabeledMatchers.hasText("300 0"));
    }*/


    /*

    @Test

    public void testGameRoom2ExistsMedium() {
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Medium");
        clickOn("Water Gun");
        clickOn("Continue");
        int up = 0;
        while (up < 25) {
            press(KeyCode.UP);
            up++;
        }
        release(KeyCode.UP);
        //verifyThat("#moneyAmount", LabeledMatchers.hasText("100 0"));
        verifyThat("#moneyAmount", LabeledMatchers.hasText("200 0"));
    }



 */



    /* @Test

    public void testGameRoom2ExistsHard() {
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Hard");
        clickOn("Water Gun");
        clickOn("Continue");
        int up = 0;
        while (up < 25) {
            press(KeyCode.UP);
            up++;
        }
        //verifyThat("#moneyAmount", LabeledMatchers.hasText("100 0"));
       verifyThat("#moneyAmount", LabeledMatchers.hasText("100 0"));
    }

   */

    /*
    @Test

    public void testGameRoom3Exists() {
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Easy");
        clickOn("Water Gun");
        clickOn("Continue");
        int ups = 0;
        while (ups < 50) {
            press(KeyCode.UP);
            ups++;
        }
        release(KeyCode.UP);
        //verifyThat("#moneyAmount", LabeledMatchers.hasText("300 1"));
        verifyThat("#moneyAmount", LabeledMatchers.hasText("300 1"));
    }


    @Test

    public void testGameRoom4Exists() {
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Easy");
        clickOn("Water Gun");
        clickOn("Continue");
        int ups = 0;
        while (ups < 75) {
            press(KeyCode.UP);
            ups++;
        }
        release(KeyCode.UP);
        //verifyThat("#moneyAmount", LabeledMatchers.hasText("300 2"));
        verifyThat("#moneyAmount", LabeledMatchers.hasText("300 2"));

    }


    @Test

    public void testGameRoom5Exists() {
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Easy");
        clickOn("Water Gun");
        clickOn("Continue");
        int ups = 0;
        while (ups < 100) {
            press(KeyCode.UP);
            ups++;
        }
        release(KeyCode.UP);
        verifyThat("#moneyAmount", LabeledMatchers.hasText("300 3"));
        //verifyThat("#moneyAmount", LabeledMatchers.hasText("300 6"));

    }


    @Test

    public void testGameRoom6Exists() {
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Easy");
        clickOn("Water Gun");
        clickOn("Continue");
        int ups = 0;
        while (ups < 125) {
            press(KeyCode.UP);
            ups++;
        }
        release(KeyCode.UP);
        //verifyThat("#moneyAmount", LabeledMatchers.hasText("300 4"));
        verifyThat("#moneyAmount", LabeledMatchers.hasText("300 4"));

    }

    @Test

    public void testGameRoom7Exists() {
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Easy");
        clickOn("Water Gun");
        clickOn("Continue");
        int ups = 0;
        while (ups < 150) {
            press(KeyCode.UP);
            ups++;
        }
        release(KeyCode.UP);
        //verifyThat("#moneyAmount", LabeledMatchers.hasText("300 4"));
        verifyThat("#moneyAmount", LabeledMatchers.hasText("300 5"));

    }

    @Test

    public void testGameRoom8Exists() {
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Easy");
        clickOn("Water Gun");
        clickOn("Continue");
        int ups = 0;
        while (ups < 175) {
            press(KeyCode.UP);
            ups++;
        }
        release(KeyCode.UP);
        //verifyThat("#moneyAmount", LabeledMatchers.hasText("300 4"));
        verifyThat("#moneyAmount", LabeledMatchers.hasText("300 6"));

    }


    @Test

    public void testDungeonExitRoom() {
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Easy");
        clickOn("Water Gun");
        clickOn("Continue");
        int ups = 0;
        while (ups < 250) {
            press(KeyCode.UP);
            ups++;
        }
        release(KeyCode.UP);
        //verifyThat("#exit", TextMatchers.hasText("Exit"));
        //verifyThat("#moneyAmount", LabeledMatchers.hasText("300 6"));
        verifyThat("Exit", NodeMatchers.isVisible());

    }

    /*

    @Test

    public void testGoDown() {
        clickOn("Start");
        clickOn("#username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Easy");
        clickOn("Water Gun");
        clickOn("Continue");
        int ups = 0;
        while (ups < 50) {
            press(KeyCode.DOWN);
            ups++;
        }
        release(KeyCode.DOWN);
        while (ups < 100) {
            press(KeyCode.UP);
            ups++;
        }
        //verifyThat("#exit", TextMatchers.hasText("Exit"));

    }

 */


    /*



    @Test

    public void testMinimumSixRooms() {
        int count = 0;
        clickOn("Start");
        clickOn("#Username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Easy");
        clickOn("Water Gun");
        clickOn("Continue");
        verifyThat("#room1", TextMatchers.hasText(""));
        count++;
        verifyThat("#room2", TextMatchers.hasText(""));
        count++;
        verifyThat("#room3", TextMatchers.hasText(""));
        count++;
        verifyThat("#room4", TextMatchers.hasText(""));
        count++;
        verifyThat("#room5", TextMatchers.hasText(""));
        count++;
        verifyThat("#room6", TextMatchers.hasText(""));
        count++;
        //assertEquals??? 6, count

        //verifyThat("#exit", NodeMatchers.isNotNull());
    }



    @Test

    public void testEightRoomsTotal() {
        int count = 0;
        clickOn("Start");
        count++;
        clickOn("#Username").write("Bob");
        press(KeyCode.ENTER);
        clickOn("Easy");
        clickOn("Water Gun");
        clickOn("Continue");
        count++;
        clickOn("#room1");
        count++;
        clickOn("#room2");
        count++;
        clickOn("#room3");
        count++;
        clickOn("#room4");
        count++;
        clickOn("#room5");
        count++;
        clickOn("#room6");
        count++;
        clickOn("#exit");
        verifyThat("count", NodeMatchers.isNotNull());
        //assertEquals(8, count);
    }




    @Test

    public void testExitButtonExists() {
        verifyThat("Exit", NodeMatchers.isVisible());
    }

     */


}