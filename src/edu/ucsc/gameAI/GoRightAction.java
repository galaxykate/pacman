package edu.ucsc.gameAI;

import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class GoRightAction implements IAction, IBinaryNode {

	public void doAction() {
	}
	
	public IAction makeDecision() {return this;}

	@Override
	public IAction makeDecision(Game game) {
		return this;
	}

	@Override
	public MOVE getMove(Game game) {
		return MOVE.RIGHT;
	}
}
