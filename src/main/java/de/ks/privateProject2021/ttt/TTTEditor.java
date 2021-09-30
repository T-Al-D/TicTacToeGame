package de.ks.privateProject2021.ttt;

import de.ks.privateProject2021.ttt.model.Block;
import de.ks.privateProject2021.ttt.model.Game;
import de.ks.privateProject2021.ttt.model.Player;
import javafx.application.Platform;

import static de.ks.privateProject2021.ttt.Constants.*;

public class TTTEditor {

    public static Game gameModel;

    public Game getGame() {
        return gameModel;
    }

    // creating the Game Object
    public void haveGame(int fieldSize, Player humanPlayerModel, Player computerPlayerModel) {
        gameModel = new Game().setGameName("TicTacToe").setFieldLength(fieldSize);
        gameModel.withPlayers(humanPlayerModel, computerPlayerModel);

        //y is for row , x is for column
        for (int y = 0; y < fieldSize; y++) {
            for (int x = 0; x < fieldSize; x++) {
                Block block = new Block().setGame(gameModel).setXPos(x).setYPos(y).
                        setPositionValue(fieldSize * x + y).setPlayer(null).setMarkedWith(NONE);
            }
        }
        humanPlayerModel.setGame(gameModel);
        computerPlayerModel.setGame(gameModel);
    }

    // creating player Objects and binding them to the Game Object
    public Player havePlayer(String playerName, int playerID) {
        return new Player().setPlayerName(playerName).setPlayerID(playerID).setVictory(false);
    }

    // marks a choosen block
    public boolean markBlock(int fieldNumber, Player player) {
        boolean success = false;
        for (Block block : gameModel.getBlockField()
        ) {
            if (block.getPositionValue() == fieldNumber && block.getPlayer() == null) {
                block.setMarkedWith(player.getPlayerID()).setPlayer(player);
                player.withOwnedBlocks(block);
                success = true;
            }
        }
        return success;
    }

    // After each move evaluates the winner
    public void evaluateMove(Player playerModel) {
        // conditionDiagonal 1:(0,4,8)  2:(2,4,6)
        // conditionRow      1:(0,1,2)  2:(3,4,5)  3:(6,7,8)
        // conditionColumn   1:(0,3,6)  2:(1,4,7)  3:(2,5,8)
        int conditionColumn1Passed = 0, conditionColumn2Passed = 0, conditionColumn3Passed = 0;
        int conditionRow1Passed = 0, conditionRow2Passed = 0, conditionRow3Passed = 0;
        int conditionDiagonal1Passed = 0, conditionDiagonal2Passed = 0;
        for (Block block : playerModel.getOwnedBlocks()
        ) {
            int blockValue = block.getPositionValue();
            if (blockValue == 0 || blockValue == 3 || blockValue == 6) {
                conditionColumn1Passed++;
            }
            if (blockValue == 1 || blockValue == 4 || blockValue == 7) {
                conditionColumn2Passed++;
            }
            if (blockValue == 2 || blockValue == 5 || blockValue == 8) {
                conditionColumn3Passed++;
            }
            if (blockValue == 0 || blockValue == 1 || blockValue == 2) {
                conditionRow1Passed++;
            }
            if (blockValue == 3 || blockValue == 4 || blockValue == 5) {
                conditionRow2Passed++;
            }
            if (blockValue == 6 || blockValue == 7 || blockValue == 8) {
                conditionRow3Passed++;
            }
            if (blockValue == 0 || blockValue == 4 || blockValue == 8) {
                conditionDiagonal1Passed++;
            }
            if (blockValue == 2 || blockValue == 4 || blockValue == 6) {
                conditionDiagonal2Passed++;
            }
        }
        // check if any condition got through
        if (conditionColumn1Passed == 3 || conditionColumn2Passed == 3 || conditionColumn3Passed == 3
                || conditionRow1Passed == 3 || conditionRow2Passed == 3 || conditionRow3Passed == 3
                || conditionDiagonal1Passed == 3 || conditionDiagonal2Passed == 3) {
            playerModel.setVictory(true);
        }
    }

    public void clearGame() {
        // clear the game so you can play again
        for (Player player : gameModel.getPlayers()
        ) {
            player.removeYou();
        }
        gameModel.removeYou();
    }
}