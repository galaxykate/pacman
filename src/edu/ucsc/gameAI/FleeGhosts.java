package edu.ucsc.gameAI;

import java.awt.Color;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import pacman.game.GameView;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class FleeGhosts implements IAction, IBinaryNode {

	public void doAction() {
	}
	
	@Override
	public IAction makeDecision(Game game) {
		return this;
	}
	
	@Override
	public MOVE getMove(Game game) {
		
		int pacmanPosition = game.getPacmanCurrentNodeIndex();
		
		// Find the closest dangerous ghost
		int closestDistance = 9999;
		GHOST closestGhost = null;
		int closestPosition = 0;
		
		for (GHOST ghost : GHOST.values()) {
			if (game.getGhostEdibleTime(ghost)==0 && game.getGhostLairTime(ghost)==0) {
				// int dist = game.getShortestPathDistance(pacmanPosition, game.getGhostCurrentNodeIndex(ghost));
				//int dist = game.getManhattanDistance(pacmanPosition, game.getGhostCurrentNodeIndex(ghost));
				int dist = (int) game.getDistance(pacmanPosition, game.getGhostCurrentNodeIndex(ghost), DM.MANHATTAN);
				if (dist < closestDistance) {
					closestDistance = dist;
					closestGhost = ghost;
				}
			}
		}
		
		// If there is a closest dangerous ghost, move away from it,
		if (closestGhost != null) {
			closestPosition = game.getGhostCurrentNodeIndex(closestGhost);
			
			GameView.addLines(game, Color.RED, pacmanPosition, closestPosition);
			System.out.println(closestDistance);
			
			return game.getNextMoveAwayFromTarget(pacmanPosition, closestPosition, DM.PATH);
		}
		
		// Otherwise, keep moving as before.
		return game.getPacmanLastMoveMade();
				
	}
}
