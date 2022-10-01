package RacerModel.Action;

import java.awt.Color;

import Controller.RacerBoard;
import RacerModel.RacerPlayer.RacerPlayer;

public class ActionOrange extends ActionFucsia {

	public ActionOrange() {
		// TODO Auto-generated constructor stub
	}

	public ActionOrange(String desc, Color color) {
		super(desc, color);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct) {
		super.doAction(rb, (RacerPlayer) rb.getPlayers().get(rb.nextPlayer()), diceValue, correct);
	}

}
