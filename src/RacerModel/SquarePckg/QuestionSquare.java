package RacerModel.SquarePckg;

import java.awt.Color;

import Controller.RacerBoard;
import Events.QuestionSquareEvent;
import RacerModel.PlayerPckg.Player;

public class QuestionSquare extends Square {

	public QuestionSquare() {
		// TODO Auto-generated constructor stub
	}

	public QuestionSquare(int id, String tag, Color color, Player curPlayer) {
		super(id, tag, color, curPlayer);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void doSquareAction(RacerBoard rb, boolean correct) {
		if(correct) {
			rb.listenQuestionSquare(new QuestionSquareEvent());
		} else super.doSquareAction(rb, correct);
		
	}

}
