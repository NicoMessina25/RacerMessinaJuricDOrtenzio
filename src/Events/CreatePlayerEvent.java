package Events;

import RacerModel.TeamColor;
import RacerModel.PlayerPckg.RacerPlayer;
import RacerModel.PlayerPckg.RacerPlayerBegginer;
import RacerModel.PlayerPckg.RacerPlayerExpert;
import Views.CustomComponents.RacerPanel;

public class CreatePlayerEvent {
	
	private RacerPlayer player;

	

	


	public CreatePlayerEvent(String name, Object tc, boolean expert) {
		player = (expert)? new RacerPlayerExpert(name, 0, (TeamColor) tc, 0): new RacerPlayerBegginer(name, 0, (TeamColor) tc, 0);
	}


	public RacerPlayer getPlayer() {
		return player;
	}


}
