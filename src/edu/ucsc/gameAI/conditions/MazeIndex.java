package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class MazeIndex implements ICondition {
	private int index;

	public MazeIndex(int index) {
		this.index = index;
	}

	// Test that the integer "index" representing a specific maze is indeed
	// equal to the game state's notion of what maze is current.
	@Override
	public boolean test(Game game) {
		return (game.getMazeIndex() == index);
	}

}
