package presenter;

import java.io.Serializable;

public class Properties implements Serializable {
	private int threadPoolSize;
	private String searchingAlogrithm;
	private int port;
	private int numOfClients;
	
	public Properties() {
		this.threadPoolSize = 10;
		this.searchingAlogrithm = "bfs";
		this.port = 5400;
		this.numOfClients = 10;
	}
	
	public Properties(int size, String solver) {
		this.threadPoolSize = size;
		this.searchingAlogrithm = solver;
	}

	public int getThreadPoolSize() {
		return threadPoolSize;
	}

	public void setThreadPoolSize(int threadPoolSize) {
		this.threadPoolSize = threadPoolSize;
	}

	public String getSearchingAlogrithm() {
		return searchingAlogrithm;
	}

	public void setSearchingAlogrithm(String searchingAlogrithm) {
		this.searchingAlogrithm = searchingAlogrithm;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getNumOfClients() {
		return numOfClients;
	}

	public void setNumOfClients(int numOfClients) {
		this.numOfClients = numOfClients;
	}


	
	
}
