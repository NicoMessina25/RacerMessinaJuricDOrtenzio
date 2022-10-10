package RacerModel.PlayerPckg;

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
	
	public RacerPlayerBegginer(String name, int id, TeamColor tColor, int begginingSquareId) {
		super(name, id,  tColor, begginingSquareId);
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
	
	/*@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(" Experto: ");
		sb.append("NO");
		return sb.toString();
	}*/

	@Override
	public Question getQuestionAdapted(Question q) {
		int N = q.getOptions().size();
		int i;
		while(q.getOptions().size() > Math.ceil(N/2.0)) {
			//System.out.println((N + " >= " + q.getOptions().size() + " > " + Math.ceil(N/2) + " > " + N/2));
			i = (int) Math.ceil(Math.random()*(q.getOptions().size()-1)); 
			q.getOptions().remove(i);
		}
		return q;
	}

	@Override
	public String typeToString() {
		// TODO Auto-generated method stub
		return "Novato";
	}
	


}
