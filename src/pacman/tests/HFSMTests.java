package pacman.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import edu.ucsc.gameAI.*;
import edu.ucsc.gameAI.conditions.*;
import edu.ucsc.gameAI.hfsm.*;
import pacman.game.Game;
import pacman.game.Constants.MOVE;

public class HFSMTests {

	LinkedList<String> said = new LinkedList<String>();
	protected void SayOnce(String say)
	{
		if (!said.contains(say))
		{
			said.add(say);
			System.out.println(say);
		}
	}
	
	boolean bLeftState; // for hfsm test
	IHFSM hfsm ;
	IHFSM upIsUp ;
	IHFSM upIsDown ;
	Collection<IHState> statesLR;
	Collection<IHState> statesUpIsUp;		
	Collection<IHState> statesUpIsDown;
	IHState neutralUU;
	IHState upUU;
	IHState downUU;
	IHState neutralUD;
	IHState upUD;
	IHState downUD;
	IHTransition leftLR;
	IHTransition rightLR;
	IHTransition neutralUpUU;
	IHTransition neutralDownUU;
	IHTransition downUpUU;
	IHTransition upDownUU;
	IHTransition neutralUpUD;
	IHTransition neutralDownUD;
	IHTransition upUpUD;
	IHTransition downDownUD;
	IHTransition EdibleUU;
	
	//HState prevState;
	
	public HFSMTests()
	{
		
		//create hfsm
		hfsm = new HFSM();
		upIsUp = new HFSM();
		upIsDown = new HFSM();
		
		statesLR = new ArrayList<IHState>();
		statesUpIsUp = new ArrayList<IHState>();		
		statesUpIsDown = new ArrayList<IHState>();
		
		
		//create states and add actions
		neutralUU = new HState("neutralUU");
		neutralUU.setAction(new NeutralAction());
		upUU = new HState("upUU");
		upUU.setAction(new GoUpAction());
		downUU = new HState("downUU");
		downUU.setAction(new GoDownAction());
		
		neutralUD = new HState("neutralUD");
		neutralUD.setAction(new NeutralAction());
		upUD = new HState("upUD");
		upUD.setAction(new GoUpAction());
		downUD = new HState("downUD");
		downUD.setAction(new GoDownAction());
		
		
		//create and add transitions to HFMS and states.
		leftLR = new HTransition(upIsUp, new PacmanLastMove(MOVE.LEFT));
		rightLR = new HTransition(upIsDown, new PacmanLastMove(MOVE.RIGHT));
		upIsUp.addTransition(rightLR);
		upIsDown.addTransition(leftLR);
		
		neutralUpUU = new HTransition(upUU, new PacmanLastMove(MOVE.UP));
		neutralDownUU = new HTransition(downUU, new PacmanLastMove(MOVE.DOWN));
		neutralUU.addTransition(neutralUpUU);
		neutralUU.addTransition(neutralDownUU);
		
		upDownUU = new HTransition(downUU, new PacmanLastMove(MOVE.DOWN));
		upUU.addTransition(upDownUU);
		downUpUU = new HTransition(upUU, new PacmanLastMove(MOVE.UP));
		downUU.addTransition(downUpUU);
		
		neutralUpUD = new HTransition(downUD, new PacmanLastMove(MOVE.UP));
		neutralDownUD = new HTransition(upUD, new PacmanLastMove(MOVE.DOWN));
		neutralUD.addTransition(neutralUpUD);
		neutralUD.addTransition(neutralDownUD);
		
		upUpUD = new HTransition(downUD, new PacmanLastMove(MOVE.UP));
		upUD.addTransition(upUpUD);
		downDownUD = new HTransition(upUD, new PacmanLastMove(MOVE.DOWN));
		downUD.addTransition(downDownUD);
		
		//add the states to the FSM.
		statesLR.add(upIsUp);
		statesLR.add(upIsDown);
		hfsm.setStates(statesLR);
		hfsm.setInitialState(upIsUp); // left state
		//prevState = (HState)upIsUp;
		
		statesUpIsUp.add(neutralUU);
		statesUpIsUp.add(upUU);
		statesUpIsUp.add(downUU);
		upIsUp.setStates(statesUpIsUp);
		upIsUp.setInitialState(neutralUU);

		statesUpIsDown.add(neutralUD);
		statesUpIsDown.add(upUD);
		statesUpIsDown.add(downUD);
		upIsDown.setStates(statesUpIsDown);
		upIsDown.setInitialState(neutralUD);
		
		// for testing hfsm
		bLeftState = true;
		
	}
	public float test(Game game){
		int tests = 0;
		int passed = 0;
		
		// hfsm
		Collection<IAction> actions;
		
		boolean bCheck = true;
		actions = hfsm.update(game).getActions();
		
		++tests;
		if (actions.contains(null))
		{
			SayOnce("Error: Null found in HFSM action list.");
			bCheck = false;
		}
		else
			++passed;
		
		if (game.getPacmanLastMoveMade() == MOVE.LEFT && !bLeftState)
		{
			bLeftState = true;
			bCheck = false;
		}
		if (game.getPacmanLastMoveMade() == MOVE.RIGHT && bLeftState)
		{
			bLeftState = false;
			bCheck = false;
		}
		if (actions.size() < 1)
			bCheck = false;
		
		if (bCheck)
		{
			IAction act = actions.iterator().next();
			MOVE mv = act.getMove(game);
			if (game.getPacmanLastMoveMade() == MOVE.UP)
			{
				++tests;
				if (bLeftState)
				{
					if (mv == MOVE.UP)
						++passed;
					else
						SayOnce("HFSM state fail 1");
				}	
				else
				{
					if (mv == MOVE.DOWN)
						++passed;
					else
						SayOnce("HFSM state fail 2");
				}
			}
			else if (game.getPacmanLastMoveMade() == MOVE.DOWN)
			{
				++tests;
				if (bLeftState)
				{
					if (mv == MOVE.DOWN)
						++passed;
					else
						SayOnce("HFSM state fail 3");
				}	
				else
				{
					if (mv == MOVE.UP)
						++passed;
					else
						SayOnce("HFSM state fail 4");
				}
			}
		}
		if (tests == 0)
			tests = passed = 1;
		return ((float)passed/(float)tests);
	}
}
