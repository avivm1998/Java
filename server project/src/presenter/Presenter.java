package presenter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.Model;
import view.ClientHandler;
import view.View;

/**
 * The presenter is in charge of connecting the view and the model using MVP. 
 * 
 * @author Aviv Mora & Ayal Naim
 *
 */
public class Presenter implements Observer {
	private HashMap<String, Command> commandPool;
	private Model m;
	private View v;
	private HashMap<String,OutputStream> clients;
	private int clientCounter = 0;
	private Integer currentClient;
	
	/**
	 * Constructor with parameters.
	 * 
	 * @param m [IN] the model
	 * @param v [IN] the view
	 */
	public Presenter(Model m, View v) {
		this.m = m;
		this.v = v;
		this.initCommandPool();
		this.clients = new HashMap<String, OutputStream>();
	}
	
	/**
	 * Puts all the commands in the command pool
	 */
	public void initCommandPool() {
		commandPool = new HashMap<String, Command>();
		commandPool.put("dir", new DirCommand(m, v));
		commandPool.put("generate", new GenerateMaze3dCommand(m, v));
		commandPool.put("display", new DisplayMazeCommand(m, v));
		commandPool.put("display cross x", new DisplayCrossSectionByXCommand(m, v));
		commandPool.put("display cross y", new DisplayCrossSectionByYCommand(m, v));
		commandPool.put("display cross z", new DisplayCrossSectionByZCommand(m, v));
		commandPool.put("save", new SaveMazeCommand(m, v));
		commandPool.put("load", new LoadMazeCommand(m, v));
		commandPool.put("maze size", new DisplayMazeSizeCommand(m, v));
		commandPool.put("file size", new DisplayMazeSizeInFileCommand(m, v));
		commandPool.put("solve", new SolveMazeCommand(m, v));
		commandPool.put("solution", new DisplaySolutionCommand(m, v));
		commandPool.put("exit", new ExitCommand(m, v));
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof ClientHandler) {
			if(arg1 instanceof Properties) {
				m.setProperties((Properties)arg1);
			}
			else if(arg1 instanceof OutputStream) {
				clientCounter++;
				clients.put("" + clientCounter, (OutputStream)arg1);
			}
			else
				try {
						String[] parameters = ((String)arg1).split(" ");
						
						switch(parameters[1]) {
							case "exit":
								commandPool.get("exit").doCommand(null);
								return;
							case "dir":
								commandPool.get("dir").doCommand(parameters[2]);
								break;
							case "generate":
								commandPool.get("generate").doCommand(parameters[0] + " " + parameters[4] + " " + parameters[5] + " " + parameters[6] + " " + parameters[7]);
								break;
							case "save":
								commandPool.get("save").doCommand(parameters[0] + " " + parameters[3] + " " + parameters[4]);
								break;
							case "load":
								commandPool.get("load").doCommand(parameters[0] + " " + parameters[3] + " " + parameters[4]);
								break;
							case "maze":
								commandPool.get("maze size").doCommand(parameters[3]);
								break;
							case "file":
								commandPool.get("file size").doCommand(parameters[3]);
								break;
							case "solve": 
								commandPool.get("solve").doCommand(parameters[0] + " " + parameters[2] + " " + parameters[3]);
								break;
							case "display":
								switch (parameters[1]) {
									case "cross":
										if(parameters[5].equals("X"))
											commandPool.get("display cross x").doCommand(parameters[6] + " " + parameters[8]);
										else if(parameters[4].equals("Y"))
											commandPool.get("display cross y").doCommand(parameters[6] + " " + parameters[8]);
										else
											commandPool.get("display cross z").doCommand(parameters[6] + " " + parameters[8]);
										break;
										
									case "solution":
										commandPool.get("solution").doCommand(parameters[3]);
										break;
		
									default:
										commandPool.get("display").doCommand(parameters[2]);
										break;
								}
								break;
							default:
								v.display("Invalid Command!");
						}
				} catch(IOException e) {
					e.printStackTrace();
				} catch(Exception e) {
					e.printStackTrace();
			}
		}
		
		if(arg0 instanceof Model) {
			if(arg1 instanceof Integer)
				currentClient = (Integer)arg1;
			else if(arg1 instanceof Maze3d)
				v.sendMazeToClient((Maze3d) arg1, clients.get("" + currentClient));
			else if(arg1 instanceof Solution) 
				v.sendSolutionToClient((Solution<Position>)arg1, clients.get("" + currentClient));
			else if(arg1 instanceof String)
				v.sendStringToClient((String)arg1, clients.get("" + currentClient));
			else
				v.sendStringToClient("Something went wrong!", clients.get("" + currentClient));
		}
	}

	/**
	 * Returns the command pool.
	 * 
	 * @return
	 */
	public HashMap<String, Command> getCommandPool() {
		return commandPool;
	}

	/**
	 * Sets the command pool.
	 * 
	 * @param commandPool
	 */
	public void setCommandPool(HashMap<String, Command> commandPool) {
		this.commandPool = commandPool;
	}

}
