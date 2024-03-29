package Events;

import javax.swing.JFrame;

public class ExitEvent {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	private JFrame activeFrame;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 * @param f
	 */
	public ExitEvent(JFrame f) {
		activeFrame = f;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	/**
	 * 
	 */
	public void exit() {

		activeFrame.dispose();
	}

}
