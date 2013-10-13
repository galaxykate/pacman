package edu.ucsc.gameAI.hfsm;

import java.util.Collection;

import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.fsm.ITransition;

public interface IResult {
	
	Collection<IAction> getActions();
	
	ITransition getTransition();
	
	int getLevel();
}
