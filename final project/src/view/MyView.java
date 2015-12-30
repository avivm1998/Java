package view;

import java.util.Observable;

/**
 *  MyView is a concrete View in the MVC pattern, connecting the model and view and holds all the valid commands.

 * @author Aviv Moran & Ayal Naim
 *
 */
public abstract class MyView extends Observable implements View {
	
	public abstract void display(String args);

	public abstract void getUserCommand();
	
	@Override
	public void notifyObservers() {
		super.notifyObservers();
	}
	
}
