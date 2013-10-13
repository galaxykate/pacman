package pacman.entries.pacman;

import edu.ucsc.gameAI.GoAction;
import edu.ucsc.gameAI.decisionTrees.binary.BinaryDecision;
import pacman.controllers.Controller;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getAction() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.pacman.mypackage).
 */
public class KatesPacMan extends SettableController {
	private BinaryDecision startNode;

	public KatesPacMan() {

		// Build a tree
		startNode = new BinaryDecision();
		startNode.setTrueBranch(new GoAction(this, MOVE.LEFT));

		// usage: this will set the current best move to GO LEFT
		GoAction action = new GoAction(this, MOVE.LEFT);
		action.doAction();
		
	}

}