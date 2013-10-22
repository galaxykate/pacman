package edu.ucsc.gameAI.hfsm;

import pacman.game.Game;
import edu.ucsc.gameAI.IAction;

public interface IHFSMBase {

	public IAction getAction();
	
	public IResult update(Game game);
	
}
