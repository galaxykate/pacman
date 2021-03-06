package edu.ucsc.gameAI.fsm;

import java.util.ArrayList;
import java.util.Collection;

import edu.ucsc.gameAI.IAction;

/**
 * An implementation of the State class for FSM.
 * 
 * @author Aaron Reed
 */
public class State implements IState {

	// Keep track of the action to perform while in, on entry, and on exit of
	// this state.
	IAction action;
	IAction entryAction;
	IAction exitAction;
	Collection<ITransition> outgoingTransitions = new ArrayList<ITransition>();

	// Constructor
	public State() {
	}

	@Override
	public IAction getAction() {
		return action;
	}

	@Override
	public IAction getEntryAction() {
		return entryAction;
	}

	@Override
	public IAction getExitAction() {
		return exitAction;
	}

	@Override
	public Collection<ITransition> getTransitions() {
		return outgoingTransitions;
	}

	@Override
	public void setAction(IAction action) {
		this.action = action;
	}

	@Override
	public void setEntryAction(IAction action) {
		this.entryAction = action;
	}

	@Override
	public void setExitAction(IAction action) {
		this.exitAction = action;
	}

	@Override
	public void setTransitions(Collection<ITransition> trans) {
		this.outgoingTransitions = trans;
	}
	
	public void addTransition(ITransition trans) {
		this.outgoingTransitions.add(trans);
	}

}