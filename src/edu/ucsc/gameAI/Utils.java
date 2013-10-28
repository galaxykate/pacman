package edu.ucsc.gameAI;

import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class Utils {

	// "target" is used to store a ghost that matched a condition
	// for use in an associated action, to avoid repeating
	// calculations.
	private GHOST target;
	public void setTarget(GHOST target) {
		this.target = target;
	}
	public GHOST getTarget() {
		return target;
	}
	
	public boolean nodeInArea(Game game, int nodeIndex, int x1, int y1, int x2, int y2) {
		int targetX = game.getNodeXCood(nodeIndex);
		int targetY = game.getNodeYCood(nodeIndex);
		if (targetX >= x1 && targetX <= x2 && targetY >= y1 && targetY <= y2) {
			return true;
		}
		return false;
	}
}
