package RacerModel;

import java.util.ArrayList;

public class Question {
	
	private int id, correctOptionId, dificulty;
	private String statement;
	private ArrayList<Option> options = new ArrayList<Option>(); // N CANTIDAD DE OPCIONES
	private Category category;
	
	
	public Question(int id, int correctOptionId, Category cat, int dificulty, String statement, ArrayList<Option> options) {
		this.id = id;
		this.dificulty = dificulty;
		this.statement = statement;
		this.options = options;
		this.correctOptionId = correctOptionId;
		setCategory(cat);
	}


	public int getId() {
		return id;
	}


	public int getCorrectOptionId() {
		return correctOptionId;
	}


	public int getDificulty() {
		return dificulty;
	}


	public String getStatement() {
		return statement;
	}


	public ArrayList<Option> getOptions() {
		return options;
	}
	/*
	public void muestraPregunta() { ESTA CLASE SE NECESITA EL XML PARA VER BIEN COMO ARMARLO
		
		System.out.println(enunciado); // MUESTRA LA PREGUNTA
		
		int i = 0;
		while (i < opciones.size()) { // MUESTRA LAS OPCIONES CON 1 2 3 4 ETC.
			
			System.out.println("\t"+(i+1)+". "+opciones.get(i));
			i++;
		}
	} 
	*/
	
	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public boolean correctAnswer(int id) { //se le pasa el id de la opción
		
		return id == correctOptionId;
		
		/*int opcionElegida = Integer.parseInt(id.toString());
		
		if (opcionElegida == idOpcionCorrecta) {
			return opcionElegida.isCorrecta();
		}*/
	}
	
}
