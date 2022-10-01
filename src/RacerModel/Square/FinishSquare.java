package RacerModel.Square;

import java.awt.Color;

import javax.swing.SwingUtilities;

import Controller.RacerBoard;
import Events.WinEvent;
import RacerModel.Player;
import Views.BoardPaneGUI;

public class FinishSquare extends Square {

	public FinishSquare() {
		// TODO Auto-generated constructor stub
	}

	public FinishSquare(int id, String tag, Color color, Player curPlayer) {
		super(id, tag, color, curPlayer);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void doSquareAction(RacerBoard rb) {
		rb.executeWin();
	}
}
