package RacerModel.SquarePckg;

import Controller.RacerBoard;
import RacerModel.PlayerPckg.Player;

public class Square {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\

	private int id;
	private String tag;
	private Player curPlayer;
	private boolean moreThanOnePlayer;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	/**
	 * 
	 */
	public Square() {
		
	}
	/**
	 * 
	 * @param id
	 * @param tag
	 * @param curPlayer
	 * @param moreThanOnePlayer
	 */
	public Square(int id, String tag, Player curPlayer, boolean moreThanOnePlayer) { //tag es string
		this.id = id;
		this.tag = tag;
		this.curPlayer = curPlayer;
		this.moreThanOnePlayer = moreThanOnePlayer;
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	
	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * 
	 * @param tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isMoreThanOnePlayer() {
		return moreThanOnePlayer;
	}
	
	/**
	 * 
	 * @param moreThanOnePlayer
	 */
	public void setMoreThanOnePlayer(boolean moreThanOnePlayer) {
		this.moreThanOnePlayer = moreThanOnePlayer;
	}

	/**
	 * 
	 * @return
	 */
	public Player getCurPlayer() {
		return curPlayer;
	}
	
	/**
	 * 
	 * @param curPlayer
	 */
	public void setCurPlayer(Player curPlayer) {
		this.curPlayer = curPlayer;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	/**
	 * 
	 * @param rb
	 * @param correct
	 */
	public void doSquareAction(RacerBoard rb, int diceValue) {
		rb.finishTurn();
	}
	
	/**
	 * 
	 * @param p
	 */
	public void addPlayer(Player p) {
		setCurPlayer(p);
	}
	
	public void removePlayer(Player p) {
		setCurPlayer(null);
	}
	
	/*@Override	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id: " + id);
		sb.append(" Tag: " + tag);
		sb.append("\n");
		return sb.toString();
	}*/

}
