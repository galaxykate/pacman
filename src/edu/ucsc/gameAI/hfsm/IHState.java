package edu.ucsc.gameAI.hfsm;

import java.util.Collection;

import edu.ucsc.gameAI.fsm.IState;

public interface IHState extends IState {
	Collection<IHState> getStates();
}
