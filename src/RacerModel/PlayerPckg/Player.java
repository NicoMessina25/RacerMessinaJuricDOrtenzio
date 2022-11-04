package RacerModel.PlayerPckg;

public class Player {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\

	
	private String name = new String();
	private int id, currentSquare;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 */
	public Player(){
		
	}
	
	/**
	 * 
	 * @param name
	 */
	public Player(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @param name
	 * @param id
	 * @param currentSquare
	 */
	public Player(String name, int id, int currentSquare) {
		this.name = name;
		this.id = id;
		this.currentSquare = currentSquare;
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getCurrentSquare() {
		return currentSquare;
	}
	
	/**
	 * 
	 * @param currentSquare
	 */
	public void setCurrentSquare(int currentSquare) {
		this.currentSquare = currentSquare;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	/**
	 * 
	 * @param number
	 * @param lastSquare
	 */
	public void moves(int number, int lastSquare) {
		currentSquare += number;
		if(currentSquare < 0) {
			currentSquare = 0;
		} else if (currentSquare > lastSquare) {
			currentSquare = lastSquare;
		}
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + name);
		sb.append(" Id: " + id);
		sb.append(" Square: " + currentSquare);
		return sb.toString();
	}
	
}
