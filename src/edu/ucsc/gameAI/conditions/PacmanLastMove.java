package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PacmanLastMove implements ICondition {

	private MOVE move;
	
	public PacmanLastMove(MOVE move) {
		this.move = move;
	}
	@Override
	public boolean test(Game game) {
		return (move == game.getPacmanLastMoveMade());
	}

}
