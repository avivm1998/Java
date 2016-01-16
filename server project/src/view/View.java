package view;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public interface View {
	void display(String args);
	void display(Maze3d maze);
	void display(Solution<Position> sol);
	void getUserCommand();	
}