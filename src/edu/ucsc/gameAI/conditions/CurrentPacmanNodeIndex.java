package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import pacman.game.Constants.GHOST;
import edu.ucsc.gameAI.ICondition;

public class CurrentPacmanNodeIndex implements ICondition {
	private int index; 
	
	public CurrentPacmanNodeIndex(int index) {
		this.index = index;
	}
	
	@Override
	public boolean test(Game game) {
		// TODO Auto-generated method stub
		return false;
	}

}
