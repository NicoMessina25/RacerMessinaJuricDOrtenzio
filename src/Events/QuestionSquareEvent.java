package Events;

import javax.swing.JButton;
import javax.swing.JTextPane;

import RacerModel.ActionDice;

public class QuestionSquareEvent {
	JButton btnStartQuestion;
	JTextPane textPaneAction;
	ActionDice actDice;
	
	public QuestionSquareEvent() {
	}

	public QuestionSquareEvent(JButton btnStartQuestion, JTextPane textPaneAction, ActionDice actDice) {
		this.btnStartQuestion = btnStartQuestion;
		this.actDice = actDice;
		this.textPaneAction = textPaneAction;
	}
	
	public JButton getBtnStartQuestion() {
		return btnStartQuestion;
	}

	public void setBtnStartQuestion(JButton btnStartQuestion) {
		this.btnStartQuestion = btnStartQuestion;
	}

	public JTextPane getTextPaneAction() {
		return textPaneAction;
	}

	public void setTextPaneAction(JTextPane textPaneAction) {
		this.textPaneAction = textPaneAction;
	}

	public ActionDice getActDice() {
		return actDice;
	}

	public void setActDice(ActionDice actDice) {
		this.actDice = actDice;
	}

	public void activateQuestionSquare() {
	
			btnStartQuestion.setEnabled(true);
			textPaneAction.setText("Casilla de Pregunta!");
			
			actDice.setValue(4); //amarillo
		
	}

}
