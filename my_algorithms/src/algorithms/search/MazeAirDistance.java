package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * MazeAirDistance a heuristic using air distance.
 * 
 * @author Aviv Moran
 *
 */
public class MazeAirDistance implements Heuristic<Position> {
	
	/**
	 * Generates the distance between two given states using air distance.
	 * 
	 * @param source      [IN] The first state {@link State}.
	 * @param destination [IN] The second state {@link State}.
	 * 
	 * @return [OUT] The distance between the given states.
	 */
	@Override
	public Double getDistance(State<Position> source,  State<Position> destination) {
		
		Double distanceX = Math.pow(destination.getState().getX() - source.getState().getX(), 2);
		Double distanceY = Math.pow(destination.getState().getY() - source.getState().getY(), 2);
		Double distanceZ = Math.pow(destination.getState().getZ() - source.getState().getZ(), 2);
		
		return Math.sqrt(distanceX + distanceY + distanceZ);
	}
}
