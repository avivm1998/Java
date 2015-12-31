package presenter;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

public class Properties implements Serializable {
	private int threadPoolSize;
	private String searchingAlogrithm;
	private String mazeGenerationAlgorithm;
	private String viewType;
	
	public Properties() {
		this.threadPoolSize = 10;
		this.searchingAlogrithm = "BFS";
		this.mazeGenerationAlgorithm = "Prim";
		this.viewType = "CLI";
	}
	
	public Properties(int size, String solver, String generator, String view) {
		this.threadPoolSize = size;
		this.searchingAlogrithm = solver;
		this.mazeGenerationAlgorithm = generator;
		this.viewType = view;
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

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	
	
}
