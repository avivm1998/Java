package algorithms.mazeGenerators;

/**
 * An abstract class representing the common base of all the maze generators.
 * 
 * @author Aviv Moran
 *
 */

public abstract class CommonMaze3dGenerator implements Maze3dGenerator {
	
	/**
	 * Generates a three dimensional maze with the given dimensions.
	 * 
	 *  @param x [IN] The number of floors of the maze.
	 *	@param y [IN] The width of each floor.
	 *	@param z [IN] The length of each floor.
	 *
	 * @return [OUT] The generated maze {@link Maze3d}.
	 */
	public abstract Maze3d generate(int x, int y, int z);
	
	/**
	 *	This function measures the time it takes to the generate function to create a maze.
	 *
	 *	@param x [IN] The number of floors of the maze.
	 *	@param y [IN] The width of each floor.
	 *	@param z [IN] The length of each floor.
	 *
	 *	@return [OUT] A string with the time it took the algorithm to generate the maze {@link String}.
	 */
	@Override
	public String measureAlgorithmTime(int x, int y, int z) {
		Long startTime = System.currentTimeMillis();
		generate(x, y, z);
		Long endTime = System.currentTimeMillis();
		
		Long totalTime = endTime - startTime;
		String result = totalTime.toString();
		
		return result + "ms";
	}

}
