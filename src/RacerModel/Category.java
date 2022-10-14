
package RacerModel;


import java.awt.Color;

public class Category {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	private String description;
	private Color color;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
		
	public Category() {
		
	}
	
	public Category(String description, Color color) {
		this.description = description;
		this.color = color;
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
