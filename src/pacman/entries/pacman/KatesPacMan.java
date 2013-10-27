package pacman.entries.pacman;

import edu.ucsc.gameAI.GoAction;
import edu.ucsc.gameAI.conditions.CurrentLevelTime;
import edu.ucsc.gameAI.decisionTrees.binary.BinaryDecision;
import pacman.controllers.Controller;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import pacman.game.GameView;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getAction() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.pacman.mypackage).
 */
public class KatesPacMan extends Controller<MOVE> {
	private BinaryDecision startNode;
	private int[] powerPillNodes;

	public KatesPacMan() {

		// Build a tree
		startNode = new BinaryDecision();

		// Is there a very close ghost? Then move away
		

		startNode.setCondition(new CurrentLevelTime(100, 200));
		startNode.setTrueBranch(new GoAction(MOVE.LEFT));
		startNode.setFalseBranch(new GoAction(MOVE.RIGHT));
	}

	public void setPowerPillNodes(Game game) {
		powerPillNodes = game.getActivePillsIndices();

	}

	@Override
	public MOVE getMove(Game game, long timeDue) {
		//GameView.addLines(game, color, fromNnodeIndex, toNodeIndex);
		return startNode.makeDecision(game).getMove(game);
	}
}