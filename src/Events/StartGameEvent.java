package Events;

import java.awt.Dimension;
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
	
	
	public void starGame() {
		boardPane.setVisible(true);
		boardPane.setSize(new Dimension(1280, 720));
		Window w = SwingUtilities.getWindowAncestor(playersPane);
		w.dispose();
	}

}
