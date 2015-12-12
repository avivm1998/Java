package algorithms.mazeGenerators;

/**
 * CellValue is an Enum that represents the value of the cell, 0 as PASSAGE and 1 as WALL. 
 * 
 * @author Aviv Moran
 * 
 */

public enum CellValue {
	PASSAGE, WALL;
	
	/**
	 * Returns the CellValue of the given Integer.
	 * 
	 * @param value [IN] The value of the cell {@link Integer}.
	 * 
	 * @return [OUT] The value of the cell {@link CellValue}.
	 */
	public static CellValue intToCellValue(int value) { 
		if(value == 0)
			return PASSAGE;
		return WALL;
	}
	
	/**
	 * Returns the Integer value of the given CellValue.
	 * 
	 * @param value [IN] The value of the cell {@link CellValue}.
	 * 
	 * @return [OUT] The value of the cell {@link Integer}.
	 */
	public static int cellValueToInt(CellValue value) { 
		if(value == PASSAGE)
			return 0;
		return 1;
	}
}