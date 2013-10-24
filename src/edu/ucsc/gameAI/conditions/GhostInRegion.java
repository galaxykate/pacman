package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;
import edu.ucsc.gameAI.Utils;

public class GhostInRegion implements ICondition {
	private int x1, y1, x2, y2;

	public GhostInRegion(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	// True if any ghost is in the region specified by the coordinates.
	@Override
	public boolean test(Game game) {

		Utils util = new Utils();
		for(GHOST ghostType : GHOST.values()) {
			if (util.nodeInArea(game, game.getGhostCurrentNodeIndex(ghostType), x1, y1, x2, y2)) {
				return true;
			}
		}
		return false;
	}

}
