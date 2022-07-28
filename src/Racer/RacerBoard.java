package Racer;

import java.util.ArrayList;

public class RacerBoard extends Board {
	
	private int amouQues = 50;
	private ArrayList<Question> questions = new ArrayList<Question>(amouQues);
	private ArrayList<Category> categories = new ArrayList<Category>();

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
	
	

}
