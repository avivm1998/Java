package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Properties;

/**
 * MyServer is the server that connects to the clients and provides them the commands they send, Also it is the View in the MVP architecture.
 * 
 * @author Aviv Moran
 *
 */
public class MyServer extends Observable implements View {
	Properties settings;
	ServerSocket server;
	public MyClientHandler handler;
	ExecutorService threadPool;
	volatile boolean stop;
	Thread mainServerThread; 
	int clientsHandled = 0;
	HashMap<String, OutputStream> clients;
	String line;
	int counter = 0;
	
	/**
	 * Defualt constructor, setting the properties and starting up the clients hash map.
	 * 
	 * @param settings [IN] the program's properties. 
	 */
	public MyServer() {
		try {
			this.setUpSettings();
			this.handler = new MyClientHandler();
			clients = new HashMap<String, OutputStream>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Starts the server, accepting clients and opening handlers for them. 
	 * 
	 * @throws Exception [THROWN] In case of non-handled exception.
	 */
	public void start() throws Exception{
		
		server = new ServerSocket(settings.getPort());
		server.setSoTimeout(5 * 1000);
		threadPool=Executors.newFixedThreadPool(settings.getThreadPoolSize());
		
		System.out.println("Server is alive and waiting for clients to connect! ");
		

		mainServerThread = new Thread(new Runnable() {			
			@Override
			public void run() {
				mainServerThread.setName("Main Thread");
				
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
							
							while(!(line = in.readLine()).equals("exit"));
							close();
								
						} catch(NullPointerException e) {
						} catch (IOException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();
				
				while(!stop){
					try {
						final Socket someClient = server.accept();
						clients.put("" + clientsHandled, someClient.getOutputStream());
						if(someClient != null){
							threadPool.execute(new Runnable() {									
								@Override
								public void run() {
									try{										
										clientsHandled++;
										System.out.println("\thandling client " + clientsHandled);
										handler.handleClient(someClient.getInputStream(), someClient.getOutputStream(), "" + clientsHandled);
										someClient.close();								
									}catch(IOException e){
										e.printStackTrace();
									}									
								}
							});								
						}
					}
					catch (SocketException e) {
						break;
					}
					catch (SocketTimeoutException e){
							System.out.println("no client connected...");
					} 
					catch (IOException e) {
						e.printStackTrace();
					} 
					
				}
			} // end of the mainServerThread task
		});
		
		mainServerThread.start();
		
	}
	
	/**
	 * An organized exit
	 * 
	 * @throws Exception [THROWN] In case the thread pool was interrupted while waiting to shutdown or bad socket closure.
	 */
	public void close() throws Exception{
		System.out.println("starting safe exit");
		
		stop = true;	
		
		// do not execute jobs in queue, continue to execute running threads
		System.out.println("shutting down");
		threadPool.shutdown();
		
		 // wait 10 seconds over and over again until all running jobs have finished
		boolean allTasksCompleted = false;
		while(!(allTasksCompleted = threadPool.awaitTermination(5, TimeUnit.SECONDS)))
			System.out.println("waiting for the clients to finish up...");
		
		System.out.println("all the tasks have finished");
		
		System.out.println("server is safely closed");
		
		//Shutting down the Model's thread pool
		setChanged();
		notifyObservers(clientsHandled + " exit");
		
		server.close();
	}


	@Override
	public void display(String args) {
		System.out.println(args);
	}


	@Override
	public void display(Maze3d maze) {
		System.out.println(maze.toString());
	}


	@Override
	public void display(Solution<Position> sol) {
		System.out.println(sol.toString());
	}

	@Override
	public void sendMazeToClient(Maze3d maze, OutputStream outToClient) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(outToClient);
			out.writeObject(maze);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void sendSolutionToClient(Solution<Position> solution, OutputStream outToClient) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(outToClient);
			out.writeObject(solution);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void sendStringToClient(String arg, OutputStream outToClient) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(outToClient);
			out.writeObject(arg);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void getUserCommand() {
		try {
			this.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets up the properties of the server.
	 */
	public void setUpSettings() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String choice = "";
		
		System.out.println("Enter 1 for default settings, and 2 to create your own!");
		choice = in.readLine();
		
		this.settings = new Properties();
		if(choice.equals("1")) 
			return;
		else {
			System.out.println("Enter the wanted ThreadPool size: ");
			choice = in.readLine();
			settings.setThreadPoolSize(Integer.parseInt(choice));
			
			System.out.println("Enter the wanted Searching Algorithm: (bfs, astar md, astar ad)");
			choice = in.readLine();
			settings.setSearchingAlogrithm(choice);
			
			System.out.println("Enter the wanted Port: ");
			choice = in.readLine();
			settings.setPort(Integer.parseInt(choice));
			
			System.out.println("Enter the wanted IP adress: ");
			choice = in.readLine();
			settings.setIp(choice);
		}
	}
}
