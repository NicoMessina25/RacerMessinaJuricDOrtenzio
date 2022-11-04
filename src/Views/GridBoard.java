package Views;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import Views.CustomComponents.RacerIcon;

public class GridBoard extends ImagePanel {

	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
			//----------------------------------------->|CONSTANTS|<-----------------------------------------------\\

	private static final long serialVersionUID = -4893649118226554016L;

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	
	private ArrayList<GridBoardCoordinate> squareCoordinates = new ArrayList<GridBoardCoordinate>();
	private ArrayList<GridBoardCoordinate> startingCoordinates = new ArrayList<GridBoardCoordinate>();
	private ArrayList<RacerIcon> playersRacerIcon;
	private int currentAnimatedSquare;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 * @param img
	 * @param h
	 */
	public GridBoard(String img, int h) {
		super(img, h);
		
		startingCoordinates.add(new GridBoardCoordinate(9, 13));
		startingCoordinates.add(new GridBoardCoordinate(10, 13));
		startingCoordinates.add(new GridBoardCoordinate(9, 12));
		startingCoordinates.add(new GridBoardCoordinate(10, 12));
		
		
		//Set the grid coordinates the cars must follow
		squareCoordinates.add(new GridBoardCoordinate(9, 13));
		squareCoordinates.add(new GridBoardCoordinate(9, 15));
		squareCoordinates.add(new GridBoardCoordinate(8, 16));
		squareCoordinates.add(new GridBoardCoordinate(6, 16));
		squareCoordinates.add(new GridBoardCoordinate(4, 15));
		squareCoordinates.add(new GridBoardCoordinate(2, 15));
		squareCoordinates.add(new GridBoardCoordinate(1, 12));
		squareCoordinates.add(new GridBoardCoordinate(1, 10));
		squareCoordinates.add(new GridBoardCoordinate(2, 9));
		squareCoordinates.add(new GridBoardCoordinate(3, 8));
		squareCoordinates.add(new GridBoardCoordinate(3, 7));
		squareCoordinates.add(new GridBoardCoordinate(3, 6));
		squareCoordinates.add(new GridBoardCoordinate(3, 5));
		squareCoordinates.add(new GridBoardCoordinate(3, 4));
		squareCoordinates.add(new GridBoardCoordinate(4, 3));
		squareCoordinates.add(new GridBoardCoordinate(6, 3));
		squareCoordinates.add(new GridBoardCoordinate(7, 5));
		squareCoordinates.add(new GridBoardCoordinate(8, 6));
		squareCoordinates.add(new GridBoardCoordinate(9, 8));
		squareCoordinates.add(new GridBoardCoordinate(9, 10));
		squareCoordinates.add(new GridBoardCoordinate(9, 11));
		
		
	}

	/**
	 * 
	 * @param img
	 * @param h
	 */
	public GridBoard(Image img, int h) {
		super(img, h);
		
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<GridBoardCoordinate> getSquareCoordinates() {
		return squareCoordinates;
	}

	/**
	 * 
	 * @param squareCoordinates
	 */
	public void setSquareCoordinates(ArrayList<GridBoardCoordinate> squareCoordinates) {
		this.squareCoordinates = squareCoordinates;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<RacerIcon> getPlayersRacerIcon() {
		return playersRacerIcon;
	}

	/**
	 * 
	 * @param playersRacerIcon
	 */
	public void setPlayersRacerIcon(ArrayList<RacerIcon> playersRacerIcon) {
		this.playersRacerIcon = playersRacerIcon;
	}
	
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	/**
	 * 
	 */
	public void setStartingPositions() {
		
		for(int i = 0; i < playersRacerIcon.size(); i++) {
			add(playersRacerIcon.get(i), "cell " + startingCoordinates.get(i).getJ() +  " " + startingCoordinates.get(i).getI());
		}
	}

	/**
	 * 
	 * @param playerId
	 * @param currentSquare
	 * @param oldSquare
	 */
	public void movePlayer(int playerId, int currentSquare, int oldSquare) {
		currentAnimatedSquare = oldSquare;
		if(currentSquare != oldSquare) {
			Timer delay = new Timer(1500/(Math.abs(currentSquare - oldSquare)), null);
			
			delay.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					if(currentSquare > oldSquare) {
						currentAnimatedSquare++;
						if(currentAnimatedSquare >= currentSquare) {
							delay.stop();
						}
					} else {
						currentAnimatedSquare--;
						if(currentAnimatedSquare <= currentSquare) {
							delay.stop();
						}
					}
					
					movingAnimation(playerId, currentAnimatedSquare);
				}
			});
			
			delay.start();
		}
		
	}
	
	/**
	 * 
	 * @param playerId
	 * @param square
	 */
	public void movingAnimation(int playerId, int square) {
		
		GridBoardCoordinate gBoardCoord;
		
		if(square > 0) {
			gBoardCoord = squareCoordinates.get(square % squareCoordinates.size());
		} else {
			gBoardCoord = startingCoordinates.get((playerId - 1) % startingCoordinates.size());
		}
		
		add(playersRacerIcon.get(playerId-1), "cell " +  gBoardCoord.getJ() +  " " +  gBoardCoord.getI());
		updateUI();
	}
	
	/**
	 * 
	 * @param currentSquare
	 * @return
	 */
	public int getPlayerLap(int currentSquare) {
		return currentSquare / squareCoordinates.size() + 1;
	}

}
