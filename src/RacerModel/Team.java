package RacerModel;

import java.awt.Color;

public class Team {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\

	private Color col;
	private String teamName = new String();
	private String teamId = new String();
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	public Team() {

	}

	public Team(String tName, String tId, Color col) {
		teamName = tName;
		this.col = col;
		setTeamId(tId);
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	public Color getCol() {
		return col;
	}

	public void setCol(Color col) {
		this.col = col;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public String toString() {
		return getTeamName();
	}

}
