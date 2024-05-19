package com.racer.racerModel.ActionPckg;

import com.racer.racerModel.PlayerPckg.RacerPlayer;

import com.racer.controller.RacerBoard;

public class ActionRed extends ActionGreen {
	

	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 */
	public ActionRed() {
	}
	
	/**
	 * 
	 * @param desc
	 * @param img
	 * @param questionNeeded
	 * @param actionToNextPlayer
	 */
	public ActionRed(String desc, String img, boolean questionNeeded, boolean actionToNextPlayer) {
		super(desc, img, questionNeeded, actionToNextPlayer);
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct) {
		super.doAction(rb, rp, diceValue, correct);
		rp.setLostTurns(1);
	}

}
