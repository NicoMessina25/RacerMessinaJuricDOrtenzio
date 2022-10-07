package Events;

import Views.RacerGUI;
import Views.WelcomePanel;

public class StartPreGameEvent {
	
	private WelcomePanel welPanel;
	private RacerGUI racerGUI;

	public StartPreGameEvent(WelcomePanel welPanel, RacerGUI racerGUI) {
		this.welPanel = welPanel;
		this.racerGUI = racerGUI;
	}
	
	public void startPreGame() {
		welPanel.dispose();
		racerGUI.setVisible(true);
	}

}
