package RacerModel.SquarePckg;

import Controller.RacerBoard;
import Events.QuestionSquareEvent;
import RacerModel.PlayerPckg.Player;

public class QuestionSquare extends Square {
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	
	/**
	 * 
	 */
	public QuestionSquare() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param id
	 * @param tag
	 * @param curPlayer
	 * @param moreThanOnePlayer 
	 */
	public QuestionSquare(int id, String tag, Player curPlayer, boolean moreThanOnePlayer) {
		super(id, tag, curPlayer, moreThanOnePlayer);

	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public void doSquareAction(RacerBoard rb, boolean correct) {
		if(correct) {
			rb.listenQuestionSquare(new QuestionSquareEvent());
		} else super.doSquareAction(rb, correct);
	}

}
