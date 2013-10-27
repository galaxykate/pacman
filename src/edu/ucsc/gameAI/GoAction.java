package edu.ucsc.gameAI;

import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class GoAction implements IAction, IBinaryNode {
	
	private MOVE dir;

	public GoAction(MOVE dir) {
		
		this.dir = dir;
	}


	@Override
	public IAction makeDecision(Game game) {
		
		return this;
	}

	@Override
	public MOVE getMove(Game game) {
		return this.dir;
	}


	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		
	}
}
