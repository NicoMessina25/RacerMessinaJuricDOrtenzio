package Racer;

import java.awt.Color;

public class Square {
	private int id,r, g, b, tag;
	private Color color = new Color(r,g,b);
	private Player curPlayer = new Player();

	public Square() {
		
	}
	
	public Square(int id, int tag, Color color) {
		this.id = id;
		this.color = color;
		this.tag = tag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
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
	
	

}
