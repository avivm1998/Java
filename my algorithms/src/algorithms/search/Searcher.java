package algorithms.search;

/**
 * Searcher is a general search problem solver.
 * 
 * @author Aviv Moran
 *
 * @param <T> [IN] The class that will be used by the State<T> class {@link State<T>}.
 */

public interface Searcher<T> {
	/**
	 * Using a search algorithm on a given search problem.
	 * 
	 * @param s [IN] The search problem to be solved {@link Searchable}.
	 */
	public Solution<T> search(Searchable<T> s);
}
