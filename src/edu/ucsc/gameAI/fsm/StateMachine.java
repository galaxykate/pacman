package edu.ucsc.gameAI.fsm;

import java.util.ArrayList;
import java.util.Collection;

import pacman.game.Game;
import edu.ucsc.gameAI.IAction;

public class StateMachine implements IStateMachine {

	Collection<IState> states;
	IState initialState;
	IState currentState;

	/*
	 * Constructor
	 */
	public StateMachine() {
	}

	/**
	 * The member function that performs the update on the FSM: - Test
	 * transitions for current state and moves to new state. - Returns a
	 * collection of IActions that result from the current state and any
	 * transitions, entrances and exits that may occur.
	 * 
	 * @return A collection of actions produced by evaluating the FSM.
	 */
	@Override
	public Collection<IAction> update(Game game) {

		// Create a list of actions to return, and a variable to track target
		// state.

		Collection<IAction> actions = new ArrayList<IAction>();
		IState targetState = currentState;

		// Assume no transition is triggered
		ITransition triggeredTransition = null;

		// Look through each transition and store the first that triggers.
		for (ITransition t : currentState.getTransitions()) {
			if (t.isTriggered(game)) {
				triggeredTransition = t;
				break;
			}
		}

		// If we have a transition
		IAction act = null;
		if (triggeredTransition != null) {

			// Find the target state.
			targetState = triggeredTransition.getTargetState();

			// Add actions for exit, transition, and entry.
			act = currentState.getExitAction();
			if (act != null)
				actions.add(act);
			act = triggeredTransition.getAction();
			if (act != null)
				actions.add(act);
			act = targetState.getEntryAction();
			if (act != null)
				actions.add(act);

		} else {
			// We don't have a transition, so just include the current state's
			// action.
			act = currentState.getAction();
			actions.add(act);
		}

		// Complete the transition and return the action list.
		currentState = targetState;
		return actions;
	}

	@Override
	public IState getCurrentState() {
		return currentState;
	}

	@Override
	public void setCurrentState(IState state) {
		currentState = state;
	}

}