package edu.ucsc.gameAI.fsm;

import pacman.game.Game;
import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.ICondition;

/**
 * An implementation of the Transition class.
 * 
 * @author Aaron Reed
 */
public class Transition implements ITransition {

        IState targetState;
        IAction action;
        ICondition condition;
        
        /**
         * Constructor for the Transition class.
         */
        public Transition() {

        }

        /**
         * Accesses the state that this transition leads to.
         * @return The state this transition leads to.
         */
        @Override
        public IState getTargetState() {
                return targetState;
        }

        /**
         * Generates the action associated with taking this transition.
         * @return The action associated with taking this transition.
         */
        @Override
        public IAction getAction() {
                return action;
        }

        /**
         * Sets the condition that determines if the transition is triggered.
         * @param condition A testable condition.
         */
        @Override
        public void setCondition(ICondition _condition) {
                condition = _condition;
        }

        /**
         * Determines if this transition is triggered.
         * @return True if triggered, false if not.
         */
        @Override
        public boolean isTriggered(Game game) {
                return condition.test(game);
        }

		@Override
		public void setTargetState(IState targetState) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setAction(IAction action) {
			// TODO Auto-generated method stub
			
		}


}