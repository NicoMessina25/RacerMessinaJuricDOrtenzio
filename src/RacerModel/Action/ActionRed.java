package RacerModel.Action;

import java.awt.Color;
import java.util.ArrayList;

import Controller.RacerBoard;
import RacerModel.RacerPlayer.RacerPlayer;

public class ActionRed extends ActionGreen {

	public ActionRed() {
		// TODO Auto-generated constructor stub
	}

	public ActionRed(String desc, Color color) {
		super(desc, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct) {
		super.doAction(rb, rp, diceValue, correct);
		rp.setLostTurns(1);
	}

}
