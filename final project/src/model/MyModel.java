package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Observable;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Properties;

public class MyModel extends Observable implements Model {

	private Socket theServer;
	private Properties settings;
	private HashMap<String, Maze3d> mazePool;
	private HashMap<String, Solution<Position>> solutionPool;
	
	public MyModel() {
		try {
			settings = new Properties();
			theServer = new Socket(settings.getIp(), settings.getPort());
			mazePool = new HashMap<String, Maze3d>();
			solutionPool = new HashMap<String, Solution<Position>>();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void generateMaze3d(String mazeName, int x, int y, int z) throws Exception {
		PrintWriter outToServer = new PrintWriter(theServer.getOutputStream());
		outToServer.println("generate 3d maze " + mazeName + " " + x + " " + y + " " + z);  
		outToServer.flush();
		
		ObjectInputStream inFromServer = new ObjectInputStream(theServer.getInputStream());
		Maze3d maze = (Maze3d)inFromServer.readObject();
		mazePool.put(mazeName, maze);
		
		setChanged();
		notifyObservers(maze);
	}

	@Override
	public void solveMaze(String mazeName, String solver) throws Exception {
		PrintWriter outToServer = new PrintWriter(theServer.getOutputStream());
		outToServer.println("solve " + mazeName + " " + solver);
		outToServer.flush();
		
		ObjectInputStream inFromServer = new ObjectInputStream(theServer.getInputStream());
		Solution<Position> solution = (Solution<Position>)inFromServer.readObject();
		solutionPool.put(mazeName, solution);
		
		setChanged();
		notifyObservers(solution);
	}

	@Override
	public void saveCompressedMaze(String mazeName, String fileName) throws Exception {
		PrintWriter outToServer = new PrintWriter(theServer.getOutputStream());
		outToServer.println("save maze " + mazeName + " " + fileName);
		outToServer.flush();
		
		ObjectInputStream inFromServer = new ObjectInputStream(theServer.getInputStream());
		String message = (String)inFromServer.readObject();

		setChanged();
		notifyObservers(message);
	}

	@Override
	public void loadDecompressedMaze(String fileName, String mazeName) throws Exception {
		PrintWriter outToServer = new PrintWriter(theServer.getOutputStream());
		outToServer.println("load maze " + fileName + " " + mazeName);
		outToServer.flush();
		
		ObjectInputStream inFromServer = new ObjectInputStream(theServer.getInputStream());
		Maze3d maze = (Maze3d)inFromServer.readObject();
		mazePool.put(mazeName, maze);
		
		setChanged();
		notifyObservers(maze);
	}

	@Override
	public Maze3d getMaze3d(String mazeName) throws Exception {
		if(mazePool.get(mazeName) == null)
			throw new Exception("There is no maze named: " + mazeName);
		return mazePool.get(mazeName);
	}

	@Override
	public int[][] getCrossSection(String mazeName, String dimension, int value) throws Exception {
		if(mazePool.get(mazeName) == null)
			throw new Exception("There is no maze named: " + mazeName);
		if(dimension.equals("X"))
			return mazePool.get(mazeName).getCrossSectionByX(value);
		else if(dimension.equals("Y"))
			return mazePool.get(mazeName).getCrossSectionByY(value);
		else
			return mazePool.get(mazeName).getCrossSectionByZ(value);
	}

	@Override
	public Solution<Position> getSolution(String mazeName) throws Exception {
		if(solutionPool.get(mazeName) == null)
			throw new Exception("There is no solution for a maze named: " + mazeName);
		return solutionPool.get(mazeName);
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
			e.printStackTrace();
		}
	}

}
