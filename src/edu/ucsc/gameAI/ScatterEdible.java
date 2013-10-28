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

public class ScatterEdible implements IAction, IBinaryNode {

	GHOST ghost;
	public ScatterEdible(GHOST ghost) {
		this.ghost = ghost;
	}	
	
	public void doAction() {
	}
	
	@Override
	public IAction makeDecision(Game game) {
		return this;
	}
	
	// Move away from a nearby edible ghost.
	@Override
	public MOVE getMove(Game game) {
		
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
				// move away from nearby ghost
				GameView.addLines(game, Color.YELLOW, myPosition, cPosition);
				return game.getNextMoveAwayFromTarget(myPosition, cPosition, game.getGhostLastMoveMade(ghost), DM.PATH);
			}
		}
			
		// We should always have found something above or the condition
		// wouldn't have fired, but just in case:
		return game.getNextMoveAwayFromTarget(myPosition, game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghost), DM.PATH);
				
	}
}
