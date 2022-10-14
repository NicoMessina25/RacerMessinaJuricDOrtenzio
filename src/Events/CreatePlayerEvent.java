package Events;

import RacerModel.Team;
import RacerModel.PlayerPckg.RacerPlayer;
import RacerModel.PlayerPckg.RacerPlayerBegginer;
import RacerModel.PlayerPckg.RacerPlayerExpert;

public class CreatePlayerEvent {

	// ------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			// ----------------------------------------->|VARIABLES|<-----------------------------------------------\\

	private RacerPlayer player;

	// ------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	public CreatePlayerEvent(String name, Object tc, boolean expert) {
		player = (expert) ? new RacerPlayerExpert(name, 0, (Team) tc, 0)
				: new RacerPlayerBegginer(name, 0, (Team) tc, 0);
	}

	// -------------------------------------------------->||GETTERS||<----------------------------------------------------------------\\

	public RacerPlayer getPlayer() {
		return player;
	}

}
