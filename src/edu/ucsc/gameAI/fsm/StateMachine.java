package edu.ucsc.gameAI.fsm;

import java.util.Collection;
import java.util.List;

import edu.ucsc.gameAI.IAction;

public class StateMachine implements IStateMachine {

	Collection<IState> states;
	IState initialState;
	IState currentState;
	
	/*
	 * Constructor
	 */
	public StateMachine(Collection<IState> _states, IState _initialState) {
		states = _states;
		initialState = _initialState;
		currentState = initialState;
	}

	/**
	 * The member function that performs the update on the FSM:
	 * - Test transitions for current state and moves to new state.
	 * - Returns a collection of IActions that result from the current
	 *   state and any transitions, entrances and exits that may occur.
	 * @return A collection of actions produced by evaluating the FSM.
	 */
	@Override
	public Collection<IAction> update() {

		// Create a list of actions to return, and a variable to track target state.
		List<IAction> actions = new List<IAction>();
		IState targetState = currentState;
		
		// Assume no transition is triggered
		ITransition triggeredTransition = null;
		
		// Look through each transition and store the first that triggers.
		for (ITransition t : currentState.getTransitions()) {
			if (t.isTriggered()) {
				triggeredTransition = t;
				break;
			}
		}
		
		// If we have a transition
		if (triggeredTransition != null) {
			
			// Find the target state.
			targetState = triggeredTransition.getTargetState();
			
			// Add actions for exit, transition, and entry.
			actions.add( currentState.getExitAction() );
			actions.add( triggeredTransition.getAction() );
			actions.add( targetState.getEntryAction() );
			
		} else {
			// We don't have a transition, so just include the current state's action.
			actions.add( currentState.getAction() );
		}
		
		// Complete the transition and return the action list.
		currentState = targetState;
		return actions;
	}

	@Override
	public IState getCurrentState() {
		return currentState;
	}

}
