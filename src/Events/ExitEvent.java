package Events;

import javax.swing.JFrame;

public class ExitEvent {
	
	private JFrame winFrame, boardPane;
	
	public ExitEvent(JFrame wf, JFrame bp) {
		winFrame = wf;
		boardPane = bp;
	}
	
	public void exit() {
		boardPane.dispose();
		winFrame.dispose();
	}

}
