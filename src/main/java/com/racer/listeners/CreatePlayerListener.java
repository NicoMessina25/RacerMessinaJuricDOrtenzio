package com.racer.listeners;

import com.racer.events.CreatePlayerEvent;

//------------------------------------------------>||INTERFACE||<--------------------------------------------------------\\

public interface CreatePlayerListener {
	/**
	 * 
	 * @param e
	 */
	public void listenCreate(CreatePlayerEvent e);
}
