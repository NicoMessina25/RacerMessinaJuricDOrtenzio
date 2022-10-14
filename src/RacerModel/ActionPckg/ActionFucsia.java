package RacerModel.ActionPckg;

import java.awt.Color;
import Controller.RacerBoard;
import RacerModel.PlayerPckg.RacerPlayer;

public class ActionFucsia extends ActionGreen {
	

	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	public ActionFucsia() {

	}

	public ActionFucsia(String desc, Color color, boolean questionNeeded, boolean actionToNextPlayer) {
		super(desc, color, questionNeeded, actionToNextPlayer);
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct) {
		if (correct) {
			super.doAction(rb, rp, diceValue, correct);
		} else
			super.doAction(rb, rp, diceValue * (-1), correct);
		;

	}

}
