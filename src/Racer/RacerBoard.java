package Racer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RacerBoard extends Board {
	
	private int amouQues = 50;
	private int amouTeam = 10;
	//private ArrayList<Question> questions = new ArrayList<Question>(amouQues);
	//private ArrayList<Category> categories = new ArrayList<Caregory>();
	private Dice dice = new Dice();
	private ArrayList<TeamColor> teamColors = new ArrayList<TeamColor>(amouTeam);

	public RacerBoard() {
		
	}

	public RacerBoard(int amouPlayers) {
		super(amouPlayers);
	}

	public int getAmouQues() {
		return amouQues;
	}

	public void setAmouQues(int amouQues) {
		this.amouQues = amouQues;
	}
	
	
	public Dice getDice() {
		return dice;
	}

	public void setDice(Dice dice) {
		this.dice = dice;
	}

	public int getAmouTeam() {
		return amouTeam;
	}

	public void setAmouTeam(int amouTeam) {
		this.amouTeam = amouTeam;
	}

	public ArrayList<TeamColor> getTeamColors() {
		return teamColors;
	}

	public void setTeamColors(ArrayList<TeamColor> teamColors) {
		this.teamColors = teamColors;
	}

	public static void main(String[] args) {
		RacerBoard rb = new RacerBoard();
		Scanner scan = new Scanner(System.in);
		int amoPlay = 0;
		
		
		rb.getTeamColors().add(new TeamColor("Alpha Romeo", new Color(63, 5, 18))); //AlphaRomeo - 0
		rb.getTeamColors().add(new TeamColor("Alpha Tauri", new Color((float) 32.5, (float) 38.4, (float) 47.5))); //AlphaTauri - 1
		rb.getTeamColors().add(new TeamColor("Alpine", new Color((float) 94.9, (float) 58, (float) 77.6)));//Alpine - 2
		rb.getTeamColors().add(new TeamColor("Aston Martin", new Color((float) 0, (float) 34.9, (float) 31)));//AstonMartin - 3
		rb.getTeamColors().add(new TeamColor("Ferrari", new Color((float) 83.1, (float) 0, (float) 0)));//Ferrari - 4
		rb.getTeamColors().add(new TeamColor("Haas", new Color((float) 100, (float) 100, (float) 100)));//Haas - 5
		rb.getTeamColors().add(new TeamColor("McLaren", new Color((float) 95.7, (float) 52.2, (float) 0)));//McLaren - 6
		rb.getTeamColors().add(new TeamColor("Mercedes", new Color((float) 0, (float) 0, (float) 0)));//Mercedes - 7
		rb.getTeamColors().add(new TeamColor("Red Bull", new Color((float) 98.8, (float) 84.7, (float) 0)));//RedBull - 8
		rb.getTeamColors().add(new TeamColor("Williams", new Color((float) 0, (float) 62.7, (float) 87.1)));//Williams - 9
		
		do {
			System.out.println("Choose the number of players (2-4)");
			amoPlay = scan.nextInt();
		} while(amoPlay < 2 && amoPlay > 4);
		
		rb.setAmouPlayers(amoPlay);
		
		for (int i = 1; i <= amoPlay; i++) {
			StringBuilder sb = new StringBuilder();
			HashSet<Color> pickedCol = new HashSet<Color>();
			
			sb.append("Enter player "); 
			sb.append(i);
			sb.append(" name");
			
			System.out.println(sb.toString());
			rb.getPlayers().get(i-1).setName(scan.next()); 
			rb.getPlayers().get(i-1).setId(i);
			
			int j = 0;
			System.out.println("Choose your F1 team");
			
			List<TeamColor> colToList =  rb.teamColors.stream().filter(c -> !pickedCol.contains(c)).collect(Collectors.toList());;
			
			Iterator<TeamColor> it = colToList.iterator();
			
			while (it.hasNext()) {
				TeamColor tC = new TeamColor();
				tC = it.next();
				j++;
				System.out.println(tC.getTeamName() + " - " + j);
			}
		
			sb.delete(0, sb.lastIndexOf(null));
			
			
		}
		
		rb.getDice().diceRoll();
		System.out.println(rb.getDice().getValue());
	}

}
