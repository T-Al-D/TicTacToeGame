package de.ks.privateProject2021.ttt.controller;

import de.ks.privateProject2021.ttt.StageManager;
import de.ks.privateProject2021.ttt.TTTEditor;
import de.ks.privateProject2021.ttt.model.Player;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class StartScreenController {

    private Player humanPlayerModel;
    private Player computerPlayerModel;
    private final TTTEditor tttEditor;
    private final Parent view;
    private Button startGameButton;

    public StartScreenController(Parent parent, TTTEditor tttEditor) {
        this.view = parent;
        this.tttEditor = tttEditor;
    }

    public void init() {
        // Load all view references
        startGameButton = (Button) view.lookup("#startGameButton");

        humanPlayerModel = tttEditor.havePlayer("Human", 0);
        computerPlayerModel = tttEditor.havePlayer("Computer", 1);

        // Add action listeners
        startGameButton.setOnAction(event -> {

            try {
                tttEditor.haveGame(3, humanPlayerModel, computerPlayerModel);

                StageManager.showMainScreen();
                stop();

            } catch (NullPointerException e) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Problem occurred!");
                a.setHeaderText("Game stopped because of Error!");
                a.setContentText("Please check the Code!");
                a.setResizable(true);
                a.showAndWait();
            }
        });
    }

    public void stop() {
        // Clear references
        startGameButton.setOnAction(null);
    }
}