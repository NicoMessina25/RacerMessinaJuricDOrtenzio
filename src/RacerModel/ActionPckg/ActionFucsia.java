package RacerModel.ActionPckg;

import Controller.RacerBoard;
import RacerModel.PlayerPckg.RacerPlayer;

public class ActionFucsia extends ActionGreen {
	

	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 */
	public ActionFucsia() {

	}
	
	/**
	 * 
	 * @param desc
	 * @param color
	 * @param questionNeeded
	 * @param actionToNextPlayer
	 */
	public ActionFucsia(String desc, String color, boolean questionNeeded, boolean actionToNextPlayer) {
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
