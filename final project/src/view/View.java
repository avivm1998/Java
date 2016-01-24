package view;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * View is the class that is in charge of the visual part of the project.
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public interface View {
	
	/**
	 * Displays the String that is given.
	 * 
	 * @param args [IN]
	 */
	void display(String args);
	
	/**
	 * Displays the maze that is given.
	 * 
	 * @param maze [IN]
	 */
	void display(Maze3d maze);
	
	/**
	 * Displays the solution that is given.
	 * 
	 * @param sol [IN]
	 */
	void display(Solution<Position> sol);
	
	/**
	 * Gets the users command
	 * 
	 */
	void getUserCommand();	
}