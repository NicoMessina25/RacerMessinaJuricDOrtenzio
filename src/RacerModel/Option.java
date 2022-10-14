
package RacerModel;



public class Option {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	private int id; 
	private String descripcion;
	private int sortNum;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	public Option(int id, String description) {
		this.id = id;
		this.descripcion = description;
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	public int getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}
	
	
	
	
	
	
	
}