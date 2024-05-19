package com.racer.racerModel.PlayerPckg;

import java.util.ArrayList;
import java.util.List;

import com.racer.racerModel.Category;
import com.racer.racerModel.Question;
import com.racer.racerModel.Team;

public abstract class RacerPlayer extends Player {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	
	private Team team;
	private int lostTurns;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 */
	public RacerPlayer() {
		super();
	}
	
	/**
	 * 
	 * @param name
	 */
	public RacerPlayer(String name) {
		super(name);
	}
	
	/**
	 * 
	 * @param name
	 * @param id
	 * @param tColor
	 * @param begginingSquareId
	 */
	public RacerPlayer(String name, int id, Team tColor, int begginingSquareId) {
		super(name, id, begginingSquareId);
		this.team = tColor;
		this.lostTurns = 0;
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	
	/**
	 * 
	 * @return
	 */
	public int getLostTurns() {
		return lostTurns;
	}
	
	/**
	 * 
	 * @param lostTurns
	 */
	public void setLostTurns(int lostTurns) {
		this.lostTurns = lostTurns;
	}
	
	/**
	 * 
	 * @return
	 */
	public Team getTeam() {
		return team;
	}
	
	/**
	 * 
	 * @param tColor
	 */
	public void setTeam(Team tColor) {
		this.team = tColor;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	/**
	 * 
	 * @return
	 */
	public abstract int getTimePerOption();
	
	/**
	 * 
	 * @param questions
	 * @param catChosen
	 * @return
	 */
	public abstract List<Question> getFilteredQuestions(ArrayList<Question> questions, Category catChosen);
	
	/**
	 * 
	 * @param q
	 * @return
	 */
	public abstract Question getQuestionAdapted(Question q);
	
	/**
	 * 
	 * @return
	 */
	public abstract String typeToString();
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(" Team: " + team.toString());
		return sb.toString();
	}
	
	
}
