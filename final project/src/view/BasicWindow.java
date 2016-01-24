
package view;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public abstract class BasicWindow extends Observable implements Runnable { 
	
	Display display;
	Shell shell;
	
	/**
	 * 
	 * @param title
	 * @param width
	 * @param height
	 */
 	public BasicWindow(String title, int width,int height) {
 		display = new Display();
 		shell  = new Shell(display);
 		shell.setSize(width,height);
 		shell.setText(title);
	}
 	
 	/**
 	 * 
 	 * @return
 	 */
 	public Display getDisplay() {
		return display;
	}


 	/**
 	 * 
 	 * @param display
 	 */
	public void setDisplay(Display display) {
		this.display = display;
	}


	/**
	 * 
	 * @return
	 */
	public Shell getShell() {
		return shell;
	}


	/**
	 * 
	 * @param shell
	 */
	public void setShell(Shell shell) {
		this.shell = shell;
	}


	/**
	 * 
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
