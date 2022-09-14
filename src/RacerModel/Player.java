package RacerModel;

public class Player {
	private String name = new String();
	private int id, currentSquare;

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
	
	public void moves(int number) {
		currentSquare += number;
		if(currentSquare < 0) {
			currentSquare = 0;
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
