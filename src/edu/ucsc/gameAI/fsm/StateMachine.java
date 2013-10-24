package edu.ucsc.gameAI.fsm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pacman.game.Game;
import edu.ucsc.gameAI.IAction;

public class StateMachine implements IStateMachine {

        Collection<IState> states;
        IState initialState;
        IState currentState;
        
        /*
         * Constructor
         */
        public StateMachine() {
        }

        /**
         * The member function that performs the update on the FSM:
         * - Test transitions for current state and moves to new state.
         * - Returns a collection of IActions that result from the current
         *   state and any transitions, entrances and exits that may occur.
         * @return A collection of actions produced by evaluating the FSM.
         */
        @Override
        public Collection<IAction> update(Game game) {

                // Create a list of actions to return, and a variable to track target state.
      	  
        		/* Aaron: So I'm a little stumped here by a basic Java question. The error says
        		 * you can't create a Collection of an interface (IAction), but if we need to
        		 * return Collection<IAction> at the end, and if we don't know what kind of 
        		 * action the function will be called with, how can we create the variable
        		 * "actions" such that it would work for any kind of action?
        		 */
        		
                Collection<IAction> actions = new ArrayList<IAction>();
                IState targetState = currentState;
                
                // Assume no transition is triggered
                ITransition triggeredTransition = null;
                
                // Look through each transition and store the first that triggers.
                for (ITransition t : currentState.getTransitions()) {
                        if (t.isTriggered(game)) {
                                triggeredTransition = t;
                                break;
                        }
                }
                
                // If we have a transition
                if (triggeredTransition != null) {
                        
                        // Find the target state.
                        targetState = triggeredTransition.getTargetState();
                        
                        // Add actions for exit, transition, and entry.
                        actions.add( currentState.getExitAction() );
                        actions.add( triggeredTransition.getAction() );
                        actions.add( targetState.getEntryAction() );
                        
                } else {
                        // We don't have a transition, so just include the current state's action.
                        actions.add( currentState.getAction() );
                }
                
                // Complete the transition and return the action list.
                currentState = targetState;
                return actions;
        }

        @Override
        public IState getCurrentState() {
                return currentState;
        }

		@Override
		public void setCurrentState(IState state) {
			// TODO Auto-generated method stub
			
		}

}