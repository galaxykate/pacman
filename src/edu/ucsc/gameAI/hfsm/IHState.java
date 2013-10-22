package edu.ucsc.gameAI.hfsm;

import java.util.Collection;
import edu.ucsc.gameAI.IAction;

/**
 * 
 * FIXME: remove IState extension in favor of IHState (IState has getState and IHState has getStates -- there should be only one!).
 *
 */
public interface IHState extends IHFSMBase {
	
	public Collection<IHState> getStates();
	
	public void setStates(Collection<IHState> states);
	
	public IAction getAction();
	
	public void setAction(IAction action);
	
	public IAction getEntryAction();
	
	public void setEntryAction(IAction action);
	
	public IAction getExitAction();
	
	public void setExitAction(IAction action);
	
	public Collection<IHTransition> getTransitions();
	
	public void setTransitions(Collection<IHTransition> transitions);
	
	public void addTransition(IHTransition transition);
	
	public IHFSM getParent();
	
	public void setParent(IHFSM parent);
	
}
