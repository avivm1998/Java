package algorithms.search;

import java.util.Comparator;

/**
 * AStar is a BFS type of search algorithm while using heuristics to decrease the number of iterations within the algorithm.
 * 
 * @author Aviv Moran
 *
 * @param <T> [IN] The class that will be used by the State<T> class {@link State<T>}
 */
public class AStar<T> extends BFS<T> {
	Heuristic<T> h;
	Searchable<T> s;
	
	/**
	 * AStar constructor, sends the comparator to the BFS constructor and sets the heuristic and the search problem.
	 * 
	 * @param s          [IN] The search problem that contains the goal state. {@link Searchable}
	 * @param h          [IN] The type of heuristic to be used to calculate the distance to the goal state {@link Heuristic}.
	 * @param comparator [IN] The way two states will be compared by in the priority queue {@link Comparator}}.
	 * 
	 */
	public AStar(Searchable<T> s, Heuristic<T> h, Comparator<State<T>> comparator) {
		super(comparator);
		this.s = s;
		this.h = h;
	}
	
	/**
	 * Using the A* search algorithm.
	 * 
	 * @param s [IN] The search problem to be solved {@link Searchable}.
	 */
	@Override
	public Solution<T> search(Searchable<T> s) {
		return super.search(s);
	}
	
	@Override
	protected void generateCost(State<T> state) {
		super.generateCost(state);
		state.setCost(state.getCost() + h.getDistance(state, s.getGoalState()));
	}
}
