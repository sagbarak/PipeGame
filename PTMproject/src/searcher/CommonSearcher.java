package searcher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;


public abstract class CommonSearcher<T> implements Searcher{

	protected PriorityQueue<State<T>> openList;
	protected HashSet<State<T>> closedSet=new HashSet<State<T>>();
	private int evaluatedNodes;

	/*-----------------------Constructors------------------*/
	public CommonSearcher() {
		openList=new PriorityQueue<State<T>>(1,new CostComparator<T>());
		evaluatedNodes = 0;
	}
	/*----------------------------------------------------*/

	protected State<T> popOpenList() {
		evaluatedNodes++;
		return openList.poll();
	}

	protected void addToOpenList(State<T> state) {
		openList.add(state);		
	}

	protected boolean openListContains(State<T> state) {
		if(openList.contains(state))
			return true;
		else
			return false;
	}

	protected boolean closedSetContains(State<T> state) {
		if(closedSet.contains(state))
			return true;
		else return false;
	}

	/* Collect states from goal state to initial state  */
	public ArrayList<T> backTrace(State<T> goalState,State<T> initialState){
		ArrayList<T> stateArr=new ArrayList<T>();
		if(goalState.getCameFrom().equals(null))
			stateArr.add(goalState.getState());
		else {
			State<T> tmpState=new State<T>(goalState);
			while(!(tmpState.getCameFrom().equals(initialState))){
				stateArr.add(tmpState.getState());
				tmpState=tmpState.getCameFrom();
			}
			stateArr.add(initialState.getState());
		}
		return stateArr;
	}
}
