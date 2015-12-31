package algorithms.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import algorithms.demo.SearchableMaze3d;
import algorithms.mazeGenerators.CellValue;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;

public class AStarTest {
	
	AStar<Position> searcher;
	SearchableMaze3d searchable;
	
	public AStarTest() {
		Maze3dGenerator mg = new SimpleMaze3dGenerator();
		Maze3d maze = mg.generate(3, 3, 3);
		searchable.setMaze(maze);
		searcher = new AStar<Position>(searchable , new MazeAirDistance(), new StateCostComparator<Position>());
	}
	
	@Test
	public void testSearch() {
		Solution<Position> sol = searcher.search(searchable);
		
		for(State<Position> state : sol.getSolution()) {
			if(searchable.getMaze().getCell(state.getState()) == CellValue.WALL)
				fail("Solution incorrect...");
		}
		
		assertEquals(true, sol.getSolution().contains(searchable.getGoalState()));
	}

}
