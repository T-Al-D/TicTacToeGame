package de.ks.privateProject2021.ttt.controller;

import de.ks.privateProject2021.ttt.StageManager;
import de.ks.privateProject2021.ttt.TTTEditor;
import de.ks.privateProject2021.ttt.model.Game;
import de.ks.privateProject2021.ttt.model.Player;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;
import java.util.Random;

import static java.lang.Thread.sleep;

public class MainScreenController {

    private final Game gameModel;
    private final Player computerPlayerModel;
    private final Player humanPlayerModel;
    private final Parent view;
    private final TTTEditor tttEditor;
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
    private final PropertyChangeListener humanPlayerVictoryListener = this::goToResultScreen;
    private final PropertyChangeListener computerPlayerVictoryListener = this::goToResultScreen;

    public MainScreenController(Parent parent, Game gameModel, Player humanPlayerModel,
                                Player computerPlayerModel, TTTEditor tttEditor) {
        this.gameModel = gameModel;
        this.computerPlayerModel = computerPlayerModel;
        this.humanPlayerModel = humanPlayerModel;
        this.view = parent;
        this.tttEditor = tttEditor;
    }

    public void init() {
        // Load all view references
        buttonZero = (Button) view.lookup("#buttonZero");
        buttonOne = (Button) view.lookup("#buttonOne");
        buttonTwo = (Button) view.lookup("#buttonTwo");
        buttonThree = (Button) view.lookup("#buttonThree");
        buttonFour = (Button) view.lookup("#buttonFour");
        buttonFive = (Button) view.lookup("#buttonFive");
        buttonSix = (Button) view.lookup("#buttonSix");
        buttonSeven = (Button) view.lookup("#buttonSeven");
        buttonEight = (Button) view.lookup("#buttonEight");

        leaveButton = (Button) view.lookup("#leaveButton");
        instructionLabel = (Label) view.lookup("#instructionLabel");

        imageZero = (ImageView) view.lookup("#imageZero");
        imageOne = (ImageView) view.lookup("#imageOne");
        imageTwo = (ImageView) view.lookup("#imageTwo");
        imageThree = (ImageView) view.lookup("#imageThree");
        imageFour = (ImageView) view.lookup("#imageFour");
        imageFive = (ImageView) view.lookup("#imageFive");
        imageSix = (ImageView) view.lookup("#imageSix");
        imageSeven = (ImageView) view.lookup("#imageSeven");
        imageEight = (ImageView) view.lookup("#imageEight");

        // Add action listeners
        leaveButton.setOnAction(event -> {
            stop();
            StageManager.showStartScreen();
        });

        buttonZero.setOnAction(event -> {
            buttonHumanAction(buttonZero, imageZero, 0);
            startComputerMove();
        });

        buttonOne.setOnAction(event -> {
            buttonHumanAction(buttonOne, imageOne, 1);
            startComputerMove();
        });

        buttonTwo.setOnAction(event -> {
            buttonHumanAction(buttonTwo, imageTwo, 2);
            startComputerMove();
        });

        buttonThree.setOnAction(event -> {
            buttonHumanAction(buttonThree, imageThree, 3);
            startComputerMove();
        });

        buttonFour.setOnAction(event -> {
            buttonHumanAction(buttonFour, imageFour, 4);
            startComputerMove();
        });

        buttonFive.setOnAction(event -> {
            buttonHumanAction(buttonFive, imageFive, 5);
            startComputerMove();
        });

        buttonSix.setOnAction(event -> {
            buttonHumanAction(buttonSix, imageSix, 6);
            startComputerMove();
        });

        buttonSeven.setOnAction(event -> {
            buttonHumanAction(buttonSeven, imageSeven, 7);
            startComputerMove();
        });

        buttonEight.setOnAction(event -> {
            buttonHumanAction(buttonEight, imageEight, 8);
            startComputerMove();
        });

        //PropertyChangeListener
        humanPlayerModel.listeners().addPropertyChangeListener(Player.PROPERTY_VICTORY, humanPlayerVictoryListener);
        computerPlayerModel.listeners().addPropertyChangeListener(Player.PROPERTY_VICTORY, computerPlayerVictoryListener);
    }

    // makes Button disappear
    private void eraseButton(Button button) {
        button.setVisible(false);
        button.setDisable(true);
    }

    // for the Human-Player
    private void buttonHumanAction(Button button, ImageView image, int fieldValue) {
        eraseButton(button);
        instructionLabel.setText("Nice, choose one more!");
        if (tttEditor.markBlock(fieldValue, humanPlayerModel)) {
            loadPictureforMove(humanPlayerModel, image);
            tttEditor.evaluateMove(humanPlayerModel);
        }
    }


    // for the Computer-Player
    private void buttonComputerAction(Button button, ImageView image) {
        eraseButton(button);
        loadPictureforMove(computerPlayerModel, image);
        tttEditor.evaluateMove(computerPlayerModel);
    }

    // this method creates redundancy, but makes the code more testable afterwards -> TestFullGame
    private void startComputerMove() {
        if (!humanPlayerModel.isVictory()) {
            computerMoveGenerator();
        }
    }

    // generates a random computermove
    public void computerMoveGenerator() {
        Button button = null;
        ImageView imageView = null;
        boolean validBlockNumber = false;
        Random random = new Random();
        int randomNumber = 9;
        while (!validBlockNumber) {
            randomNumber = random.nextInt(9);
            if (tttEditor.markBlock(randomNumber, computerPlayerModel)) {
                validBlockNumber = true;
                switch (randomNumber) {
                    case 0 -> {
                        button = buttonZero;
                        imageView = imageZero;
                    }
                    case 1 -> {
                        button = buttonOne;
                        imageView = imageOne;
                    }
                    case 2 -> {
                        button = buttonTwo;
                        imageView = imageTwo;
                    }
                    case 3 -> {
                        button = buttonThree;
                        imageView = imageThree;
                    }
                    case 4 -> {
                        button = buttonFour;
                        imageView = imageFour;
                    }
                    case 5 -> {
                        button = buttonFive;
                        imageView = imageFive;
                    }
                    case 6 -> {
                        button = buttonSix;
                        imageView = imageSix;
                    }
                    case 7 -> {
                        button = buttonSeven;
                        imageView = imageSeven;
                    }
                    case 8 -> {
                        button = buttonEight;
                        imageView = imageEight;
                    }
                    default -> {
                    }
                }
            }
        }
        buttonComputerAction(Objects.requireNonNull(button), imageView);
    }

    private void loadPictureforMove(Player player, ImageView imageview) {
        if (player.getPlayerName().equals("Human")) {
            loadAndSetPicture("circle.jpg", imageview);
        } else {
            loadAndSetPicture("cross.jpg", imageview);
        }
    }

    private void loadAndSetPicture(String dataName, ImageView image) {
        try {
            Image img = new Image(Objects.requireNonNull(
                    StageManager.class.getResource("pictures/" + dataName)).toString());
            image.setImage(img);
        } catch (Exception e) {
            System.err.println(image + "Picture could not be loaded!");
            e.printStackTrace();
        }
    }

    private void goToResultScreen(PropertyChangeEvent propertyChangeEvent) {
        try {
            stop();
            sleep(1000);
            StageManager.showResultScreen();
        } catch (Exception e) {
            System.err.println(e + "has been found!");
        }
    }

    public void stop() {
        // Clear references
        leaveButton.setOnAction(null);
    }
}