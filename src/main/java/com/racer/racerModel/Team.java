package com.racer.racerModel;

import java.awt.Color;

public class Team {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\

	private Color col;
	private String teamName = new String();
	private String teamId = new String();
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	/**
	 * 
	 */
	public Team() {

	}
	
	/**
	 * 
	 * @param tName
	 * @param tId
	 * @param col
	 */
	public Team(String tName, String tId, Color col) {
		teamName = tName;
		this.col = col;
		setTeamId(tId);
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	/**
	 * 
	 * @return
	 */
	public Color getCol() {
		return col;
	}

	/**
	 * 
	 * @param col
	 */
	public void setCol(Color col) {
		this.col = col;
	}

	/**
	 * 
	 * @return
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * 
	 * @param teamName
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 * 
	 * @return
	 */
	public String getTeamId() {
		return teamId;
	}

	/**
	 * 
	 * @param teamId
	 */
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public String toString() {
		return getTeamName();
	}

}
