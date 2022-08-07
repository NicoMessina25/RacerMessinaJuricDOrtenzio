package Events;

import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class StartGameEvent {
	
	private JFrame boardPane;
	private JPanel playersPane;
	
	public StartGameEvent() {
		
	}
	
	public StartGameEvent(JFrame boardPane, JPanel playersPane) {
		this.boardPane = boardPane;
		this.playersPane = playersPane;
	}

	public JFrame getBoardPane() {
		return boardPane;
	}



	public void setBoardPane(JFrame boardPane) {
		this.boardPane = boardPane;
	}
	
	
	public void starGame() {
		boardPane.setVisible(true);
		Window w = SwingUtilities.getWindowAncestor(playersPane);
		w.dispose();
	}

}
