package algorithms.search;

/**
 * State represents the view of a certain vertex in the search problem.
 * 
 * @author Aviv Moran
 *
 * @param <T> [IN] The class that will be used by the State<T> class {@link State<T>}
 */
public class State<T> {
	
	private T state;
	private double cost;
	private State<T> cameFrom;
	
	/**
	 * State constructor setting the state with the given T
	 * 
	 * @param t [IN] The state to be set {@link T}.
	 */
	public State(T t) { 
		this.state = t	;
	}
	
	/**
	 * Returns the cost of the state.
	 * 
	 * @return [OUT] The cost of the state.
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * Sets the cost of the states  with the given cost.
	 * 
	 * @param cost [OUT] The cost to be set.
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	/**
	 * Returns the previous vertex from the calling vertex.
	 * 
	 * @return [OUT] The previous state in the path.
	 */
	public State<T> getCameFrom() {
		return cameFrom;
	}

	/**
	 * Sets the previous vertex in the path.
	 * 
	 * @param cameFrom [IN] The previous state in the path.
	 */
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	
	/**
	 * Returns the state of the calling object.
	 * 
	 * @return [OUT] The state of the calling object {@link T}.
	 */
	public T getState() {
		return state;
	}
	
	/**
	 * Sets the state of the calling object.
	 * 
	 * @param state [IN] The state to be set in the object.
	 */
	public void setState(T state) {
		this.state = state;
	}
	
	/**
	 * Overrides the equals method in the Object class.
	 * 
	 * @param obj [IN] The other object to be compared to {@link Object}.
	 * 
	 * @return [OUT] True if same , False if different.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		State<T> other = (State<T>)obj;
		return this.getState().equals(other.getState());
	}
	
	/**
	 * Overloads the equals method in the Object class.
	 * 
	 * @param obj [IN] The other object to be compared to {@link State}.
	 * 
	 * @return [OUT] True if same , False if different.
	 */
	public boolean equal(State<T> other) { 
		return this.getState() == other.getState();
	}
	
	/**
	 * Overrides the hashCode method in the Object class , used by HashTables.
	 * 
	 * @return [OUT] The matching hash code of the state.
	 */
	@Override
	public int hashCode() {
		return this.state.hashCode();
	}
}
