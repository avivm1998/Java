package server.presenter;

import server.model.Model;
import server.view.View;

/**
 * GenerateMaze3dCommand generates a maze with the given name and sizes with the MazeGenerator algorithms.
 * When the maze is ready, the command will send a message to the user.
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public class GenerateMaze3dCommand implements Command {
	
	Model m;
	View v;
	
	/**
	 * Constructor setting the Model and View from the controller.
	 * 
	 * @param m [IN] The model facade {@link Model}.
	 * @param v [IN] The view facade {@link View}.
	 */
	public GenerateMaze3dCommand(Model m, View v) {
		this.m = m;
		this.v = v;
		
	}
	
	@Override
	public void doCommand(String args) throws Exception {
		String[] parameters = args.split(" ");
		m.generateMaze3d(parameters[0], parameters[1], Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]), Integer.parseInt(parameters[4]));
	}

}
