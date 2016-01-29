package server.presenter;

import java.io.File;

import server.model.Model;
import server.view.View;

/**
 * Dircommand is a command that displays all the files and directories of a given path.
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public class DirCommand implements Command {
	
	Model m;
	View v;
	
	/**
	 * Constructor setting the Model and View from the controller.
	 * 
	 * @param m [IN] The model facade {@link Model}.
	 * @param v [IN] The view facade {@link View}.
	 */
	public DirCommand(Model m, View v) {
		this.m = m;
		this.v = v;
	}
	
	@Override
	public void doCommand(String args) throws Exception{
		StringBuilder result = new StringBuilder();
		
		try {
			File[] filesList = m.getFileDirectoryList(new File(args));
			
	        for(File f : filesList){
	            if(f.isDirectory())
	                result.append(f.getName() + "\n");
	            if(f.isFile()){
	            	result.append(f.getName() + "\n");
	            }
	        }
		} catch(Exception e) {
			throw e;
		}
		
		v.display(result.toString());
	}

}
