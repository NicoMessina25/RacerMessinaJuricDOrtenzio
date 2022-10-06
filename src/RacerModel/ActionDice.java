package RacerModel;


import java.util.ArrayList;

import RacerModel.ActionPckg.Action;

public class ActionDice extends Dice {
	
	private ArrayList<Action> actions = new ArrayList<Action>(6);

	public ActionDice() {
		
	}
	
	public Action getAction() {
		return actions.get(super.getValue()-1);
	}
	
	public ArrayList<Action> getActions(){
		return actions;
	}
	
	public void setActions(ArrayList<Action> actions) {
		this.actions = actions;
	}
}

