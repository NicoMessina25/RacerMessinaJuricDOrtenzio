package Listeners;

import Events.DeletePlayerEvent;

//------------------------------------------------>||INTERFACE||<--------------------------------------------------------\\

public interface DeletePlayerListener {
	/**
	 * 
	 * @param e
	 */
	public void listenDeletePlayer(DeletePlayerEvent e);
}
