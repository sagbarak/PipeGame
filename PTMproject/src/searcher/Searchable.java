package searcher;

import java.util.ArrayList;

public interface Searchable<T>{
	State<T> getInitialState();
	ArrayList<State<T>> getAllPossibleStates(State<T> state);
	public boolean isGoalState(State<T> state);
	int grade(State<T> st);
}
