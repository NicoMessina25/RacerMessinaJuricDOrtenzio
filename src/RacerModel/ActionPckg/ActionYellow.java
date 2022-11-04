package RacerModel.ActionPckg;

import Controller.RacerBoard;
import RacerModel.PlayerPckg.RacerPlayer;

public class ActionYellow extends ActionGreen {
	

	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 */
	public ActionYellow() {
	}
	
	/**
	 * 
	 * @param desc
	 * @param img
	 * @param questionNeeded
	 * @param actionToNextPlayer
	 */
	public ActionYellow(String desc, String img, boolean questionNeeded, boolean actionToNextPlayer) {
		super(desc, img, questionNeeded, actionToNextPlayer);
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct) {		
		if(correct) {
			super.doAction(rb, rp, diceValue, correct);	
		} else rb.getDice().setValue(0);
	}

}
