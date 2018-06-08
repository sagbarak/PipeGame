package searcher;

public class State<T>{
		private T state;
		private int cost;
		private State<T> cameFrom;

		/*----------------------------Constructors------------------------------*/
		public State(T state) {
			this.state=state;
			this.cost=0;
		}
		public State(State<T> state) {
			this.state=state.getState();
			this.cameFrom=state.cameFrom;
			this.cost=state.cost;
		}
		/*---------------------------Getters/Setters----------------------------*/
		public State() {}
		
		public void setCameFrom(State<T> fromState) {
			this.cameFrom=fromState;	
		}
		
		public State<T> getCameFrom() {
			return this.cameFrom;
		}
		
		public T getState() {
			return state;
		}
		
		public void setState(T state) {
			this.state=state;
			this.cost=0;
		}

		public int getCost() {
			return cost;
		}

		public void setCost(int cost) {
			this.cost = cost;
		}
		/*----------------------------------------------------------------------*/
		
		/* (non-Java doc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((state == null) ? 0 : state.hashCode());
			return result;
		}
		/* (non-Java doc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof State)) {
				return false;
			}
			@SuppressWarnings("unchecked")
			State<T> other = (State<T>)obj;
			if (state == null) {
				if (other.state != null) {
					return false;
				}
			} else if (!state.equals(other.state)) {
				return false;
			}
			return true;
		}

	}
