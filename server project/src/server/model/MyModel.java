package server.model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.demo.SearchableMaze3d;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator2;
import algorithms.mazeGenerators.Position;
import algorithms.search.AStar;
import algorithms.search.BFS;
import algorithms.search.CommonSearcher;
import algorithms.search.MazeAirDistance;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.Solution;
import algorithms.search.StateCostComparator;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import server.presenter.Properties;

/**
 *  MyModel is a concrete Model in the MVC pattern, connecting the model and view and holds all the valid commands.

 * @author Aviv Moran & Ayal Naim
 *
 */
public class MyModel extends Observable implements Model{
	
	private ExecutorService threadPool;
	private HashMap<String, Maze3d> mazePool;
	private HashMap<String, Solution<Position>> solutionPool;
	private HashMap<Maze3d, Solution<Position>> cache;
	private Properties settings;
	
	/**
	 * Constructor initializing the maze and solution HashMaps.
	 */
	@SuppressWarnings("unchecked")
	public MyModel() {
		settings = new Properties();
		setProperties(settings);
		mazePool = new HashMap<String, Maze3d>();
		solutionPool = new HashMap<String, Solution<Position>>();
		cache = new HashMap<Maze3d, Solution<Position>>();
		
		try {
			ObjectInputStream in = new ObjectInputStream(new GZIPInputStream(new FileInputStream("cache.zip")));
			cache = (HashMap<Maze3d, Solution<Position>>) in.readObject();
			in.close();
		} catch (EOFException e) {}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the thread pool.
	 * 
	 * @return The thread pool. {@link ExecutorService}
	 */
	public ExecutorService getThreadPool() {
		return threadPool;
	}

	/**
	 * Sets the thread pool with the given executor.
	 * 
	 * @param threadPool [IN] The executor. {@link ExecutorService}
	 */
	public void setThreadPool(ExecutorService threadPool) {
		this.threadPool = threadPool;
	}

	/**
	 * Gets the cache.
	 * 
	 * @return The cache. {@link HashMap}
	 */
	public HashMap<Maze3d, Solution<Position>> getCache() {
		return cache;
	}

	/**
	 * Sets the cache with the given hash map.
	 * 
	 * @param cache [IN] The hash map. {@link HashMap}
	 */
	public void setCache(HashMap<Maze3d, Solution<Position>> cache) {
		this.cache = cache;
	}

	/**
	 * Gets the maze HashMap.
	 * 
	 * @return [OUT] The mazePool {@link HashMap}.
	 */
	public HashMap<String, Maze3d> getMazePool() {
		return mazePool;
	}

	/**
	 * Sets the mazePool with the given HashMap.
	 * 
	 * @param mazePool [IN] The mazePool to be set {@link HashMap}.
	 */
	public void setMazePool(HashMap<String, Maze3d> mazePool) {
		this.mazePool = mazePool;
	}
	
	/**
	 * Gets the solution HashMap.
	 * 
	 * @return [OUT] The solutionPool {@link HashMap}.
	 */
	public HashMap<String, Solution<Position>> getSolutionPool() {
		return solutionPool;
	}

	/**
	 * Sets the solutionPool with the given HashMap.
	 * 
	 * @param solutionPool [IN] The solutionPool to be set {@link HashMap}.
	 */
	public void setSolutionPool(HashMap<String, Solution<Position>> solutionPool) {
		this.solutionPool = solutionPool;
	}

	@Override
	public Maze3d getMaze3d(String args) throws Exception {
		if(mazePool.get(args) == null)
			throw new Exception("There is not maze named " + args);
		return mazePool.get(args);
	}
	
	@Override
	public Solution<Position> getSolution(String args) throws Exception {
		
		if(mazePool.get(args) == null)
			throw new Exception("There is no maze named " + args);
		return solutionPool.get(args);
	}
		
	@Override
	public void setProperties(Properties settings) {
		this.settings = settings;
		threadPool = Executors.newFixedThreadPool(settings.getThreadPoolSize());
	}
	
	@Override
	public File[] getFileDirectoryList(File f) {
		return f.listFiles();
	}

	@Override
	public void generateMaze3d(String client, String name, int x, int y, int z) throws Exception {
		if(mazePool.get(name) != null)
			throw new Exception("Can not override " + name);
		
		if(x <= 0 || y <= 0 || z <= 0) 
			throw new Exception("Bad dimension input");
		
		Future<Maze3d> mazeFuture = threadPool.submit(new Callable<Maze3d>() {
			@Override
			public Maze3d call() throws Exception {
				Maze3dGenerator mg = new MyMaze3dGenerator2();
				Maze3d maze = mg.generate(x, y, z);
				mazePool.put(name, maze);
				setChanged();
				notifyObservers(new Integer(client));
				setChanged();
				notifyObservers(maze);
				return maze;
			}
		});
	}

	@Override
	public int[][] getCrossSection(String mazeName,String dimension, int value) throws Exception {
		if(mazePool.get(mazeName) == null)
			throw new Exception("There is no maze named " + mazeName);
		Maze3d maze = mazePool.get(mazeName);
		this.setChanged();
		this.notifyObservers();
		
		switch(dimension) {
			case "X":
				return maze.getCrossSectionByX(value);
			case "Y":
				return maze.getCrossSectionByY(value);
			case "Z":
				return maze.getCrossSectionByZ(value);
			default:
				return null;
		}
		
	}

	@Override
	public void saveCompressedMaze(String client, String mazeName, String fileName) throws Exception {
		if(mazePool.get(mazeName) == null) 
			throw new Exception("There is not maze named" + mazeName);
		
		Maze3d maze = mazePool.get(mazeName);
		OutputStream out = null;
		byte[] compressedMaze = maze.toByteArray();
		
		try {
			out = new MyCompressorOutputStream(new FileOutputStream("./" + fileName));
			out.write(compressedMaze);
			
			this.setChanged();
			this.notifyObservers(fileName + " was saved successfully!");
			
		} catch (IOException e) {
			System.out.println("IO Exception");
		}
		finally {
			try {
				out.close();
			} catch (IOException e) {
				System.out.println("IO Exception");
			}
		}
	}

	@Override
	public void loadDecompressedMaze(String client, String fileName, String mazeName) throws Exception {
		File f = new File(fileName);
		FileInputStream fin = null;
		InputStream in = null;
		int x = 0;
		int y = 0;
		int z = 0;
		
		try {
			System.out.println(fileName);
			fin = new FileInputStream(fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	
		try {
			for (int i = 0; i < 6; i++) {
				fin.read();
			}
			
			x = fin.read();
			y = fin.read();
			z = fin.read();
			fin.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		byte decompressedMaze[] = new byte[9 + (x * y * z)];
		
		try {
			in = new MyDecompressorInputStream(new FileInputStream(f));
			in.read(decompressedMaze);
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found Exception");
		} catch (IOException e) {
			System.out.println("IO Exception");
		}
		finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println("IO Exception");
			}
		}
		Future<Maze3d> mazeFuture = threadPool.submit(new Callable<Maze3d>() {
			@Override
			public Maze3d call() throws Exception {
				Maze3d maze = new Maze3d(decompressedMaze);
				mazePool.put(mazeName, maze);
				setChanged();
				notifyObservers(new Integer(client));
				setChanged();
				notifyObservers(mazePool.get(mazeName));
				return maze;
			}
		});
		
	}
	

	@Override
	public void solveMaze(String client, String mazeName, String generator) throws Exception{
		if(mazePool.get(mazeName) == null)
			throw new Exception("There is no maze named " + mazeName);
		
		if(cache.get(mazePool.get(mazeName)) != null) {
			setChanged();
			notifyObservers(cache.get(mazePool.get(mazeName)));
			return;
		}
		
		Future<Solution<Position>> solutionFuture = threadPool.submit(new Callable<Solution<Position>>() {
			@Override
			public Solution<Position> call() throws Exception {
				Maze3d maze = mazePool.get(mazeName);
				CommonSearcher<Position> cs = null;
				SearchableMaze3d s = null;
				
				switch(generator) {
				case "bfs":
					cs = new BFS<Position>(new StateCostComparator<Position>());
					break;
				case "astar md":
					cs = new AStar<Position>(s, new MazeManhattanDistance(), new StateCostComparator<Position>());
					break;
				case "astar ad":
					cs = new AStar<Position>(s, new MazeAirDistance(), new StateCostComparator<Position>());
					break;
				}
				
				s = new SearchableMaze3d();
				s.setMaze(maze);
				Solution<Position> sol = cs.search(s);
				solutionPool.put(mazeName, sol);
				cache.put(s.getMaze(), sol);
				setChanged();
				notifyObservers(new Integer(client));
				setChanged();
				notifyObservers(sol);
				return sol;
			}
		});
	}
	
	@Override
	public void exit() {
		threadPool.shutdown();
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("cache.zip")));
			out.writeObject(cache);
			out.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.exit(0);
	}
}
