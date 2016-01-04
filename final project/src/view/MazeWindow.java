package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

public class MazeWindow extends BasicWindow implements View {

	//Timer timer;
	//TimerTask task;
	String fileName;
	MazeDisplayer mazeDisplayer;
	Position player;
	String mazeName;
	
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
		shell.setLayout(new GridLayout(2,false));
		
		Menu menuBar = new Menu(shell, SWT.BAR);
		Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
		Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
		
		MenuItem fileHeader = new MenuItem(menuBar, SWT.CASCADE);
		fileHeader.setText("File");
		fileHeader.setMenu(fileMenu);
		
		MenuItem helpHeader = new MenuItem(menuBar, SWT.CASCADE);
		helpHeader.setText("Help");
		helpHeader.setMenu(helpMenu);
		
		MenuItem saveFile = new MenuItem(fileMenu, SWT.PUSH);
		saveFile.setText("Save");
		saveFile.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Shell mazeSaver = new Shell(display);
				mazeSaver.setSize(250, 300);
				mazeSaver.setLayout(new GridLayout(2,false));
				
				Label sourceMazeName = new Label(mazeSaver, SWT.BORDER);
				sourceMazeName.setText("Wanted maze name: ");
				sourceMazeName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,1));
				
				Text sourceMazeNameInput = new Text(mazeSaver, SWT.BORDER);
				sourceMazeNameInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,1));
				sourceMazeNameInput.setText("");
				
				Label destFileName = new Label(mazeSaver, SWT.BORDER);
				destFileName.setText("Wanted file name and file type: ");
				destFileName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,1));
				
				Text destFileNameInput = new Text(mazeSaver, SWT.BORDER);
				destFileNameInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,1));
				destFileNameInput.setText("");
				
				Button generate = new Button(mazeSaver, SWT.BORDER);
				generate.setText("Save!");
				generate.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
				generate.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						if(!(destFileNameInput.getText().equals("") || sourceMazeNameInput.getText().equals(""))) {
							mazeName = sourceMazeNameInput.getText();
							setChanged();
							notifyObservers("save maze " + sourceMazeNameInput.getText() + " " + destFileNameInput.getText());
							mazeSaver.dispose();
						}
						
						
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {}
				});
				
				mazeSaver.open();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			
			
		});
		
		MenuItem loadFile = new MenuItem(fileMenu, SWT.PUSH);
		loadFile.setText("Load");
		loadFile.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Shell mazeLoader = new Shell(display);
				mazeLoader.setSize(250, 300);
				mazeLoader.setLayout(new GridLayout(2,false));
				
				Label fileName = new Label(mazeLoader, SWT.BORDER);
				fileName.setText("Wanted file name: ");
				fileName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,1));
				
				Text fileNameInput = new Text(mazeLoader, SWT.BORDER);
				fileNameInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,1));
				fileNameInput.setText("");
				
				Label destMazeName = new Label(mazeLoader, SWT.BORDER);
				destMazeName.setText("Wanted destination maze name: ");
				destMazeName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,1));
				
				Text mazeNameInput = new Text(mazeLoader, SWT.BORDER);
				mazeNameInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,1));
				mazeNameInput.setText("");
				
				Button generate = new Button(mazeLoader, SWT.BORDER);
				generate.setText("Load!");
				generate.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
				generate.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						if(!(fileNameInput.getText().equals("") || mazeNameInput.getText().equals(""))) {
							mazeName = mazeNameInput.getText();
							setChanged();
							notifyObservers("load maze " + fileNameInput.getText() + " " + mazeNameInput.getText());
							mazeLoader.dispose();
						}
						
						
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {}
				});
				
				mazeLoader.open();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			
			
		});
		
		MenuItem propertiesFile = new MenuItem(fileMenu, SWT.PUSH);
		propertiesFile.setText("Load Properties");
		propertiesFile.addSelectionListener(new SelectionListener() {
			
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
		
		MenuItem exit = new MenuItem(fileMenu, SWT.PUSH);
		exit.setText("Exit");
		exit.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setChanged();
				notifyObservers("exit");
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		MenuItem solve = new MenuItem(helpMenu, SWT.PUSH);
		solve.setText("Solve");
		solve.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setChanged();
				notifyObservers("solve " + mazeName + " " + "bfs");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		MenuItem hint = new MenuItem(helpMenu, SWT.PUSH);
		hint.setText("Hint");
		
		shell.setMenuBar(menuBar);
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
		
		
		Button generate = new Button(shell, SWT.PUSH);
		generate.setText("Generate Maze3d");
		generate.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		generate.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Shell mazeGeneration = new Shell(display);
				mazeGeneration.setSize(250, 300);
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
							mazeName = nameInput.getText();
							setChanged();
							notifyObservers("generate 3d maze " + nameInput.getText() + " " + floorsInput.getText() + " " + floorWidthInput.getText() + " " + floorLengthInput.getText());
							mazeGeneration.dispose();
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
		
		/*
		 * if(arg0.keyCode == SWT.ARROW_DOWN)
					mazeDisplayer.moveDown();
				else if(arg0.keyCode == SWT.ARROW_UP)
					mazeDisplayer.moveUp();
				else if(arg0.keyCode == SWT.ARROW_LEFT)
					mazeDisplayer.moveLeft();
				else if(arg0.keyCode == SWT.ARROW_RIGHT)
					mazeDisplayer.moveRight();
		 */
		
			/*
			 * mazeDisplayer.addKeyListener(new KeyListener() {
			
				@Override
				public void keyReleased(KeyEvent arg0) {}
				
				@Override
				public void keyPressed(KeyEvent arg0) {
					ArrayList<String> pos = new ArrayList<String>(Arrays.asList(maze.getPossibleMoves(character)));
					switch (arg0.keyCode) {
					case SWT.PAGE_UP:
						if (pos.contains(character.getUp().toString())) {
							player.setX(player.getX()+1);
						}else{
							MessageBox ms = new MessageBox(shell);
							ms.setMessage("Cant move there");
							ms.open();
						}
						break;
	
					case SWT.PAGE_DOWN:
						if (pos.contains(character.getDown().toString())) {
							player.setX(player.getX()-1);
						}else{
							MessageBox ms = new MessageBox(shell);
							ms.setMessage("Cant move there");
							ms.open();
						}
						break;
						
					case SWT.ARROW_RIGHT:
						if (pos.contains(character.getRight().toString())) {
							player.setX(player.getY()+1);
						}else{
							MessageBox ms = new MessageBox(shell);
							ms.setMessage("Cant move there");
							ms.open();
						}
						break;
						
					case SWT.ARROW_LEFT:
						if (pos.contains(character.getLeft().toString())) {
							player.setX(player.getX()-1);
						}else{
							MessageBox ms = new MessageBox(shell);
							ms.setMessage("Cant move there");
							ms.open();
						}
						break;
						
					case SWT.ARROW_DOWN:
						if (pos.contains(character.getForward().toString())) {
							player.setX(player.getZ()+1);
						}else{
							MessageBox ms = new MessageBox(shell);
							ms.setMessage("Cant move there");
							ms.open();
						}
						break;
						
					case SWT.ARROW_UP:
						if (pos.contains(character.getBackward().toString())) {
							player.setX(player.getX()-1);
						}else{
							MessageBox ms = new MessageBox(shell);
							ms.setMessage("Cant move there");
							ms.open();
						}
						break;
					}
					mazeDisplayer.setCharacterPosition(row, col);;
					
					switch (m.getCross()) {
					case 0:
						setChanged();
						notifyObservers("display cross section by X "+player.getX()+" for "+mazeName);
						break;
					case 1:
						setChanged();
						notifyObservers("display cross section by Y "+player.getY()+" for "+mazeName);
						break;
					case 2:
						setChanged();
						notifyObservers("display cross section by Z "+player.getZ()+" for "+mazeName);;
						break;
					}
					mazeDisplayer.redraw();
				}
			});
			 */
		
		
		
		
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
		/*
		 * this.getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				String[] s = args.split("\n");
				
				Shell message = new Shell(display);
				message.setSize(100, 70);
				message.setLayout(new GridLayout(1, false));
				
				Text text = new Text(message, SWT.None);
				text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, s.length));
				text.setText(args);
				
				message.open();
				
			}
		});
		 */
		
		this.getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				MessageBox msg = new MessageBox(shell, SWT.BORDER);
				msg.setMessage(args);
				msg.open();
			}
		});
		
	}
	
	public void display(Maze3d maze) {
		
		this.getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				if(mazeDisplayer == null) {
					mazeDisplayer = new Maze3D(shell, SWT.BORDER);
					mazeDisplayer.setMazeData(maze.getCrossSectionByX(maze.getEntrance().getX() + 1));
					mazeDisplayer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,2));
					mazeDisplayer.redraw();
					shell.setSize(shell.getSize().x + 1, shell.getSize().y + 1);
				}
				
				else {
					mazeDisplayer.setMazeData(maze.getCrossSectionByX(maze.getEntrance().getX() + 1));
					mazeDisplayer.redraw();
				}
				/*
				 * mazeDisplayer.addKeyListener(new KeyListener() {
					
					@Override
					public void keyReleased(KeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void keyPressed(KeyEvent arg0) {
						if(arg0.keyCode == SWT.ARROW_DOWN)
							mazeDisplayer.moveDown();
						else if(arg0.keyCode == SWT.ARROW_UP)
							mazeDisplayer.moveUp();
						else if(arg0.keyCode == SWT.ARROW_LEFT)
							mazeDisplayer.moveLeft();
						else if(arg0.keyCode == SWT.ARROW_RIGHT)
							mazeDisplayer.moveRight();
					}
				});
				 */
			}
		});
	}
	
	@Override
	public void getUserCommand() {
		this.run();
	}
	
	
}
