package edu.ucsc.gameAI;

import pacman.entries.pacman.SettableController;
import pacman.game.Constants.MOVE;
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

	public IAction makeDecision() {
		return this;
	}
}
