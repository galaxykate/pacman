package edu.ucsc.gameAI;

import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class ConsoleLog implements IAction, IBinaryNode {
	String msg = "";
	public ConsoleLog(String msg) {
		this.msg = msg;
	}
	public void doAction() {
		// TODO: This doesn't work... maybe b/c doAction isn't actually ever called?
		System.out.println(msg);
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
