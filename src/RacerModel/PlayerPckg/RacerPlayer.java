package RacerModel.PlayerPckg;

import java.util.ArrayList;
import java.util.List;

import RacerModel.Category;
import RacerModel.Question;
import RacerModel.TeamColor;

public abstract class RacerPlayer extends Player {
	private TeamColor tColor;
	private int lostTurns;

	public RacerPlayer() {
		super();
	}
	
	public RacerPlayer(String name) {
		super(name);
	}
	
	public RacerPlayer(String name, int id, TeamColor tColor, int begginingSquareId) {
		super(name, id, begginingSquareId);
		this.tColor = tColor;
		this.lostTurns = 0;
	}

	public int getLostTurns() {
		return lostTurns;
	}

	public void setLostTurns(int lostTurns) {
		this.lostTurns = lostTurns;
	}
	
	public TeamColor getTeamColor() {
		return tColor;
	}

	public void setTeamColor(TeamColor tColor) {
		this.tColor = tColor;
	}
	
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
