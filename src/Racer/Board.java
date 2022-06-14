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
	

}
