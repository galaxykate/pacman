package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class MazeIndex implements ICondition {
	private int index;

	public MazeIndex(int index) {
this.index = index;
	}

	@Override
	public boolean test(Game game) {
		// TODO Auto-generated method stub
		return false;
	}

}
