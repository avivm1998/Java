package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Shell;

import algorithms.mazeGenerators.Position;

public abstract class GeneralPlayer {
	
	Position position;
	Shell parent;
	
	/**
	 * 
	 * @param parent - the window in which the character is
	 */
	public GeneralPlayer(Shell parent) {
		this.parent = parent;
	}
	
	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * @return the x value of the character
	 */
	public int getX(){
		return this.position.getX();
	}

	/**
	 * @return the y value of the character
	 */
	public int getY(){
		return this.position.getY();
	}

	/**
	 * @return the z value of the character
	 */
	public int getZ(){
		return this.position.getZ();
	}
	
	/**
	 * moving the character up
	 */
	public void moveUp() {
		position.setX(position.getX() + 1);
	}
	
	/**
	 * moving the character down
	 */
	public void moveDown() {
		position.setX(position.getX() - 1);
	}
	
	/**
	 * moving the character left
	 */
	public void moveLeft() {
		position.setY(position.getZ() - 1);
	}
	
	/**
	 * moving the character right
	 */
	public void moveRight() {
		position.setY(position.getZ() + 1);
	}
	
	/**
	 * moving the character forward
	 */
	public void moveForward() {
		position.setX(position.getY() - 1);
	}
	
	/**
	 * moving the character backward
	 */
	public void moveBackward() {
		position.setY(position.getY() + 1);
	}
	
	/**
	 * drawing the character 
	 * 
	 * @param e - the {@link PaintEvent}
	 * @param x - the x value in the canvas
	 * @param y - the y value in the canvas
	 * @param width - the width of the character
	 * @param height - the height of the character
	 */
	public abstract void draw(PaintEvent e,int x,int y,int width,int height);
}
