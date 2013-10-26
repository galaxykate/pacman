package pacman.tests;

import java.util.LinkedList;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.*;
import edu.ucsc.gameAI.conditions.*;

public class ActionsAndConditionsTests {

	LinkedList<String> said = new LinkedList<String>();
	protected void SayOnce(String say)
	{
		if (!said.contains(say))
		{
			said.add(say);
			System.out.println(say);
		}
	}
	
	public float test(Game game) {
		
		int tests = 0;
		int passed = 0;
		boolean bFailed = false;
		
		// Game State Condition Tests
		++tests;
		if (new MazeIndex(game.getMazeIndex()).test(game))
			++passed;//System.out.println("MazeIndex passed");
		else
			SayOnce("MazeIndex failed");
		
		++tests;
		if (new LevelCount(game.getCurrentLevel()).test(game))
			++passed;//SayOnce("LevelCount passed");
		else
			SayOnce("LevelCount failed");
		++tests;
		if (new CurrentLevelTime(game.getCurrentLevelTime()-1,game.getCurrentLevelTime()+1).test(game))
			++passed;//SayOnce("CurrentLevelTime passed");
		else
			SayOnce("CurrentLevelTime failed");
		
		++tests;
		if (new TotalTime(game.getTotalTime()-1,game.getTotalTime()+1).test(game))
			++passed;//SayOnce("CurrentLevelTime passed");
		else
			SayOnce("TotalTime failed");
		++tests;
		if (new Score(game.getScore()-1,game.getScore()+1).test(game))
			++passed;//SayOnce("CurrentLevelTime passed");
		else
			SayOnce("Score failed");		
		++tests;
		if (new GhostEatScore(game.getGhostCurrentEdibleScore()-1,game.getGhostCurrentEdibleScore()+1).test(game))
			++passed;//SayOnce("CurrentLevelTime passed");
		else
			SayOnce("GhostEatScore failed");
		++tests;
		if (new TimeOfLastGlobalReversal(game.getTimeOfLastGlobalReversal()-1,game.getTimeOfLastGlobalReversal()+1).test(game))
			++passed;//SayOnce("CurrentLevelTime passed");
		else
			SayOnce("TimeOfLastGlobalReversal failed");
		++tests;
		if ((new PacmanWasEaten().test(game) == game.wasPacManEaten()))
			++passed;//SayOnce("CurrentLevelTime passed");
		else
			SayOnce("PacmanWasEaten failed");
		++tests;
		if ((new PillWasEaten().test(game) == game.wasPillEaten()))
			++passed;//SayOnce("CurrentLevelTime passed");
		else
			SayOnce("PillWasEaten failed");
		++tests;
		if ((new PowerPillWasEaten().test(game) == game.wasPowerPillEaten()))
			++passed;//SayOnce("CurrentLevelTime passed");
		else
			SayOnce("PowerPillWasEaten failed");
		
		++tests; bFailed = false;
		for (int pill : game.getPillIndices())
			if (!(new IsPillStillAvailable(pill).test(game) == game.isPillStillAvailable(pill)))
			{
				SayOnce("IsPillStillAvailable failed");
				bFailed = true;
			}
		if (!bFailed) ++passed;
		
		++tests; bFailed = false;
		for (int pill : game.getPillIndices())
			if (!(new IsPowerPillStillAvailable(pill).test(game) == game.isPowerPillStillAvailable(pill)))
			{
				SayOnce("IsPowerPillStillAvailable failed");
				bFailed = true;
			}
		if (!bFailed) ++passed;
		
		++tests; bFailed = false;
		for (GHOST ghost : GHOST.values())
			if (!(new GhostEaten(ghost).test(game) == game.wasGhostEaten(ghost)))
			//if (!(new GhostEaten(ghost).test(game) == game.isGhostEdible(ghost)))
			{
				SayOnce("GhostEaten failed");
				bFailed = true;
			}
		if (!bFailed) ++passed;
		
		// Ghost State Conditions
		for (GHOST ghost : GHOST.values())
		{
			++tests;
			if ((new CurrentGhostNodeIndex(ghost, game.getGhostCurrentNodeIndex(ghost))).test(game))
				++passed;//SayOnce("CurrentLevelTime passed");
			else
				SayOnce("CurrentGhostNodeIndex failed");
			++tests;
			if ((new EdibleTime(ghost, game.getGhostEdibleTime(ghost)-1,game.getGhostEdibleTime(ghost)+1)).test(game))
				++passed;//SayOnce("CurrentLevelTime passed");
			else
				SayOnce("EdibleTime failed");
			++tests;
			if ((new LairTime(ghost, game.getGhostLairTime(ghost)-1,game.getGhostLairTime(ghost)+1)).test(game))
				++passed;//SayOnce("CurrentLevelTime passed");
			else
				SayOnce("GhostEaten failed");
			++tests;
			if ((new GhostLastMove(ghost, game.getGhostLastMoveMade(ghost))).test(game))
				++passed;//SayOnce("CurrentLevelTime passed");
			else
				SayOnce("GhostLastMove failed");
		}
		
		// Ms Pac ManState Conditions
		++tests;
		if ((new CurrentPacmanNodeIndex(game.getPacmanCurrentNodeIndex())).test(game))
			++passed;//SayOnce("CurrentLevelTime passed");
		else
			SayOnce("CurrentPacmanNodeIndex failed");
		++tests;
		if ((new NumberOfLivesRemaining(game.getPacmanNumberOfLivesRemaining(),game.getPacmanNumberOfLivesRemaining()).test(game)))
			++passed;//SayOnce("CurrentLevelTime passed");
		else
			SayOnce("NumberOfLivesRemaining failed");
		++tests;
		if ((new PacmanLastMove(game.getPacmanLastMoveMade()).test(game)))
			++passed;//SayOnce("CurrentLevelTime passed");
		else
			SayOnce("PacmanLastMove failed");
	
		// Inference Conditions
		int px;
		int py;
		++tests; bFailed = false;
		for (int pill : game.getActivePillsIndices())
		{
			px = game.getNodeXCood(pill);
			py = game.getNodeYCood(pill);
			if (!(new PillInRegion(px-1,py-1,px+1,py+1).test(game)))
			{
				SayOnce("PillInRegion failed");
				bFailed = true;
			}
		}
		if (!bFailed) ++passed;
		
		++tests; bFailed = false;
		for (int ppill : game.getActivePowerPillsIndices())
		{
			px = game.getNodeXCood(ppill);
			py = game.getNodeYCood(ppill);
			if (!(new PowerPillInRegion(px-1,py-1,px+1,py+1).test(game)))
			{
				SayOnce("PowerPillInRegion failed");
				bFailed = true;
			}
		}
		if (!bFailed) ++passed;
		
		++tests; bFailed = false;
		for (GHOST ghost : GHOST.values())
		{
			int ig = game.getGhostCurrentNodeIndex(ghost);
			int gx = game.getNodeXCood(ig);
			int gy = game.getNodeYCood(ig);
			if (!(new GhostInRegion(gx-1,gy-1,gx+1,gy+1).test(game)))
			{
				SayOnce("GhostInRegion failed");
				bFailed = true;
			}
		}
		if (!bFailed) ++passed;
		
		++tests;
		int ipac = game.getPacmanCurrentNodeIndex();
		int pacx = game.getNodeXCood(ipac);
		int pacy = game.getNodeYCood(ipac);
		if ((new PacmanInRegion(pacx-1,pacy-1,pacx+1,pacy+1).test(game)))
			++passed;//SayOnce("CurrentLevelTime passed");
		else
			SayOnce("PacmanLastMove failed");
		
		// actions
		++tests;
		if ((new GoUpAction().getClass() == GoUpAction.class))
			++passed;//SayOnce("CurrentLevelTime passed");
		else
			SayOnce("GoUpAction failed");
		if (!(new GoLeftAction().getClass() == GoLeftAction.class))
			SayOnce("GoLeftAction failed");
		if (!(new GoRightAction().getClass() == GoRightAction.class))
			SayOnce("GoRightAction failed");
		if (!(new GoDownAction().getClass() == GoDownAction.class))
			SayOnce("GoDownAction failed");
	
		if (tests == 0)
			tests = passed = 1;
		return ((float)passed/(float)tests);
	}
}
