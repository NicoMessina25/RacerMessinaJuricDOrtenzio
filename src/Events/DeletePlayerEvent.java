package Events;

import java.awt.Color;

import RacerModel.TeamColor;
import RacerModel.PlayerPckg.RacerPlayer;
import RacerModel.PlayerPckg.RacerPlayerBegginer;
import RacerModel.PlayerPckg.RacerPlayerExpert;
import Views.CustomComponents.RacerPanelCard;

public class DeletePlayerEvent {
	
	private int playerId;

	

	public DeletePlayerEvent(int playerId) {
		//player = (expert)? new RacerPlayerExpert(playerName, 0, new TeamColor(teamName, teamId, tColor), 0):new RacerPlayerBegginer(playerName, 0, new TeamColor(teamName, teamId, tColor), 0);
		//rpc = new RacerPanelCard(tColor.darker(), tColor.brighter(), tColor, playerName, teamName, teamId, expert);
		this.playerId = playerId;
	}

	public int getPlayerId() {
		return playerId;
	}
}
