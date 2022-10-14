package RacerModel.PlayerPckg;

public class Player {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\

	
	private String name = new String();
	private int id, currentSquare;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	

	public Player(){
		
	}
	
	public Player(String name) {
		this.name = name;
	}
	
	public Player(String name, int id, int currentSquare) {
		this.name = name;
		this.id = id;
		this.currentSquare = currentSquare;
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getCurrentSquare() {
		return currentSquare;
	}

	public void setCurrentSquare(int currentSquare) {
		this.currentSquare = currentSquare;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	public void moves(int number, int lastSquare) {
		currentSquare += number;
		if(currentSquare < 0) {
			currentSquare = 0;
		} else if (currentSquare > lastSquare) {
			currentSquare = lastSquare;
		}
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + name);
		sb.append(" Id: " + id);
		sb.append(" Square: " + currentSquare);
		return sb.toString();
	}
	
}
