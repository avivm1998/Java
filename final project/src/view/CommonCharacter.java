package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Shell;

import algorithms.mazeGenerators.Position;

/**
 * CommonCharacter is a class that allows us to change our character easily.
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public abstract class CommonCharacter {
	
	Position position;
	Shell parent;
	
	/**
	 * Constructor with parameters, Setting the parent composite and starting position.
	 * 
	 * @param parent [IN] the window in which the character is in.
	 * @param start  [IN] the start position.
	 */
	public CommonCharacter(Shell parent, Position start) {
		this.parent = parent;
		this.position = start;
	}
	
	/**
	 * Returns the character's position. 
	 * 
	 * @return The position.
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Sets the position.
	 * 
	 * @param position [IN] The position to be set.
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * @return The current floor of the character.
	 */
	public int getX(){
		return this.position.getX();
	}

	/**
	 * @return The row of the character.
	 */
	public int getY(){
		return this.position.getY();
	}

	/**
	 * @return The column of the character.
	 */
	public int getZ(){
		return this.position.getZ();
	}
	
	/**
	 * Moving the character one step up.
	 */
	public void moveUp() {
		position.setX(position.getX() - 1);
	}
	
	/**
	 * Moving the character one step down.
	 */
	public void moveDown() {
		position.setX(position.getX() - 1);
	}
	
	/**
	 * Moving the character one step left.
	 */
	public void moveLeft() {
		position.setZ(position.getZ() - 1);
	}
	
	/**
	 * Moving the character one step right.
	 */
	public void moveRight() {
		position.setZ(position.getZ() + 1);
	}
	
	/**
	 * Moving the character one step forward.
	 */
	public void moveForward() {
		position.setY(position.getY() - 1);
	}
	
	/**
	 * Moving the character one step backwards.
	 */
	public void moveBackward() {
		position.setY(position.getY() + 1);
	}
	
	/**
	 * Drawing the character. 
	 * 
	 * @param e      [IN] The event {@link PaintEvent}
	 * @param x      [IN] The x value in the canvas
	 * @param y      [IN] The y value in the canvas
	 * @param width  [IN] The width of the character
	 * @param height [IN] The height of the character
	 */
	public abstract void draw(PaintEvent e,int x,int y,int width,int height);
}