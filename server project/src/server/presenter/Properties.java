package server.presenter;

import java.io.Serializable;

/**
 * Properties is a class that it's data members are the project's properties
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public class Properties implements Serializable {
	private int threadPoolSize;
	private String searchingAlogrithm;
	private int port;
	private String ip;
	
	/**
	 * Default constructor ,sets the thread pool size to 10, the searching algorithm to "bfs" ,the character John Cena, the port 2000 and the IP 127.0.0.1.
	 */
	public Properties() {
		this.threadPoolSize = 10;
		this.searchingAlogrithm = "bfs";
		this.port = 2000;
		this.ip = "127.0.0.1";
	}
	
	/**
	 * Constructor ,sets the thread pool size, searching algorithm, character, port and IP.
	 * 
	 * @param size   		  [IN] the size of the thread pool.
	 * @param solver 		  [IN] the searching algorithm.
	 * @param port            [IN] the port.
	 * @param ip              [IN] the IP
	 */
	public Properties(int size, String solver, int port, String ip) {
		this.threadPoolSize = size;
		this.searchingAlogrithm = solver;
		this.port = port;
		this.ip = ip;
	}
	
	/**
	 * Returns the size of the thread pool.
	 * 
	 * @return The thread pool size.
	 */
	public int getThreadPoolSize() {
		return threadPoolSize;
	}

	/**
	 * Sets the thread pool size.
	 * 
	 * @param threadPoolSize [IN] the thread pool size
	 */
	public void setThreadPoolSize(int threadPoolSize) {
		this.threadPoolSize = threadPoolSize;
	}

	/**
	 * Returns the searching algorithm
	 * 
	 * @return The searching algorithm.
	 */
	public String getSearchingAlogrithm() {
		return searchingAlogrithm;
	}

	/**
	 * Sets the searching algorithm.
	 * 
	 * @param searchingAlogrithm [IN] the searching algorithm.
	 */
	public void setSearchingAlogrithm(String searchingAlogrithm) {
		this.searchingAlogrithm = searchingAlogrithm;
	}
	
	/**
	 * Returns the port.
	 * 
	 * @return The port.
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Sets the port.
	 * 
	 * @param port [IN] the port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * Returns the IP.
	 * 
	 * @return The IP.
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Sets the IP.
	 * 
	 * @param ip [IN] the IP
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
