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
	
	/**
	 * 
	 */
	public Action() {
		
	}
	
	/**
	 * 
	 * @param desc
	 * @param img
	 * @param questionNeeded
	 * @param actionToNextPlayer
	 */
	public Action(String desc, String img, boolean questionNeeded, boolean actionToNextPlayer) {
		this.desc = desc;
		this.img = img;
		this.questionNeeded = questionNeeded;
		this.actionToNextPlayer = actionToNextPlayer;
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	
	/**
	 * 
	 * @return
	 */
	public String getDesc() {
		return desc;
	}
	
	/**
	 * 
	 * @param desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	/**
	 * 
	 * @return
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isQuestionNeeded() {
		return questionNeeded;
	}
	
	/**
	 * 
	 * @param questionNeeded
	 */
	public void setQuestionNeeded(boolean questionNeeded) {
		this.questionNeeded = questionNeeded;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isActionToNextPlayer() {
		return actionToNextPlayer;
	}
	
	/**
	 * 
	 * @param actionToNextPlayer
	 */
	public void setActionToNextPlayer(boolean actionToNextPlayer) {
		this.actionToNextPlayer = actionToNextPlayer;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getImg() {
		return img;
	}
	
	/**
	 * 
	 * @param img
	 */
	public void setImg(String img) {
		this.img = img;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\

	/**
	 * 
	 * @param rb
	 * @param rp
	 * @param diceValue
	 * @param correct
	 */
	public abstract void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct);

}
