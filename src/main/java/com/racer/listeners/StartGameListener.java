package com.racer.listeners;

import com.racer.events.StartGameEvent;

public interface StartGameListener {
	/**
	 * 
	 * @param e
	 */
	public void listenStartGame(StartGameEvent e);
}
