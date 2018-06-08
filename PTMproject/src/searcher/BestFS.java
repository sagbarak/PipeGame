package searcher;

import java.util.ArrayList;

public class BestFS<T> extends CommonSearcher<T> {

	public ArrayList<T> search(Searchable<T> s) {
		addToOpenList(s.getInitialState());

		while(openList.size()>0) {
			State<T> n=popOpenList();
			closedSet.add(n);

			if(s.isGoalState(n))
				return backTrace(n,s.getInitialState());

			ArrayList<State<T>> successors=s.getAllPossibleStates(n);
			for(State<T> state: successors) {
				boolean conditionc =(!closedSetContains(state));
				boolean conditiono=(!openListContains(state));
				if(conditionc&&conditiono){
					state.setCameFrom(n);
					state.setCost(n.getCost()+state.getCost());
					addToOpenList(state);
				}
				else {
					if(state.getCameFrom().getCost()>n.getCost()) {
						state.setCost(n.getCost()+state.getCost());
						state.setCameFrom(n);
						if(closedSet.contains(state)) {
							openList.add(state);
							closedSet.remove(state);
						}
						else {
							openList.remove(state);
							openList.add(state);
						}
					}
				}
			}
		}
		return null;
	}
}


