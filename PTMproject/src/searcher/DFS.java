package searcher;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class DFS<T> extends CommonSearcher<T> {


	public ArrayList<T> search(Searchable<T> s){
		// Mark all the vertices as not visited(By default
		// set as false)
		LinkedList<State<T>> visited = new LinkedList<State<T>>();

		// Create a queue for BFS
		Stack<State<T>> stack = new Stack<State<T>>();

		// Mark the current node as visited and enqueue it
		stack.add(s.getInitialState());
		visited.add(s.getInitialState());

		while (!stack.isEmpty())
		{
			// Dequeue a vertex from queue and print it
			State<T> n = stack.pop();

			if(s.isGoalState(n)) {
				return backTrace(n,s.getInitialState());
			}

			ArrayList<State<T>> successors=s.getAllPossibleStates(n);
			for(State<T> state: successors) {
				boolean conditionv = (!visited.contains(state));
				if(conditionv) {
					boolean conditionq=(!stack.contains(state));
					if(conditionq){
						state.setCameFrom(n);
						state.setCost(n.getCost()+1);
						stack.push(state);
					}
					else
						if(state.getCameFrom().getCost()>n.getCost()) {
							state.setCost(n.getCost()+1);
							state.setCameFrom(n);
						}
				}
			}
			visited.add(n);
		}
		return null;
	}
}