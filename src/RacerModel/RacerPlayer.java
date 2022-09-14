package RacerModel;

public class RacerPlayer extends Player {
	private TeamColor tColor;
	private int lostTurns, timePerOption;
	private boolean expert;

	public RacerPlayer() {
		super();
	}
	
	public RacerPlayer(String name) {
		super(name);
	}
	
	public RacerPlayer(String name, int id, TeamColor tColor, boolean expert, int timePerOption) {
		super(name, id, 0);
		this.tColor = tColor;
		this.expert = expert;
		this.lostTurns = 0;
		this.timePerOption = timePerOption;
	}

	public int getLostTurns() {
		return lostTurns;
	}

	public void setLostTurns(int lostTurns) {
		this.lostTurns = lostTurns;
	}

	public int getTimePerOption() {
		return timePerOption;
	}

	public void setTimePerOption(int timePOp) {
		timePerOption = timePOp;
	}
	
	public TeamColor getTeamColor() {
		return tColor;
	}

	public void setTeamColor(TeamColor tColor) {
		this.tColor = tColor;
	}

	public boolean isExpert() {
		return expert;
	}

	public void setExpert(boolean expert) {
		this.expert = expert;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(" Expert: ");
		if(expert) {
			sb.append("SI");
		} else sb.append("NO");
		sb.append(" Team: " + tColor.toString());
		return sb.toString();
	}
}
