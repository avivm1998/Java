package view;


import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Position;
import algorithms.search.State;

/**
 * Maze2D is a 2D maze displayer class.
 * It is in charge of most of the images and the movement.
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public class Maze2D extends MazeDisplayer{
	
	 public Image walls;
	 public Image floorDownPassage;
	 public Image floorUpPassage;
	 public Image upAndDownPassage;
	 public Image goal;
	 public Image winScreen;
	 public Image solutionPath;
	 
	 /**
	  * Constructor with parameters.
	  * 
	  * @param parent [IN] 
	  * @param style  [IN]
	  * @param player [IN]
	  */
	 public Maze2D(Composite parent,int style, CommonCharacter player, int theme){
	        super(parent, style);
	    	
	        try {
	        	if(theme == 2) {
	        		walls = new Image(this.getDisplay(), new FileInputStream("resources/Deadpool_walls.jpg"));
	        		floorDownPassage = new Image(this.getDisplay(), new FileInputStream("resources/down_arrow.png"));
	        		floorUpPassage = new Image(this.getDisplay(), new FileInputStream("resources/up_arrow.png"));
	        		upAndDownPassage = new Image(this.getDisplay(), new FileInputStream("resources/up_down_arrow.png"));
	        		goal = new Image(this.getDisplay(), new FileInputStream("resources/chimichanga.jpg"));
	        		winScreen = new Image(this.getDisplay(), new FileInputStream("resources/Deadpool_win_screen.jpg"));
	        		solutionPath = new Image(this.getDisplay(), new FileInputStream("resources/two_guns.gif"));
	        	}
	        	else {
	        		walls = new Image(this.getDisplay(), new FileInputStream("resources/wwe.jpg"));
	        		floorDownPassage = new Image(this.getDisplay(), new FileInputStream("resources/down_arrow.png"));
	        		floorUpPassage = new Image(this.getDisplay(), new FileInputStream("resources/up_arrow.png"));
	        		upAndDownPassage = new Image(this.getDisplay(), new FileInputStream("resources/up_down_arrow.png"));
	        		goal = new Image(this.getDisplay(), new FileInputStream("resources/championship_belt.png"));
	        		winScreen = new Image(this.getDisplay(), new FileInputStream("resources/win_screen.png"));
	        		solutionPath = new Image(this.getDisplay(), new FileInputStream("resources/solution_path.jpg"));
	        	}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
	       
	       this.player = player;
	        
	        // set a white background   (red, green, blue)
	        setBackground(new Color(null, 255, 255, 255));
	    	addPaintListener(new PaintListener() {
				
				@Override
				public void paintControl(PaintEvent e) {
					   e.gc.setForeground(new Color(null,0,0,0));
					   e.gc.setBackground(new Color(null,0,0,0));

					   int width=getSize().x;
					   int height=getSize().y;

					   int w=width/mazeData[0].length;
					   int h=height/mazeData.length;
					  
					   for(int i=0;i<mazeData.length;i++)
					      for(int j=0;j<mazeData[i].length;j++){
					          int x=j*w;
					          int y=i*h;

					          if(player.position.equals(maze.getGoalPosition())) {
								  e.gc.drawImage(winScreen, 0, 0, winScreen.getBounds().width, winScreen.getBounds().height, 0, 0, width, height);
								  lockedKeys = true;
								  System.out.println("GAME OVER!");
								  return;
					          }
					          if(showSolution == true && solution.getSolution().indexOf(new State<Position>(new Position(currentFloor, i, j))) != -1)
					        	  e.gc.drawImage(solutionPath, 0, 0, solutionPath.getBounds().width,  solutionPath.getBounds().height, x, y, w, h);
					          if(mazeData[i][j] == 1)
					        	  e.gc.drawImage(walls, 0, 0, walls.getBounds().width,  walls.getBounds().height, x, y, w, h);
					          if(player.position.getY() == i && player.position.getZ() == j)
					        	  player.draw(e, x, y, w, h);
					          if(mazeData[i][j] == 2) 
					        	  e.gc.drawImage(floorDownPassage, 0, 0, floorDownPassage.getBounds().width,  floorDownPassage.getBounds().height, x, y, w, h);
					          if(mazeData[i][j] == 3) 
					        	  e.gc.drawImage(floorUpPassage, 0, 0, floorUpPassage.getBounds().width,  floorUpPassage.getBounds().height, x, y, w, h);
					          if(mazeData[i][j] == 4) 
					        	  e.gc.drawImage(upAndDownPassage, 0, 0, upAndDownPassage.getBounds().width,  upAndDownPassage.getBounds().height, x, y, w, h);
					          if(mazeData[i][j] == 5) 
					        	  e.gc.drawImage(goal, 0, 0, goal.getBounds().width,  goal.getBounds().height, x, y, w, h);
					      }
					   
					}
			});
	    	
	    	addKeyListener(new KeyListener() {
				
				@Override
				public void keyReleased(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyPressed(KeyEvent arg0) {
					if(lockedKeys == true)
			        	  return;
					if(arg0.keyCode == SWT.ARROW_DOWN)
						moveDown();
					else if(arg0.keyCode == SWT.ARROW_UP)
						moveUp();
					else if(arg0.keyCode == SWT.ARROW_LEFT)
						moveLeft();
					else if(arg0.keyCode == SWT.ARROW_RIGHT)
						moveRight();
					else if(arg0.keyCode == SWT.PAGE_DOWN) {
						if(mazeData[player.getY()][player.getZ()] == 2 || mazeData[player.getY()][player.getZ()] == 4) {
							setMazeData(maze.getFloorState(currentFloor - 1), currentFloor - 1);
							player.position.setX(player.getX() - 1);
							redraw();
						}
					}
					else if(arg0.keyCode == SWT.PAGE_UP) {
						if(mazeData[player.getY()][player.getZ()] == 3 || mazeData[player.getY()][player.getZ()] == 4) {
							setMazeData(maze.getFloorState(currentFloor + 1), currentFloor + 1);
							player.position.setX(player.getX() + 1);
							redraw();
						}
					}
				}
			});
	    	
	    	addMouseWheelListener(new MouseWheelListener() {
				
				@Override
				public void mouseScrolled(MouseEvent arg0) {
					if((arg0.stateMask & SWT.CONTROL) == SWT.CONTROL)
					{
						if(arg0.count > 0){
	                        int width = getSize().x;
	                        int height = getSize().y;

	                        setSize((int)(width * 1.05), (int)(height * 1.05));


	                    }
	                    else {

	                        int width = getSize().x;
	                        int height = getSize().y;

	                        setSize((int)(width * 0.95), (int)(height * 0.95));

	                        }
					}
					
				}
			});
	 }

	@Override
	public void setCharacterPosition(int row, int col) {
		player.position.setY(row);
		player.position.setZ(col);
		moveCharacter(row, col);
	}

	/**
	 * Moves the character to a new position.
	 * 
	 * @param row
	 * @param col
	 */
	private void moveCharacter(int row,int col){
		if(row >= 0 && row < mazeData[0].length && col >= 0 && col < mazeData.length && mazeData[col][row] != 1){
			player.position.setY(row);
			player.position.setZ(col);
			redraw();
		}
	}
	
	@Override
	public void moveUp() {
		if(player.getY() > 0)	
			if(mazeData[player.getY() - 1][player.getZ()] != 1) {
				player.moveForward();
				redraw();
			}
	}

	@Override
	public void moveDown() {
		if(player.getY() < mazeData.length - 1)	
			if(mazeData[player.getY() + 1][player.getZ()] != 1) {
				player.moveBackward();
				redraw();
			}
	}

	@Override
	public void moveLeft() {
		if(player.getZ() > 0)
			if(mazeData[player.getY()][player.getZ() - 1] != 1) {
				player.moveLeft();
				redraw();
			}
	}

	@Override
	public void moveRight() {
		if(player.getZ() < mazeData[0].length - 1)
			if(mazeData[player.getY()][player.getZ() + 1] != 1)	{
				player.moveRight();
				redraw();
			}
	}

}
