package com.racer.racerModel.SquarePckg;

import com.racer.controller.RacerBoard;

import com.racer.events.QuestionSquareEvent;
import com.racer.racerModel.PlayerPckg.Player;

public class QuestionSquare extends Square {
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	
	/**
	 * 
	 */
	public QuestionSquare() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param id
	 * @param tag
	 * @param curPlayer
	 * @param moreThanOnePlayer 
	 */
	public QuestionSquare(int id, String tag, Player curPlayer, boolean moreThanOnePlayer) {
		super(id, tag, curPlayer, moreThanOnePlayer);

	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public void doSquareAction(RacerBoard rb, int diceValue) {
		if(diceValue != 0) {
			rb.listenQuestionSquare(new QuestionSquareEvent());
		} else super.doSquareAction(rb, diceValue);
	}
	

}
