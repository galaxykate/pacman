package edu.ucsc.gameAI;

/**
 * The Action interface for decision tree action nodes and FSM actions. 
 * 
 * @author Josh McCoy
 */
public interface IAction {
	
	/**
	 * Performs the action associated with this Action node.
	 */
	public void doAction();
}
