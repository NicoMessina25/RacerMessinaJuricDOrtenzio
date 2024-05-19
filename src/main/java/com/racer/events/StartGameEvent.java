package com.racer.events;

import java.awt.Dimension;
import java.awt.Window;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.racer.views.BoardPaneGUI;

public class StartGameEvent {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
			
	private BoardPaneGUI boardPane;
	private JPanel playersPane;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	/**
	 * 
	 */
	public StartGameEvent() {

	}
	
	/**
	 * 
	 * @param boardPane
	 * @param playersPane
	 */
	public StartGameEvent(BoardPaneGUI boardPane, JPanel playersPane) {
		this.boardPane = boardPane;
		this.playersPane = playersPane;
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	
	/**
	 * 
	 * @return
	 */
	public BoardPaneGUI getBoardPane() {
		return boardPane;
	}
	
	/**
	 * 
	 * @param boardPane
	 */
	public void setBoardPane(BoardPaneGUI boardPane) {
		this.boardPane = boardPane;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	/**
	 * 
	 */
	public void starGame() {
		boardPane.setVisible(true);
		boardPane.setSize(new Dimension(1280, 720));
		Window w = SwingUtilities.getWindowAncestor(playersPane);
		w.dispose();
	}

}
