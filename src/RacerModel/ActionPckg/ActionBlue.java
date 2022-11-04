package RacerModel.ActionPckg;

import Controller.RacerBoard;
import RacerModel.PlayerPckg.RacerPlayer;

public class ActionBlue extends ActionGreen {
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
		
	/**
	 * 
	 */
	public ActionBlue() {
	
	}
	
	/**
	 * 
	 * @param desc
	 * @param img
	 * @param questionNeeded
	 * @param actionToNextPlayer
	 */
	public ActionBlue(String desc, String img, boolean questionNeeded, boolean actionToNextPlayer) {
		super(desc, img, questionNeeded, actionToNextPlayer);

	}
	
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\

	@Override
	public void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct) {
		if (correct) {
			super.doAction(rb, rp, diceValue * 2, correct);
		} else
			rp.setLostTurns(1);

	}

}
