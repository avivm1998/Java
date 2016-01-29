package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Solution is the output of the search problem solver.
 * 
 * @author Aviv Moran
 */
public class Solution<T> implements Serializable{
	private ArrayList<State<T>> solution;

	/**
	 * Returns the solution of the calling object.
	 * 
	 * @return [OUT] The solution {@link ArrayList}.
	 */
	public ArrayList<State<T>> getSolution() {
		return solution;
	}

	/**
	 * Sets the solution with the given array.
	 * 
	 * @param solution [OUT] The solution to be set {@link ArrayList}.
	 */
	public void setSolution(ArrayList<State<T>> solution) {
		this.solution = solution;
	}
	
	/**
	 * Prints the solution.
	 */
	public void print() { 
		System.out.println(this.toString());
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		for(State<T> s : solution) {
			result.append("(" + s.getState().toString() + ")" + " -> ");
		}
		
		return result.toString();
	}
}
