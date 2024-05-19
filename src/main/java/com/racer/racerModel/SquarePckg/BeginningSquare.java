package com.racer.racerModel.SquarePckg;

import java.util.ArrayList;

import com.racer.racerModel.PlayerPckg.Player;

public class BeginningSquare extends Square {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	private ArrayList<Player> curPlayers = new ArrayList<Player>();
		
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 */
	public BeginningSquare() {
		
	}
	
	/**
	 * 
	 * @param id
	 * @param tag
	 * @param curPlayer
	 * @param moreThanOnePlayer 
	 */
	public BeginningSquare(int id, String tag, Player curPlayer, boolean moreThanOnePlayer) {
		super(id, tag, curPlayer, moreThanOnePlayer);
	}
	
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Player> getCurPlayers() {
		return curPlayers;
	}

	/**
	 * 
	 * @param curPlayers
	 */
	public void setCurPlayers(ArrayList<Player> curPlayers) {
		this.curPlayers = curPlayers;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public void addPlayer(Player p) {
		curPlayers.add(p);
	}
	
	@Override
	public void removePlayer(Player p) {
		curPlayers.remove(p);
	}

}
