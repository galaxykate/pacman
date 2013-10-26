package pacman;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import pacman.controllers.Controller;
import pacman.controllers.HumanController;
import pacman.controllers.KeyBoardInput;
import pacman.controllers.examples.AggressiveGhosts;
import pacman.controllers.examples.Legacy;
import pacman.controllers.examples.Legacy2TheReckoning;
import pacman.controllers.examples.NearestPillPacMan;
import pacman.controllers.examples.NearestPillPacManVS;
import pacman.controllers.examples.RandomGhosts;
import pacman.controllers.examples.RandomNonRevPacMan;
import pacman.controllers.examples.RandomPacMan;
import pacman.controllers.examples.StarterGhosts;
import pacman.controllers.examples.StarterPacMan;
import pacman.entries.ghosts.*;
import pacman.game.Game;
import pacman.game.GameView;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.tests.*;
import static pacman.game.Constants.*;
/*
import edu.ucsc.gameAI.*;
import edu.ucsc.gameAI.conditions.*;
import edu.ucsc.gameAI.decisionTrees.binary.BinaryDecision;
import edu.ucsc.gameAI.fsm.*;
import edu.ucsc.gameAI.hfsm.*;
*/

/**
 * This class may be used to execute the game in timed or un-timed modes, with or without
 * visuals. Competitors should implement their controllers in game.entries.ghosts and 
 * game.entries.pacman respectively. The skeleton classes are already provided. The package
 * structure should not be changed (although you may create sub-packages in these packages).
 */
@SuppressWarnings("unused")
public class Evaluator
{	
	ActionsAndConditionsTests actTest;
	DecisionTreeTests decTest;
	FSMTests fsmTest;
	HFSMTests hfsmTest;
	LinkedList<Float> actResults;
	LinkedList<Float> decResults;
	LinkedList<Float> fsmResults;
	LinkedList<Float> hfsmResults;
	boolean testActionsAndConditions;
	boolean testDecisionTrees;
	boolean testFSMs;
	boolean testHFSMs;
	
	public Evaluator()
	{
		actResults = new LinkedList<Float>();
		decResults = new LinkedList<Float>();
		fsmResults = new LinkedList<Float>();
		hfsmResults = new LinkedList<Float>();
	}
	public void printScores()
	{
		Iterator<Float> iter;
		float avg=0;
		
		System.out.print("Actions and Conditions: ");
		if (!testActionsAndConditions)
			System.out.println("Test Disabled.");
		else if (actResults.size() < 1)
			System.out.println("Required classes are not implemented. Tests not run.");			
		else
		{
			avg=0;
			iter = actResults.iterator();
			while (iter.hasNext())
				avg+=iter.next();
			
			System.out.println(100*avg/actResults.size()+"% of points.");
		}
		System.out.print("Decision Trees: ");
		if (!testDecisionTrees)
			System.out.println("Test Disabled.");
		else if (decResults.size() < 1)
			System.out.println("Required classes are not implemented. Tests not run.");			
		else
		{
			avg=0;
			iter = decResults.iterator();
			while (iter.hasNext())
				avg+=iter.next();
			
			System.out.println(100*avg/decResults.size() + "% of points.");
		}
		System.out.print("FSM: ");
		if (!testFSMs)
			System.out.println("Test Disabled.");
		else if (fsmResults.size() < 1)
			System.out.println("Required classes are not implemented. Tests not run.");			
		else
		{
			avg=0;
			iter = fsmResults.iterator();
			while (iter.hasNext())
				avg+=iter.next();
			
			System.out.println(100*avg/fsmResults.size()+"% of points.");
		}
		System.out.print("HFSM: ");
		if (!testHFSMs)
			System.out.println("Test Disabled.");
		else if (hfsmResults.size() < 1)
			System.out.println("HFSM classes are not implemented. Tests not run.");			
		else
		{
			avg=0;
			iter = hfsmResults.iterator();
			while (iter.hasNext())
				avg+=iter.next();
			
			System.out.println(100*avg/hfsmResults.size()+"% of points.");
		}
		
		
	}
	
    // could be a function run every frame of runExperiment, accepting game as a parameter
    public void runUnitTests(Game game, Controller<MOVE> pacManController,Controller<EnumMap<GHOST,MOVE>> ghostController, boolean testActionsAndConditions, boolean testDecisionTrees, boolean testFSMs, boolean testHFSMs)
    {
    	this.testActionsAndConditions = testActionsAndConditions; 
    	this.testDecisionTrees = testDecisionTrees; 
    	this.testFSMs = testFSMs; 
    	this.testHFSMs = testHFSMs;
    	String packageBase = "edu.ucsc.gameAI.";
    	String conditionBase = packageBase + "conditions.";
    	String decisionTreeBase = packageBase + "decisionTrees.binary.";
    	String fsmBase = packageBase + "fsm.";
    	String hfsmBase = packageBase + "hfsm.";
    
    	boolean classesExist;
    	
    	if(testActionsAndConditions) {
    		classesExist = true;
    		
    		//actions
    		if (!testForClass(packageBase + "GoUpAction"))
    			classesExist = false;
    		if (!testForClass(packageBase + "GoDownAction"))
    			classesExist = false;
    		
    		//general game state conditions
    		if (!testForClass(conditionBase + "MazeIndex"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "LevelCount"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "CurrentLevelTime"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "TotalTime"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "Score"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "GhostEatScore"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "TimeOfLastGlobalReversal"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "PacmanWasEaten"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "PillWasEaten"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "PowerPillWasEaten"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "IsPillStillAvailable"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "IsPowerPillStillAvailable"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "GhostEaten"))
    			classesExist = false;
    		
    		//ghost state conditions
    		if (!testForClass(conditionBase + "CurrentGhostNodeIndex"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "EdibleTime"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "LairTime"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "GhostLastMove"))
    			classesExist = false;
    		
    		//ms pacman conditions
    		if (!testForClass(conditionBase + "CurrentPacmanNodeIndex"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "NumberOfLivesRemaining"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "PacmanLastMove"))
    			classesExist = false;
    		
    		//conditions with inference
    		if (!testForClass(conditionBase + "PillInRegion"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "PowerPillInRegion"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "GhostInRegion"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "PacmanInRegion"))
    			classesExist = false;
    		
    		if(!classesExist) {
    			;//System.err.println("Required classes are not implemented. Not running action and condition tests.");
    		}else{
    			if (actTest == null)
    				actTest = new ActionsAndConditionsTests();
    			
    			actResults.add(actTest.test(game));
    		}
    	}
    	
    	if(testDecisionTrees) {
    		classesExist = true;
    		if (!testForClass(decisionTreeBase + "BinaryDecision"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "PacmanInRegion"))
    			classesExist = false;
    		if(!classesExist) {
    			;//System.err.println("Required classes are not implemented. Not running decision tree tests.");
    		}else{
    			if (decTest == null)
    				decTest = new DecisionTreeTests();
    			
    			decResults.add(decTest.test(game));
    		}
    	}
 
    	if(testFSMs) {
    		classesExist = true;
    		if (!testForClass(fsmBase + "State"))
    			classesExist = false;
    		if (!testForClass(fsmBase + "StateMachine"))
    			classesExist = false;
    		if (!testForClass(fsmBase + "Transition"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "PacmanInRegion"))
    			classesExist = false;
    		if(!classesExist) {
    			;//System.err.println("Required classes are not implemented. Not running FSM tests.");
    		}else{
    			if (fsmTest == null)
    				fsmTest = new FSMTests();
    			
    			fsmResults.add(fsmTest.test(game));
    		}
    	}
    	
    	if(testHFSMs) {
    		classesExist = true;
    		if (!testForClass(hfsmBase + "HFSM"))
    			classesExist = false;
    		if (!testForClass(hfsmBase + "HFSMBase"))
    			classesExist = false;
    		if (!testForClass(hfsmBase + "HState"))
    			classesExist = false;
    		if (!testForClass(hfsmBase + "HTransition"))
    			classesExist = false;
    		if (!testForClass(hfsmBase + "Result"))
    			classesExist = false;
    		if (!testForClass(conditionBase + "PacmanLastMove"))
    			classesExist = false;
    		
    		if(!classesExist) {
    			;//System.err.println("Required classes are not implemented. Not running HFSM tests.");
    		}else{
    			if (hfsmTest == null)
    				hfsmTest = new HFSMTests();
    			
    			hfsmResults.add(hfsmTest.test(game));
    		}
    	}
		
    }
    
    private boolean testForClass(String className) {
    	try{
    		Class.forName(className);
    		//Class.forName(className, false, null);
    	}catch(ClassNotFoundException e){
    		;//System.err.println("Required class for project 1 not implemented: " + className);
    		return false;
    	}
    	return true;
    }
	
}