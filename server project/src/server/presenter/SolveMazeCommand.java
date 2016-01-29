package server.presenter;

import server.model.Model;
import server.view.View;

/**
 * SolveMazeCommand solves the given maze with the given searching algorithm and places the solution in the solution HashMap
 * When the solution is ready, the command will send a message to the user.
 *  
 * @author Aviv Moran & Ayal Naim
 *
 */
public class SolveMazeCommand implements Command {

	Model m;
	View v;
	
	/**
	 * Constructor setting the Model and View from the controller.
	 * 
	 * @param m [IN] The model facade {@link Model}.
	 * @param v [IN] The view facade {@link View}.
	 */
	public SolveMazeCommand(Model m, View v) {
		this.m = m;
		this.v = v;
	}
	
	@Override
	public void doCommand(String args) throws Exception {
		String[] parameters = args.split(" ");
		m.solveMaze(parameters[0], parameters[1], parameters[2]);
	}

}
