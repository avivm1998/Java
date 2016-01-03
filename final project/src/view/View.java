package view;

import algorithms.mazeGenerators.Maze3d;

public interface View {
	void display(String args);
	void display(Maze3d maze);
	void getUserCommand();	
}