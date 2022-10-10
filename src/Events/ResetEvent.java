package Events;

import java.awt.Dimension;

import Views.BoardPaneGUI;
import Views.WelcomePanel;
import Views.WinPanel;

public class ResetEvent {
	
	private WinPanel winP;
	private BoardPaneGUI bp;
	private WelcomePanel welP;

	public ResetEvent(WinPanel winP, BoardPaneGUI bp, WelcomePanel welP) {
		this.winP = winP;
		this.bp = bp;
		this.welP = welP;
	}
	
	public void reset() {
		welP.setVisible(true);
		welP.setSize(new Dimension(1280, 720));
		bp.dispose();
		winP.dispose();
	}

	

}
