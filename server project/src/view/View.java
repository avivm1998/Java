package view;

import java.io.OutputStream;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public interface View {
	void display(String args);
	void display(Maze3d maze);
	void display(Solution<Position> sol);
	public void sendMazeToClient(Maze3d maze, OutputStream outToServer);
	public void sendSolutionToClient(Solution<Position> solution, OutputStream outToServer);
	public void sendStringToClient(String args, OutputStream outToServer);
	void getUserCommand();	
}