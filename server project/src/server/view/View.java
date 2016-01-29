package server.view;

import java.io.OutputStream;

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
	 * @param args [IN] The string. {@link String}
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
	 * Sends the given maze to the given client.
	 * 
	 * @param maze        [IN] The maze. {@link Maze3d}
	 * @param outToClient [IN] The output stream of the client. {@link OutputStream}
	 */
	public void sendMazeToClient(Maze3d maze, OutputStream outToClient);
	
	/**
	 * Sends the given solution to the given client.
	 * 
	 * @param solution    [IN] The solution to be sent. {@link Solution}
	 * @param outToClient [IN] The output stream of the client. {@link OutputStream}
	 */
	public void sendSolutionToClient(Solution<Position> solution, OutputStream outToClient);
	
	/**
	 * Sends the given string to the given client.
	 * 
	 * @param arg         [IN] The message. {@link String}
	 * @param outToClient [IN] The output stream of the client. {@link OutputStream}
	 */
	public void sendStringToClient(String args, OutputStream outToClient);
	
	/**
	 * Gets the users commands.
	 */
	void getUserCommand();	
}