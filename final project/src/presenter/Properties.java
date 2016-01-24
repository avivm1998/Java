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
	//private String viewType;
	
	/**
	 * Default constructor ,sets the threadpool size to 10 and the searching alogrithm to "bfs".
	 */
	public Properties() {
		this.threadPoolSize = 10;
		this.searchingAlogrithm = "bfs";
		//this.viewType = "MazeWindow";
	}
	
	/**
	 * Constructor ,sets the threadpool size and searching alogrithm.
	 * 
	 * @param size   [IN] the size of the threadpool
	 * @param solver [IN] the searching algo
	 */
	public Properties(int size, String solver) {
		this.threadPoolSize = size;
		this.searchingAlogrithm = solver;
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
	
	
}
