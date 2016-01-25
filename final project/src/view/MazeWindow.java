package view;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

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
import algorithms.search.Solution;
import presenter.Properties;

public class MazeWindow extends BasicWindow implements View {
	String fileName;
	MazeDisplayer mazeDisplayer;
	Position player;
	String mazeName;
	Properties settings;
	
	public MazeWindow(String title, int width, int height) {
		super(title, width, height);
	}

	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(2,false));
		
		Button generate = new Button(shell, SWT.PUSH);
		generate.setText("Generate Maze3d");
		generate.setEnabled(false);
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
							if(mazeDisplayer != null){
								mazeDisplayer.showSolution = false;
								mazeDisplayer.solution = null;
							}
							mazeName = nameInput.getText();
							setChanged();
							notifyObservers("generate 3d maze " + nameInput.getText() + " " + floorsInput.getText() + " " + floorWidthInput.getText() + " " + floorLengthInput.getText());
							mazeGeneration.dispose();
						}
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {}
				});
				
				mazeGeneration.open();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		Menu menuBar = new Menu(shell, SWT.BAR);
		Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
		Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
		
		MenuItem fileHeader = new MenuItem(menuBar, SWT.CASCADE);
		fileHeader.setText("File");
		fileHeader.setMenu(fileMenu);
		
		MenuItem helpHeader = new MenuItem(menuBar, SWT.CASCADE);
		helpHeader.setText("Help");
		helpHeader.setMenu(helpMenu);
		
		MenuItem solve = new MenuItem(helpMenu, SWT.PUSH);
		solve.setText("Solve");
		solve.setEnabled(false);
		solve.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				mazeDisplayer.showSolution = true;
				getDisplay().syncExec(new Runnable() {
					@Override
					public void run() {
						mazeDisplayer.redraw();
					}
				});
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		MenuItem hint = new MenuItem(helpMenu, SWT.PUSH);
		hint.setText("Hint");
		hint.setEnabled(false);
		hint.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(!mazeDisplayer.showSolution) {
					Random rand = new Random();
					mazeDisplayer.player.setPosition(mazeDisplayer.solution.getSolution().get(rand.nextInt(mazeDisplayer.solution.getSolution().size())).getState());
					display("You are on the right path!");
					getDisplay().syncExec(new Runnable() { 
						@Override
						public void run() {
							mazeDisplayer.redraw();
						}
					});
				}
				else 
					display("The solution is already shown!");
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		MenuItem saveFile = new MenuItem(fileMenu, SWT.PUSH);
		saveFile.setText("Save");
		saveFile.setEnabled(false);
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
		loadFile.setEnabled(false);
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
				fd.setFilterPath("resources");
				String[] filterExt = { "*.xml"};
				fd.setFilterExtensions(filterExt);
				fileName = fd.open();
				
				try {
					XMLDecoder in = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
					settings = (Properties)in.readObject();
					in.close();
					setChanged();
					notifyObservers(settings);
				
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				
				}
				
				saveFile.setEnabled(true);
				loadFile.setEnabled(true);
				solve.setEnabled(true);
				hint.setEnabled(true);
				generate.setEnabled(true);
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
		
		
		
		shell.setMenuBar(menuBar);
		
		
	}
	
	@Override
	public void display(String args) {
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
					mazeDisplayer = new Maze2D(shell, SWT.DOUBLE_BUFFERED | SWT.BORDER, new JohnCenaCharacter(shell, maze.getEntrance()));
					mazeDisplayer.maze = maze;
					mazeDisplayer.setMazeData(maze.getFloorState(maze.getEntrance().getX()), maze.getEntrance().getX());
					mazeDisplayer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,2));
					mazeDisplayer.redraw();
					shell.setSize(shell.getSize().x + 1, shell.getSize().y + 1);
					
				}
				
				else {
					mazeDisplayer.maze = maze;
					mazeDisplayer.setMazeData(maze.getFloorState(maze.getEntrance().getX()), maze.getEntrance().getX());
					mazeDisplayer.player.setPosition(maze.getEntrance());
					mazeDisplayer.redraw();
				}
			}
		});
		
		setChanged();
		notifyObservers("solve " + mazeName + " " + settings.getSearchingAlogrithm());
	}
	
	@Override
	public void display(Solution<Position> sol) {
		mazeDisplayer.solution = sol;
		getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				mazeDisplayer.redraw();
			}
		});
	}
	
	@Override
	public void getUserCommand() {
		this.run();
	}
}
