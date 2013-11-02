package edu.ucsc.gameAI;

import java.util.ArrayList;

import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class GoNearestPill implements IAction, IBinaryNode {

	public void doAction() {
	}

	@Override
	public IAction makeDecision(Game game) {
		return this;
	}

	@Override
	public MOVE getMove(Game game) {

		int pacIndex = game.getPacmanCurrentNodeIndex();
		int pillIndex = -1;

		int[] pills = game.getActivePowerPillsIndices();
		int d = 9999;
		for (int i = 0; i < pills.length; i++) {
			int pillD = game.getShortestPathDistance(pacIndex, pills[i]);
			if (pillD < d) {
				d = pillD;
				pillIndex = pills[i];

			}

		}

		// No pills? keep going wherever
		if (pillIndex < 0) {
			return game.getPacmanLastMoveMade();
		}

		MOVE move = game.getNextMoveTowardsTarget(pacIndex, pillIndex, DM.PATH);
		System.out.println("Going to pill " + pillIndex + " d:" + d + " "
				+ move);

		return move;
	}
}
