package Racer;

import java.awt.Color;

public class Player {
	private String name = new String();
	private int id;
	private Color color;

	public Player(){
		
	}
	
	public Player(String name, int id, Color color) {
		this.name = name;
		this.id = id;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
