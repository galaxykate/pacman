package edu.ucsc.gameAI;

import java.util.ArrayList;

import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class GoNearestPill implements IAction, IBinaryNode {

	public void doAction() {
	}
	
	@Override
	public IAction makeDecision(Game game) {
		return this;
	}

	private int _findNearestPill(Game game, int current, boolean includeNormalPills, boolean includePowerPills) {
		
		ArrayList<Integer> targets=new ArrayList<Integer>();
		
		if (includeNormalPills) {
			int[] pills=game.getPillIndices();
			for(int i=0;i<pills.length;i++)					//check which pills are available			
				if(game.isPillStillAvailable(i))
					targets.add(pills[i]);
		}
		
		if (includePowerPills) {
			int[] powerPills=game.getPowerPillIndices();
			for(int i=0;i<powerPills.length;i++)			//check with power pills are available
				if(game.isPowerPillStillAvailable(i))
					targets.add(powerPills[i]);				
		}
			
		int[] targetsArray=new int[targets.size()];		//convert from ArrayList to array
		
		for(int i=0;i<targetsArray.length;i++)
			targetsArray[i]=targets.get(i);
		
		//return the next direction once the closest target has been identified
		return game.getClosestNodeIndexFromNodeIndex(current, targetsArray, DM.PATH);
	}
	
	private int findNearestPowerPill(Game game, int current) {
		return _findNearestPill(game, current, false, true);
	}
	private int findNearestAnyPill(Game game, int current) {
		return _findNearestPill(game, current, true, true);
	}
	private int findNearestNormalPill(Game game, int current) {
		return _findNearestPill(game, current, true, false);
	}
	
	@Override
	public MOVE getMove(Game game) {
		
		int pacmanPosition = game.getPacmanCurrentNodeIndex();
		int nearestPillPosition = findNearestAnyPill(game, pacmanPosition);
		
		return game.getNextMoveTowardsTarget(pacmanPosition, nearestPillPosition, DM.PATH);
	}
	
}
