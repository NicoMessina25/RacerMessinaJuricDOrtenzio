package Events;

import Views.WinPanel;

public class WinEvent {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\

	private WinPanel wp;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	public WinEvent() {
		
	}
	
	/**
	 * 
	 * @param wp
	 */
	public WinEvent(WinPanel wp) {
		this.wp = wp;
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	
	public WinPanel getWp() {
		return wp;
	}

	public void setWp(WinPanel wp) {
		this.wp = wp;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	/**
	 * 
	 */
	public void win() {
		wp.setVisible(true);
	}

}
