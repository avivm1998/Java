package view;

import java.io.IOException;
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

public class MyServer implements View {

	int port;
	ServerSocket server;
	ClientHandler handler;
	int numOfClients;
	ExecutorService threadPool;
	
	volatile boolean stop;
	
	Thread mainServerThread; 
	
	int clientsHandled = 0;
	
	public MyServer(int port, ClientHandler ch ,int numOfClients) {
		this.port = port;
		this.handler = ch;
		this.numOfClients = numOfClients;
	}
	
	
	public void start() throws Exception{
		server = new ServerSocket(port);
		server.setSoTimeout(10 * 1000);
		threadPool=Executors.newFixedThreadPool(numOfClients);
		
		mainServerThread = new Thread(new Runnable() {			
			@Override
			public void run() {
				while(!stop){
					try {
						final Socket someClient = server.accept();
						if(someClient != null){
							threadPool.execute(new Runnable() {									
								@Override
								public void run() {
									try{										
										clientsHandled++;
										System.out.println("\thandling client " + clientsHandled);
										handler.handleClient(someClient.getInputStream(), someClient.getOutputStream());
										someClient.close();
										System.out.println("\tdone handling client " + clientsHandled);										
									}catch(IOException e){
										e.printStackTrace();
									}									
								}
							});								
						}
					}
					catch (SocketTimeoutException e){
						System.out.println("no clinet connected...");
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
		// TODO Auto-generated method stub
		
	}


	@Override
	public void display(Maze3d maze) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void display(Solution<Position> sol) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void getUserCommand() {
		try {
			this.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
