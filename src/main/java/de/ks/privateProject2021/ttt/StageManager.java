package de.ks.privateProject2021.ttt;

import de.ks.privateProject2021.ttt.controller.MainScreenController;
import de.ks.privateProject2021.ttt.controller.ResultScreenController;
import de.ks.privateProject2021.ttt.controller.StartScreenController;
import de.ks.privateProject2021.ttt.model.Game;
import de.ks.privateProject2021.ttt.model.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class StageManager extends Application {

    private static Stage stage;
    private static Game gameModel = new Game();
    private static Player humanPlayerModel;
    private static Player computerPlayerModel;
    private static TTTEditor tttEditor = new TTTEditor();
    private static StartScreenController startScreenController;
    private static MainScreenController mainScreenController;
    private static ResultScreenController resultScreenController;

    @Override
    public void start(Stage primaryStage) {
        // start application
        stage = primaryStage;
        showStartScreen();
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        cleanup();
    }

    private static void cleanup() {
        // call cascading stop, erase References
        if (startScreenController != null) {
            startScreenController.stop();
            startScreenController = null;
        }
        if (mainScreenController != null) {
            mainScreenController.stop();
            mainScreenController = null;
        }
        if (resultScreenController != null) {
            resultScreenController.stop();
            resultScreenController = null;
        }
    }

    public static void showStartScreen() {
        Parent parent = loadAndSetScene("StartScreenTTT.fxml", "Start");
        startScreenController = new StartScreenController(parent, tttEditor);
        startScreenController.init();
        System.out.println("Show the Start Screen");
    }

    public static void showMainScreen() {
        settingPlayers();
        Parent parent = loadAndSetScene("MainScreenTTT.fxml", "Main");
        mainScreenController = new MainScreenController(parent, gameModel, humanPlayerModel, computerPlayerModel,
                tttEditor);
        mainScreenController.init();
        System.out.println("Show the Main Screen");
    }

    public static void showResultScreen() {
        Parent parent = loadAndSetScene("ResultScreenTTT.fxml", "Result");
        resultScreenController = new ResultScreenController(parent, humanPlayerModel, computerPlayerModel, tttEditor);
        resultScreenController.init();
        System.out.println("Show the Result Screen");
    }

    private static Parent loadAndSetScene(String fxmlFile, String title) {
        cleanup();
        try {
            //LOAD VIEW
            Parent root = FXMLLoader.load(Objects.requireNonNull(
                    StageManager.class.getResource("views/" + fxmlFile)));
            Scene scene = new Scene(root);
            //DISPLAY
            stage.setScene(scene);
            stage.setTitle(title);
            return root;
        } catch (Exception e) {
            System.err.println(title + "Scene could not be loaded!");
            e.printStackTrace();
        }
        return null;
    }

    // set players for test
    private static void settingPlayers() {
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

    // only needed for TESTS
    public TTTEditor getTTTEditor() {
        return tttEditor;
    }
}