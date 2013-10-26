package edu.ucsc.gameAI.hfsm;

import java.util.Collection;

import edu.ucsc.gameAI.IAction;
import pacman.game.Game;


/**
 * This is the core of the HFSM implementation.
 * 
 * Pay particular attention to the update member function. The psuedocode
 * starting on Millington and Funge page 326 is the basis for this function.
 * It will need to be light adapted to this framework. The results variable 
 * mentioned in update() is a good candidate for a member variable. Line
 * 51 of the psuedocode refers to a targetState variable. This variable is
 * the target state of triggeredTransition (also known as result.getTransition())
 * whose existence is checked on line 34.
 * 
 * 
 * 
 * Some hints to help implementation:
 * 
 * Extending/inheriting your implementation of HState will simplify this class. You can inherit a large number of accessors.
 * 
 * Automatically setting the parents states added via setStates saves time.
 * 
 * Making a constructor that takes a String as a name will assist in your debugging.
 * 
 * If the current state is null in when getAction() is called, set the current state to the initial state.
 * 
 * In the books implementation, there is an updateDown() member function that does not appear in the interface.
 * 
 * The ArrayList class is a class that can be assigned to the Collection generic.
 * 
 * Adding an addAction member function to Result that adds an IAction when it its reference is not null will cut down 
 * on code clutter and help cut down on null reference errors.
 * The same is true for adding transitions to HTransition.  
 * 
 * HFSM extends IHState when extends IHFSMBase in turn. 
 */
public interface IHFSM extends IHState {
	
	public IResult update(Game game);
	
	public Collection<IAction> updateDown(IHState state, int level, Game game);
	
	public void setInitialState(IHState initialState);
	
	public IHState getInitialState();
	
	public IHFSM getParent();
	
	public void setParent(IHFSM parent);
	
	/*
	 * updateDown is present in the book's implementation of HFSM. Here is the
	 * function signature from the reference implementation. 
	 */
	//protected Collection<IAction> updateDown(IHState state, int level, Game game)
}
