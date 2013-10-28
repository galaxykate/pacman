package pacman.entries.pacman;

import java.awt.Color;

import edu.ucsc.gameAI.GoAction;
import edu.ucsc.gameAI.conditions.CurrentLevelTime;
import edu.ucsc.gameAI.decisionTrees.binary.BinaryDecision;
import pacman.controllers.Controller;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import pacman.game.GameView;
import pacman.game.internal.Maze;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getAction() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.pacman.mypackage).
 */
public class KatesPacMan extends Controller<MOVE> {
	private BinaryDecision startNode;
	private int[] powerPillNodes;
	private float[] threatMap;

	public KatesPacMan() {

		// Build a threat map
		threatMap = new float[2000];

		// Build a tree
		// Is 
	}

	public void setPowerPillNodes(Game game) {
		powerPillNodes = game.getActivePillsIndices();

	}

	public void addToThreatMap(Game game, int node, MOVE direction,
			float threat, int steps) {

		if (steps > 0) {
			threatMap[node] += threat;
			System.out.println(node + ": " + threatMap[node]);

			if (game.isJunction(node)) {
				// Get available directions
				MOVE[] moves = game.getPossibleMoves(node);
				for (MOVE move : moves) {
					int nextNode = game.getNeighbour(node, move);
					addToThreatMap(game, nextNode, move, threat / moves.length,
							steps - 1);
				}
			} else {
				int nextNode = game.getNeighbour(node, direction);
				System.out.println(node + " " + direction + " = " + nextNode);
				if (nextNode >= 0) {
					addToThreatMap(game, nextNode, direction, threat * .98f,
							steps - 1);
				}
			}
		}
	}

	@Override
	public MOVE getMove(Game game, long timeDue) {
		
		// Update the threat map
		// Add the ghost positions

		Maze maze = game.getCurrentMaze();
		// reset the map
		for (int i = 0; i < maze.graph.length; i++) {
			threatMap[i] *= .0f;
		}

		for (GHOST ghostType : GHOST.values()) {
			int ghostLocation = game.getGhostCurrentNodeIndex(ghostType);
			MOVE lastMove = game.getGhostLastMoveMade(ghostType);
			addToThreatMap(game, ghostLocation, lastMove, 1, 15);
		}

		// Show the threatMap
		for (int i = 0; i < game.getCurrentMaze().graph.length; i++) {
			float value = (threatMap[i]) % 1;
			// value = (i * .01f) % 1;
			Color color = Color.getHSBColor(value, 1, value);
			GameView.addPoints(game, color, i);

		}

		// GameView.addLines(game, color, fromNnodeIndex, toNodeIndex);
		return startNode.makeDecision(game).getMove(game);
	}
}