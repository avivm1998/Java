package algorithms.search;

/**
 * Heuristic is an evaluation way using distance from the goal, used by the A* algorithm. 
 * 
 * @author Aviv Moran
 *
 * @param <Position> {@link Position}
 */
public interface Heuristic<Position> {
	/**
	 * Generates the distance between two given states.
	 * 
	 * @param source      [IN] The first state {@link State}.
	 * @param destination [IN] The second state {@link State}.
	 * 
	 * @return [OUT] The distance between the given states.
	 */
	Double getDistance(State<Position> source, State<Position> destination);
}
