package presenter;

import model.Model;
import view.View;

/**
 * DisplayCrossSectionByXCommand displays the plane of the given maze when the X dimension is the given index.
 * 
 * @author Aviv Moran & Ayal Naim
 * 
 */
public class DisplayCrossSectionByXCommand implements Command {

	Model m;
	View v;
	
	/**
	 * Constructor setting the Model and View from the controller.
	 * 
	 * @param m [IN] The model facade {@link Model}.
	 * @param v [IN] The view facade {@link View}.
	 * 
	 */
	public DisplayCrossSectionByXCommand(Model m, View v) {
		this.m = m;
		this.v = v;
		
	}
	
	@Override
	public void doCommand(String args) throws Exception{
		String[] parameters = args.split(" ");
		StringBuilder result = new StringBuilder();
		
		if(Integer.parseInt(parameters[0]) < 0 || Integer.parseInt(parameters[0]) > m.getMaze3d(parameters[1]).getNumberOfFloors())
			throw new Exception("Invalid index");
		
		int[][] plane = m.getCrossSection(parameters[1], "X",  Integer.parseInt(parameters[0]));
		
		for (int i = 0; i < plane.length; i++) {
			for (int j = 0; j < plane[0].length; j++) {
				result.append(plane[i][j]);
			}
			result.append('\n');
		}
		
		v.display(result.toString());
	}

}
