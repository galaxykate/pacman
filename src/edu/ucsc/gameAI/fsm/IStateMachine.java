package edu.ucsc.gameAI.fsm;

import java.util.Collection;

import edu.ucsc.gameAI.IAction;

/**
 * The interface for a state machine. This Interface must be extended for 
 * state machine implementations to interface wit automatic testing and 
 * grading. 
 * @author Josh McCoy
 *
 */
public interface IStateMachine {
	
	/**
	 * The member function that performs the update on the FSM:
	 * - Test transitions for current state and moves to new state.
	 * - Returns a collection of IActions that result from the current
	 *   state and any transitions, entrances and exits that may occur.
	 * @return A collection of actions produced by evaluating the FSM.
	 */
	Collection<IAction> update();
	
	/**
	 * Retrieves the current state of the finite state machine.
	 * @return The current state of the finite state machine.
	 */
	IState getCurrentState();
}
