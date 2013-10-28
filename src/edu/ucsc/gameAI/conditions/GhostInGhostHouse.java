package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class GhostInGhostHouse implements ICondition {
	private GHOST ghost;

	public GhostInGhostHouse(GHOST ghost) {
		this.ghost = ghost;
	}

	@Override
	public boolean test(Game game) {
		return game.getGhostCurrentNodeIndex(ghost) == game.getCurrentMaze().lairNodeIndex;
	}

}
