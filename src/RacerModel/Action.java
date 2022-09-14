package RacerModel;

import java.awt.Color;

import Controller.RacerBoard;

public abstract class Action {
	
	private String desc = new String();
	private Color color;

	public Action() {
		
	}
	
	public Action(String desc, Color color) {
		this.desc = desc;
		this.color = color;
	}

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
	
	public abstract void doAction(RacerBoard rb, RacerPlayer rp, int diceValue, boolean correct);
}
