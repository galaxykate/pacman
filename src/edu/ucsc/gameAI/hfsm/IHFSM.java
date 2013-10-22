package edu.ucsc.gameAI.hfsm;

import java.util.Collection;

import edu.ucsc.gameAI.IAction;
import pacman.game.Game;

public interface IHFSM extends IHState {
	
	public IResult update(Game game);
	
	public Collection<IAction> updateDown(IHState state, int level, Game game);
	
	public void setInitialState(IHState initialState);
	
	public IHState getInitialState();
	
	public IHFSM getParent();
	
	public void setParent(IHFSM parent);

}
