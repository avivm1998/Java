package model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Properties;

public class MyModel extends Observable implements Model {

	private Socket theServer;
	private Properties settings;
	
	public MyModel() {
		try {
			theServer = new Socket(settings.getIp(), settings.getPort());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void generateMaze3d(String mazeName, int x, int y, int z) throws Exception {
		PrintWriter outToServer = new PrintWriter(theServer.getOutputStream());
		outToServer.println("generate 3d maze " + mazeName + " " + x + " " + y + " " + z);
		//generate will return the maze as soon as possible.
	}

	@Override
	public void solveMaze(String mazeName, String solver) throws Exception {
		PrintWriter outToServer = new PrintWriter(theServer.getOutputStream());
		outToServer.println("solve " + mazeName + " " + solver);
		//solve will return the maze as soon as possible.
	}

	@Override
	public void saveCompressedMaze(String mazeName, String fileName) throws Exception {
		PrintWriter outToServer = new PrintWriter(theServer.getOutputStream());
		outToServer.println("save maze " + mazeName + " " + fileName);

	}

	@Override
	public void loadDecompressedMaze(String fileName, String mazeName) throws Exception {
		PrintWriter outToServer = new PrintWriter(theServer.getOutputStream());
		outToServer.println("load maze " + fileName + " " + mazeName);

	}

	@Override
	public Maze3d getMaze3d(String mazeName) throws Exception {
		//generate will return the maze as soon as possible.
		return null;
	}

	@Override
	public int[][] getCrossSection(String mazeName, String dimension, int value) throws Exception {
		//getCrossSection will return the plane as soon as possible.
		return null;
	}

	@Override
	public Solution<Position> getSolution(String mazeName) throws Exception {
		//generate will return the maze as soon as possible.
		return null;
	}

	@Override
	public void setProperties(Properties settings) {
		this.settings = settings;
	}

	@Override
	public void exit() {
		try {
			theServer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
