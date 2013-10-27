package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class GhostEatScore implements ICondition {
	private int min, max;

	public GhostEatScore(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean test(Game game) {
		int score = game.getGhostCurrentEdibleScore();
		return (score >= this.min && score < this.max);
	}

}
