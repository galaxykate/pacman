package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class IsEdible implements ICondition {
	
	Game ggame;
	GHOST gghost;
	public IsEdible(Game game, GHOST ghost)
	{
		ggame=game;
		gghost=ghost;
	}
	
	public boolean test() 
	{
		return ggame.isGhostEdible(gghost);
	}
}
