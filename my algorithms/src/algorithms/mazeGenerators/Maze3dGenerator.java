package algorithms.mazeGenerators;

/**
 * Maze3dGenerator is an Interface thats implemented by maze generators.
 * 
 * @author Aviv Moran
 *
 */

public interface Maze3dGenerator {
	/**
	 * Generates a three dimensional maze with the given dimensions.
	 * 
	 *  @param x [IN] The number of floors of the maze.
	 *	@param y [IN] The width of each floor.
	 *	@param z [IN] The length of each floor.
	 *
	 * @return [OUT] The generated maze {@link Maze3d}.
	 */
	Maze3d generate(int x, int y, int z);
	
	/**
	 *	This function measures the time it takes to the generate function to create a maze.
	 *
	 *	@param x [IN] The number of floors of the maze.
	 *	@param y [IN] The width of each floor.
	 *	@param z [IN] The length of each floor.
	 *
	 *	@return [OUT] A string with the time it took the algorithm to generate the maze {@link String}.
	 */
	String measureAlgorithmTime(int x, int y, int z);
}
