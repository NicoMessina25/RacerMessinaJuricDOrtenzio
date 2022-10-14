package RacerModel.PlayerPckg;

import java.util.ArrayList;
import java.util.List;

import RacerModel.Category;
import RacerModel.Question;
import RacerModel.Team;

public abstract class RacerPlayer extends Player {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	
	private Team tColor;
	private int lostTurns;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	

	public RacerPlayer() {
		super();
	}
	
	public RacerPlayer(String name) {
		super(name);
	}
	
	public RacerPlayer(String name, int id, Team tColor, int begginingSquareId) {
		super(name, id, begginingSquareId);
		this.tColor = tColor;
		this.lostTurns = 0;
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	

	public int getLostTurns() {
		return lostTurns;
	}

	public void setLostTurns(int lostTurns) {
		this.lostTurns = lostTurns;
	}
	
	public Team getTeamColor() {
		return tColor;
	}

	public void setTeamColor(Team tColor) {
		this.tColor = tColor;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	public abstract int getTimePerOption();
	
	public abstract List<Question> getFilteredQuestions(ArrayList<Question> questions, Category catChosen);
	
	public abstract Question getQuestionAdapted(Question q);
	
	public abstract String typeToString();
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(" Team: " + tColor.toString());
		return sb.toString();
	}
	
	
}
