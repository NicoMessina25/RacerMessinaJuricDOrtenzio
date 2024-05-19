package com.racer.racerModel.ActionPckg;

import com.racer.racerModel.PlayerPckg.RacerPlayer;

import com.racer.controller.RacerBoard;

public class ActionGreen extends Action {
	

	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 */
	public ActionGreen() {

	}
	
	/**
	 * 
	 * @param desc
	 * @param img
	 * @param questionNeeded
	 * @param actionToNextPlayer
	 */
	public ActionGreen(String desc, String img, boolean questionNeeded, boolean actionToNextPlayer) {
		super(desc, img, questionNeeded, actionToNextPlayer);
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct) {
		rb.movePlayer(rp, diceValue);

	}

}
