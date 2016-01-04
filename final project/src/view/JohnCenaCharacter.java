package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;

public class JohnCenaCharacter extends CommonCharacter {
	
	Image character;
	
	public JohnCenaCharacter(Shell parent) {
		super(parent);
		try {
			character = new Image(parent.getDisplay(), new FileInputStream("resources/john_cena.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(PaintEvent e, int x, int y, int width, int height) {
		e.gc.drawImage(character, 0, 0, character.getBounds().width, character.getBounds().height, x, y, width, height);

	}

}
