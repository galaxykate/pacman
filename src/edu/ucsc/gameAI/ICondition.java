package edu.ucsc.gameAI;

/**
 * The interface for decision making for decision trees, finite state machines
 * and hierarchical finite state machines.
 * 
 * @author Josh McCoy
 */

public interface ICondition {
	/**
	 * Evaluates the decision node's condition and returns the true or false result.
	 * @return Result of evaluating the conditional expression.
	 */
	public boolean test();
	
}
