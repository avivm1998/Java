package presenter;

import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.Model;
import view.View;

/**
 * The Presenter is in charge of connecting the view and the model in the MVP architecture. 
 * 
 * @author Aviv Mora & Ayal Naim
 *
 */
public class Presenter implements Observer {
	private Model m;
	private View v;
	
	/**
	 * Constructor with parameters, Setting the model and view of the architecture.
	 * 
	 * @param m [IN] The model.
	 * @param v [IN] The view.
	 */
	public Presenter(Model m, View v) {
		this.m = m;
		this.v = v;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof View) {
			if(arg1 instanceof Properties) {
				m.setProperties((Properties)arg1);
			}
			
			else {
				String[] parameters = ((String)arg1).split(" ");
				
				try {
					switch(parameters[0]) {
						case "exit":
							m.exit();
							return;
						case "generate":
							m.generateMaze3d(parameters[3], Integer.parseInt(parameters[4]), Integer.parseInt(parameters[5]), Integer.parseInt(parameters[6]));
							break;
						case "save":
							m.saveCompressedMaze(parameters[2], parameters[3]);
							break;
						case "load":
							m.loadDecompressedMaze(parameters[2], parameters[3]);
							break;
						case "solve": 
							m.solveMaze(parameters[1], parameters[2]);;
							break;
						default:
							v.display("Invalid Command!");
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if(arg0 instanceof Model) {
			if(arg1 instanceof Maze3d)
				v.display((Maze3d)arg1);
			else if(arg1 instanceof Solution) 
				v.display((Solution<Position>)arg1);
			else if(arg1 instanceof String)
				v.display((String)arg1);
			else
				v.display("Something went wrong!");
		}

	}

}
