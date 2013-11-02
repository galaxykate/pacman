package edu.ucsc.gameAI;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class ReduceThreat implements IAction, IBinaryNode {

	private float[] threatMap;

	public ReduceThreat(float[] threatMap) {
		this.threatMap = threatMap;
	}

	@Override
	public IAction makeDecision(Game game) {

		return this;
	}

	@Override
	public MOVE getMove(Game game) {

		MOVE bestMove = game.getPacmanLastMoveMade();
		int pacNode = game.getPacmanCurrentNodeIndex();

		int pillIndex = -1;

		// Find the closest pill as a backup
		int[] pills = game.getActivePowerPillsIndices();
		int d = 9999;
		for (int i = 0; i < pills.length; i++) {
			int pillD = game.getShortestPathDistance(pacNode, pills[i]);
			if (pillD < d) {
				d = pillD;
				pillIndex = pills[i];
			}
		}

		float currentThreat = threatMap[pacNode];
		float maxThreat = 0;
		MOVE[] moves = game.getPossibleMoves(pacNode);

		float leastThreatSlope = 5;

		for (MOVE move : moves) {
			int node = game.getNeighbour(pacNode, move);
			float threat = threatMap[node];
			maxThreat = Math.max(maxThreat, threat);
			System.out.println(threat + " " + currentThreat);

			// Calculate if there is a safe exit this way
			
			
			float threatSlope = threat - currentThreat;
			System.out.println(move + " " + threatSlope);
			if (threatSlope < leastThreatSlope) {
				leastThreatSlope = threatSlope;
				bestMove = move;
			}
		}

		System.out.println("Best: " + bestMove + " slope: " + leastThreatSlope);
		return bestMove;
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub

	}
}
