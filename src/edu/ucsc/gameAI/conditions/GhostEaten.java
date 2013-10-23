package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class GhostEaten implements ICondition {
	private GHOST ghost;

	public GhostEaten(GHOST ghost) {
		this.ghost = ghost;
	}

	@Override
	public boolean test(Game game) {
		// TODO Auto-generated method stub
		return false;
	}

}
