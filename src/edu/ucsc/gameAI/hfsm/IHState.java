package edu.ucsc.gameAI.hfsm;

import java.util.Collection;

import edu.ucsc.gameAI.IAction;

/**
 * A possible class definition of HState:
 * public class HState extends HFSMBase implements IHState
 * 
 * 
 */
public interface IHState extends IHFSMBase {
	
	/*
	 * The reference implmenetion as the following members:
	 	protected IAction entryAction;
		protected IAction exitAction;
		protected IAction action;
		protected Collection<IHTransition> transitions;
		protected IHFSM parent;
		protected String name = "unnamed";
	 */
	
	/**
	 * As this is a single, non-hierarchical state, this member function should
	 * return a Collection that only contains this state. Here is an example:
	    Collection<IHState> states = new ArrayList<IHState>();
		states.add(this);
		return states;
	 * @return A collection containing this state.
	 */
	public Collection<IHState> getStates();
	
	/**
	 * This function should do nothing as it cannot contain hierarchy. HFSM fills that role.
	 */
	public void setStates(Collection<IHState> states);
	
	public IAction getAction();
	
	public void setAction(IAction action);
	
	public IAction getEntryAction();
	
	public void setEntryAction(IAction action);
	
	public IAction getExitAction();
	
	public void setExitAction(IAction action);
	
	public Collection<IHTransition> getTransitions();
	
	public void setTransitions(Collection<IHTransition> transitions);
	
	/**
	 * This is a useful member function that adds a transition when the transition
	 * passed in as an argument is not null. Here is a sample implementation: 
	  if(null == transitions) {
			transitions = new ArrayList<IHTransition>();
		}
		if(null != transition) {
			transitions.add(transition);
		}
	 * @param transition
	 */
	public void addTransition(IHTransition transition);
	
	public IHFSM getParent();
	
	public void setParent(IHFSM parent);
	
}
