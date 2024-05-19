package com.racer.listeners;

import com.racer.events.StartQuestionEvent;

//------------------------------------------------>||INTERFACE||<--------------------------------------------------------\\

public interface StartQuestionListener {
	/**
	 * 
	 * @param e
	 */
	public void listenStartQuestion(StartQuestionEvent e);
}
