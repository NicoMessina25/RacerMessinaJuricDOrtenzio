package RacerModel.Square;

import java.awt.Color;

import Controller.RacerBoard;
import RacerModel.Player;

public class Square {
	private int id;
	String tag;
	private Color color;
	private Player curPlayer = new Player();

	public Square() {
		
	}
	
	public Square(int id, String tag, Color color, Player curPlayer) { //tag es string
		this.id = id;
		this.color = color;
		this.tag = tag;
		this.curPlayer = curPlayer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Player getCurPlayer() {
		return curPlayer;
	}

	public void setCurPlayer(Player curPlayer) {
		this.curPlayer = curPlayer;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id: " + id);
		sb.append(" Tag: " + tag);
		sb.append("\n");
		return sb.toString();
	}
	
	public void doSquareAction(RacerBoard rb) {
		
	}

}
