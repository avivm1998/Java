package algorithms.mazeGenerators;

/**
 * Position is a three dimensional coordinate.
 * 
 * @author Aviv Moran
 *
 */

public class Position {
	private int x;
	private int y;
	private int z;
	
	/**
	 * Position copy constructor.
	 * 
	 * @param other [IN] The position to be copied to the new one {@link Position}.
	 */
	public Position(Position other) {
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
	}
	
	/**
	 * Position default constructor.
	 */
	public Position() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	/**
	 * Position constructor.
	 * 
	 * @param x [IN] The X dimension value.
	 * @param y [IN] The Y dimension value.
	 * @param z [IN] The Z dimension value.
	 */
	public Position(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Returns the X dimension value of the coordinate.
	 * 
	 * @return [OUT] The X attribute.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the X dimension value with to the given value.
	 * 
	 * @param x [IN] The X dimension value to be set.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Returns the Y dimension value of the coordinate.
	 * 
	 * @return [OUT] The Y attribute.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the Y dimension value with to the given value.
	 * 
	 * @param y [IN] The Y dimension value to be set.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Returns the Z dimension value of the coordinate.
	 * 
	 * @return [OUT] The Z value.
	 */
	public int getZ() {
		return z;
	}
	
	/**
	 * Sets the Z dimension value with to the given value.
	 * 
	 * @param z [IN] The Z dimension value to be set.
	 */
	public void setZ(int z) {
		this.z = z;
	}
	
	/**
	 * Overrides the Object class equals method that checks if two objects are the same.
	 * 
	 * @param obj [IN] The other position to be compared with {@link Position}.
	 * 
	 * @return [OUT] A boolean that represents the answer {@link Boolean}. 
	 */
	@Override
	public boolean equals(Object obj) {
		Position other = (Position)obj;
		return (this.x == other.x) && (this.y == other.y) && (this.z == other.z);
	}
	
	/**
	 * Overrides the Object class toString that converts the calling object to a string.
	 * 
	 * @return [OUT] A string matching the calling coordinate {@link String}.
	 */
	@Override
	public String toString() {
	return "" + this.x + "," + this.y + "," + this.z;
	}
	
	/**
	 * Overrides the Object class hashCode that generates a unique code for the object that is used by HashTables.
	 * 
	 * @return [OUT] The calling coordinate matching hash code.  
	 */
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
}
