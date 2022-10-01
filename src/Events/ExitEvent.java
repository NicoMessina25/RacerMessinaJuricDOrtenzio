package Events;

import javax.swing.JFrame;

public class ExitEvent {
	
	private JFrame frame;
	
	public ExitEvent(JFrame f) {
		frame = f;
	}
	
	public void exit() {
		frame.dispose();
	}

}
