package RacerModel.PlayerPckg;

import java.util.ArrayList;
import java.util.List;

import RacerModel.Category;
import RacerModel.Question;
import RacerModel.Team;

public class RacerPlayerExpert extends RacerPlayer {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
			
	
	private int timePerOption;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 */
	public RacerPlayerExpert() {
		
	}
	
	/**
	 * 
	 * @param name
	 */
	public RacerPlayerExpert(String name) {
		super(name);

	}
	/**
	 * 
	 * @param name
	 * @param id
	 * @param tColor
	 * @param begginingSquareId
	 */
	public RacerPlayerExpert(String name, int id, Team tColor, int begginingSquareId) {
		super(name, id, tColor, begginingSquareId);
		timePerOption = 10;
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
		return questions.stream().filter(q -> q.getDificulty() >= 3 && q.getCategory()
				.getDescription().equals(catChosen.getDescription())).toList();
	}
	
	

	@Override
	public Question getQuestionAdapted(Question q) {
		return q;
	}

	@Override
	public String typeToString() {
		return "Experto";
	}
	
  /*@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(" Experto: ");
		sb.append("SI");
		return sb.toString();
	}*/
}
