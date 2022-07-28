package Racer;

import java.awt.Color;

public class Player {
	private String name = new String();
	private int id;
	private TeamColor tColor;

	public Player(){
		
	}
	
	public Player(String name, int id, TeamColor tColor) {
		this.name = name;
		this.id = id;
		this.tColor = tColor;
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

	public TeamColor getTeamColor() {
		return tColor;
	}

	public void setTeamColor(TeamColor tColor) {
		this.tColor = tColor;
	}
	
}
