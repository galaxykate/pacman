package pacman.entries.pacman;

import java.awt.Color;

import edu.ucsc.gameAI.ChaseGhost;
import edu.ucsc.gameAI.FleeGhosts;
import edu.ucsc.gameAI.GoAction;
import edu.ucsc.gameAI.GoNearestInnerPill;
import edu.ucsc.gameAI.GoNearestPill;
import edu.ucsc.gameAI.GoNearestPowerPill;
import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.ReduceThreat;
import edu.ucsc.gameAI.conditions.AverageEdibleTime;
import edu.ucsc.gameAI.conditions.CurrentLevelTime;
import edu.ucsc.gameAI.conditions.GhostDistanceToPacman;
import edu.ucsc.gameAI.conditions.PacmanThreat;
import edu.ucsc.gameAI.conditions.PowerPillDistanceToPacman;
import edu.ucsc.gameAI.conditions.ShortestPathIsSafe;
import edu.ucsc.gameAI.decisionTrees.binary.BinaryDecision;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;
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
	private float changePower = 0;

	public KatesPacMan() {

		// Build a threat map
		threatMap = new float[2000];
		for (int i = 0; i < threatMap.length; i++) {
			threatMap[i] = (float) Math.random();
		}

		BinaryDecision areEdible = new BinaryDecision();
		BinaryDecision edibleGhosts = new BinaryDecision();
		BinaryDecision pillMaxing = new BinaryDecision();
		BinaryDecision threatened = new BinaryDecision();
		BinaryDecision test = new BinaryDecision();

		// Build a tree
		areEdible.setCondition(new AverageEdibleTime(1, 1000));
		areEdible.setFalseBranch(pillMaxing);
		areEdible.setTrueBranch(edibleGhosts);

		// Can I reach a ghost before it goes back to normal?
		edibleGhosts.setCondition(new AverageEdibleTime(1, 200));
		edibleGhosts.setTrueBranch(new ChaseGhost());
		edibleGhosts.setFalseBranch(pillMaxing);

		// Interior cleared?
		pillMaxing.setCondition(new GhostDistanceToPacman(0, 200));
		pillMaxing.setTrueBranch(threatened);
		pillMaxing.setFalseBranch(new GoNearestPill());

		// Threatened: is there a powerpill nearby?
		threatened.setCondition(new ShortestPathIsSafe());
		threatened.setTrueBranch(new GoNearestPowerPill());
		threatened.setFalseBranch(new GoNearestPill());

		// Threatened: is there a powerpill nearby?
		test.setCondition(new PacmanThreat(threatMap, .3f, 1000));
		test.setTrueBranch(new GoNearestPill());
		test.setFalseBranch(new GoNearestInnerPill());
		// test.setTrueBranch(new ReduceThreat(threatMap));

		startNode = areEdible;
		startNode = test;

	}

	public void setPowerPillNodes(Game game) {
		powerPillNodes = game.getActivePillsIndices();

	}

	public void addToThreatMap(Game game, int node, MOVE direction,
			float threat, int steps) {

		if (steps > 0) {
			threatMap[node] += threat;

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
				if (nextNode >= 0) {
					addToThreatMap(game, nextNode, direction, threat * .98f,
							steps - 1);
				}
			}
		}
	}

	@Override
	public MOVE getMove(Game game, long timeDue) {

		// refill the change power
		changePower += 2;

		// Update the threat map
		// Add the ghost positions

		Maze maze = game.getCurrentMaze();
		// reset the map
		for (int i = 0; i < maze.graph.length; i++) {
			threatMap[i] *= 0f;
		}

		for (GHOST ghostType : GHOST.values()) {
			int ghostLocation = game.getGhostCurrentNodeIndex(ghostType);
			MOVE lastMove = game.getGhostLastMoveMade(ghostType);
			float threat = 2;
			float time = game.getGhostEdibleTime(ghostType);
			if (time > 0)
				threat *= -1;
			addToThreatMap(game, ghostLocation, lastMove, threat, 55);
		}

		// Show the threatMap
		for (int i = 0; i < game.getCurrentMaze().graph.length; i++) {
			float value = (threatMap[i]) % 1;
			// value = (i * .01f) % 1;
			Color color = Color.getHSBColor(value, 1, Math.abs(value));
			GameView.addPoints(game, color, i);

		}

		int pacNode = game.getPacmanCurrentNodeIndex();

		int x = game.getNodeXCood(pacNode);
		int y = game.getNodeYCood(pacNode);
		// System.out.println(x + " " + y);

		MOVE move = MOVE.NEUTRAL;

		// GameView.addLines(game, color, fromNnodeIndex, toNodeIndex);
		IAction terminal = startNode.makeDecision(game);
		if (terminal != null)
			move = terminal.getMove(game);

		MOVE last = game.getPacmanLastMoveMade();
		if (move != last) {
			if (changePower < 1) {
				// oops, can't change!
				move = last;
			} else {
				changePower = 0;
			}
		}
		// System.out.println(move);
		return move;

	}
}