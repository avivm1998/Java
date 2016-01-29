package server.presenter;

import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import server.model.Model;
import server.view.View;

/**
 * DisplaySolutionCommand display the solution of the given maze from the solution HashMap sent from the model.
 */
public class DisplaySolutionCommand implements Command {
	
	Model m;
	View v;
	
	/**
	 * Constructor setting the Model and View from the controller.
	 * 
	 * @param m [IN] The model facade {@link Model}.
	 * @param v [IN] The view facade {@link View}.
	 */
	public DisplaySolutionCommand(Model m, View v) {
		this.m = m;
		this.v = v;
		
	}
	
	@Override
	public void doCommand(String args) throws Exception {
		Solution<Position> sol = new Solution<Position>();
		
		try {
			sol = m.getSolution(args);
		} catch(Exception e) {
			throw e;
		}
		
		v.display(sol.toString());
	}

}
