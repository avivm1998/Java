package server.presenter;

import algorithms.mazeGenerators.Maze3d;
import server.model.Model;
import server.view.View;

/**
 * DisplayMazeCommand displays the given maze.
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public class DisplayMazeCommand implements Command {
	
	Model m;
	View v;
	
	/**
	 * Constructor setting the Model and View from the controller.
	 * 
	 * @param m [IN] The model facade {@link Model}.
	 * @param v [IN] The view facade {@link View}.
	 */
	public DisplayMazeCommand(Model m, View v) {
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
		v.display(maze.toString());
	}

}
