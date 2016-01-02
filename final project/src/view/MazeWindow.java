package view;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MazeWindow extends BasicWindow implements View {

	Timer timer;
	TimerTask task;
	String fileName;
	
	public MazeWindow(String title, int width, int height) {
		super(title, width, height);
	}

	/*	private void randomWalk(MazeDisplayer maze){
		Random r=new Random();
		boolean b1,b2;
		b1=r.nextBoolean();
		b2=r.nextBoolean();
		if(b1&&b2)
			maze.moveUp();
		if(b1&&!b2)
			maze.moveDown();
		if(!b1&&b2)
			maze.moveRight();
		if(!b1&&!b2)
			maze.moveLeft();
		
		maze.redraw();
	}
	 */
	
	@Override
	void initWidgets() {
		
		
		/*
		 * Button startButton=new Button(shell, SWT.PUSH);
		startButton.setText("Start");
		startButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
				
		
		//MazeDisplayer maze=new Maze2D(shell, SWT.BORDER);		
		MazeDisplayer maze=new Maze3D(shell, SWT.BORDER);
		maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,2));
		
		Button stopButton=new Button(shell, SWT.PUSH);
		stopButton.setText("Stop");
		stopButton.setLayoutData(new GridData(SWT.None, SWT.None, false, false, 1, 1));
		stopButton.setEnabled(false);
		startButton.addSelectionListener(new SelectionListener() {
		@Override
			public void widgetSelected(SelectionEvent arg0) {
				timer=new Timer();
				task=new TimerTask() {
					@Override
					public void run() {
						display.syncExec(new Runnable() {
							@Override
							public void run() {
								randomWalk(maze);
							}
						});
					}
				};				
				timer.scheduleAtFixedRate(task, 0, 100);				
				startButton.setEnabled(false);
				stopButton.setEnabled(true);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		stopButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				task.cancel();
				timer.cancel();
				startButton.setEnabled(true);
				stopButton.setEnabled(false);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		 */
		
		shell.setLayout(new GridLayout(2,false));
		
		Button generate = new Button(shell, SWT.PUSH);
		generate.setText("Generate Maze3d");
		generate.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		generate.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Shell mazeGeneration = new Shell(display);
				mazeGeneration.setLayout(new GridLayout(2,false));
				
				Label name = new Label(mazeGeneration, SWT.BORDER);
				name.setText("Wanted maze name: ");
				name.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,1));
				
				Text nameInput = new Text(mazeGeneration, SWT.BORDER);
				nameInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,1));
				nameInput.setText("");
				
				Label floors = new Label(mazeGeneration, SWT.BORDER);
				floors.setText("Wanted number of floors: ");
				floors.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,1));
				
				Text floorsInput = new Text(mazeGeneration, SWT.BORDER);
				floorsInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,1));
				floorsInput.setText("");
				
				Label floorWidth = new Label(mazeGeneration, SWT.BORDER);
				floorWidth.setText("Wanted floor width: ");
				floorWidth.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,1));
				
				Text floorWidthInput = new Text(mazeGeneration, SWT.BORDER);
				floorWidthInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,1));
				floorWidthInput.setText("");
				
				Label floorLength = new Label(mazeGeneration, SWT.BORDER);
				floorLength.setText("Wanted floor length: ");
				floorLength.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,1));
				
				Text floorLengthInput = new Text(mazeGeneration, SWT.BORDER);
				floorLengthInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,1));
				floorLengthInput.setText("");
				
				Button generate = new Button(mazeGeneration, SWT.BORDER);
				generate.setText("Generate!");
				generate.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
				generate.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						if(!(nameInput.getText().equals("") || floorsInput.getText().equals("") || floorWidthInput.getText().equals("") || floorLengthInput.getText().equals(""))) {
							setChanged();
							notifyObservers("generate 3d maze " + nameInput.getText() + " " + floorsInput.getText() + " " + floorWidthInput.getText() + " " + floorLengthInput.getText());
						}
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
					}
					
				});
				
				mazeGeneration.open();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		MazeDisplayer maze=new Maze3D(shell, SWT.BORDER);
		maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,2));
		
		Button openProperties = new Button(shell, SWT.PUSH);
		openProperties.setText("Set Properties");
		openProperties.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		
		openProperties.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fd = new FileDialog(shell,SWT.OPEN);
				fd.setText("open");
				fd.setFilterPath("");
				String[] filterExt = { "*.xml"};
				fd.setFilterExtensions(filterExt);
				fileName = fd.open();				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		/*
		 * Button displayButton = new Button(shell, SWT.BORDER);
		displayButton.setText("Display Maze3d");
		displayButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		displayButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				Shell mazeDisplay = new Shell(display);
				mazeDisplay.setLayout(new GridLayout(2,false));
				
				Label name = new Label(mazeDisplay, SWT.BORDER);
				name.setText("Wanted maze name: ");
				name.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,1));
				
				Text nameInput = new Text(mazeDisplay, SWT.BORDER);
				nameInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,1));
				nameInput.setText("");
				
				Button generate = new Button(mazeDisplay, SWT.BORDER);
				generate.setText("Display!");
				generate.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
				generate.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						if(!(nameInput.getText().equals(""))) {
							setChanged();
							notifyObservers("display " + nameInput.getText());
						}
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
					}
					
				});
				
				mazeDisplay.open();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		 */
	}
	
	@Override
	public void display(String args) {
		this.getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				String[] s = args.split("\n");
				
				Shell message = new Shell(display);
				message.setLayout(new GridLayout(1, false));
				
				Text text = new Text(message, SWT.None);
				text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, s.length));
				text.setText(args);
				
				message.open();
				
			}
		});
	}
	
	@Override
	public void getUserCommand() {
		this.run();
	}
	
	
}
