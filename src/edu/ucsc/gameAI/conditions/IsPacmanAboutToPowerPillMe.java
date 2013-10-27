package edu.ucsc.gameAI.conditions;

import java.awt.Color;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import pacman.game.GameView;
import edu.ucsc.gameAI.ICondition;

public class IsPacmanAboutToPowerPillMe implements ICondition {

	private GHOST ghost;
	public IsPacmanAboutToPowerPillMe(GHOST ghost) {
		this.ghost = ghost;
	}

	// Returns true if Pacman is near a power pill and ghost is near Pacman
	@Override
	public boolean test(Game game) {
		if (
				(new PacmanInRegion(4, 4, 30, 30).test(game) && 
				new PowerPillInRegion(4, 4, 30, 30).test(game) &&
				new GhostInRegion(4, 4, 30, 30).test(game))
				||
				
				// Curiously, if we don't do this behavior on the upper right
				// corner, the score improves by about 80 points. Maybe something
				// to do with StarterPacMan always going left first, so this is
				// likely to be the last corner she gets to when she's most
				// desperate?
				
//				(new PacmanInRegion(74, 4, 104, 30).test(game) && 
//				new PowerPillInRegion(74, 4, 104, 30).test(game) &&
//						new GhostInRegion(74, 4, 104, 30).test(game))
//				||
				(new PacmanInRegion(4, 86, 30, 116).test(game) && 
				new PowerPillInRegion(4, 86, 30, 116).test(game) &&		
						new GhostInRegion(4, 86, 30, 116).test(game))
				||
				(new PacmanInRegion(74, 86, 104, 116).test(game) && 
				new PowerPillInRegion(74, 86, 104, 116).test(game) &&		
						new GhostInRegion(74, 86, 104, 116).test(game))
			) {
				int myPosition = game.getGhostCurrentNodeIndex(ghost);
				int pacmanPosition = game.getPacmanCurrentNodeIndex();
				GameView.addLines(game, Color.RED, pacmanPosition, myPosition);
				return true;
		}
		
		return false;
	}

}
