
package RacerModel;



public class Option {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	private int id; 
	private String descripcion;
	private int sortNum;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 * @param id
	 * @param description
	 */
	public Option(int id, String description) {
		this.id = id;
		this.descripcion = description;
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	
	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * 
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * 
	 * @return
	 */
	public int getSortNum() {
		return sortNum;
	}

	/**
	 * 
	 * @param sortNum
	 */
	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}
	
	
	
	
	
	
	
}