package Views;

public class GridBoardCoordinate {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	private int i, j;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	/**
	 * 
	 */
	public GridBoardCoordinate() {
		
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 */
	public GridBoardCoordinate(int i, int j) {
		setI(i);
		setJ(j);
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	/**
	 * 
	 * @return
	 */
	public int getJ() {
		return j;
	}

	/**
	 * 
	 * @param j
	 */
	public void setJ(int j) {
		this.j = j;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getI() {
		return i;
	}

	/**
	 * 
	 * @param i
	 */
	public void setI(int i) {
		this.i = i;
	}
	
	

}
