package de.ks.privateProject2021.ttt.controller;

import de.ks.privateProject2021.ttt.StageManager;
import de.ks.privateProject2021.ttt.TTTEditor;
import de.ks.privateProject2021.ttt.model.Player;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ResultScreenController {

    private final Player humanPlayerModel;
    private final Player computerPlayerModel;
    private final TTTEditor tttEditor;
    private final Parent view;
    private Label resultLabel;
    private Button backToStartButton;

    public ResultScreenController(Parent parent, Player playerModel, Player computerPlayerModel, TTTEditor tttEditor) {
        this.computerPlayerModel = computerPlayerModel;
        this.humanPlayerModel = playerModel;
        this.view = parent;
        this.tttEditor = tttEditor;
    }

    public void init() {

        // Load all view references
        resultLabel = (Label) view.lookup("#resultLabel");
        backToStartButton = (Button) view.lookup("#backToStartButton");

        // Add action listeners
        backToStartButton.setOnAction(event -> {
            stop();
            tttEditor.clearGame();
            StageManager.showStartScreen();
        });

        if (humanPlayerModel.isVictory() && !computerPlayerModel.isVictory()) {
            resultLabel.setText("Congratulations, you won !");
        } else if (!humanPlayerModel.isVictory() && computerPlayerModel.isVictory()) {
            resultLabel.setText("Sorry, you lost !");
        } else {
            resultLabel.setText("A draw !");
        }
    }

    public void stop() {
        // Clear references
        backToStartButton.setOnAction(null);
    }
}