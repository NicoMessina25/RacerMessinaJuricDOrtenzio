package com.racer.events;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.racer.views.WelcomePanel;

import com.racer.controller.RacerBoard;

public class ResetEvent {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	private JFrame fToClose;
	
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 * @param frameToClose
	 */
	public ResetEvent(JFrame frameToClose) {
		this.fToClose = frameToClose;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	/**
	 * 
	 * @param rb
	 */
	public void reset(RacerBoard rb) {
		WelcomePanel welP = new WelcomePanel(rb);
		welP.setVisible(true);
		welP.setSize(new Dimension(1280, 720));
		fToClose.dispose();
		
	}

	

}
