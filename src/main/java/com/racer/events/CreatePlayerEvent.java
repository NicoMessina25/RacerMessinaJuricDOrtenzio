package com.racer.events;

import com.racer.racerModel.Team;
import com.racer.racerModel.PlayerPckg.RacerPlayer;
import com.racer.racerModel.PlayerPckg.RacerPlayerBegginer;
import com.racer.racerModel.PlayerPckg.RacerPlayerExpert;

public class CreatePlayerEvent {

	// ------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			// ----------------------------------------->|VARIABLES|<-----------------------------------------------\\

	private RacerPlayer player;

	// ------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 * @param name
	 * @param tc
	 * @param expert
	 */
	public CreatePlayerEvent(String name, Object tc, boolean expert) {
		player = (expert) ? new RacerPlayerExpert(name, 0, (Team) tc, 0)
				: new RacerPlayerBegginer(name, 0, (Team) tc, 0);
	}

	// -------------------------------------------------->||GETTERS||<----------------------------------------------------------------\\
	
	/**
	 * 
	 * @return
	 */
	public RacerPlayer getPlayer() {
		return player;
	}

}
