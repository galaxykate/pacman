package edu.ucsc.gameAI.fsm;

import java.util.Collection;
import edu.ucsc.gameAI.IAction;

/**
 * An implementation of the State class for FSM.
 * 
 * @author Aaron Reed
 */
public class State implements IState {

	// Keep track of the action to perform while in, on entry, and on exit of this state.
	IAction action;
	IAction entryAction;
	IAction exitAction;
	Collection<ITransition> outgoingTransitions;
	
	// Constructor
	public State(IAction _action, IAction _entryAction, IAction _exitAction, Collection<ITransition> _outgoingTransitions) {
		action = _action;
		entryAction = _entryAction;
		exitAction = _exitAction;
		outgoingTransitions = _outgoingTransitions;
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

}
