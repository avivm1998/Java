package client.view;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * View is the class that is in charge of the visual part of the project and the V in the MVP architecture.
 * 
 * @author Aviv Moran & Ayal Naim
 */
public interface View {
	
	/**
	 * Displays the String that is given.
	 * 
	 * @param args [IN] The message. {@link String}
	 */
	void display(String args);
	
	/**
	 * Displays the maze that is given.
	 * 
	 * @param maze [IN] The maze. {@link Maze3d}
	 */
	void display(Maze3d maze);
	
	/**
	 * Displays the solution that is given.
	 * 
	 * @param sol [IN] The solution. {@link Solution}
	 */
	void display(Solution<Position> sol);
	
	/**
	 * Gets the users commands.
	 */
	void getUserCommand();	
}