package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

/**
 * MyMaze3dGenerator is a maze generator that uses Prim's Randomized Algorithm for the creation of the maze.
 * 
 * @author Aviv Moran
 *
 */

public class MyMaze3dGenerator extends CommonMaze3dGenerator {

	/**
	 * Generates a three dimensional maze with the given dimensions using Prim's Randomized Algorithm.
	 * 
	 *  @param x [IN] The number of floors of the maze.
	 *	@param y [IN] The width of each floor.
	 *	@param z [IN] The length of each floor.
	 *
	 * @return [OUT] The generated maze {@link Maze3d}.
	 */
	@Override
	public Maze3d generate(int x, int y, int z) {
		ArrayList<Position> cells = new ArrayList<Position>();
		ArrayList<Position> walls = new ArrayList<Position>();
		Random rnd = new Random();
		Direction direction;
		Maze3d maze = new Maze3d();
		
		maze.initMazeToWalls(x, y, z);
		
		Position entrance = new Position(rnd.nextInt(x), rnd.nextInt(y), rnd.nextInt(z));
		maze.setEntrance(entrance);

		maze.setCell(entrance, CellValue.PASSAGE);
		cells.add(entrance);
		walls = maze.getNeigbooringWalls(entrance);
			
		while(walls.isEmpty() == false) {
			Position tmpWall = new Position(walls.get(rnd.nextInt(walls.size())));
			direction = Direction.intToDirection(rnd.nextInt(6));
			Position newCell = maze.getNeighborCell(tmpWall, direction);
			
			if(cells.contains(newCell) == false) {
				// Make the chosen wall to a passage and adds it to the cells list.
				maze.setCell(tmpWall, CellValue.PASSAGE);
				
				// Takes the cell in the opposite direction of and makes it a cell.
				if (newCell != null) {
					maze.setCell(newCell, CellValue.PASSAGE);
					cells.add(newCell);
					walls.addAll(maze.getNeigbooringWalls(newCell));
				}
			}

			walls.remove(tmpWall);
		}
		
		Position exit;
		
		do {
			exit = new Position(cells.get(rnd.nextInt(cells.size())));
		} while (exit.equals(maze.getEntrance()));
		
		
		maze.setExit(exit);
		maze.setCell(exit, CellValue.PASSAGE);
		
		return maze;
	}

}
