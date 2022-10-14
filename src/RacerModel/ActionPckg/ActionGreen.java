package RacerModel.ActionPckg;

import java.awt.Color;

import Controller.RacerBoard;
import RacerModel.PlayerPckg.RacerPlayer;

public class ActionGreen extends Action {
	

	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	public ActionGreen() {

	}

	public ActionGreen(String desc, Color color, boolean questionNeeded, boolean actionToNextPlayer) {
		super(desc, color, questionNeeded, actionToNextPlayer);
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct) {
		rb.movePlayer(rp, diceValue);

	}

}
