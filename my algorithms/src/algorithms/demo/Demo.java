package algorithms.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.AStar;
import algorithms.search.BFS;
import algorithms.search.CommonSearcher;
import algorithms.search.MazeAirDistance;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.Solution;
import algorithms.search.StateCostComparator;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

/**
 * Demo is a main-like class using the other classes.
 * 
 * @author Aviv Moran
 *
 */

public class Demo {
	public void run() { 
		Maze3dGenerator mg = new MyMaze3dGenerator();
		SearchableMaze3d maze = new SearchableMaze3d();
		maze.setMaze(mg.generate(8,8,8));
		
		maze.getMaze().print();
		
		CommonSearcher<Position> s = new BFS<Position>(new StateCostComparator<Position>());
		Solution<Position> solBFS = s.search(maze);
		System.out.println("\nThe BFS solution: ");
		solBFS.print();
		
		CommonSearcher<Position> s2 = new AStar<Position>(maze, new MazeAirDistance(), new StateCostComparator<Position>());
		Solution<Position> solAStarAirDistance = s2.search(maze);
		System.out.println("\nThe A* using air distance heuristic solution: ");
		solAStarAirDistance.print();
		
		CommonSearcher<Position> s3 = new AStar<Position>(maze, new MazeManhattanDistance(), new StateCostComparator<Position>());
		Solution<Position> solAStarManhattanDistance = s3.search(maze);
		System.out.println("\nThe A* using manhattan distance heuristic solution: ");
		solAStarManhattanDistance.print();
		
		System.out.println("BFS nodes evaluated: " + s.getNumberOfNodesEvaluated());
		System.out.println("A* air distance nodes evaluated: " + s2.getNumberOfNodesEvaluated());
		System.out.println("A* manhattan distance nodes evaluated: " + s3.getNumberOfNodesEvaluated());
	}
	
	public static void main(String[] args) {
		Maze3dGenerator mg = new MyMaze3dGenerator();
		//Maze3dGenerator mg = new MyMaze3dGenerator2();
		Maze3d maze = mg.generate(5, 5, 5);
		
		try {
			OutputStream out = new MyCompressorOutputStream(new FileOutputStream("1.maz"));
			out.write(maze.toByteArray());
			out.flush();
			out.close();
			
			InputStream in = new MyDecompressorInputStream(new FileInputStream("1.maz"));
			byte b[]=new byte[maze.toByteArray().length];
			in.read(b);
			in.close();
			Maze3d loaded=new Maze3d(b);
			System.out.println(loaded.equals(maze));
			maze.print();
			System.out.println("SUP");
			loaded.print();
		} catch(FileNotFoundException e) {
			System.out.println("File Not Found Exception");
		} catch (IOException e) {
			System.out.println("IO Exception");
		}
	}
}
