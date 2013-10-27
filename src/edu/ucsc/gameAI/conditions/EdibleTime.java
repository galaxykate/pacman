package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class EdibleTime implements ICondition {

	private GHOST ghost;
	private int min, max;

	public EdibleTime(GHOST ghost, int min, int max) {
		this.ghost = ghost;
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean test(Game game) {
		int t = game.getGhostEdibleTime(ghost);
		return (t >= this.min && t < this.max);
	}

}
