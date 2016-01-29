package algorithms.search;

/**
 * Searcher is a general search problem solver.
 * 
 * @author Aviv Moran
 */

public interface Searcher<T> {
	/**
	 * Using a search algorithm on a given search problem.
	 * 
	 * @param s [IN] The search problem to be solved {@link Searchable}.
	 * 
	 * @return The solution. {@link Solution}
	 */
	public Solution<T> search(Searchable<T> s);
}
