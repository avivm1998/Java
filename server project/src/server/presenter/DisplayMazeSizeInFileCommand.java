package server.presenter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.Maze3d;
import io.MyCompressorOutputStream;
import server.model.Model;
import server.view.View;

/**
 * DisplayMazeSizeInFile displays the size of the compressed maze inside the file.
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public class DisplayMazeSizeInFileCommand implements Command {

	Model m;
	View v;
	
	/**
	 * Constructor setting the Model and View from the controller.
	 * 
	 * @param m [IN] The model facade {@link Model}.
	 * @param v [IN] The view facade {@link View}.
	 */
	public DisplayMazeSizeInFileCommand(Model m, View v) {
		this.m = m;
		this.v = v;
		
	}
	
	@Override
	public void doCommand(String args) throws Exception {
		Maze3d maze = new Maze3d();
		
		try {
			maze = m.getMaze3d(args);
			OutputStream out=new MyCompressorOutputStream(new FileOutputStream("temp.txt"));
			out.write(maze.toByteArray());
			out.flush();
			out.close();
		} catch(Exception e) {
			throw e;
		}
		
		v.display("" + new File("temp.txt").length());
	}

}
