package Racer;

import java.util.ArrayList;

public class ActionDice extends Dice {
	
	private ArrayList<Action> actions = new ArrayList<Action>(6);

	public ActionDice() {
		
	}
	
	public Action getAction(int value) {
		return actions.get(value - 1);
	}
}


