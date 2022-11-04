package Events;

import java.awt.Dimension;

import javax.swing.JFrame;

import Controller.RacerBoard;
import Views.WelcomePanel;

public class ResetEvent {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	private JFrame fToClose;
	
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 * @param frameToClose
	 */
	public ResetEvent(JFrame frameToClose) {
		this.fToClose = frameToClose;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	/**
	 * 
	 * @param rb
	 */
	public void reset(RacerBoard rb) {
		WelcomePanel welP = new WelcomePanel(rb);
		welP.setVisible(true);
		welP.setSize(new Dimension(1280, 720));
		fToClose.dispose();
		
	}

	

}
