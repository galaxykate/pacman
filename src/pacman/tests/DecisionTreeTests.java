package pacman.tests;

import java.util.LinkedList;

import edu.ucsc.gameAI.*;
import edu.ucsc.gameAI.conditions.*;
import edu.ucsc.gameAI.decisionTrees.binary.BinaryDecision;
import pacman.game.Game;

public class DecisionTreeTests {

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
		
		// Decision Tree
		BinaryDecision root = new BinaryDecision();
		int ipac = game.getPacmanCurrentNodeIndex();
		int pacx = game.getNodeXCood(ipac);
		int pacy = game.getNodeYCood(ipac);
		root.setCondition(new PacmanInRegion(pacx-1,pacy-1,pacx+1,pacy+1));
		root.setTrueBranch(new GoUpAction());
		root.setFalseBranch(new GoDownAction());
		if (root.makeDecision(game).getClass() != GoUpAction.class)
		{
			SayOnce("Decision Tree failed");
			return 0.0f;
		}
		return 1.0f;

	}
}
