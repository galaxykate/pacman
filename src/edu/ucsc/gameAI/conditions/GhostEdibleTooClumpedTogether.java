
package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;
import edu.ucsc.gameAI.Utils;

public class GhostEdibleTooClumpedTogether implements ICondition {
	private GHOST ghost;

	public GhostEdibleTooClumpedTogether(GHOST ghost) {
		this.ghost = ghost;
	}

	// Return true if the given edible ghost is too near any other edible ghost.
	@Override
	public boolean test(Game game) {
		
		int myPosition = game.getGhostCurrentNodeIndex(ghost);
		for (GHOST candidate : GHOST.values()) {
			// skip ourselves, unedible, and unpositioned ghosts.
			if (ghost == candidate ||
					!(new IsEdible(candidate).test(game)) ||
					new GhostInGhostHouse(candidate).test(game)) {	
				continue;
			}
			int cPosition = game.getGhostCurrentNodeIndex(candidate);
			if ((int)game.getEuclideanDistance(myPosition, cPosition) < 10) {
				//System.out.println("too close!!");
				return true;
			}
		}
			
		return false;
	}

}
