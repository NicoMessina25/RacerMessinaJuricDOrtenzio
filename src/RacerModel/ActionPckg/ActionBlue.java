package RacerModel.ActionPckg;

import java.awt.Color;

import Controller.RacerBoard;
import RacerModel.PlayerPckg.RacerPlayer;

public class ActionBlue extends ActionGreen {
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
		

	public ActionBlue() {
	
	}

	public ActionBlue(String desc, Color color, boolean questionNeeded, boolean actionToNextPlayer) {
		super(desc, color, questionNeeded, actionToNextPlayer);

	}
	
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\

	@Override
	public void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct) {
		if (correct) {
			super.doAction(rb, rp, diceValue * 2, correct);
		} else
			rp.setLostTurns(1);

	}

}
