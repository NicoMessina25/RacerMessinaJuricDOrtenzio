package Racer;

import java.awt.Color;

public class RacerPlayer extends Player {
	private int lostTurns, timePOp;

	public RacerPlayer() {
		super();
	}
	
	public RacerPlayer(String name, int id, Color color) {
		super(name, id, color);
	}

	public int getLostTurns() {
		return lostTurns;
	}

	public void setLostTurns(int lostTurns) {
		this.lostTurns = lostTurns;
	}

	public int getTimePOp() {
		return timePOp;
	}

	public void setTimePOp(int timePOp) {
		this.timePOp = timePOp;
	}
	
	
}
