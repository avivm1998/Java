package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class MyMaze3dGenerator2 extends CommonMaze3dGenerator {
	public Maze3d generate(int x, int y, int z) {
		Maze3d maze = new Maze3d();
		Random rand = new Random();
		Stack<Position> stack = new Stack<Position>();
		
		maze.initMazeToWalls(x, y, z);
		
		Position entrance = new Position(rand.nextInt(x), rand.nextInt(y), rand.nextInt(z));
		maze.setEntrance(entrance);
		stack.push(entrance);
		dfsGenerate(maze , stack);
		
		Position exit = new Position(rand.nextInt(x), rand.nextInt(y), rand.nextInt(z));
		while(maze.getCell(exit) == CellValue.WALL || exit == entrance) {
			exit = new Position(rand.nextInt(x), rand.nextInt(y), rand.nextInt(z));
		}
		
		maze.setExit(exit);
		
		return maze;
	}
	
	/**
	 * a code executing DFS algorithm
	 * @param maze  [IN] The maze. {@link Maze3d}
	 * @param stack [IN] The stack. {@link Stack}
	 */
	public void dfsGenerate(Maze3d maze ,Stack<Position> stack) {
		
		boolean flag = false;
		Position head = new Position(stack.pop());
		ArrayList<Direction> allDirections = new ArrayList<Direction>();
		
		maze.setCell(head, CellValue.PASSAGE);
		
		allDirections.add(Direction.UP);
		allDirections.add(Direction.DOWN);
		allDirections.add(Direction.BACKWARDS);
		allDirections.add(Direction.FORWARDS);
		allDirections.add(Direction.RIGHT);
		allDirections.add(Direction.LEFT);
		Collections.shuffle(allDirections);
		
		for (int i = 0; i < allDirections.size(); i++) {
			Direction direction = allDirections.get(i);
			
			if(maze.getNeighborCell(head, direction) == null) {
				continue;
			}
			if(maze.getNeighborCell(maze.getNeighborCell(head, direction), direction) == null) {
				continue;
			}
			
			head = maze.getNeighborCell(head, direction);
			head = maze.getNeighborCell(head, direction);
			if (maze.getCell(head) == CellValue.WALL) {
				stack.push(head);
				maze.setCell(head, CellValue.PASSAGE);
				head = maze.getNeighborCell(head, Direction.getOppositeDirection(direction));
				maze.setCell(head, CellValue.PASSAGE);
				flag = true;
				head = maze.getNeighborCell(head, direction);
				dfsGenerate(maze, stack);
			}
			head = maze.getNeighborCell(head, Direction.getOppositeDirection(direction));
			head = maze.getNeighborCell(head, Direction.getOppositeDirection(direction));
		}
		
		if(!flag && !stack.isEmpty())
			stack.pop();
		return;
		
		/*
		 * for (int i = 0; i < 6; i++) {
			switch (shufl.get(i)) {
				case 0:
					//back
					head.mbackward();
					head.mbackward();
					if(maze.inTheMaze(head))
						if(maze.maze[head.x][head.y][head.z] == 1){
							stack.push(head);
							maze.maze[head.x][head.y][head.z] = 0;
							head.mforward();
							maze.maze[head.x][head.y][head.z] = 0;
							flag = true;
							head.mbackward();
							dfsGenerate(maze ,stack);
						}
					head.mforward();
					head.mforward();
				break;
				
				case 1:
					//down
					head.mdown();
					head.mdown();
					if(maze.inTheMaze(head))
						if(maze.maze[head.x][head.y][head.z] == 1){
							stack.push(head);
							maze.maze[head.x][head.y][head.z] = 0;
							head.mup();
							maze.maze[head.x][head.y][head.z] = 0;
							flag = true;
							head.mdown();
							dfsGenerate(maze ,stack);
						}
					head.mup();
					head.mup();
				break;
				
				case 2:
					//forward
					head.mforward();
					head.mforward();
					if(maze.inTheMaze(head))
						if(maze.maze[head.x][head.y][head.z] == 1){
							stack.push(head);
							maze.maze[head.x][head.y][head.z] = 0;
							head.mbackward();
							maze.maze[head.x][head.y][head.z] = 0;
							flag = true;
							head.mforward();
							dfsGenerate(maze ,stack);
						}
					head.mbackward();
					head.mbackward();
				break;
				
				case 3:
					//left
					head.mleft();
					head.mleft();
					if(maze.inTheMaze(head))
						if(maze.maze[head.x][head.y][head.z] == 1){
							stack.push(head);
							maze.maze[head.x][head.y][head.z] = 0;
							head.mright();
							maze.maze[head.x][head.y][head.z] = 0;
							flag = true;
							head.mleft();
							dfsGenerate(maze ,stack);
						}
					head.mright();
					head.mright();
				break;
				
				case 4:
					//right
					head.mright();
					head.mright();
					if(maze.inTheMaze(head))
						if(maze.maze[head.x][head.y][head.z] == 1){
							stack.push(head);
							maze.maze[head.x][head.y][head.z] = 0;
							head.mleft();
							maze.maze[head.x][head.y][head.z] = 0;
							flag = true;
							head.mright();
							dfsGenerate(maze ,stack);
						}
					head.mleft();
					head.mleft();
				break;
				
				case 5:
					//up
					head.mup();
					head.mup();
					if(maze.inTheMaze(head))
						if(maze.maze[head.x][head.y][head.z] == 1){
							stack.push(head);
							maze.maze[head.x][head.y][head.z] = 0;
							head.mdown();
							maze.maze[head.x][head.y][head.z] = 0;
							flag = true;
							head.mup();
							dfsGenerate(maze ,stack);
						}
					head.mdown();
					head.mdown();
				break;
				
				default:
					break;
			}
		}
		 */
	}
}
