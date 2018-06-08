package searcher;

import java.util.ArrayList;

import java.util.LinkedList;

public class BFS<T> extends CommonSearcher<T> {

	public ArrayList<T> search(Searchable<T> s){
		// Mark all the vertices as not visited(By default set as false)

		// Create a queue for BFS
		LinkedList<State<T>> queue = new LinkedList<State<T>>();

		// Mark the current node as visited and enqueue it
		queue.add(s.getInitialState());

		while (queue.size() != 0)
		{
			// Dequeue a vertex from queue and print it
			State<T> n = queue.poll();
			
			if(s.isGoalState(n)) {
				return backTrace(n,s.getInitialState());
			}

			ArrayList<State<T>> successors=s.getAllPossibleStates(n);
			for(State<T> state: successors) {
		
					boolean conditionq=(!queue.contains(state));
					if(conditionq){
						state.setCameFrom(n);
						state.setCost(n.getCost()+1);
						queue.add(state);
					}
					else
						if(state.getCameFrom().getCost()>n.getCost()) {
						state.setCost(n.getCost()+1);
						state.setCameFrom(n);
					}
				}
			
			}
		return null;
	}
}
