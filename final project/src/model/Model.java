package model;

import java.io.File;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Properties;

public interface Model {
	void generateMaze3d(String mazeName, int x, int y, int z) throws Exception;
	void solveMaze(String mazeName, String Solver) throws Exception;
	void saveCompressedMaze(String mazeName, String fileName) throws Exception;
	void loadDecompressedMaze(String fileName, String mazeName) throws Exception;
	File[] getFileDirectoryList(File f);
	Maze3d getMaze3d(String mazeName) throws Exception;
	int[][] getCrossSection(String mazeName, String dimension, int value) throws Exception;
	Solution<Position> getSolution(String mazeName) throws Exception;
	void setProperties(Properties settings);
	void exit();
}
