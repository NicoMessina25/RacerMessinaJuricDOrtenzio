package RacerModel;

import java.awt.Color;

import Controller.RacerBoard;

public class ActionYellow extends ActionGreen {

	public ActionYellow() {
		// TODO Auto-generated constructor stub
	}

	public ActionYellow(String desc, Color color) {
		super(desc, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct) {		
		if(correct) {
			if(rb.getActionDice().getValue() == 3) {
				super.doAction(rb, (RacerPlayer) rb.getPlayers().get(rb.nextPlayer()), diceValue, correct);
			} else super.doAction(rb, rp, diceValue, correct);	
		}
	}

}
