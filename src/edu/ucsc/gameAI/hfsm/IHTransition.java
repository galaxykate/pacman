package edu.ucsc.gameAI.hfsm;

import edu.ucsc.gameAI.fsm.ITransition;

public interface IHTransition extends ITransition {
	int getLevel();
}
