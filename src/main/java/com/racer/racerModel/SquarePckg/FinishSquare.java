package com.racer.racerModel.SquarePckg;

import com.racer.controller.RacerBoard;

import com.racer.events.WinEvent;
import com.racer.racerModel.PlayerPckg.Player;

public class FinishSquare extends Square {
	
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 */
	public FinishSquare() {
		
	}
	
	/**
	 * 
	 * @param id
	 * @param tag
	 * @param curPlayer
	 * @param moreThanOnePlayer 
	 */
	public FinishSquare(int id, String tag, Player curPlayer, boolean moreThanOnePlayer) {
		super(id, tag, curPlayer, moreThanOnePlayer);
		
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public void doSquareAction(RacerBoard rb, int diceValue) {
		rb.listenWin(new WinEvent());
	}
}
