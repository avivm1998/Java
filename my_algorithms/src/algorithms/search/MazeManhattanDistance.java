package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * MazeManhattanDistance a heuristic using Manhattan distance.
 * 
 * @author Aviv Moran
 *
 */

public class MazeManhattanDistance implements Heuristic<Position> {

	/**
	 * Generates the distance between two given states using Manhattan distance.
	 * 
	 * @param source      [IN] The first state {@link State}.
	 * @param destination [IN] The second state {@link State}.
	 * 
	 * @return [OUT] The distance between the given states.
	 */
	@Override
	public Double getDistance(State<Position> source, State<Position> destination) {
		Integer distance = Math.abs(destination.getState().getX() - source.getState().getX()) + Math.abs(destination.getState().getY() - source.getState().getY()) + Math.abs(destination.getState().getZ() - source.getState().getZ());
		
		return distance.doubleValue();
	}

}
