package RacerModel.Action;

import java.awt.Color;

import Controller.RacerBoard;
import RacerModel.RacerPlayer.RacerPlayer;

public class ActionBlue extends ActionGreen {

	public ActionBlue() {
		// TODO Auto-generated constructor stub
	}

	public ActionBlue(String desc, Color color) {
		super(desc, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct) {
		if(correct) {
			super.doAction(rb, rp, diceValue*2, correct);
		} else rp.setLostTurns(1);

	}

}
