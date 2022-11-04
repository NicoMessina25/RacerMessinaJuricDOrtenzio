package Listeners;

import Events.CreatePlayerEvent;

//------------------------------------------------>||INTERFACE||<--------------------------------------------------------\\

public interface CreatePlayerListener {
	/**
	 * 
	 * @param e
	 */
	public void listenCreate(CreatePlayerEvent e);
}
