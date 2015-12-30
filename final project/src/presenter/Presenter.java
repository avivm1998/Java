package presenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;
import model.Model;
import view.View;

public class Presenter implements Observer {
	private HashMap<String, Command> commandPool;
	private Model m;
	private View v;
	
	public Presenter(Model m, View v) {
		this.m = m;
		this.v = v;
		this.initCommandPool();
	}
	
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
		if(arg0 instanceof View) {
			try {
					String[] parameters = ((String)arg1).split(" ");
				
					switch(parameters[0]) {
						case "exit":
							commandPool.get("exit").doCommand(null);
							return;
						case "dir":
							commandPool.get("dir").doCommand(parameters[1]);
							break;
						case "generate":
							commandPool.get("generate").doCommand(parameters[3] + " " + parameters[4] + " " + parameters[5] + " " + parameters[6]);
							break;
						case "save":
							commandPool.get("save").doCommand(parameters[2] + " " + parameters[3]);
							break;
						case "load":
							commandPool.get("load").doCommand(parameters[2] + " " + parameters[3]);
							break;
						case "maze":
							commandPool.get("maze size").doCommand(parameters[2]);
							break;
						case "file":
							commandPool.get("file size").doCommand(parameters[2]);
							break;
						case "solve": 
							commandPool.get("solve").doCommand(parameters[1] + " " + parameters[2]);
							break;
						case "display":
							switch (parameters[1]) {
								case "cross":
									if(parameters[4].equals("X"))
										commandPool.get("display cross x").doCommand(parameters[5] + " " + parameters[7]);
									else if(parameters[4].equals("Y"))
										commandPool.get("display cross y").doCommand(parameters[5] + " " + parameters[7]);
									else
										commandPool.get("display cross z").doCommand(parameters[5] + " " + parameters[7]);
									break;
									
								case "solution":
									commandPool.get("solution").doCommand(parameters[2]);
									break;
	
								default:
									commandPool.get("display").doCommand(parameters[1]);
									break;
							}
							break;
						default:
							System.out.println("Invalid command!");
					}
			} catch(IOException e) {
				e.printStackTrace();
			} catch(Exception e) {
				e.getMessage();
			}
		}
		
		if(arg0 instanceof Model) {
			if(arg1 instanceof Maze3d) 
				v.display("The maze is ready!");
			else if(arg1 instanceof Solution) 
				v.display("The solution is ready!");
			else if(arg1 instanceof String)
				v.display((String)arg1);
			else
				v.display("Something went wrong!");
		}
	}

	public HashMap<String, Command> getCommandPool() {
		return commandPool;
	}

	public void setCommandPool(HashMap<String, Command> commandPool) {
		this.commandPool = commandPool;
	}

}
