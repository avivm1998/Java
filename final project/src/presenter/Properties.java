package presenter;

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
	private int character;
	private int port;
	private String ip;
	
	/**
	 * Default constructor ,sets the threadpool size to 10 and the searching alogrithm to "bfs".
	 */
	public Properties() {
		this.threadPoolSize = 10;
		this.searchingAlogrithm = "bfs";
		this.character = 1;
		this.port = 2000;
		this.ip = "127.0.0.1";
	}
	
	/**
	 * Constructor ,sets the threadpool size and searching alogrithm.
	 * 
	 * @param size   		  [IN] the size of the threadpool.
	 * @param solver 		  [IN] the searching algo.
	 * @param characterChoice [IN] the character chosen.
	 */
	public Properties(int size, String solver, int characterChoice, int port, String ip) {
		this.threadPoolSize = size;
		this.searchingAlogrithm = solver;
		this.character = characterChoice;
		this.port = port;
		this.ip = ip;
	}
	
	/**
	 * Returns the size of the threadpool.
	 * 
	 * @return
	 */
	public int getThreadPoolSize() {
		return threadPoolSize;
	}

	/**
	 * Sets the threadpool size.
	 * 
	 * @param threadPoolSize [IN] the threadpool size
	 */
	public void setThreadPoolSize(int threadPoolSize) {
		this.threadPoolSize = threadPoolSize;
	}

	/**
	 * Returns the searching alogrithm
	 * 
	 * @return
	 */
	public String getSearchingAlogrithm() {
		return searchingAlogrithm;
	}

	/**
	 * Sets the searching alogrithm.
	 * 
	 * @param searchingAlogrithm [IN] the searching alogrithm.
	 */
	public void setSearchingAlogrithm(String searchingAlogrithm) {
		this.searchingAlogrithm = searchingAlogrithm;
	}

	/**
	 * Returns the character chosen
	 * 
	 * @return
	 */
	public int getCharacter() {
		return character;
	}

	/**
	 * Sets the character.
	 * 
	 * @param character [IN]
	 */
	public void setCharacter(int character) {
		this.character = character;
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
