package Views;

public class GridBoardCoordinate {
	
	private int i, j;

	public GridBoardCoordinate() {
		
	}
	
	public GridBoardCoordinate(int i, int j) {
		setI(i);
		setJ(j);
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
	
	

}
