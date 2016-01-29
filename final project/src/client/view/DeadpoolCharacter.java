package client.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;

import algorithms.mazeGenerators.Position;

/**
 * DeadpoolCharacter is the class that represents our Deadpool character.
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public class DeadpoolCharacter extends CommonCharacter {

	Image character;
	
	/**
	 * Constructor with parameters.
	 * 
	 * @param parent [IN] the shell.
	 * @param start  [IN] the starting position.
	 */
	public DeadpoolCharacter(Shell parent, Position start) {
		super(parent, start);
		try {
			character = new Image(parent.getDisplay(), new FileInputStream("resources/Deadpool.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void draw(PaintEvent e, int x, int y, int width, int height) {
		e.gc.drawImage(character, 0, 0, character.getBounds().width, character.getBounds().height, x, y, width, height);

	}


}
