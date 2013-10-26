package pacman.tests;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import edu.ucsc.gameAI.*;
import edu.ucsc.gameAI.conditions.PacmanInRegion;
import edu.ucsc.gameAI.fsm.*;
import pacman.game.Game;

public class FSMTests {

	LinkedList<String> said = new LinkedList<String>();
	protected void SayOnce(String say)
	{
		if (!said.contains(say))
		{
			said.add(say);
			System.out.println(say);
		}
	}
	
	int ix1 = 0;
	int ix2 = 110;
	int iy1 = 0;
	int iy2 = 60;
	int iy3 = 120;
	State stateRun;
	State stateChase;
	Transition transScared;
	Collection<ITransition> listtscared ;
	Transition transCool ;
	Collection<ITransition> listtcool ;
	StateMachine fsm;
	State prevState;
	boolean bInit;
	
	
	public FSMTests()
	{
		stateRun = new State();
		stateRun.setAction(new GoDownAction());
		stateChase = new State();
		stateChase.setAction(new GoUpAction());
		transScared = new Transition();
		transScared.setCondition(new PacmanInRegion(ix1,iy1,ix2,iy2)); // top half of the screen
		transScared.setTargetState(stateRun);
		transScared.setAction(new GoLeftAction());
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
		prevState = stateChase;
		bInit = false;
	}
	
	public float test(Game game) {
		
		int tests = 0;
		int passed = 0;
		
		int ip = game.getPacmanCurrentNodeIndex();
		int pacx = game.getNodeXCood(ip);
		int pacy = game.getNodeYCood(ip);
		
		if (!bInit)
		{
			bInit = true;
			/*
			if (ix1 <= pacx && ix2 >= pacx &&
				iy1 <= pacy && iy2 >= pacy   )
				bWasInRegion1 = true;
			else
				bWasInRegion1 = false;
				*/
		}
		
		// FSM (up when pacman in the bottom half of screen, down otherwise)
		
		Collection<IAction> actions = fsm.update(game);
		
		++tests;
		if (actions.contains(null))//Evaluator.nullInCollection(actions))
			SayOnce("Error: Null found in FSM action list");
		else
		{
			++passed;
			
			
			// test state transition
			if (prevState == stateChase && fsm.getCurrentState() == stateRun)
			{
				++tests;
				if (actions.size() > 0)
				{
					++passed;
					++tests;
					if (actions.iterator().next().getClass() == GoLeftAction.class)
						++passed;
					else
						SayOnce("FSM transition action failed");
				}
				else
				{
					SayOnce("FSM transition action missing");
				}
			}
			else if (ix1 <= pacx && ix2 >= pacx &&
					 iy1 <= pacy && iy2 >= pacy &&
					 actions.size() > 0) // test result
			{
				//if (actions.size() == 0)
				//	SayOnce("FSM action result missing");
				boolean bFound = false;
				
				for (Iterator<IAction> iterator = actions.iterator(); iterator.hasNext();) 
				{ 
					IAction act = iterator.next();
					if (act.getClass() == GoDownAction.class)
						bFound = true;
				}
				
				++tests;
				if (bFound)
					++passed;
				else
					SayOnce("FSM action result failed");
				
				++tests;
				if (fsm.getCurrentState() == stateRun)
					++passed;
				else
					SayOnce("FSM state failed");
			}
		}
		
		prevState = (State)fsm.getCurrentState();
		
		if (tests == 0)
			tests = passed = 1;
		return ((float)passed/(float)tests);
	}
}
