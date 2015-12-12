package algorithms.demo;

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
		Demo d = new Demo();
		d.run();
	}
}
