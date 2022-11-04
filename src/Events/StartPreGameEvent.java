package Events;

import Views.PreGamePanel;
import Views.WelcomePanel;

public class StartPreGameEvent {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	private WelcomePanel welPanel;
	private PreGamePanel racerGUI;

	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 * @param welPanel
	 * @param racerGUI
	 */
	public StartPreGameEvent(WelcomePanel welPanel, PreGamePanel racerGUI) {
		this.welPanel = welPanel;
		this.racerGUI = racerGUI;
	}
	
	//--------------------------------------------------->||GETTERS||<------------------------------------------------------------\\
	
	/**
	 * 
	 * @return
	 */
	public WelcomePanel getWelPanel() {
		return welPanel;
	}
	
	/**
	 * 
	 * @return
	 */
	public PreGamePanel getRacerGUI() {
		return racerGUI;
	}

}
