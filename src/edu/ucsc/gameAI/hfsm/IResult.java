package edu.ucsc.gameAI.hfsm;

import java.util.Collection;

import edu.ucsc.gameAI.IAction;

public interface IResult {
	
	public Collection<IAction> getActions();
	
	public void setActions(Collection<IAction> actions);
	
	public void addAction(IAction action);
	
	public void addActions(Collection<IAction> actions);
	
	public IHTransition getTransition();
	
	public void setTransition(IHTransition transition);
	
	public int getLevel();
	
	public void setLevel(int level);
}
