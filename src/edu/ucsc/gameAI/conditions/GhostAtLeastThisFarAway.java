package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import pacman.game.Constants.GHOST;
import edu.ucsc.gameAI.ICondition;

public class GhostAtLeastThisFarAway implements ICondition {

	private int dist;
	
	public GhostAtLeastThisFarAway(int dist) {
		this.dist = dist;
	}
	
	@Override
	public boolean test(Game game) {
		int pacLocation = game.getPacmanCurrentNodeIndex();
		for(GHOST ghostType : GHOST.values()) {
			int ghostLocation = game.getGhostCurrentNodeIndex(ghostType);
			int pathLengthToPacman = game.getShortestPathDistance(ghostLocation, pacLocation);
			if (pathLengthToPacman < dist) {
				return false;
			}
		}
		return true;
	}

}
