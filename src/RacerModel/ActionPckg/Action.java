package RacerModel.ActionPckg;

import java.awt.Color;

import Controller.RacerBoard;
import RacerModel.PlayerPckg.RacerPlayer;

public abstract class Action {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
		
	private String desc = new String();
	private Color color;
	private String img;
	private boolean questionNeeded;
	private boolean actionToNextPlayer;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	public Action() {
		
	}
	
	public Action(String desc, String img, boolean questionNeeded, boolean actionToNextPlayer) {
		this.desc = desc;
		this.img = img;
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
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\


	public abstract void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct);

}
