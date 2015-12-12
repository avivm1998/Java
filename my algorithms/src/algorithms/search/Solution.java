package algorithms.search;

import java.util.ArrayList;

/**
 * Solution is the output of the search problem solver.
 * 
 * @author Aviv Moran
 *
 * @param <T> [IN] The class that will be used by the State<T> class {@link State<T>}
 */
public class Solution<T> {
	private ArrayList<State<T>> solution;

	/**
	 * Returns the solution of the calling object.
	 * 
	 * @return [OUT] The solution {@link ArrayList<State>}.
	 */
	public ArrayList<State<T>> getSolution() {
		return solution;
	}

	/**
	 * Sets the solution with the given array.
	 * 
	 * @param solution [OUT] The solution to be set {@link ArrayList<State>}.
	 */
	public void setSolution(ArrayList<State<T>> solution) {
		this.solution = solution;
	}
	
	/**
	 * Prints the solution.
	 */
	public void print() { 
		for(State<T> s : solution) { 
			System.out.println("Next Step: " + s.getState().toString());
		}
	}
}
