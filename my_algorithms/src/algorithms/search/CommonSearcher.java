package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * CommonSearcher is an abstract class that represents the common base of all the search problem solvers.
 * 
 * @author moranav
 */

public abstract class CommonSearcher<T> implements Searcher<T> {
	 
	 protected PriorityQueue<State<T>> openList;
	 private int evaluatedNodes;
	 
	 /**
	  * Solving the given search problem..
	  * 
      * @param s [IN] The search problem to be solved {@link Searchable}.
      * 
	  * @return [OUT] The solution of the problem {@link Solution}.
	  */
	 public abstract Solution<T> search(Searchable<T> s);
	 
	 /**
	  * Generates the cost of the path leading from the entrance to the given state.
	  * 
	  * @param state [IN] The state to generate it's path cost {@link State}
	  */
	 protected abstract void generateCost(State<T> state);
	 
	 /**
	  * CommonSearcher constructor, sets the comparator to the priority queue.
	  * 
	  * @param comparator [IN] The way two states will be compared by in the queue {@link Comparator}
	  */
	 public CommonSearcher(Comparator<State<T>> comparator) { 
		 this.openList = new PriorityQueue<State<T>>(comparator);
	 }
	 
	 /**
	  * Returns the amount of nodes used in the search method.
	  *  
	  * @return [OUT] The amount of nodes used by the search algorithm.
	  */
	 public int getNumberOfNodesEvaluated() { 
		 return this.evaluatedNodes;
	 }
	 
	 /**
	  * Removes the state on the top of the queue and increase the number of nodes evaluated.
	  * 
	  * @return [OUT] The best state there is to move to {@link State}.
	  */
	 protected State<T> popOpenList() { 
		 this.evaluatedNodes++;
		 return this.openList.poll();
	 }
	 
	 /**
	  * Adds the given state to the priority queue.
	  * 
	  * @param state [IN] The state to be added to the opened queue {@link State}.
	  */
	 protected void addToOpenList(State<T> state) { 
		 this.openList.add(state);	
	 }
	 
	 /**
	  * Backtracks the path from the entrance to the given state.
	  * 
	  * @param dest [IN] The last state of the path from the entrance {@link State}.
	  * 
	  * @return The path from the entrance to the given state {@link ArrayList}
	  */
	 public ArrayList<State<T>> backTrace(State<T> dest) {
		ArrayList<State<T>> path = new ArrayList<State<T>>();
		
		while(dest.getCameFrom() != null) { 
			path.add(0,dest);
			dest = dest.getCameFrom();
		}
		path.add(0,dest);
		
		return path;
	 }
}
