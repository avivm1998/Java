package algorithms.search;

import java.util.ArrayList;

/**
 * Searchable a general search problem.
 * 
 * @author Aviv Moran
 *
 * @param <T> [IN] The class that will be used by the State<T> class {@link State<T>}
 */

public interface Searchable<T> {
	
	/**
	 * Returns the initial state of the search problem.
	 * 
	 * @return [OUT] The initial state {@link State}.
	 */
	State<T> getInitialState();
	
	/**
	 * Returns the goal state of the search problem.
	 * 
	 * @return [OUT] The goal state {@link State}.
	 */
    State<T> getGoalState();
    
    /**
     * Returns all the possible next states from the given state.
     * 
     * @param s [IN] The original state to move from {@link State}.
     * 
     * @return [OUT] All the available neighbor states {@link ArrayList<State>}.
     */
    ArrayList<State<T>> getAllPossibleMoves(State<T> s);
}
