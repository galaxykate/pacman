package pacman.entries.pacman;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import edu.ucsc.gameAI.ConsoleLog;
import edu.ucsc.gameAI.FleeGhosts;
import edu.ucsc.gameAI.GoAction;
import edu.ucsc.gameAI.GoNearestPill;
import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.conditions.CurrentLevelTime;
import edu.ucsc.gameAI.conditions.GhostAtLeastThisFarAway;
import edu.ucsc.gameAI.conditions.GhostDistanceToPacman;
import edu.ucsc.gameAI.conditions.PacmanInRegion;
import edu.ucsc.gameAI.conditions.PillWasEaten;
import edu.ucsc.gameAI.decisionTrees.binary.BinaryDecision;
import edu.ucsc.gameAI.fsm.ITransition;
import edu.ucsc.gameAI.fsm.State;
import edu.ucsc.gameAI.fsm.StateMachine;
import edu.ucsc.gameAI.fsm.Transition;
import pacman.controllers.Controller;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import static java.util.Arrays.asList;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getAction() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.pacman.mypackage).
 */
public class AaronsPacMan extends Controller<MOVE> {
	
	private BinaryDecision startNode;
	private StateMachine stateMachine;
	private State pillEating;
	private State ghostFleeing;
	private Transition ghostTooClose;
	private Transition safeDistance;

	public AaronsPacMan() {

		// BinaryTree basic version:
		startNode = new BinaryDecision();

		startNode.setCondition(new CurrentLevelTime(50, 100));
		startNode.setTrueBranch(new GoAction(MOVE.LEFT));
		startNode.setFalseBranch(new GoAction(MOVE.RIGHT));
		
		// FSM version:
		stateMachine = new StateMachine();
		
		pillEating = new State();
		ghostFleeing = new State();
		
		pillEating.setAction(new GoNearestPill());
		
		ghostTooClose = new Transition(); 
		ghostTooClose.setCondition(new GhostDistanceToPacman(0, 7*5));
		ghostTooClose.setTargetState(ghostFleeing);
		//ghostFleeing.setEntryAction(new ConsoleLog("ghostTooClose! entering state ghostFleeing"));

		pillEating.addTransition(ghostTooClose);		
		
		ghostFleeing.setAction(new FleeGhosts());
		
		safeDistance = new Transition();
		safeDistance.setCondition(new GhostAtLeastThisFarAway(10*5));
		safeDistance.setTargetState(pillEating);
		//pillEating.setEntryAction(new ConsoleLog("safeDistance. entering state pillEating"));
		
		ghostFleeing.addTransition(safeDistance);

		stateMachine.setCurrentState(pillEating);
	}

	public MOVE getMove(Game game, long timeDue) {
		// For decision tree:
		//return startNode.makeDecision(game).getMove(game);
		
		// For FSM:
		// Update the state machine, and get the list of actions triggered.
		Collection<IAction> actions = stateMachine.update(game);
		
		// Perform any triggered actions (so far only used to show console messages.)
		for (IAction a : actions) {
			if (a != null) {
				a.doAction();
			}
		}
		
		// Return the action appropriate to the current state.
		return stateMachine.getCurrentState().getAction().getMove(game);
	}
}