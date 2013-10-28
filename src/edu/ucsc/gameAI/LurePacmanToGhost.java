package edu.ucsc.gameAI;

import java.awt.Color;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import pacman.game.GameView;
import edu.ucsc.gameAI.conditions.GhostInGhostHouse;
import edu.ucsc.gameAI.conditions.IsEdible;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class LurePacmanToGhost implements IAction, IBinaryNode {

	GHOST ghost;
	public LurePacmanToGhost(GHOST ghost) {
		this.ghost = ghost;
	}
	
	public void doAction() {
	}
	
	@Override
	public IAction makeDecision(Game game) {
		return this;
	}
	
	// Move towards the nearest non-edible ghost, unless doing so
	// leads straight into Pacman.
	@Override
	public MOVE getMove(Game game) {
		
		int myPosition = game.getGhostCurrentNodeIndex(ghost);
		int pacmanPosition = game.getPacmanCurrentNodeIndex();
		MOVE myLastMove = game.getGhostLastMoveMade(ghost);
		
		// Find nearest dangerous ghost.
		int smallestDist = 9999;
		int candidatePosition = -1;
		for (GHOST candidate : GHOST.values()) {
			// skip ourselves, edible, and unpositioned ghosts.
			if (ghost == candidate ||
					new IsEdible(candidate).test(game) ||
					new GhostInGhostHouse(candidate).test(game)) {	
				continue;
			}
			int cPosition = game.getGhostCurrentNodeIndex(candidate);
			int candidateDist = (int) game.getDistance(myPosition, cPosition, myLastMove, DM.PATH);
			if (candidateDist < smallestDist) {
				smallestDist = candidateDist;
				candidatePosition = cPosition;
			}			
		}
		
		// If we didn't find a suitable ghost, just run away.
		if (candidatePosition == -1) {
			return game.getNextMoveAwayFromTarget(myPosition, pacmanPosition, myLastMove, DM.PATH);
		}
		
		//System.out.print("found lure candidate!");
		MOVE towardsDangerousGhost = game.getNextMoveTowardsTarget(myPosition, candidatePosition, myLastMove, DM.PATH);
		
		
		// Would moving towards this ghost lead us straight to Pacman?
		// Originally we had code in here to check.
		// But actually, it turns out suicidal moves here produce a better score.
		// Apparently, the point sacrifice of possibly allowing Pacman to eat
		// the ghost is worth the trade-off of bringing dangerous ghosts closer
		// to Pacman.
		// Old code below:
		//MOVE towardsPacman = game.getNextMoveTowardsTarget(myPosition, pacmanPosition, myLastMove, DM.PATH);
		//if (towardsDangerousGhost == towardsPacman) { //  && game.getGhostCurrentEdibleScore() > 1000
		//	//System.out.println("... but in direction of Pacman");
		//	//GameView.addLines(game, Color.RED, myPosition, candidatePosition);
		//	return game.getNextMoveAwayFromTarget(myPosition, pacmanPosition, myLastMove, DM.PATH);
		//}
		//System.out.println(" (luring)");

		//GameView.addLines(game, Color.ORANGE, myPosition, candidatePosition);
		return towardsDangerousGhost;
		
	}
}

