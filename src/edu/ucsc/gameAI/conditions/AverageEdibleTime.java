package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class AverageEdibleTime implements ICondition {

	private int min, max;

	public AverageEdibleTime(int min, int max) {

		this.min = min;
		this.max = max;
	}

	@Override
	public boolean test(Game game) {

		float minTime = 99999;
		float maxTime = -999;
		float total = 0;

		for (GHOST ghostType : GHOST.values()) {
			float time = game.getGhostEdibleTime(ghostType);
			minTime = Math.min(time, minTime);
			maxTime = Math.max(time, maxTime);
			total += time;
		}
		float t = maxTime;

		return (t >= min && t < max);
	}
}
