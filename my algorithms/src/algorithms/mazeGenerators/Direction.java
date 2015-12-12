package algorithms.mazeGenerators;

/**
 * An enum class that represents the directions of movements in the maze.
 * 
 * @author Aviv Moran
 *
 */

public enum Direction {
	UP, DOWN, FORWARDS, BACKWARDS, RIGHT, LEFT;
	
	/**
	 * This function converts the direction given as an integer to a Direction.
	 * 
	 * @param direction [IN] The direction {@link Integer}.
	 * 
	 * @return [OUT] The matching Direction (And null in case of bad input) {@link Direction}.
	 */
	public static Direction intToDirection(int direction) {
		switch(direction) {
			case 0:
				return UP;
			case 1:
				return DOWN;
			case 2:
				return FORWARDS;
			case 3:
				return BACKWARDS;
			case 4:
				return RIGHT;
			case 5: 
				return LEFT;
		}
		
		return null;
	}
	
	/**
	 * This function returns the opposite direction of the given direction.
	 * 
	 * @param direction [IN] A direction to be reversed {@link Direction}.
	 * 
	 * @return [OUT] The matching Direction on the opposite side {@link Direction}.
	 */
	public static Direction getOppositeDirection(Direction direction) {
		switch(direction) { 
			case UP:
				return DOWN;
			case DOWN:
				return UP;
			case FORWARDS:
				return BACKWARDS;
			case BACKWARDS:
				return FORWARDS;
			case RIGHT:
				return LEFT;
			case LEFT:
				return RIGHT;
		}
		
		return null;
	}
}
