package com.racer.racerModel.ActionPckg;

import com.racer.racerModel.PlayerPckg.RacerPlayer;

import com.racer.controller.RacerBoard;

public class ActionOrange extends ActionFucsia {

	// ------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 */
	public ActionOrange() {
	}
	
	/**
	 * 
	 * @param desc
	 * @param img
	 * @param questionNeeded
	 * @param actionToNextPlayer
	 */
	public ActionOrange(String desc, String img, boolean questionNeeded, boolean actionToNextPlayer) {
		super(desc, img, questionNeeded, actionToNextPlayer);
	}
	
	// ------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct) {
		super.doAction(rb, (RacerPlayer) rb.getPlayers().get(rb.nextPlayer()), diceValue, correct);
	}

}
