package searcher;

import java.util.Comparator;

public class GradeComparator<T> implements Comparator<State<T>> {

	public int compare(State<T> state1, State<T> state2) {
		return (state2.getCost()-state1.getCost());
	}
}