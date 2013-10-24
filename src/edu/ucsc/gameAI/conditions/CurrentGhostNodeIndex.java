package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import pacman.game.Constants.GHOST;
import edu.ucsc.gameAI.ICondition;

public class CurrentGhostNodeIndex implements ICondition {
	private GHOST ghost;
	private int index; 
	
	public CurrentGhostNodeIndex(GHOST ghost, int index) {
		this.ghost = ghost;
		this.index = index;
	}
	
	@Override
	public boolean test(Game game) {
		// TODO Auto-generated method stub
		return false;
	}

}
