package RacerModel.ActionPckg;

import java.awt.Color;

import Controller.RacerBoard;
import RacerModel.PlayerPckg.RacerPlayer;

public class ActionOrange extends ActionFucsia {

	// ------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	public ActionOrange() {
		// TODO Auto-generated constructor stub
	}

	public ActionOrange(String desc, Color color, boolean questionNeeded, boolean actionToNextPlayer) {
		super(desc, color, questionNeeded, actionToNextPlayer);
		// TODO Auto-generated constructor stub
	}
	
	// ------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct) {
		super.doAction(rb, (RacerPlayer) rb.getPlayers().get(rb.nextPlayer()), diceValue, correct);
	}

}
