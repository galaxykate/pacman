package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class IsEdible implements ICondition {
	
	GHOST gghost;
	public IsEdible(GHOST ghost)
	{
		gghost=ghost;
	}
	
	public boolean test(Game game) 
	{
		return game.isGhostEdible(gghost);
	}
}
