package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import pacman.game.Constants.GHOST;
import edu.ucsc.gameAI.ICondition;

public class NumberOfLivesRemaining implements ICondition {
	private int min, max; 
	
	public NumberOfLivesRemaining(int min) {
		this.min = min;
		//this.max = max;
	}
	
	@Override
	public boolean test(Game game) {
		// TODO Auto-generated method stub
		return false;
	}

}
