package edu.ucsc.gameAI;

import java.awt.Color;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import pacman.game.GameView;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class ChasePacman implements IAction, IBinaryNode {

	GHOST ghost;
	public ChasePacman(GHOST ghost) {
		this.ghost = ghost;
	}
	
	public void doAction() {
	}
	
	@Override
	public IAction makeDecision(Game game) {
		return this;
	}
	
	@Override
	public MOVE getMove(Game game) {
		
		int myPosition = game.getGhostCurrentNodeIndex(ghost);
		int pacmanPosition = game.getPacmanCurrentNodeIndex();
					
		GameView.addLines(game, Color.GREEN, myPosition, pacmanPosition);
		//System.out.println(closestDistance);
			
		return game.getNextMoveTowardsTarget(myPosition, pacmanPosition, DM.PATH);
		
	}
}

