package edu.ucsc.gameAI;

import java.awt.Color;
import java.util.ArrayList;

import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import pacman.game.GameView;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class GoNearestInnerPill implements IAction, IBinaryNode {

	public GoNearestInnerPill() {

	}

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

		int pacX = game.getNodeXCood(pacIndex);
		int pacY = game.getNodeYCood(pacIndex);
		// System.out.println("Pacman: " + pacX + " " + pacY);

		int[] pills = game.getActivePillsIndices();
		int d = 9999;
		int mazeW = 106;
		int mazeH = 117;
		int borderX = 5;
		int borderY = 5;

		// Try to go to an inner pill
		for (int i = 0; i < pills.length; i++) {
			int index = pills[i];
			int x = game.getNodeXCood(index);
			int y = game.getNodeYCood(index);

			if (x >= borderX && x < mazeW - borderX && y >= borderY
					&& y < mazeH - borderY) {
				int pillD = game.getShortestPathDistance(pacIndex, index);

				// Color color = Color.getHSBColor((pillD*.1f)%1, 1, 1);
				// GameView.addPoints(game, color, index);

				if (pillD < d) {
					d = pillD;
					pillIndex = index;

				}
			}
		}

		if (pillIndex < 0) {

			// Try to go to an inner pill
			for (int i = 0; i < pills.length; i++) {
				int index = pills[i];
				int pillD = game.getShortestPathDistance(pacIndex, index);

				if (pillD < d) {
					d = pillD;
					pillIndex = index;

				}

			}
		}

		int x = game.getNodeXCood(pillIndex);
		int y = game.getNodeYCood(pillIndex);
		MOVE move = game.getNextMoveTowardsTarget(pacIndex, pillIndex, DM.PATH);
		// System.out.println("At " + pacIndex + " Going to pill " + x + " " + y
		// + " d:" + d + " " + move);
		return move;
	}

}
