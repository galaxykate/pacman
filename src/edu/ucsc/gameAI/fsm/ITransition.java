package edu.ucsc.gameAI.fsm;

import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.ICondition;

/**
 * The interface for transitions in finite state machines.
 * 
 * @author Josh McCoy
 */
public interface ITransition {
	
	/**
	 * Accesses the state that this transition leads to.
	 * @return The state this transition leads to.
	 */
	IState getTargetState();
	
	/**
	 * Generates the action associated with taking this transition.
	 * @return The action associated with taking this transition.
	 */
	IAction getAction();
	
	/**
	 * Sets the condition that determines if the transition is triggered.
	 * @param condition A testable condition.
	 */
	public void setCondition(ICondition condition);
	
	/**
	 * Determines if this transition is triggered.
	 * @return True if triggered, false if not.
	 */
	public boolean isTriggered();
}
