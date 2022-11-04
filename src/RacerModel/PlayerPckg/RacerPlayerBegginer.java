package RacerModel.PlayerPckg;

import java.util.ArrayList;
import java.util.List;

import RacerModel.Category;
import RacerModel.Question;
import RacerModel.Team;

public class RacerPlayerBegginer extends RacerPlayer {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
		

	private int timePerOption;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	/**
	 * 
	 */
	public RacerPlayerBegginer() {
		super();
	}
	
	/**
	 * 
	 * @param name
	 */
	public RacerPlayerBegginer(String name) {
		super(name);
	}
	
	/**
	 * 
	 * @param name
	 * @param id
	 * @param tColor
	 * @param begginingSquareId
	 */
	public RacerPlayerBegginer(String name, int id, Team tColor, int begginingSquareId) {
		super(name, id,  tColor, begginingSquareId);
		timePerOption = 15;
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
		
	@Override
	public int getTimePerOption() {
		return timePerOption;
	}
	
	/**
	 * 
	 * @param timePOp
	 */
	public void setTimePerOption(int timePOp) {
		timePerOption = timePOp;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public List<Question> getFilteredQuestions(ArrayList<Question> questions, Category catChosen){		
		return questions.stream().filter(q -> q.getDificulty() <= 3 && q.getCategory()
				.getDescription().equals(catChosen.getDescription())).toList();
	}
	
	

	@Override
	public Question getQuestionAdapted(Question q) {
		int N = q.getOptions().size();
		int i;
		while(q.getOptions().size() > Math.ceil(N/2.0)) {
			i = (int) Math.floor(Math.random()*(q.getOptions().size()-1)) + 1; 
			q.getOptions().remove(i);
		}
		return q;
	}

	@Override
	public String typeToString() {
		// TODO Auto-generated method stub
		return "Novato";
	}
	
  /*@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(" Experto: ");
		sb.append("NO");
		return sb.toString();
	}*/

}
