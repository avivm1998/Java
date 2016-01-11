package presenter;

import java.io.Serializable;

public class Properties implements Serializable {
	private int threadPoolSize;
	private String searchingAlogrithm;
	//private String viewType;
	
	public Properties() {
		this.threadPoolSize = 10;
		this.searchingAlogrithm = "bfs";
		//this.viewType = "MazeWindow";
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

	/*
	 * public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	 */
	
	
}
