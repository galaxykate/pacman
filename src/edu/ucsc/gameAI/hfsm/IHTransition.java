package edu.ucsc.gameAI.hfsm;

import pacman.game.Game;
import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.ICondition;

/**
 * Note that this class intentionally does not borrow from FSM's ITransition.
 * They are separated for pedagogical reasons.
 * 
 * @author Josh McCoy
 *
 */
public interface IHTransition {
	/**
	 * Accesses the state that this transition leads to.
	 * @return The state this transition leads to.
	 */
	public IHState getTargetState();
	
	/**
	 * Sets the target state of this transition.
	 * @param targetState The target state.
	 */
	public void setTargetState(IHState targetState);
	
	/**
	 * Generates the action associated with taking this transition.
	 * @return The action associated with taking this transition.
	 */
	public IAction getAction();
	
	/**
	 * Sets the action for enacting the transition.
	 * @param action Transition action.
	 */
	public void setAction(IAction action);
	
	/**
	 * Sets the condition that determines if the transition is triggered.
	 * @param condition A testable condition.
	 */
	public void setCondition(ICondition condition);
	
	/**
	 * Determines if this transition is triggered.
	 * @param game TODO
	 * @return True if triggered, false if not.
	 */
	public boolean isTriggered(Game game);

	
	public int getLevel();
	
	public void setLevel(int level);
}
