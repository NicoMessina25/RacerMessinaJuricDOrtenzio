package Events;

import RacerModel.TeamColor;
import RacerModel.PlayerPckg.RacerPlayer;
import RacerModel.PlayerPckg.RacerPlayerBegginer;
import RacerModel.PlayerPckg.RacerPlayerExpert;

public class CreatePlayerEvent {
	
	private String name;
	private TeamColor tc;
	private boolean expert;
	

	public CreatePlayerEvent(String name, Object tc, boolean expert) {
		this.name = name;
		this.tc = (TeamColor) tc;
		this.expert = expert;

	}
	
	public RacerPlayer generatePlayer(int id, int begginingSquareId) {
		return (expert)? new RacerPlayerExpert(name, id, tc, begginingSquareId): new RacerPlayerBegginer(name, id, tc, begginingSquareId);
	}

}
