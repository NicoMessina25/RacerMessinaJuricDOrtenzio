package Controller;

import java.util.ArrayList;

import RacerModel.PlayerPckg.Player;
import RacerModel.SquarePckg.Square;

/**
 * This class is the controller between the view and the model of a board game
 */

public abstract class Board {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------------\\
	
			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	/** Board number of squares */
	private int amountSquares;
	/**id of the player who is currently playing*/
	private int playerTurn;
	/**id of the finish square, the first player to reach it wins*/
	private int finalSquareId;
	/**id of the beginning square, all the players must start from this square*/
	private int beginningSquareId;
	
	
	/**array of the players who are playing*/
	private ArrayList<Player> players = new ArrayList<Player>();
	/**array that contains the squares of the board*/
	private ArrayList<Square> squares = new ArrayList<Square>();
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * Constructors of the class
	 */
	public Board() {
		
	}
	
	/**
	 * @param amountSquares: an integer of the number of squares of the board
	 */
	public Board(int amountSquares) {
		this.amountSquares = amountSquares;
	}
	
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	
	
	/**
	 * Returns the array of players
	 * @return the array of players
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	/**
	 * Sets the array of players
	 * @param players: an array of players
	 */
	
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	/**
	 * Returns the array of squares
	 * @return the array of squares
	 */
	
	public ArrayList<Square> getSquares() {
		return squares;
	}
	
	/**
	 * Sets the array of squares
	 * @param squares: an array of squares
	 */
	
	public void setSquares(ArrayList<Square> squares) {
		this.squares = squares;
	}
	
	/**
	 * Returns the amount of squares
	 * @return the amount of squares
	 */
	
	public int getAmountSquares() {
		return amountSquares;
	}
	
	/**
	 * Sets the amount of squares
	 * @param amountSquares: an integer
	 */

	public void setAmountSquares(int amountSquares) {
		this.amountSquares = amountSquares;
	}
	
	/**
	 * Returns the id of the player who is playing this turn
	 * @return the id
	 */
	
	public int getPlayerTurn() {
		return playerTurn;
	}
	
	/**
	 * Sets the id of the player who is playing this turn
	 * @param playerTurn: an integer
	 */
	
	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}
	
	/**
	 * Returns the id of the square which the players have to reach to win the game
	 * @return the id
	 */
	
	public int getFinalSquareId() {
		return finalSquareId;
	}
	
	/**
	 * Sets the id of the square which the players have to reach to win the game
	 * @param finalSquareId: an integer
	 */

	public void setFinalSquareId(int finalSquareId) {
		this.finalSquareId = finalSquareId;
	}
	
	/**
	 * Returns the id of the starting square, all the players must start from this square
	 * @return the id
	 */

	public int getBeginningSquareId() {
		return beginningSquareId;
	}
	
	/**
	 * Sets the id of the starting square, all the players must start from this square
	 * @param begginingSquareId: an integer
	 */

	public void setBeginningSquareId(int begginingSquareId) {
		this.beginningSquareId = begginingSquareId;
	}
	
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	/**
	 * adds a player to the array
	 * @param rp: a Player
	 */
	
	public void addPlayer(Player rp){
		players.add(rp);
	};
	
	/**
	 * removes a player of the array
	 * @param id: an integer, which is the id of the player to delete
	 */
	
	public void removePlayer(int id) {
		players.remove(id);
	}
	
	public abstract void movePlayer(Player p, int number);

	/**
	 * Returns the player who is playing this turn
	 * @return the Player
	 */
	
	public Player getCurrentPlayer() {
		return getPlayers().get(getPlayerTurn());
	}
	
	/**
	 * Returns the id of player whose turn is next
	 * @return the id
	 */

	public int nextPlayer() {
		if (playerTurn < players.size() - 1) {
			return playerTurn + 1;
		} else return 0;
	}
	
	/**
	 * set the playerTurn to the next player who has to play
	 */

	public void nextTurn() {
		setPlayerTurn(nextPlayer());
	}
	
	/**
	 * Returns the player who is next to the current player's turn
	 * @return the Player
	 */
	
	public Player getNextPLayer() {
		return getPlayers().get(nextPlayer());
	}

}
