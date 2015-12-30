package presenter;

import model.Model;
import view.View;

/**
 * ExitCommand exits the program safely, making sure every file is closed and lets the running threads finish.
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public class ExitCommand implements Command {

	Model m;
	View v;
	
	/**
	 * Constructor setting the Model and View from the controller.
	 * 
	 * @param m [IN] The model facade {@link Model}.
	 * @param v [IN] The view facade {@link View}.
	 */
	public ExitCommand(Model m, View v) {
		this.m = m;
		this.v = v;
		
	}
	@Override
	public void doCommand(String args) {
		m.exit();
	}

}
