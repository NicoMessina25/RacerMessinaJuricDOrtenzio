package RacerModel.ActionPckg;

import java.awt.Color;

import Controller.RacerBoard;
import RacerModel.PlayerPckg.RacerPlayer;

public abstract class Action {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
		
	private String desc = new String();
	private Color color;
	private boolean questionNeeded;
	private boolean actionToNextPlayer;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	public Action() {
		
	}
	
	public Action(String desc, Color color, boolean questionNeeded, boolean actionToNextPlayer) {
		this.desc = desc;
		this.color = color;
		this.questionNeeded = questionNeeded;
		this.actionToNextPlayer = actionToNextPlayer;
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public boolean isQuestionNeeded() {
		return questionNeeded;
	}

	public void setQuestionNeeded(boolean questionNeeded) {
		this.questionNeeded = questionNeeded;
	}
	
	public boolean isActionToNextPlayer() {
		return actionToNextPlayer;
	}

	public void setActionToNextPlayer(boolean actionToNextPlayer) {
		this.actionToNextPlayer = actionToNextPlayer;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\


	public abstract void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct);

}
