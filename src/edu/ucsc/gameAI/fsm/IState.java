package edu.ucsc.gameAI.fsm;

import java.util.Collection;

import edu.ucsc.gameAI.IAction;

/**
 * The interface for a state in a finite state machine. 
 * 
 * @author Josh McCoy
 */
public interface IState {
	
	/**
	 * Determines the action appropriate for being in this state. 
	 * @return Action for being in this state.
	 */
	IAction getAction();
	
	/**
	 * Generates the action for entering this state.
	 * @return The action associated with entering this state.
	 */
	IAction getEntryAction();
	
	/**
	 * Retrieves the action for exiting this state.
	 * @return The action associated with exiting this state.
	 */
	IAction getExitAction();
	
	/**
	 * Accessor for all transitions out of this state.
	 * @return The outbound transitions from this state.
	 */
	Collection<ITransition> getTransitions();
}

