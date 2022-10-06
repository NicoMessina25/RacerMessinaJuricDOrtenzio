
package RacerModel;



public class Option {
	
	private int id; // id es la respuesta
	private String descripcion;
	private int sortNum;
	
	public Option(int id, String description) {
		this.id = id;
		this.descripcion = description;
	}

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