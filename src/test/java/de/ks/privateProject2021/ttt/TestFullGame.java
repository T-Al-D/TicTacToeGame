package de.ks.privateProject2021.ttt;

import de.ks.privateProject2021.ttt.model.Game;
import de.ks.privateProject2021.ttt.model.Player;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

public class TestFullGame extends ApplicationTest {

    private static Stage stage;
    private static StageManager stageManager;
    private static TTTEditor tttEditor;
    private static Game gameModel;
    private static Player humanPlayerModel;
    private static Player computerPlayerModel;

    private Button startGameButton;
    private Text gameNameText;

    private Label resultLabel;

    private Button buttonZero;
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;
    private Button buttonFive;
    private Button buttonSix;
    private Button buttonSeven;
    private Button buttonEight;
    private Button leaveButton;
    private Label instructionLabel;
    private ImageView imageZero;
    private ImageView imageOne;
    private ImageView imageTwo;
    private ImageView imageThree;
    private ImageView imageFour;
    private ImageView imageFive;
    private ImageView imageSix;
    private ImageView imageSeven;
    private ImageView imageEight;

    public void loadAllStartScreenReferences() {
        startGameButton = lookup("#startGameButton").query();
        gameNameText = lookup("#gameNameText").query();
    }

    public void loadAllResultScreenReferences() {
        resultLabel = lookup("#resultLabel").query();
    }

    public void loadAllMainScreenReferences() {
        buttonZero = lookup("#buttonZero").query();
        buttonOne = lookup("#buttonOne").query();
        buttonTwo = lookup("#buttonTwo").query();
        buttonThree = lookup("#buttonThree").query();
        buttonFour = lookup("#buttonFour").query();
        buttonFive = lookup("#buttonOne").query();
        buttonSix = lookup("#buttonSix").query();
        buttonSeven = lookup("#buttonSeven").query();
        buttonEight = lookup("#buttonEight").query();

        imageZero = lookup("#imageZero").query();
        imageOne = lookup("#imageOne").query();
        imageTwo = lookup("#imageTwo").query();
        imageThree = lookup("#imageThree").query();
        imageFour = lookup("#imageFour").query();
        imageFive = lookup("#imageFive").query();
        imageSix = lookup("#imageSix").query();
        imageSeven = lookup("#imageSeven").query();
        imageEight = lookup("#imageEight").query();

        instructionLabel = lookup("#instructionLabel").query();
        leaveButton = lookup("#leaveButton").query();
    }

    private void settingPlayers() {
        gameModel = tttEditor.getGame();
        for (Player player : gameModel.getPlayers()
        ) {
            if (player.getPlayerName().equals("Human")) {
                humanPlayerModel = player;
            }
            if (player.getPlayerName().equals("Computer")) {
                computerPlayerModel = player;
            }
        }
    }

    @Override
    public void start(Stage stage) {
        //start Application
        TestFullGame.stage = stage;
        stageManager = new StageManager();
        stageManager.start(stage);
        tttEditor = stageManager.getTTTEditor();
        gameModel = tttEditor.getGame();
        TestFullGame.stage.centerOnScreen();
    }

    @Test
    public void correctTextsAndLabels() {
        // startGameButton and leaveButton are also tested here !
        loadAllStartScreenReferences();

        WaitForAsyncUtils.waitForFxEvents(); // waiting for change

        Assert.assertEquals("Title is not Start!", "Start", stage.getTitle());
        Assert.assertEquals("GameName is Incorrect!", "TicTacToe Game (3x3)", gameNameText.getText());
        Platform.runLater(() -> clickOn(startGameButton));

        WaitForAsyncUtils.waitForFxEvents(); // waiting for change

        settingPlayers();
        loadAllMainScreenReferences();
        Assert.assertEquals("Title is not Main!", "Main", stage.getTitle());
        Assert.assertEquals("Instruction Label is incorrect!", "You go first !",
                instructionLabel.getText());
        Platform.runLater(() -> clickOn(buttonFour));

        WaitForAsyncUtils.waitForFxEvents(); // waiting for change

        Assert.assertEquals("Instruction Label did not change!", "Nice, choose one more!",
                instructionLabel.getText());
        Platform.runLater(() -> clickOn(leaveButton));

        WaitForAsyncUtils.waitForFxEvents(); // waiting for change

        Assert.assertEquals("Title is not Start!", "Start", stage.getTitle());
    }

    /*
     I made this Project, so I can prepare for one of my exams (therefore it was made in a hurry).

     Usually I should have coded more tests, but unfortunately (I have only realized it towards the end of this
     "micro-Project") it is very hard to test the Code that I have written, especially when planing to use Functions
     from the MainScreenController (buttonHumanAction(...) and buttonComputerAction(...)) which should not be a
     possibility in the first place.

     Therefore, I am also unable to create a test where I can find out how to correct the BUG that happens during a draw!

     I should have used more PropertyChangeListener and should have created Variables like boolean hasTurn so I can load
     the pictures and change the GUI, while checking the methods in the Editor. On the other hand, Variables, like the
     coordinates are redundant.

     This shows again how important tests are (test-first-principle).
     I am sorry that this project has turnout that way.
     I will take this as a learning lesson and try to be better in the future.

    * */
}
