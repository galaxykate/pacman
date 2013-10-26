package pacman.entries.ghosts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;

import edu.ucsc.gameAI.GoDownAction;
import edu.ucsc.gameAI.GoUpAction;
import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.NeutralAction;
import edu.ucsc.gameAI.conditions.PacmanLastMove;
import edu.ucsc.gameAI.hfsm.HFSM;
import edu.ucsc.gameAI.hfsm.HState;
import edu.ucsc.gameAI.hfsm.HTransition;
import edu.ucsc.gameAI.hfsm.IHFSM;
import edu.ucsc.gameAI.hfsm.IHState;
import edu.ucsc.gameAI.hfsm.IHTransition;
import pacman.controllers.Controller;
import pacman.game.Game;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;

public class HFSMGhost extends Controller<EnumMap<GHOST,MOVE>> {
	
	private EnumMap<GHOST, MOVE> myMoves=new EnumMap<GHOST, MOVE>(GHOST.class);

	IHFSM leftRightHFSM;
	
	public HFSMGhost()
	{	
		leftRightHFSM = createHFSM();
	}
	
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue)
	{
		myMoves.clear();

		MOVE theMove;
		
		Collection<IAction> actions = leftRightHFSM.update(game).getActions();
		
		
		
		if(!actions.isEmpty()) {
			IAction[] actionArray = (IAction[])actions.toArray();
			theMove = actionArray[0].getMove();
		}else{
			theMove = MOVE.NEUTRAL;
		}
		
		for(GHOST ghost : GHOST.values())	//for each ghost
		{
			if(game.doesGhostRequireAction(ghost))		//if ghost requires an action
			{
				myMoves.put(ghost, theMove);
			}
		}
		
		return myMoves;
		
	}
	
	private IHFSM createHFSM() {
		IHFSM hfsm = new HFSM("LR");
		IHFSM upIsUp = new HFSM("UU");
		IHFSM upIsDown = new HFSM("UD");
		
		Collection<IHState> statesLR = new ArrayList<IHState>();
		Collection<IHState> statesUpIsUp = new ArrayList<IHState>();		
		Collection<IHState> statesUpIsDown = new ArrayList<IHState>();
		
		
		//create states and add actions
		IHState neutralUU = new HState("neutralUU");
		neutralUU.setAction(new NeutralAction());
		IHState upUU = new HState("upUU");
		upUU.setAction(new GoUpAction());
		IHState downUU = new HState("downUU");
		downUU.setAction(new GoDownAction());
		
		IHState neutralUD = new HState("neutralUD");
		neutralUD.setAction(new NeutralAction());
		IHState upUD = new HState("upUD");
		upUD.setAction(new GoUpAction());
		IHState downUD = new HState("downUD");
		downUD.setAction(new GoDownAction());

		
		//create and add transitions to HFMS and states.
		IHTransition leftLR = new HTransition(upIsUp, new PacmanLastMove(MOVE.LEFT));
		IHTransition rightLR = new HTransition(upIsDown, new PacmanLastMove(MOVE.RIGHT));
		upIsUp.addTransition(rightLR);
		upIsDown.addTransition(leftLR);
		
		
		IHTransition neutralUpUU = new HTransition(upUU, new PacmanLastMove(MOVE.UP));
		IHTransition neutralDownUU = new HTransition(downUU, new PacmanLastMove(MOVE.DOWN));
		neutralUU.addTransition(neutralUpUU);
		neutralUU.addTransition(neutralDownUU);
		
		
		IHTransition upDownUU = new HTransition(downUU, new PacmanLastMove(MOVE.DOWN));
		upUU.addTransition(upDownUU);
		IHTransition downUpUU = new HTransition(upUU, new PacmanLastMove(MOVE.UP));
		downUU.addTransition(downUpUU);
		
		IHTransition neutralUpUD = new HTransition(downUD, new PacmanLastMove(MOVE.UP));
		IHTransition neutralDownUD = new HTransition(upUD, new PacmanLastMove(MOVE.DOWN));
		neutralUD.addTransition(neutralUpUD);
		neutralUD.addTransition(neutralDownUD);
		
		IHTransition upUpUD = new HTransition(downUD, new PacmanLastMove(MOVE.UP));
		upUD.addTransition(upUpUD);
		IHTransition downDownUD = new HTransition(upUD, new PacmanLastMove(MOVE.DOWN));
		downUD.addTransition(downDownUD);
		
		//add the states to the FSM.
		statesLR.add(upIsUp);
		statesLR.add(upIsDown);
		hfsm.setStates(statesLR);
		hfsm.setInitialState(upIsUp);
		
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
		
		return hfsm;
	}
}
