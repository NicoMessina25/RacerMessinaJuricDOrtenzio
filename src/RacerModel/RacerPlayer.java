package RacerModel;

public class RacerPlayer extends Player {
	private TeamColor tColor;
	private int lostTurns, timePerOption;
	private boolean expert;

	public RacerPlayer() {
		super();
	}
	
	public RacerPlayer(String name, int id, int currentSquare, TeamColor tColor, boolean expert) {
		super(name, id, currentSquare);
		this.tColor = tColor;
		this.expert = expert;
		
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
