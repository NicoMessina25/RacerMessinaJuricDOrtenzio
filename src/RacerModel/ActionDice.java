package RacerModel;


import java.util.ArrayList;

import RacerModel.ActionPckg.Action;

public class ActionDice extends Dice {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	private ArrayList<Action> actions = new ArrayList<Action>(6);
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 */
	public ActionDice() {
		
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	/**
	 * 
	 * @return
	 */
	public ArrayList<Action> getActions(){
		return actions;
	}
	
	/**
	 * 
	 * @param actions
	 */
	public void setActions(ArrayList<Action> actions) {
		this.actions = actions;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	/**
	 * 
	 * @return
	 */
	public Action getAction() {
		return actions.get(super.getValue()-1);
	}
}

