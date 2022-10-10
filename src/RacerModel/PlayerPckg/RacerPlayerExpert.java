package RacerModel.PlayerPckg;

import java.util.ArrayList;
import java.util.List;

import RacerModel.Category;
import RacerModel.Question;
import RacerModel.TeamColor;

public class RacerPlayerExpert extends RacerPlayer {
	
	private int timePerOption;

	public RacerPlayerExpert() {
		// TODO Auto-generated constructor stub
	}

	public RacerPlayerExpert(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public RacerPlayerExpert(String name, int id, TeamColor tColor, int begginingSquareId) {
		super(name, id, tColor, begginingSquareId);
		timePerOption = 10;
	}
	
	@Override
	public int getTimePerOption() {
		return timePerOption;
	}

	public void setTimePerOption(int timePOp) {
		timePerOption = timePOp;
	}
	
	@Override
	public List<Question> getFilteredQuestions(ArrayList<Question> questions, Category catChosen){		
		return questions.stream().filter(q -> q.getDificulty() >= 3 && q.getCategory().getDescription().equals(catChosen.getDescription())).toList();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(" Experto: ");
		sb.append("SI");
		return sb.toString();
	}

	@Override
	public Question getQuestionAdapted(Question q) {
		// TODO Auto-generated method stub
		return q;
	}

	@Override
	public String typeToString() {
		// TODO Auto-generated method stub
		return "Experto";
	}

}
