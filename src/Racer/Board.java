package Racer;

import java.util.ArrayList;

public class Board {
	private int amouPlayers;
	final int rows = 7, columns = 6;
	private ArrayList<Player> players = new ArrayList<Player>(amouPlayers);
	private ArrayList<Square> squares = new ArrayList<Square>(rows*columns);
	

	public Board() {
		
	}
	
	public Board(int amouPlayers) {
		this.amouPlayers = amouPlayers;
	}

	public int getAmouPlayers() {
		return amouPlayers;
	}

	public void setAmouPlayers(int amouPlayers) {
		this.amouPlayers = amouPlayers;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public ArrayList<Square> getSquares() {
		return squares;
	}

	public void setSquares(ArrayList<Square> squares) {
		this.squares = squares;
	}
	

}
