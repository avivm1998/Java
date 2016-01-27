package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Properties;

public class MyServer implements View {
	Properties settings;
	ServerSocket server;
	public MyClientHandler handler;
	ExecutorService threadPool;
	volatile boolean stop;
	Thread mainServerThread; 
	int clientsHandled = 0;
	HashMap<String, OutputStream> clients;
	
	/**
	 * Constructer with parameters
	 * 
	 * @param settings [IN] the program's properties. 
	 */
	public MyServer() {
		try {
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
		server.setSoTimeout(10 * 1000);
		threadPool=Executors.newFixedThreadPool(settings.getThreadPoolSize());
		
		System.out.println("Server is alive and waiting for clients to connect! ");
		
		mainServerThread = new Thread(new Runnable() {			
			@Override
			public void run() {
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
					catch (SocketTimeoutException e){
						System.out.println("no client connected...");
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("done accepting new clients.");
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
		stop=true;	
		// do not execute jobs in queue, continue to execute running threads
		System.out.println("shutting down");
		threadPool.shutdown();
		// wait 10 seconds over and over again until all running jobs have finished
		boolean allTasksCompleted = false;
		while(!(allTasksCompleted = threadPool.awaitTermination(10, TimeUnit.SECONDS)));
		
		System.out.println("all the tasks have finished");

		mainServerThread.join();		
		System.out.println("main server thread is done");
		
		server.close();
		System.out.println("server is safely closed");
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
			
			System.out.println("Enter the wanted Searching Algorithm: ");
			choice = in.readLine();
			settings.setSearchingAlogrithm(choice);
			
			System.out.println("Enter the wanted Character: 1 for John Cena , 2 for Deadpool");
			choice = in.readLine();
			settings.setCharacter(Integer.parseInt(choice));
			
			System.out.println("Enter the wanted port: ");
			choice = in.readLine();
			settings.setPort(Integer.parseInt(choice));
			
			System.out.println("Enter the wanted IP adress: ");
			choice = in.readLine();
			settings.setIp(choice);
		}
	}
}
