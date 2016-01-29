package client.view;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;


// this is (1) the common type, and (2) a type of widget
// (1) we can switch among different MazeDisplayers
// (2) other programmers can use it naturally
/**
 * MazeDisplayer extends Canvas ,it displays the maze. 
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public abstract class MazeDisplayer extends Canvas{
	
	public CommonCharacter player;
	int[][] mazeData;
	int currentFloor;
	Maze3d maze;
	Solution<Position> solution;
	boolean showSolution;
	public boolean lockedKeys = false;
	
	/**
	 * Constructor with parameters, setting the parent composite and the style of the widget.
	 * 
	 * @param parent [IN] The composite. {@link Composite}
	 * @param style  [IN] The style.
	 */
	public MazeDisplayer(Composite parent, int style) {
		super(parent, style);
		showSolution = false;
	}

	/**
	 * Sets the maze data to the first parameter and the current floor to the second parameter.
	 * 
	 * @param mazeData    [IN] The 2 dimensional cross. {@link Integer[][]}
	 * @param floorNumber [IN] The floor it represents. 
	 */
	public void setMazeData(int[][] mazeData, int floorNumber){
		this.mazeData=mazeData;
		this.currentFloor = floorNumber;
	}
	
	/**
	 * Sets the charector's position by the row and column that was sent.
	 * 
	 * @param row [IN] the row.
	 * @param col [IN] the column.
	 */
	public abstract  void setCharacterPosition(int row,int col);

	/**
	 * Moves the character up.
	 * 
	 */
	public abstract void moveUp();

	/**
	 * Moves the character down
	 * 
	 */
	public abstract  void moveDown();

	/**
	 * Moves the character left
	 * 
	 */
	public abstract  void moveLeft();

	/**
	 * Moves the character right
	 * 
	 */
	public  abstract void moveRight();

}