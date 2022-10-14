package RacerModel.SquarePckg;

import java.awt.Color;

import Controller.RacerBoard;
import RacerModel.PlayerPckg.Player;

public class FinishSquare extends Square {
	
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	public FinishSquare() {
		// TODO Auto-generated constructor stub
	}

	public FinishSquare(int id, String tag, Color color, Player curPlayer) {
		super(id, tag, color, curPlayer);
		// TODO Auto-generated constructor stub
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public void doSquareAction(RacerBoard rb, boolean correct) {
		rb.executeWin(rb.getPlayerToAnswer());
	}
}
