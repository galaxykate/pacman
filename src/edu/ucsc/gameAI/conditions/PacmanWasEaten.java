package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PacmanWasEaten implements ICondition {

	@Override
	public boolean test(Game game) {
		return game.wasPacManEaten();
	}

}
