package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PowerPillWasEaten implements ICondition {

	// Return true if a power pill (the big ones) was eaten in last time step.
	@Override
	public boolean test(Game game) {
		// TODO Auto-generated method stub
		return game.wasPowerPillEaten();
	}

}
