package Events;

import java.awt.Window;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Controller.RacerBoard;
import Views.BoardPaneGUI;

public class StartGameEvent {
	
	private BoardPaneGUI boardPane;
	private JPanel playersPane;
	
	public StartGameEvent() {
		
	}
	
	public StartGameEvent(BoardPaneGUI boardPane, JPanel playersPane) {
		this.boardPane = boardPane;
		this.playersPane = playersPane;
	}

	public BoardPaneGUI getBoardPane() {
		return boardPane;
	}



	public void setBoardPane(BoardPaneGUI boardPane) {
		this.boardPane = boardPane;
	}
	
	
	public void starGame(RacerBoard rb) {
		boardPane.setVisible(true);
		Window w = SwingUtilities.getWindowAncestor(playersPane);
		w.dispose();
	}

}
