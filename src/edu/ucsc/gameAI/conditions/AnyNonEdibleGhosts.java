package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class AnyNonEdibleGhosts implements ICondition {
	
	// True if there are any non-edible ghosts positioned on the map (not in lair)
	public boolean test(Game game) 
	{
		for (GHOST ghost : GHOST.values()) {
			if (!game.isGhostEdible(ghost) && !(new GhostInGhostHouse(ghost).test(game))) {
				return true;
			}
		}
		
		return false;
	}
}
