package RacerModel.ActionPckg;

import java.awt.Color;

import Controller.RacerBoard;
import RacerModel.PlayerPckg.RacerPlayer;

public class ActionGreen extends Action {

	public ActionGreen() {
		// TODO Auto-generated constructor stub
	}

	public ActionGreen(String desc, Color color, boolean questionNeeded, boolean actionToNextPlayer) {
		super(desc, color, questionNeeded, actionToNextPlayer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct) {
		rb.movePlayer(rp, diceValue);

	}

}
