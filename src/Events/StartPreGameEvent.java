package Events;

import Views.PreGamePanel;
import Views.WelcomePanel;

public class StartPreGameEvent {
	
	private WelcomePanel welPanel;
	private PreGamePanel racerGUI;

	

	public StartPreGameEvent(WelcomePanel welPanel, PreGamePanel racerGUI) {
		this.welPanel = welPanel;
		this.racerGUI = racerGUI;
	}
	
	public WelcomePanel getWelPanel() {
		return welPanel;
	}

	public PreGamePanel getRacerGUI() {
		return racerGUI;
	}

}
