package view;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;


// this is (1) the common type, and (2) a type of widget
// (1) we can switch among different MazeDisplayers
// (2) other programmers can use it naturally
public abstract class MazeDisplayer extends Canvas{
	
	// just as a stub...
	int[][] mazeData;
	int currentFloor;
	Maze3d maze;

	
	public MazeDisplayer(Composite parent, int style) {
		super(parent, style);
	}

	public void setMazeData(int[][] mazeData, int floorNumber){
		this.mazeData=mazeData;
		this.currentFloor = floorNumber;
	}
	
	public abstract  void setCharacterPosition(int row,int col);

	public abstract void moveUp();

	public abstract  void moveDown();

	public abstract  void moveLeft();

	public  abstract void moveRight();

}