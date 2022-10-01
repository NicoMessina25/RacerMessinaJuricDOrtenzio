package Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Controller.RacerBoard;
import RacerModel.Player;
import RacerModel.RacerPlayer.RacerPlayer;
import Views.QuestionPanel;

public class StartQuestionEvent {
	
	private RacerPlayer player;
	private QuestionPanel questionPanel;

	public StartQuestionEvent(Player player, QuestionPanel qP) {
		this.player = (RacerPlayer) player;
		questionPanel = qP;
	}
	
	public void startQuestion(RacerBoard rb) {
		
		
		rb.setTimeLeft(player.getTimePerOption()*rb.getCurQuestion().getOptions().size());
		questionPanel.setSize(400, 400);
		questionPanel.setVisible(true);
		questionPanel.setLocationRelativeTo(null);
		rb.setTimer(new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rb.setTimeLeft(rb.getTimeLeft() - 1);;
				if(rb.getTimeLeft() == 0) {
					rb.getTimer().stop();
				}
				questionPanel.updateTimeLeft(rb.getTimeLeft());
				
			}
			
		}));
		rb.startTimer();

	}

}
