package RacerModel.SquarePckg;

import Controller.RacerBoard;
import Events.WinEvent;
import RacerModel.PlayerPckg.Player;

public class FinishSquare extends Square {
	
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 */
	public FinishSquare() {
		
	}
	
	/**
	 * 
	 * @param id
	 * @param tag
	 * @param curPlayer
	 * @param moreThanOnePlayer 
	 */
	public FinishSquare(int id, String tag, Player curPlayer, boolean moreThanOnePlayer) {
		super(id, tag, curPlayer, moreThanOnePlayer);
		
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public void doSquareAction(RacerBoard rb, boolean correct) {
		rb.listenWin(new WinEvent());
	}
}
