package edu.ucsc.gameAI;

import java.awt.Color;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import pacman.game.GameView;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class ChaseGhost implements IAction, IBinaryNode {

	GHOST ghost;
	boolean closest = false;

	public ChaseGhost(GHOST ghost) {

		this.ghost = ghost;

	}

	public ChaseGhost() {
		closest = true;

	}

	public void doAction() {
	}

	@Override
	public IAction makeDecision(Game game) {
		return this;
	}

	@Override
	public MOVE getMove(Game game) {

		// Get the closest ghost
		int pacmanPosition = game.getPacmanCurrentNodeIndex();
		if (closest) {
			int d = 9999;
			for (GHOST ghostType : GHOST.values()) {
				int ghostD = game.getManhattanDistance(pacmanPosition,
						game.getGhostCurrentNodeIndex(ghostType));

				if (ghostD < d) {
					d = ghostD;
					ghost = ghostType;
				}
			}
		}

		int ghostPosition = game.getGhostCurrentNodeIndex(ghost);

		GameView.addLines(game, Color.GREEN, ghostPosition, pacmanPosition);
		// System.out.println(closestDistance);

		return game.getNextMoveTowardsTarget(pacmanPosition, ghostPosition,
				DM.PATH);

	}
}
