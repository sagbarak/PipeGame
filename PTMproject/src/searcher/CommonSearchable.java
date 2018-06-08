package searcher;

import java.util.ArrayList;

public abstract class CommonSearchable<T> implements Searchable<T> {
	State<T> state;
	public abstract ArrayList<State<T>> getAllPossibleStates(State<T> state);
	public abstract boolean isGoalState(State<T> state);
	public State<T> getState() {
		return state;
	}
}
