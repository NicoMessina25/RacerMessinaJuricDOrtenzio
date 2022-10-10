package RacerModel;

import java.awt.Color;

public class TeamColor {
	
	private Color col;
	private String teamName = new String();
	private String teamId = new String();

	public TeamColor() {
		
	}
	
	public TeamColor(String tName, String tId, Color col) {
		teamName = tName;
		this.col = col; 
		setTeamId(tId);
	}

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

	public String toString() {
		/*StringBuilder sb = new StringBuilder();
		sb.append(teamName);
		return sb.toString();*/
		return getTeamName();
	}

}
