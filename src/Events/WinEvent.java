package Events;

import java.awt.Dimension;

import Views.WinPanel;

public class WinEvent {
	
	private WinPanel wp;

	public WinEvent(WinPanel wp) {
		this.wp = wp;
	}
	
	public void win() {
		wp.setVisible(true);
	}

}
