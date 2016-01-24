
package view;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * A basic window display that is used in any window. 
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public abstract class BasicWindow extends Observable implements Runnable { 
	
	Display display;
	Shell shell;
	
	/**
	 * Constructor with parameters.
	 * 
	 * @param title  [IN] the window's title.
	 * @param width  [IN] the window's width.
	 * @param height [IN] the window's height.
	 */
 	public BasicWindow(String title, int width,int height) {
 		display = new Display();
 		shell  = new Shell(display);
 		shell.setSize(width,height);
 		shell.setText(title);
	}
 	
 	/**
 	 * Returns the display.
 	 * 
 	 * @return
 	 */
 	public Display getDisplay() {
		return display;
	}


 	/**
 	 * Sets the display.
 	 * 
 	 * @param display [IN] the display.
 	 */
	public void setDisplay(Display display) {
		this.display = display;
	}


	/**
	 * Returns the shell.
	 * 
	 * @return
	 */
	public Shell getShell() {
		return shell;
	}


	/**
	 * Sets the shell.
	 * 
	 * @param shell [IN] the shell.
	 */
	public void setShell(Shell shell) {
		this.shell = shell;
	}

	/**
	 * Initializes the widgets as implemented.
	 */
	abstract void initWidgets();

	@Override
	public void run() {
		initWidgets();
		shell.open();
		// main event loop
		 while(!shell.isDisposed()){ // while window isn't closed

		    // 1. read events, put then in a queue.
		    // 2. dispatch the assigned listener
		    if(!display.readAndDispatch()){ 	// if the queue is empty
		       display.sleep(); 			// sleep until an event occurs 
		    }

		 } // shell is disposed

		 display.dispose(); // dispose OS components
	}
	

}
