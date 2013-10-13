package edu.ucsc.gameAI.hfsm;

import java.util.Collection;
import edu.ucsc.gameAI.IAction;

public interface IHFSMBase {

	Collection<IAction> getAction();
	
	IResult update();
}
