package edu.ucsc.gameAI;

import pacman.entries.pacman.SettableController;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class GoAction implements IAction, IBinaryNode {
	private SettableController controller;
	private MOVE dir;

	public GoAction(SettableController controller, MOVE dir) {
		this.controller = controller;
		this.dir = dir;
	}

	public void doAction() {
		// TODO Auto-generated method stub
		controller.setMove(this.dir);
	}

	@Override
	public IAction makeDecision(Game game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MOVE getMove() {
		// TODO Auto-generated method stub
		return null;
	}
}
