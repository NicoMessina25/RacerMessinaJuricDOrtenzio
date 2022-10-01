package RacerModel.RacerPlayer;

import java.util.ArrayList;
import java.util.List;

import RacerModel.Category;
import RacerModel.Question;
import RacerModel.TeamColor;

public class RacerPlayerBegginer extends RacerPlayer {

	private int timePerOption;
	
	public RacerPlayerBegginer() {
		super();
	}
	
	public RacerPlayerBegginer(String name) {
		super(name);
	}
	
	public RacerPlayerBegginer(String name, int id, TeamColor tColor) {
		super(name, id,  tColor);
		timePerOption = 15;
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
		return questions.stream().filter(q -> q.getDificulty() <= 3 && q.getCategory().getDescription().equals(catChosen.getDescription())).toList();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(" Experto: ");
		sb.append("NO");
		return sb.toString();
	}
	


}
