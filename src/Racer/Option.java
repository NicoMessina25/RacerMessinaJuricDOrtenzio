package Racer;

public class Option {
	
	private int id; // id es la respuesta
	private String descripcion; 
	private boolean correcta;
	
	public Option(int id) {
		this.id = id;
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

	public boolean isCorrecta() {
		return correcta;
	}

	public void setCorrecta(boolean correcta) {
		this.correcta = correcta;
	}

	
	
	
	
	
	
	
}