package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class Score implements ICondition {
	private int min, max;

	public Score(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean test(Game game) {
		int score = game.getScore();
		return (score >= min && score < max);
	}

}
