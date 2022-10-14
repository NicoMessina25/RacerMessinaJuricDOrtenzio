package RacerModel;


import java.util.ArrayList;

import RacerModel.ActionPckg.Action;

public class ActionDice extends Dice {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	private ArrayList<Action> actions = new ArrayList<Action>(6);
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	public ActionDice() {
		
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	
	public ArrayList<Action> getActions(){
		return actions;
	}
	
	public void setActions(ArrayList<Action> actions) {
		this.actions = actions;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	public Action getAction() {
		return actions.get(super.getValue()-1);
	}
}

