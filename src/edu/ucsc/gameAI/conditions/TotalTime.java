package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class TotalTime implements ICondition {
	private int min, max;

	public TotalTime(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean test(Game game) {
		int time = game.getTotalTime();
		return (time >= min && time < max);
	}

}
