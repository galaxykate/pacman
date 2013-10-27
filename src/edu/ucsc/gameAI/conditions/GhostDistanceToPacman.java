package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import pacman.game.Constants.GHOST;
import edu.ucsc.gameAI.ICondition;

public class GhostDistanceToPacman implements ICondition {

	private int min;
	private int max;
	
	public GhostDistanceToPacman(int min, int max) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public boolean test(Game game) {
		int pacLocation = game.getPacmanCurrentNodeIndex();
		for(GHOST ghostType : GHOST.values()) {
			int ghostLocation = game.getGhostCurrentNodeIndex(ghostType);
			int pathLengthToPacman = game.getShortestPathDistance(ghostLocation, pacLocation);
			if (pathLengthToPacman >= min && pathLengthToPacman < max) {
				return true;
			}
		}
		return false;
	}

}
