package edu.ucsc.gameAI.decisionTrees.binary;

import edu.ucsc.gameAI.ICondition;

/**
 * The interface for BinaryDecision nodes for implementing binary decision
 * trees. Every decision node in the binary tree implementation should
 * implement this interface.
 * 
 * @author Josh McCoy
 *
 */
public interface IBinaryDecision {

	/**
	 * Setter for the true branch.
	 * @param node The node associated with the true branch of the decision.
	 */
	public void setTrueBranch(IBinaryNode node);
	
	/**
	 * Getter for the true branch. 
	 * @return The node associated with the true branch of the decision.
	 */
	public IBinaryNode getTrueBranch();
	
	/**
	 * Setter for the false branch.
	 * @param node The node associated with the false branch of the decision.
	 */
	public void setFalseBranch(IBinaryNode node);
	
	/**
	 * Getter for the false branch.
	 * @return The node associated with the false branch of the decision.
	 */
	public IBinaryNode getFalseBranch();
	
	/**
	 * Sets the condition that determines if the decision is triggered.
	 * @param condition A testable condition.
	 */
	public void setCondition(ICondition condition);
	
}
