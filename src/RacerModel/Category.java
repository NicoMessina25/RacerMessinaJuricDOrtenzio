
package RacerModel;


import java.awt.Color;

public class Category {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	private String description;
	private Color color;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 */
	public Category() {
		
	}
	
	/**
	 * 
	 * @param description
	 * @param color
	 */
	public Category(String description, Color color) {
		this.description = description;
		this.color = color;
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	
	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 
	 * @return
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
}
