package algorithms.mazeGenerators;

import java.util.Random;

/**
 * SimpleMaze3dGenerator is a maze generator that uses a naive randomized algorithm.
 * 
 * @author Aviv Moran
 *
 */

public class SimpleMaze3dGenerator extends CommonMaze3dGenerator {

	/**
	 * Generates a three dimensional maze with the given dimensions using a naive randomized algorithm.
	 * 
	 *  @param x [IN] The number of floors of the maze.
	 *	@param y [IN] The width of each floor.
	 *	@param z [IN] The length of each floor.
	 *
	 * @return [OUT] The generated maze {@link Maze3d}.
	 */
	@Override
	public Maze3d generate(int x, int y, int z) {
		Maze3d maze = new Maze3d();
		Random rnd = new Random();
		
		maze.initMazeToRandomValues(x, y, z);
		
		Position entrance = new Position(rnd.nextInt(x), rnd.nextInt(y), rnd.nextInt(z));
		maze.setCell(entrance, CellValue.PASSAGE);
		maze.setEntrance(entrance);
		
		Position exit = new Position(rnd.nextInt(x), rnd.nextInt(y), rnd.nextInt(z));
		maze.setCell(exit, CellValue.PASSAGE);
		maze.setExit(exit);
		
		Position newCell = new Position(maze.getEntrance());
		while(newCell.getX() != exit.getX()) {
			if(newCell.getX() < exit.getX()) {
				newCell.setX(newCell.getX() + 1);
				maze.setCell(newCell,CellValue.PASSAGE);
			}
			else {
				newCell.setX(newCell.getX() - 1);
				maze.setCell(newCell, CellValue.PASSAGE);
			}
		}
		
		while(newCell.getY() != exit.getY()) {
			if(newCell.getY() < exit.getY()) {
				newCell.setY(newCell.getY() + 1);
				maze.setCell(newCell,CellValue.PASSAGE);
			}
			else {
				newCell.setY(newCell.getY() - 1);
				maze.setCell(newCell, CellValue.PASSAGE);
			}
		}
		
		while(newCell.getZ() != exit.getZ()) {
			if(newCell.getZ() < exit.getZ()) {
				newCell.setZ(newCell.getZ() + 1);
				maze.setCell(newCell,CellValue.PASSAGE);
			}
			else {
				newCell.setZ(newCell.getZ() - 1);
				maze.setCell(newCell, CellValue.PASSAGE);
			}
		}
		
		return maze;
	}
}
