package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Maze3d is a three dimensional maze.
 * 
 * @author Aviv Moran
 *
 */

@SuppressWarnings("serial")
public class Maze3d implements Serializable {
	private int[][][] maze;
	private Position entrance;
	private Position exit;
	
	public Maze3d() {}
	
	public Maze3d(byte[] b) { 
		this.entrance = new Position(b[0], b[1], b[2]);
		this.exit = new Position(b[3], b[4], b[5]);
		this.maze = new int[b[6]][b[7]][b[8]];
		
		int index = 9;
		for (int i = 0; i < b[6]; i++) {
			for (int j = 0; j < b[7]; j++) {
				for (int k = 0; k < b[8]; k++) {
					maze[i][j][k] = b[index];
					index++;
				}
			}
		}
	}
	
	/**
	 * Returns the whole maze in 0s and 1s.
	 * 
	 * @return [OUT] The maze {@link Integer[][][]}.
	 */
	public int[][][] getMaze() {
		return maze;
	}
	
	/**
	 * Sets the attribute "maze" with the given array.
	 * 
	 * @param maze [IN] The array that will be set into the "maze" attribute {@link Integer[][][]}.
	 */
	public void setMaze(int[][][] maze) {
		this.maze = maze;
	}
	
	/**
	 * Returns the entrance of the maze.
	 * 
	 * @return [OUT] The position of the entrance {@link Position}.
	 */
	public Position getEntrance() {
		return entrance;
	}
	
	/**
	 * Sets the entrance of the maze with the given Position.
	 * 
	 * @param entrance [IN] The position to be set as the entrance of the maze {@link Position}.
	 */
	public void setEntrance(Position entrance) {
		this.entrance = entrance;
	}
	
	/**
	 * Returns the exit of the maze.
	 * 
	 * @return [OUT] The position of the exit of the maze {@link Position}.
	 */
	public Position getExit() {
		return exit;
	}
	
	/**
	 * Sets the exit of the maze with the given position.
	 * 
	 * @param exit [IN] The position to be set as the exit of the maze {@link Position}.
	 */
	public void setExit(Position exit) {
		this.exit = exit;
	}
	
	/**
	 * Returns the entrance of the maze.
	 * 
	 * @return [OUT] The position of the entrance {@link Position}.
	 */
	public Position getStartPosition() { 
		return entrance;
	}
	
	/**
	 * Returns the exit of the maze.
	 * 
	 * @return [OUT] The position of the exit of the maze {@link Position}.
	 */
	public Position getGoalPosition() {
		return exit;
	}
	
	/**
	 * Returns the neighbor cell on the given direction of the given position.
	 * If the cell in the given direction is out of the maze null will be returned.
	 * 
	 * @param cell 		[IN] The position of the cell {@link Position}.
	 * @param direction [IN] The direction from the cell {@link Direction}.
	 * 
	 * @return [OUT] Position The neighbor of cell in the given direction {@link Position}.
	 */
	public Position getNeighborCell(Position cell, Direction direction) { 
		
		switch(direction) {
			case UP:
				if(cell.getX() + 1 < this.maze.length)
					return new Position(cell.getX() + 1, cell.getY(), cell.getZ());
				break;
			case DOWN:
				if(cell.getX() - 1 >= 0)
					return new Position(cell.getX() - 1, cell.getY(), cell.getZ());
				break;
			case FORWARDS:
				if(cell.getY() + 1 < this.maze[0].length)
					return new Position(cell.getX(), cell.getY() + 1, cell.getZ());
				break;
			case BACKWARDS:
				if(cell.getY() - 1 >= 0)
					return new Position(cell.getX(), cell.getY() - 1, cell.getZ());
				break;
			case RIGHT:
				if(cell.getZ() + 1 < this.maze[0][0].length)
					return new Position(cell.getX(), cell.getY(), cell.getZ() + 1);
				break;
			case LEFT:
				if(cell.getZ() - 1 >= 0)
					return new Position(cell.getX(), cell.getY(), cell.getZ() - 1);
				break;
		}
		
		return null;
	}
	
	/**
	 * Returns the walls surrounding the given position.
	 * 
	 * @param pos [IN] The position of the cell {@link Position}.
	 * 
	 * @return [OUT] The walls the surround the cell {@link ArrayList<Position>}.
	 */
	public ArrayList<Position> getNeigbooringWalls (Position pos) {
		Direction[] allDirections = {Direction.UP, Direction.DOWN, Direction.FORWARDS, Direction.BACKWARDS, Direction.RIGHT, Direction.LEFT};
		ArrayList<Position> walls = new ArrayList<Position>();
		
		for(Direction dir : allDirections) {
			Position current = this.getNeighborCell(pos, dir);
			if (current != null) {
				if (this.getCell(current) == CellValue.WALL) {
					walls.add(current);
				}
			}
		}
		
		return walls;
	}
	
	/**
	 * Prints the maze , it's entrance and it's exit.
	 */
	public void print() { 
		System.out.println(this.toString());
	}
	
	/**
	 * Sets up the maze with just walls.
	 * 
	 *  @param x [IN] The number of floors of the maze.
	 *	@param y [IN] The width of each floor.
	 *	@param z [IN] The length of each floor.
	 */
	public void initMazeToWalls(int x, int y, int z) {
		this.maze = new int[x][y][z];
		
		try {
			for (int i = 0; i <	x; i++) 
				for (int j = 0; j < y; j++) 
					for (int k = 0; k < z; k++)
						this.maze[i][j][k] = CellValue.cellValueToInt(CellValue.WALL);
		} catch (Exception e) {}
	}
	
	/**
	 * Sets up the maze with random values.
	 * 
	 *  @param x [IN] The number of floors of the maze.
	 *	@param y [IN] The width of each floor.
	 *	@param z [IN] The length of each floor.
	 */
	public void initMazeToRandomValues(int x, int y, int z) {
		Random rnd = new Random();
		this.maze = new int[x][y][z];
		
		try {
			for (int i = 0; i <	x; i++) 
				for (int j = 0; j < y; j++) 
					for (int k = 0; k < z; k++)
						this.maze[i][j][k] = rnd.nextInt(2);
		} catch (Exception e) {}
	}
	
	
	/**
	 * Returns the value of the given position in the maze.
	 * 
	 * @param pos [IN] The position of the cell {@link Position}.
	 * 
	 * @return [OUT] It's CellValue {@link CellValue}.
	 */
	public CellValue getCell(Position pos) { 
		try {
			return CellValue.intToCellValue(this.maze[pos.getX()][pos.getY()][pos.getZ()]);
		} catch (Exception e) {}
		
		return null;
	}
	
	/**
	 * Sets the given position to the given value.
	 * 
	 * @param pos   [IN] The position to be set {@link Position}. 
	 * 
	 * @param value [IN] The value to set to the position {@link CellValue}.
	 */
	public void setCell(Position pos, CellValue value) { 
		try {
			this.maze[pos.getX()][pos.getY()][pos.getZ()] = CellValue.cellValueToInt(value) ;
		} catch (Exception e) {}
	}
	
	/**
	 * Returns all the possible movements from the given position. 
	 * 
	 * @param pos [IN] The position to get the moves from {@link Position}.
	 * 
	 * @return [OUT] The possible moves {@link String[]}.
	 */
	public String[] getPossibleMoves(Position pos) { 
		Direction[] allDirections = {Direction.UP, Direction.DOWN, Direction.FORWARDS, Direction.BACKWARDS, Direction.RIGHT, Direction.LEFT};
		ArrayList<String> moves = new ArrayList<String>();
		
		for(Direction direction : allDirections) {
			if(this.getCell(this.getNeighborCell(pos, direction)) == CellValue.PASSAGE)
				moves.add(this.getNeighborCell(pos, direction).toString());
		}
		
		String[] allMoves = new String[moves.size()];
		
		return moves.toArray(allMoves);
	}
	
	/**
	 * Returns the number of the maze's floors(X dimension).
	 * 
	 * @return [OUT] The number of floors.
	 */
	public int getNumberOfFloors() { 
		return this.maze.length;
	}
	
	/**
	 * Returns the width of each floor(Y dimension).
	 * 
	 * @return [OUT] The width of each floor.
	 */
	public int getFloorWidth() { 
		return this.maze[0].length;
	}
	
	/**
	 * Returns the length of each floor(Z dimension).
	 * 
	 * @return [OUT] The length of each floor.
	 */
	public int getFloorLength() { 
		return this.maze[0][0].length;
	}
	
	/**
	 * Returns the plane when the X dimension is the given value.
	 * 
	 * @param x [IN] The value of the X dimension.
	 * 
	 * @return [OUT] The cut when x is the given value {@link Integer[][]}.
	 * 
	 * @throws IndexOutOfBoundsException when given a value outside the maze {@link IndexOutOfBoundsException}.
	 */
	public int[][] getCrossSectionByX(int x) { 
		if(x > 0 && x <= this.maze.length)
			return this.maze[x - 1];
		throw new IndexOutOfBoundsException("Bad Index");
	}
	
	/**
	 * Returns the plane when the Y dimension is the given value.
	 * 
	 * @param y [IN] The value of the Y dimension.
	 * 
	 * @return [OUT] The cut when y is the given value {@link Integer[][]}.
	 * 
	 * @throws IndexOutOfBoundsException when given a value outside the maze {@link IndexOutOfBoundsException}.
	 */
	public int[][] getCrossSectionByY(int y) { 
		if (y > 0 && y <= this.maze[0].length) {
			int[][] plane = new int[this.getNumberOfFloors()][this.getFloorLength()];
			for (int i = 0; i < this.getNumberOfFloors(); i++)
				for (int j = 0; j < this.getFloorLength(); j++)
					plane[i][j] = this.maze[i][y - 1][j];
			return plane;
		}
		
		throw new IndexOutOfBoundsException("Bad Index");
	}
	
	/**
	 * Returns the plane when the Z dimension is the given value.
	 * 
	 * @param z [IN] The value of the Z dimension.
	 * 
	 * @return [OUT] The cut when z is the given value {@link Integer[][]}.
	 * 
	 * @throws IndexOutOfBoundsException when given a value outside the maze {@link IndexOutOfBoundsException}.
	 */
	public int[][] getCrossSectionByZ(int z) { 
		if (z > 0 && z <= this.maze[0][0].length) {
			int[][] plane = new int[this.getNumberOfFloors()][this.getFloorWidth()];
			for (int i = 0; i < this.getNumberOfFloors(); i++)
				for (int j = 0; j < this.getFloorWidth(); j++)
					plane[i][j] = this.maze[i][j][z - 1];
			return plane;
		}
		
		throw new IndexOutOfBoundsException("Bad Index");
	}
	
	public byte[] toByteArray() {
		ArrayList<Byte> compressedMaze = new ArrayList<Byte>();
		
		compressedMaze.add(new Byte((byte)entrance.getX()));
		compressedMaze.add(new Byte((byte)entrance.getY()));
		compressedMaze.add(new Byte((byte)entrance.getZ()));
		
		compressedMaze.add(new Byte((byte)exit.getX()));
		compressedMaze.add(new Byte((byte)exit.getY()));
		compressedMaze.add(new Byte((byte)exit.getZ()));
		
		compressedMaze.add(new Byte((byte)this.getNumberOfFloors()));
		compressedMaze.add(new Byte((byte)this.getFloorWidth()));
		compressedMaze.add(new Byte((byte)this.getFloorLength()));
		
		for (int i = 0; i < this.getNumberOfFloors(); i++) {
			for (int j = 0; j < this.getFloorWidth(); j++) {
				for (int k = 0; k < this.getFloorLength(); k++) {
					compressedMaze.add(new Byte((byte)maze[i][j][k]));
				}
			}
		}
		
		Byte[] temp = new Byte[compressedMaze.size()];
		compressedMaze.toArray(temp);
		byte[] result = new byte[compressedMaze.size()];
		
		for (int i = 0; i < temp.length; i++) {
			result[i] = temp[i];
		}
		
		return result;
		
	}
	
	public int[][] getFloorState(int floorNumber) {
		int[][] result = new int[this.getFloorWidth()][this.getFloorLength()];
		
		for (int i = 0; i < this.getFloorWidth(); i++) {
			for (int j = 0; j < this.getFloorLength(); j++) {
				if(floorNumber > 0 && floorNumber < this.getNumberOfFloors()) {
					if(this.getCell(new Position(floorNumber, i, j)) == CellValue.WALL)
						result[i][j] = 1;
					
					else if(this.getCell(new Position(floorNumber, i, j)) == this.getCell(new Position(floorNumber - 1, i, j)) && this.getCell(new Position(floorNumber, i, j)) == this.getCell(new Position(floorNumber + 1, i, j))) {
						result[i][j] = 4;
						continue;
					}
					
					else if(this.getCell(new Position(floorNumber - 1, i, j)) == this.getCell(new Position(floorNumber, i, j))) {
						result[i][j] = 2;
					}

					else if(this.getCell(new Position(floorNumber + 1, i, j)) == this.getCell(new Position(floorNumber, i, j))) {
						result[i][j] = 3;
					}
						
					else
						result[i][j] = 0;
				}
				if(floorNumber == 0) {
					if(this.getCell(new Position(floorNumber, i, j)) == CellValue.WALL)
						result[i][j] = 1;
					else if(this.getCell(new Position(floorNumber + 1, i, j)) == this.getCell(new Position(floorNumber, i, j))) {
						result[i][j] = 3;
					}
					else
						result[i][j] = 0;
				}
				
				if(floorNumber == this.getNumberOfFloors() - 1) {
					if(this.getCell(new Position(floorNumber, i, j)) == CellValue.WALL)
						result[i][j] = 1;
					else if(this.getCell(new Position(floorNumber - 1, i, j)) == this.getCell(new Position(floorNumber, i, j))) {
						result[i][j] = 2;
					}
					else
						result[i][j] = 0;
				}
			}
		}
		
		return result;
	}
	
	@Override
	public boolean equals(Object arg0) {
		/*
		 * Maze3d other = (Maze3d)arg0;
		boolean flag = true;
		
		if(other.maze.length != this.maze.length || other.maze[0].length != this.maze[0].length || other.maze[0][0].length != this.maze[0][0].length)
			flag = false;
		else {
			for (int i = 0; i < maze.length; i++) {
				for (int j = 0; j < maze[0].length; j++) {
					for (int k = 0; k < maze[0][0].length; k++) {
						if(this.maze[i][j][k] != other.maze[i][j][k])
							flag = false;
					}
				}
			}
		}
		
		return other.entrance.equals(this.entrance) && other.exit.equals(this.exit) && flag;
		 */
		
		return this.hashCode() == ((Maze3d)arg0).hashCode();
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < this.getMaze().length; i++) {
			result.append("Floor #" + i + "\n");
			for (int j = 0; j < this.getMaze()[i].length; j++) {
				for (int k = 0; k < this.getMaze()[i][j].length; k++) {
					result.append(this.getMaze()[i][j][k] + "\t");
				}
				result.append("\n");
			}
		}
		
		result.append("Entrance: " + this.getEntrance().getX() + ", " + this.getEntrance().getY() + ", " + this.getEntrance().getZ() + "\n");
		result.append("Exit: " + this.getExit().getX() + ", " + this.getExit().getY() + ", " + this.getExit().getZ() + "\n");
		
		return result.toString();
	}
	
	@Override
	public int hashCode() {
		return Arrays.hashCode(this.toByteArray());
	}
}
