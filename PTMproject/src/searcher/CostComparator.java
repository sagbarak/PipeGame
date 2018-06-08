package searcher;

import java.util.Comparator;

public class CostComparator<T> implements Comparator<State<T>> {
	public int compare(State<T> state1, State<T> state2) {
		return (state1.getCost()-state2.getCost());
	}
}
