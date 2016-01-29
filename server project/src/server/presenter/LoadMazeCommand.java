package server.presenter;

import server.model.Model;
import server.view.View;

/**
 * LoadMazeCommand loads and decompresses the maze written in the given file the the given maze name.
 * When the maze is ready, the command will send a message to the user.
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public class LoadMazeCommand implements Command {
	
	Model m;
	View v;
	
	/**
	 * Constructor setting the Model and View from the controller.
	 * 
	 * @param m [IN] The model facade {@link Model}.
	 * @param v [IN] The view facade {@link View}.
	 */
	public LoadMazeCommand(Model m, View v) {
		this.m = m;
		this.v = v;
		
	}
	@Override
	public void doCommand(String args) throws Exception {
		String[] parameters = args.split(" ");
		
		try {
			m.loadDecompressedMaze(parameters[0], parameters[1], parameters[2]);
		} catch(Exception e) {
			throw e;
		}
	}

}
