package presenter;

import model.Model;
import view.View;

/**
 * SaveMazeCommand saves and compresses the given maze to the given file.
 * When the file is ready, the command will send a message to the user.
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public class SaveMazeCommand implements Command {
	
	Model m;
	View v;
	
	/**
	 * Constructor setting the Model and View from the controller.
	 * 
	 * @param m [IN] The model facade {@link Model}.
	 * @param v [IN] The view facade {@link View}.
	 */
	public SaveMazeCommand(Model m, View v) {
		this.m = m;
		this.v = v;
		
	}
	
	@Override
	public void doCommand(String args) throws Exception {
		String[] parameters = args.split(" ");
		
		try {
			m.saveCompressedMaze(parameters[0], parameters[1]);
		} catch(Exception e) {
			throw e;
		}
	}

}
