package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import pacman.game.Constants.GHOST;
import edu.ucsc.gameAI.ICondition;
import edu.ucsc.gameAI.Utils;

public class ShortestPathIsSafe implements ICondition {

	private int nodeIndex;
	private boolean toNearestPowerPill;

	public ShortestPathIsSafe(int nodeIndex) {
		this.nodeIndex = nodeIndex;
	}

	public ShortestPathIsSafe() {
		this.toNearestPowerPill = true;
	}

	@Override
	public boolean test(Game game) {
		int pacIndex = game.getPacmanCurrentNodeIndex();

		// Get nearest power pill index
		if (toNearestPowerPill) {
			nodeIndex = pacIndex;
			int[] pills = game.getActivePowerPillsIndices();
			int d = 9999;
			for (int i = 0; i < pills.length; i++) {
				int pillD = game.getShortestPathDistance(pacIndex, pills[i]);
				if (pillD < d) {
					d = pillD;
					nodeIndex = pills[i];
				}

			}
		}

		int[] path = game.getShortestPath(pacIndex, nodeIndex);

		// for each ghost, test if the ghost will reach this path in n steps
		for (int i = 0; i < path.length; i++) {
			boolean isSafe = true;
			for (GHOST ghostType : GHOST.values()) {
				int ghostPos = game.getGhostCurrentNodeIndex(ghostType);
				// Can this ghost get to this node in time?

				int d = game.getShortestPathDistance(ghostPos, path[i]);
				if (d < i)
					isSafe = false;
			}
			if (!isSafe)
				return false;
		}
		return true;
	}
}
