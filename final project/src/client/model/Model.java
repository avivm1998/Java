package client.model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import client.presenter.Properties;

/**
 * The model facade in the MVP architectural pattern.
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public interface Model {
	/**
	 * Generates a new Maze3d with the given dimensions and saves it in the database with the given name.
	 * 
	 * @param mazeName [IN] The wanted name. {@link String}
	 * @param x        [IN] The number of floors.
	 * @param y        [IN] The number of rows.
	 * @param z        [IN] The number of columns.
	 * 
	 * @throws Exception [THROWN] In case the maze name was taken.
	 */
	void generateMaze3d(String mazeName, int x, int y, int z) throws Exception;
	
	/**
	 * Solving the maze matching to the given name with the matching solving algorithm to the solver given.
	 * 
	 * @param mazeName [IN] The maze. {@link String}
	 * @param Solver   [IN] The solving algorithm {@link Searcher}
	 * 
	 * @throws Exception [THROWN] In case there is no such maze to solve.
	 */
	void solveMaze(String mazeName, String Solver) throws Exception;
	
	/**
	 * Saves the matching maze to the given name to the given file location.
	 * 
	 * @param mazeName [IN] The name of the maze. {@link String}
	 * @param fileName [IN] The wanted name of the saved file. {@link String}
	 * 
	 * @throws Exception [THROWN] In case there is no such maze to save.
	 */
	void saveCompressedMaze(String mazeName, String fileName) throws Exception;
	
	/**
	 * Loads the maze from the given file name and stores it under the given name.
	 * 
	 * @param fileName [IN] The name of the file to be read from. {@link String}
	 * @param mazeName [IN] The name of the maze to be stored with. {@link String}
	 * 
	 * @throws Exception [THROWN] In case there is no such file.
	 */
	void loadDecompressedMaze(String fileName, String mazeName) throws Exception;
	
	/**
	 * Returns the maze mathcing to the given name.
	 * 
	 * @param mazeName [IN] The name of the maze. {@link String}
	 * 
	 * @return The matching maze. {@link Maze3d}
	 * 
	 * @throws Exception [THROWN] In case there is no such maze.
	 */
	Maze3d getMaze3d(String mazeName) throws Exception;
	
	/**
	 * Returns the cross section with the given dimension and the given value from the matching maze to the given name.
	 *  
	 * @param mazeName  [IN] The name of the maze. {@link String}
	 * @param dimension [IN] The wanted dimension. {@link String}
	 * @param value     [IN] The value of the given dimension.
	 * 
	 * @return The cross section.
	 *  
	 * @throws Exception [THROWN] In case there is no such maze, or invalid value input.
	 */
	int[][] getCrossSection(String mazeName, String dimension, int value) throws Exception;
	
	/**
	 * Returns the solution for the matching maze to the given name.
	 * 
	 * @param mazeName [IN] The name of the maze. {@link String}
	 * 
	 * @return The solution. {@link Solution}
	 * 
	 * @throws Exception [THROWN] In case there is no such maze.
	 */
	Solution<Position> getSolution(String mazeName) throws Exception;
	
	/**
	 * Sets the settings of the model with the given Properties.
	 * 
	 * @param settings [IN] The wanted settings. {@link Properties}
	 */
	void setProperties(Properties settings);
	
	/**
	 * Safely exiting the program.
	 */
	void exit();
}
