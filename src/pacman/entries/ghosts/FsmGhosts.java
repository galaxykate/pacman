package pacman.entries.ghosts;

import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedList;

import pacman.controllers.Controller;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.conditions.PacmanInRegion;
import edu.ucsc.gameAI.fsm.ITransition;
import edu.ucsc.gameAI.fsm.State;
import edu.ucsc.gameAI.fsm.StateMachine;
import edu.ucsc.gameAI.fsm.Transition;
import edu.ucsc.gameAI.*;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getActions() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.ghosts.mypackage).
 */
public class FsmGhosts extends Controller<EnumMap<GHOST,MOVE>>
{
	private EnumMap<GHOST, MOVE> myMoves=new EnumMap<GHOST, MOVE>(GHOST.class);
	
	int ix1 = 0;
	int ix2 = 110;
	int iy1 = 0;
	int iy2 = 60;
	int iy3 = 120;
	State stateRun = new State();
	State stateChase = new State();
	Transition transScared = new Transition();
	LinkedList<ITransition> listtscared = new LinkedList<ITransition>();
	Transition transCool = new Transition();
	LinkedList<ITransition> listtcool = new LinkedList<ITransition>();
	StateMachine fsm = new StateMachine();
	
	
	public FsmGhosts()
	{
		int ix1 = 0;
		int ix2 = 110;
		int iy1 = 0;
		int iy2 = 60;
		int iy3 = 120;
		stateRun = new State();
		stateRun.setAction(new GoDownAction());
		stateChase = new State();
		stateChase.setAction(new GoUpAction());
		transScared = new Transition();
		transScared.setCondition(new PacmanInRegion(ix1,iy1,ix2,iy2)); // top half of the screen
		transScared.setTargetState(stateRun);
		listtscared = new LinkedList<ITransition>();
		listtscared.add(transScared);
		transCool = new Transition();
		transCool.setCondition(new PacmanInRegion(ix1,iy2+1,ix2,iy3)); // bottom of screen (screen is (4,4) to (104,116))
		transCool.setTargetState(stateChase);
		listtcool = new LinkedList<ITransition>();
		listtcool.add(transCool);
		stateRun.setTransitions(listtcool);
		stateChase.setTransitions(listtscared);
		fsm = new StateMachine();
		fsm.setCurrentState(stateChase);
		
	}
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue)
	{
		myMoves.clear();
		
		for(GHOST ghost : GHOST.values())	//for each ghost
		{
			if(game.doesGhostRequireAction(ghost))		//if ghost requires an action
			{
				Collection<IAction> actions = fsm.update(game);
				if (actions.size() > 0)
					myMoves.put(ghost, actions.iterator().next().getMove());
			}
		}
		
		return myMoves;
	}
}