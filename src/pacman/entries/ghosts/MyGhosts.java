package pacman.entries.ghosts;

import java.util.EnumMap;

import pacman.controllers.Controller;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getActions() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.ghosts.mypackage).
 */
public class MyGhosts extends Controller<EnumMap<GHOST,MOVE>>
{
	private EnumMap<GHOST, MOVE> myMoves=new EnumMap<GHOST, MOVE>(GHOST.class);
	private Controller<EnumMap<GHOST,MOVE>> ghostAI;
	
	public MyGhosts() {
		ghostAI = new AaronsGhosts();
	}
	
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue) {
		return ghostAI.getMove(game, timeDue);
	}
}