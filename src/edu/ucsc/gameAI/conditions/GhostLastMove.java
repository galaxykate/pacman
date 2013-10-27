package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class GhostLastMove implements ICondition {

	private GHOST ghost;
	private MOVE move;

	public GhostLastMove(GHOST ghost, MOVE move) {
		this.ghost = ghost;
		this.move = move;
	}

	@Override
	public boolean test(Game game) {
		return (game.getGhostLastMoveMade(ghost) == move);
	}

}
