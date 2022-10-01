package Controller;

import java.util.ArrayList;

import RacerModel.Player;
import RacerModel.RacerPlayer.RacerPlayer;
import RacerModel.Square.Square;

public class Board {
	private int rows, columns;
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Square> squares = new ArrayList<Square>(); //rows*columns + 1
	private int playerTurn;

	public Board() {
		
	}
	
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
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
	
	public void addPlayer(RacerPlayer rp){
		players.add(rp);
	};
	
	public void removePlayer() {
		players.remove(players.size() - 1);
	}

	public void setSquares(ArrayList<Square> squares) {
		this.squares = squares;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public int getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}
	
	public int nextPlayer() {
		if (playerTurn < players.size() - 1) {
			return playerTurn + 1;
		} else return 0;
	}

	public void nextTurn() {
		if (playerTurn < players.size() - 1) {
			playerTurn++;
		} else playerTurn = 0;
	}

}
