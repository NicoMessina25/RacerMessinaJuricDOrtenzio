package Events;

import Controller.RacerBoard;
import RacerModel.TeamColor;

public class CreatePlayerEvent {
	
	private String name;
	private TeamColor tc;
	private boolean expert;
	private int timePerOption;
	

	public CreatePlayerEvent(String name, Object tc, boolean expert) {
		this.name = name;
		this.tc = (TeamColor) tc;
		this.expert = expert;
		
		if(expert) {
			timePerOption = 10;
		} else timePerOption = 15;

		
	}
	
	public void create(RacerBoard rb) {
		
		if (rb.getPlayers().size() > 0) {
			rb.setLastId(rb.getLastId() + 1);
		} else {
			rb.setLastId(1);
		}
		
		rb.addPlayer(name, (TeamColor) tc, expert, timePerOption);
	}

}
