package edu.ucsc.gameAI.decisionTrees.binary;

import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.ICondition;


/**
 * A reference implementation for the BinaryDecision class.
 * 
 * @author Josh McCoy
 */
public class BinaryDecision implements IBinaryDecision {

	private IBinaryNode trueBranch;
	private IBinaryNode falseBranch;
	private ICondition condition;
	
	/**
	 * Setter for the true branch.
	 * @param node The node associated with the true branch of the decision.
	 */
	@Override
	public void setTrueBranch(IBinaryNode node)
	{
		trueBranch=node;
	}
	
	/**
	 * Getter for the true branch. 
	 * @return The node associated with the true branch of the decision.
	 */
	@Override
	public IBinaryNode getTrueBranch() {return trueBranch;}
	
	/**
	 * Setter for the false branch.
	 * @param node The node associated with the false branch of the decision.
	 */
	@Override
	public void setFalseBranch(IBinaryNode node)
	{
		falseBranch=node;
	}
	
	/**
	 * Getter for the false branch.
	 * @return The node associated with the false branch of the decision.
	 */
	@Override
	public IBinaryNode getFalseBranch() {return falseBranch;}
	
	public IAction makeDecision()
	{
		if (condition.test())
			return trueBranch.makeDecision();
		else
			return falseBranch.makeDecision();
	}

	@Override
	public void setCondition(ICondition condition) {
		this.condition = condition;
	}

	
}
