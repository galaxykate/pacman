package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class GhostInRegion implements ICondition {
	private int x1, y1, x2, y2;

	public GhostInRegion(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	@Override
	public boolean test(Game game) {
		// TODO Auto-generated method stub
		return false;
	}

}
