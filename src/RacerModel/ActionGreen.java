package RacerModel;

import java.awt.Color;
import java.util.ArrayList;

import Controller.RacerBoard;

public class ActionGreen extends Action {

	public ActionGreen() {
		// TODO Auto-generated constructor stub
	}

	public ActionGreen(String desc, Color color) {
		super(desc, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct) {
		rb.movePlayer(rp, diceValue);

	}

}
