package Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Controller.RacerBoard;
import RacerModel.Question;
import RacerModel.PlayerPckg.Player;
import RacerModel.PlayerPckg.RacerPlayer;
import Views.QuestionPanel;

public class StartQuestionEvent {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	private RacerPlayer player;
	private QuestionPanel questionPanel;
	private Timer timer;
	private Question question;
	private int timeLeft;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 * @param player
	 * @param q
	 * @param qP
	 */
	public StartQuestionEvent(Player player, Question q, QuestionPanel qP) {
		this.player = (RacerPlayer) player;
		questionPanel = qP;
		question = q;
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	
	/**
	 * 
	 * @return
	 */
	public int getTimeLeft() {
		return timeLeft;
	}
	
	/**
	 * 
	 * @param timeLeft
	 */
	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}
	
	/**
	 * 
	 * @return
	 */
	public Timer getTimer() {
		return timer;
	}
	
	/**
	 * 
	 * @param timer
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	/**
	 * 
	 * @return
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * 
	 * @param question
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	/**
	 * 
	 * @param rb
	 */
	public void startQuestion(RacerBoard rb) {
			
			
			setTimeLeft(player.getTimePerOption()*question.getOptions().size());
			questionPanel.setSize(900, 500);
			questionPanel.setVisible(true);
			questionPanel.setLocationRelativeTo(null);
			timer = new Timer(1000, new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					setTimeLeft(getTimeLeft()-1);
					if(timeLeft == 0) {
						timer.stop();
					}
					questionPanel.updateTimeLeft(getTimeLeft());
					
				}
				
			});
			timer.start();

	}

}
