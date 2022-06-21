package Racer;

import java.util.ArrayList;

public class RacerBoard extends Board {
	
	private int amouQues = 50;
	private ArrayList<Question> questions = new ArrayList<Question>(amouQues);
	private ArrayList<Category> categories = new ArrayList<Caregory>();
	private Dice dice = new Dice();

	public RacerBoard() {
		
	}

	public RacerBoard(int amouPlayers) {
		super(amouPlayers);
	}

	public int getAmouQues() {
		return amouQues;
	}

	public void setAmouQues(int amouQues) {
		this.amouQues = amouQues;
	}
	
	
	public Dice getDice() {
		return dice;
	}

	public void setDice(Dice dice) {
		this.dice = dice;
	}

	public static void main(String[] args) {
		RacerBoard rb = new RacerBoard();
		rb.getDice().diceRoll();
		System.out.println(rb.getDice().getValue());
	}

}
