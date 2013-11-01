package pacman.entries.ghosts;

import java.util.EnumMap;

import pacman.controllers.Controller;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.conditions.AnyNonEdibleGhosts;
import edu.ucsc.gameAI.conditions.GhostEdibleTooClumpedTogether;
import edu.ucsc.gameAI.conditions.IsEdible;
import edu.ucsc.gameAI.conditions.IsPacmanAboutToPowerPillMe;
import edu.ucsc.gameAI.decisionTrees.binary.*;
import edu.ucsc.gameAI.*;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getActions() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.ghosts.mypackage).
 */
public class AaronsGhosts extends Controller<EnumMap<GHOST,MOVE>>
{
	private EnumMap<GHOST, MOVE> myMoves=new EnumMap<GHOST, MOVE>(GHOST.class);
	
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue)
	{
		/*
		 * The ghost behavior is fairly straightforward. The core is a basic
		 * gang up on PacMan approach, with all the ghosts seeking PacMan's
		 * current position. The smart parts are all designed to prevent PacMan
		 * from eating ghosts, since this is where he gets most of his
		 * points. The ghosts will start running away from PacMan when he
		 * gets near a pill, avoid clumping up while edible to prevent a
		 * Pac-buffet, and try to lure PacMan towards non-edible ghosts.
		 */
		
		
		myMoves.clear();

		for(GHOST ghost : GHOST.values())	//for each ghost
		{
			BinaryDecision binaryTree = new BinaryDecision();
			binaryTree.setCondition(new IsEdible(ghost));

			BinaryDecision closeToPill = new BinaryDecision();
			binaryTree.setFalseBranch(closeToPill);
			
			closeToPill.setCondition(new IsPacmanAboutToPowerPillMe(ghost));
			closeToPill.setTrueBranch(new FleePacman(ghost));
			closeToPill.setFalseBranch(new ChasePacman(ghost));
	
			BinaryDecision canLure = new BinaryDecision();

			canLure.setCondition(new AnyNonEdibleGhosts());
			canLure.setTrueBranch(new LurePacmanToGhost(ghost));
			canLure.setFalseBranch(new FleePacman(ghost));
			
			binaryTree.setTrueBranch(canLure);
//			BinaryDecision deadlyBuffet = new BinaryDecision();
//			binaryTree.setTrueBranch(deadlyBuffet);
//			deadlyBuffet.setCondition(new GhostEdibleTooClumpedTogether(ghost));
//			deadlyBuffet.setTrueBranch(new ScatterEdible(ghost));
//			deadlyBuffet.setFalseBranch(canLure);
			
			if(game.doesGhostRequireAction(ghost))		//if ghost requires an action
			{
				myMoves.put(ghost, binaryTree.makeDecision(game).getMove(game));
			}
		}
		
		
		return myMoves;
	}
}