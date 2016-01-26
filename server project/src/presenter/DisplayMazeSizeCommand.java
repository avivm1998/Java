package presenter;

import algorithms.mazeGenerators.Maze3d;
import model.Model;
import view.View;

/**
 * DisplayMazeSize displays the size of the maze in the memory.
 *
 * @author Aviv Moran & Ayal Naim
 *
 */
public class DisplayMazeSizeCommand implements Command {

	Model m;
	View v;
	
	/**
	 * Constructor setting the Model and View from the controller.
	 * 
	 * @param m [IN] The model facade {@link Model}.
	 * @param v [IN] The view facade {@link View}.
	 */
	public DisplayMazeSizeCommand(Model m, View v) {
		this.m = m;
		this.v = v;
		
	}
	
	@Override
	public void doCommand(String args) throws Exception {
		Maze3d maze = new Maze3d();
		
		try {
			maze = m.getMaze3d(args);
		} catch(Exception e) {
			throw e;
		}
		
		v.display("" + (maze.getNumberOfFloors() * maze.getFloorWidth() * maze.getFloorLength() * 4 + 2 * 3 * 4));
	}

}
