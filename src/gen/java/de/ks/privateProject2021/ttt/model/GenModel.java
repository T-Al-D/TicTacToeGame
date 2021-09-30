package de.ks.privateProject2021.ttt.model;

import org.fulib.builder.ClassModelDecorator;
import org.fulib.builder.ClassModelManager;
import org.fulib.builder.reflect.Link;

import java.util.List;


public class GenModel implements ClassModelDecorator {
	class Game {
		String gameName;
		int fieldLength;

		@Link("game")
		List<Block> blockField;

		@Link("game")
		List<Player> players;
	}

	class Player {
		String playerName;
		int playerID;
		boolean victory;

		@Link("player")
		List<Block> ownedBlocks;

		@Link("players")
		Game game;
	}

	class Block {
		int xPos;
		int yPos;
		int positionValue;
		int markedWith;

		@Link("ownedBlocks")
		Player player;

		@Link("blockField")
		Game game;
	}

	@Override
	public void decorate(ClassModelManager mm) {
		mm.haveNestedClasses(GenModel.class);
	}
}

