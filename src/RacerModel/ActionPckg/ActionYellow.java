package RacerModel.ActionPckg;

import Controller.RacerBoard;
import RacerModel.PlayerPckg.RacerPlayer;

public class ActionYellow extends ActionGreen {
	

	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	public ActionYellow() {
		// TODO Auto-generated constructor stub
	}

	public ActionYellow(String desc, String img, boolean questionNeeded, boolean actionToNextPlayer) {
		super(desc, img, questionNeeded, actionToNextPlayer);
		// TODO Auto-generated constructor stub
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct) {		
		if(correct) {
			super.doAction(rb, rp, diceValue, correct);	
		}
	}

}
