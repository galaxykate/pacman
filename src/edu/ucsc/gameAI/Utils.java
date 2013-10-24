package edu.ucsc.gameAI;

import pacman.game.Game;

public class Utils {

	public boolean nodeInArea(Game game, int nodeIndex, int x1, int y1, int x2, int y2) {
		int targetX = game.getNodeXCood(nodeIndex);
		int targetY = game.getNodeYCood(nodeIndex);
		if (targetX >= x1 && targetX <= x2 && targetY >= y1 && targetY <= y2) {
			return true;
		}
		return false;
	}
}
