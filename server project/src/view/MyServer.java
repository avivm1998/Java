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
	static Object lock;
	
	/**
	 * Constructer with parameters
	 * 
	 * @param settings [IN] the program's properties. 
	 */
	public MyServer() {
		try {
			lock = new Object();
			this.setUpSettings();
			this.handler = new MyClientHandler();
			clients = new HashMap<String, OutputStream>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
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
							line = in.readLine();
							
							if(line.equals("exit")) 
								close();
								
						} catch (IOException e) {
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
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


						
						/*
						 * new Thread(new Runnable() {
								@Override
								public void run() {
									BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
									String line;
									
									try {
										if((line = in.readLine()).equals("exit"))
											close();
									} catch (Exception e) {
										e.printStackTrace();
									}
								};
							}).start();
						 */
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
	 * @throws Exception
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

	public void sendMazeToClient(Maze3d maze, OutputStream outToServer) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(outToServer);
			out.writeObject(maze);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendSolutionToClient(Solution<Position> solution, OutputStream outToServer) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(outToServer);
			out.writeObject(solution);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendStringToClient(String arg, OutputStream outToServer) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(outToServer);
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
