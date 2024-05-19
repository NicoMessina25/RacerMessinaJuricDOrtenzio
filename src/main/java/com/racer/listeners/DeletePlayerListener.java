package com.racer.listeners;

import com.racer.events.DeletePlayerEvent;

//------------------------------------------------>||INTERFACE||<--------------------------------------------------------\\

public interface DeletePlayerListener {
	/**
	 * 
	 * @param e
	 */
	public void listenDeletePlayer(DeletePlayerEvent e);
}
