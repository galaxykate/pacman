package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PacmanThreat implements ICondition {

	private float[] threatMap;
	private float min, max;
	private MOVE move;

	public PacmanThreat(float[] threatMap, float min, float max) {
		this.threatMap = threatMap;
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean test(Game game) {
		int pacNode = game.getPacmanCurrentNodeIndex();
		float threat = threatMap[pacNode];
		return (threat >= this.min && threat < this.max);
	}

}
