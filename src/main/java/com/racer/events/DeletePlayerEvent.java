package com.racer.events;

public class DeletePlayerEvent {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\

	private int playerId;

	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 * @param playerId
	 */
	public DeletePlayerEvent(int playerId) {
		this.playerId = playerId;
	}
	
	//-------------------------------------------------->||GETTERS||<---------------------------------------------------------------\\
	
	/**
	 * 
	 * @return
	 */
	public int getPlayerId() {
		return playerId;
	}
}

