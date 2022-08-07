package RacerModel;

import java.awt.Color;

public class Category {
	private String description;
	private Color color;
	
	public Category() {
		
	}
	
	public Category(String description, Color color) {
		this.description = description;
		this.color = color;
	}

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
