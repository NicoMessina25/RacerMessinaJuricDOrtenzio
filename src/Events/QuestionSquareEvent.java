package Events;

import javax.swing.JButton;
import javax.swing.JTextPane;

import RacerModel.ActionDice;

public class QuestionSquareEvent {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
		
	JButton btnStartQuestion;
	JTextPane textPaneAction;
	ActionDice actDice;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 */
	public QuestionSquareEvent() {
	}
	
	/**
	 * 
	 * @param btnStartQuestion
	 * @param textPaneAction
	 * @param actDice
	 */
	public QuestionSquareEvent(JButton btnStartQuestion, JTextPane textPaneAction, ActionDice actDice) {
		this.btnStartQuestion = btnStartQuestion;
		this.actDice = actDice;
		this.textPaneAction = textPaneAction;
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	
	/**
	 * 
	 * @return
	 */
	public JButton getBtnStartQuestion() {
		return btnStartQuestion;
	}
	
	/**
	 * 
	 * @param btnStartQuestion
	 */
	public void setBtnStartQuestion(JButton btnStartQuestion) {
		this.btnStartQuestion = btnStartQuestion;
	}
	
	/**
	 * 
	 * @return
	 */
	public JTextPane getTextPaneAction() {
		return textPaneAction;
	}
	
	/**
	 * 
	 * @param textPaneAction
	 */
	public void setTextPaneAction(JTextPane textPaneAction) {
		this.textPaneAction = textPaneAction;
	}
	
	/**
	 * 
	 * @return
	 */
	public ActionDice getActDice() {
		return actDice;
	}
	
	/**
	 * 
	 * @param actDice
	 */
	public void setActDice(ActionDice actDice) {
		this.actDice = actDice;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	/**
	 * 
	 */
	public void activateQuestionSquare() {
	
			btnStartQuestion.setEnabled(true);
			textPaneAction.setText("Casilla de Pregunta!");
			
			actDice.setValue(4); //amarillo
		
	}

}
