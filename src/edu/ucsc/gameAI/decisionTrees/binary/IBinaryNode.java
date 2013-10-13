package edu.ucsc.gameAI.decisionTrees.binary;

import edu.ucsc.gameAI.IAction;

/**
 * The interface for decision tree nodes. All node (e.g. Decision and Action
 * nodes) that are to be placed on the decision tree must implement this
 * interface.
 * 
 * @author Josh McCoy
 *
 */

public interface IBinaryNode {
	/**
	 * Recurses through the binary tree until a leaf/action node is reached.
	 * @return The terminal Action of evaluate the binary decision tree.
	 */
	IAction makeDecision();
}
