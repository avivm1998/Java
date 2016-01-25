package presenter;

import java.io.Serializable;

public class Properties implements Serializable {
	private int threadPoolSize;
	private String searchingAlogrithm;
	private int port;
	private String ip;
	
	public Properties() {
		this.threadPoolSize = 10;
		this.searchingAlogrithm = "bfs";
		this.port = 2000;
		this.ip = "127.0.0.1";
	}
	
	public Properties(int size, String solver, int port, String ip) {
		this.threadPoolSize = size;
		this.searchingAlogrithm = solver;
		this.port = port;
		this.ip = ip;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
