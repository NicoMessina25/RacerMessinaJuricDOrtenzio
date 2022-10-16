package RacerModel.ActionPckg;

import Controller.RacerBoard;
import RacerModel.PlayerPckg.RacerPlayer;

public class ActionRed extends ActionGreen {
	

	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	public ActionRed() {
		// TODO Auto-generated constructor stub
	}

	public ActionRed(String desc, String img, boolean questionNeeded, boolean actionToNextPlayer) {
		super(desc, img, questionNeeded, actionToNextPlayer);
		// TODO Auto-generated constructor stub
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct) {
		super.doAction(rb, rp, diceValue, correct);
		rp.setLostTurns(1);
	}

}
