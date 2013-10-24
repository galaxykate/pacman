package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PillWasEaten implements ICondition {

	// Return true if a normal pill was eaten in the last time step.
	@Override
	public boolean test(Game game) {
		return game.wasPillEaten();
	}

}
