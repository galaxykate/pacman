package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import pacman.game.Constants.GHOST;
import edu.ucsc.gameAI.ICondition;

public class PowerPillDistanceToPacman implements ICondition {

	private int min;
	private int max;
	private int pillIndex;

	public PowerPillDistanceToPacman(int pillIndex, int min, int max) {
		this.min = min;
		this.max = max;
		this.pillIndex = pillIndex;
	}

	@Override
	public boolean test(Game game) {
		int pacLocation = game.getPacmanCurrentNodeIndex();

		int pathLengthToPacman = game.getShortestPathDistance(pillIndex,
				pacLocation);

		if (pathLengthToPacman >= min && pathLengthToPacman < max) {
			return true;
		}

		return false;
	}

}
