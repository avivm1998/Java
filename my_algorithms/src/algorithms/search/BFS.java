package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

/**
 * BFS is a search problem solver using the Best First Search Algorithm.
 * 
 * @author Aviv Moran
 */
public class BFS<T> extends CommonSearcher<T> {
	
	/**
	 * BFS constructor, sending the given comparator to the CommonSearcher constructor.
	 * 
	 * @param comparator [IN] The wanted way of two states to be compared by in the priority queue.
	 */
	 public BFS(Comparator<State<T>> comparator) {
		super(comparator);
	}
	
	/**
	 * Solving the given search problem with the Best First Search Algorithm.
	 * 
	 * @param s [IN] The search problem to be solved {@link Searchable}.
	 * 
	 * @return [OUT] The solution of the problem {@link Solution}.
	 */
	@Override
	public Solution<T> search(Searchable<T> s) {
		State<T> visitedState;
		this.addToOpenList(s.getInitialState());
		HashSet<State<T>> closedSet = new HashSet<State<T>>(); 
		Solution<T> solution = new Solution<T>();
		ArrayList<State<T>> successors = new ArrayList<State<T>>();
		
		while(this.openList.size() > 0) {
			visitedState = this.popOpenList();
			closedSet.add(visitedState);
		
		
		if(visitedState.equals(s.getGoalState())) { 
			solution.setSolution(backTrace(visitedState));
			return solution;
		}
		
		successors = s.getAllPossibleMoves(visitedState);
		
		for(State<T> state : successors) { 
			//State<T> temp = this.findState(state, closedSet);
			state.setCameFrom(visitedState);
			this.generateCost(state);
			if(closedSet.contains(state) == false && this.openList.contains(state) == false) {
				//state.setCameFrom(visitedState);
				this.addToOpenList(state);
				//this.generateCost(state);
			}
			
			
			  else if(state.getCost() < visitedState.getCost()) { 
				if(this.openList.contains(state) == false) { 
					this.addToOpenList(state);
				}
				
				else { 
					this.openList.remove(state);
					this.addToOpenList(state);
				}
			 
			
			/*
			 * else if(temp.getCost() < state.getCost()) {
				this.openList.remove(state);
				closedSet.remove(state);
				this.addToOpenList(temp);
			}		
			 */
		 }	
	  }
   }
		
		return null;
}
	/**
	  * Generates the cost of the path leading from the entrance to the given state.
	  * 
	  * @param state [IN] The state to generate it's path cost {@link State}
	  */
	@Override
	protected void generateCost(State<T> state) {
		 state.setCost(state.getCameFrom().getCost() + 1);
	 }
		
	/*
	 * 	 public State<T> findState(State<T> state, HashSet<State<T>> closeSet) {
		 State<T> foundState = null;
		 
		 if(this.openList.contains(state)) { 
			 for (State<T> s : openList) {
		    	 if (state.equals(s))
		    		foundState = s;
		    	    return foundState;
		     }
		 }
		 else {
			 for (State<T> s : closeSet) {
		    	 if (state.equals(s))
		    		foundState = s;
		    	    return foundState;
		     }
		 }
		 
		 return null;
	   }
	 */
	 
}


	
	

