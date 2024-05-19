package com.racer.racerModel;

import java.util.Random;

public class Dice {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	private int value;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 */
	public Dice() {
		
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	
	/**
	 * 
	 * @return
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	/**
	 * 
	 */
	public void diceRoll() {
		Random ran = new Random();
		this.setValue(ran.nextInt(6) + 1);
	}

}
