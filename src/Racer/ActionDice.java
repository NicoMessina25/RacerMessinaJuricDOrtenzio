package Racer;

<<<<<<< HEAD
public class ActionDice extends Dice {
	

}
=======
import java.util.ArrayList;

public class ActionDice extends Dice {
	
	private ArrayList<Action> actions = new ArrayList<Action>(6);

	public ActionDice() {
		
	}
	
	public Action getAction(int value) {
		return actions.get(value - 1);
	}
}


>>>>>>> 2e4ee7abc1ac8744898df979e0e9e4fac3379315
