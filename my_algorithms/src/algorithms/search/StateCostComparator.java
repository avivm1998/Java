package algorithms.search;

/**
 * StateCostCoparator overrides the compare method in the interface Comparator.
 * 
 * @author Aviv Moran
 * 
 * @param T [IN] The class that will be used by the State<T> class {@link State<T>} 
 */

import java.util.Comparator;

public class StateCostComparator<T> implements Comparator<State<T>> {

	/**
	 * Compares two states by their costs.
	 * 
	 * @param s1 [IN] The first state to be compared.
	 * @param s2 [IN] The second sate to be compared.
	 * 
	 * @return [OUT] Negative number for s1 < s2 , Zero for s1 = s2 , and a positive number for s1 > s2
	 */
	@Override
	public int compare(State<T> s1, State<T> s2) {
		return Double.compare(s1.getCost(), s2.getCost());
	}
	
}
