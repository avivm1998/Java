package view;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

public class Maze2D extends MazeDisplayer{
	
	 public GeneralPlayer player;
	 public Image walls;
	 public Image winScreen;
	 
	 public Maze2D(Composite parent,int style){
	        super(parent, style);
	    	
	        try {
				walls = new Image(this.getDisplay(), new FileInputStream("C:/Users/moranav/Downloads/wwe.jpg"));
				winScreen = new Image(this.getDisplay(), new FileInputStream("C:/Users/moranav/Downloads/champ.jpg"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	       
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
					          if(mazeData[i][j]!=0)
					              e.gc.drawImage(walls, x, y);
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

	private void moveCharacter(int row,int col){
		if(row >= 0 && row < mazeData[0].length && col >= 0 && col < mazeData.length && mazeData[col][row] == 0){
			player.position.setY(row);
			player.position.setZ(col);
			getDisplay().syncExec(new Runnable() {
				
				@Override
				public void run() {
					redraw();
				}
			});
		}
	}
	
	@Override
	public void moveUp() {
		player.moveForward();
	}

	@Override
	public void moveDown() {
		player.moveBackward();
	}

	@Override
	public void moveLeft() {
		player.moveLeft();
	}

	@Override
	public void moveRight() {
		player.moveRight();
	}

}
