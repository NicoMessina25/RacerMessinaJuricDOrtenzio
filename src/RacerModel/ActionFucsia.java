package RacerModel;

import java.awt.Color;
import Controller.RacerBoard;

public class ActionFucsia extends ActionGreen {

	public ActionFucsia() {
		// TODO Auto-generated constructor stub
	}

	public ActionFucsia(String desc, Color color) {
		super(desc, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct) {
		if(correct) {
			super.doAction(rb, rp, diceValue, correct);
		} else super.doAction(rb, rp, diceValue*(-1), correct);;
		

	}

}
