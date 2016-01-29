package algorithms.demo;

import java.util.ArrayList;

import algorithms.mazeGenerators.CellValue;
import algorithms.mazeGenerators.Direction;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Searchable;
import algorithms.search.State;

/**
 * SearchableMaze3d is an adapter between a three dimensional maze and a search problem.
 * 
 * @author Aviv Moran
 *
 */

public class SearchableMaze3d implements Searchable<Position> {
	private Maze3d maze;
	
	/**
	 * Returns the initial state of the search problem.
	 * 
	 * @return [OUT] The initial state {@link State}.
	 */
	@Override
	public State<Position> getInitialState() {
		return new State<Position>(this.maze.getEntrance());
	}
	
	/**
	 * Returns the goal state of the search problem.
	 * 
	 * @return [OUT] The goal state {@link State}.
	 */
	@Override
	public State<Position> getGoalState() {
		return new State<Position>(this.maze.getExit());
	}
	
	/**
     * Returns all the possible next states from the given state.
     * 
     * @param s [IN] The original state to move from {@link State}.
     * 
     * @return [OUT] All the available neighbor states {@link ArrayList}.
     */
	@Override
	public ArrayList<State<Position>> getAllPossibleMoves(State<Position> s) {
		Direction[] allDirections = {Direction.UP, Direction.DOWN, Direction.FORWARDS, Direction.BACKWARDS, Direction.RIGHT, Direction.LEFT};
		ArrayList<State<Position>> moves = new ArrayList<State<Position>>();
		
		for(Direction direction : allDirections) {
			if(maze.getCell(maze.getNeighborCell(s.getState(), direction)) == CellValue.PASSAGE) { 
				moves.add(new State<Position>(maze.getNeighborCell(s.getState(), direction)));
			}
		}
		
		return moves;
	}
	
	/**
	 * Returns the three dimensional maze.
	 * 
	 * @return [OUT] The maze that being turned to a search problem {@link Maze3d}.
	 */
	public Maze3d getMaze() {
		return maze;
	}

	/**
	 * Sets the three dimensional maze.
	 * 
	 * @param maze [IN] The maze to be set as the maze of the state problem {@link Maze3d}
	 */
	public void setMaze(Maze3d maze) {
		this.maze = maze;
	}
	
	
}

