
package RacerModel;

import java.util.ArrayList;

public class Question {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\

	private int id, correctOptionId, dificulty;
	private String statement;
	private ArrayList<Option> options = new ArrayList<Option>(); // N CANTIDAD DE OPCIONES
	private Category category;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 * @param id
	 * @param correctOptionId
	 * @param cat
	 * @param dificulty
	 * @param statement
	 * @param options
	 */
	public Question(int id, int correctOptionId, Category cat, int dificulty, String statement, ArrayList<Option> options) {
		this.id = id;
		this.dificulty = dificulty;
		this.statement = statement;
		this.options = options;
		this.correctOptionId = correctOptionId;
		setCategory(cat);
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
	public int getCorrectOptionId() {
		return correctOptionId;
	}

	/**
	 * 
	 * @return
	 */
	public int getDificulty() {
		return dificulty;
	}

	/**
	 * 
	 * @return
	 */
	public String getStatement() {
		return statement;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Option> getOptions() {
		return options;

	}

	/**
	 * 
	 * @return
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * 
	 * @param category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\

	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean correctAnswer(int id) { 
		
		return id == correctOptionId;
	}
	
}

