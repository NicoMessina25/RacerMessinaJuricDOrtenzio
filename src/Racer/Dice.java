package Racer;

import java.util.Random;

public class Dice {
	
	private int value;

	public Dice() {
		
	}
	
	public void diceRoll() {
		Random ran = new Random();
		this.setValue(ran.nextInt(6) + 1);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
