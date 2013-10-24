package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;
import edu.ucsc.gameAI.Utils;

public class PowerPillInRegion implements ICondition {
	private int x1, y1, x2, y2;

	public PowerPillInRegion(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	@Override
	public boolean test(Game game) {
		
		Utils util = new Utils();
		
		for(int pillIndex : game.getActivePowerPillsIndices()) {
			if (util.nodeInArea(game, pillIndex, x1, y1, x2, y2)) {
				return true;
			}
		}
		return false;

	}

}
