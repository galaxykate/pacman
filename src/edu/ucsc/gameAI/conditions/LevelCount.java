package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class LevelCount implements ICondition {
	private int level;

	public LevelCount(int level) {
		this.level = level;
	}

	@Override
	public boolean test(Game game) {
		return game.getCurrentLevel() == level;
	}
}
