package presenter;

import java.io.Serializable;

public class Properties implements Serializable {
	private int threadPoolSize;
	private String searchingAlogrithm;
	private String mazeGenerationAlgorithm;
	
	public Properties() {}
	
	public Properties(int size, String solver, String generator) {
		this.threadPoolSize = size;
		this.searchingAlogrithm = solver;
		this.mazeGenerationAlgorithm = generator;
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

	public String getMazeGenerationAlgorithm() {
		return mazeGenerationAlgorithm;
	}

	public void setMazeGenerationAlgorithm(String mazeGenerationAlgorithm) {
		this.mazeGenerationAlgorithm = mazeGenerationAlgorithm;
	}
	
	
}
