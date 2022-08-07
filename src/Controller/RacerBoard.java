package Controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import Events.StartGameEvent;
import Listeners.StartGameListener;
import RacerModel.Action;
import RacerModel.ActionDice;
import RacerModel.Category;
import RacerModel.Dice;
import RacerModel.Player;
import RacerModel.Question;
import RacerModel.RacerPlayer;
import RacerModel.Square;
import RacerModel.TeamColor;




public class RacerBoard extends Board implements StartGameListener {
	
	private int amouQues = 50;
	private int amouTeam = 10;
	private ArrayList<Question> questions = new ArrayList<Question>(amouQues);
	private ArrayList<Category> categories = new ArrayList<Category>();
	private Dice dice = new Dice();
	private ArrayList<TeamColor> teamColors = new ArrayList<TeamColor>(amouTeam);
	private ActionDice actionDice = new ActionDice();
	private Question curQuestion;



	public RacerBoard() {		
	}
	
	public RacerBoard(int rows, int columns) {
		super(rows, columns);
	}


	public int getAmouQues() {
		return amouQues;
	}

	public void setAmouQues(int amouQues) {
		this.amouQues = amouQues;
	}
	
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
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
	
	public ActionDice getActionDice() {
		return actionDice;
	}

	public void setActionDice(ActionDice actionDice) {
		this.actionDice = actionDice;
	}

	public Question getCurQuestion() {
		return curQuestion;
	}

	public void setCurQuestion(Question curQuestion) {
		this.curQuestion = curQuestion;
	}

	public String genPlayersStatus() {
		StringBuilder sb = new StringBuilder();
		
		for(Player p: super.getPlayers()) {
			sb.append(p.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public boolean validateFields(String name, TeamColor tc, int amountOfPLayers, int id) {
		boolean valid = true;
		Iterator<Player> it = super.getPlayers().iterator();
		
		while (it.hasNext() && valid) {
			RacerPlayer player = (RacerPlayer) it.next();
			valid = !(name.equals(player.getName()) || tc.equals(player.getTeamColor()));
		}
		valid = valid && !name.equals("") && id <= amountOfPLayers;
		return valid;
	}
	
	@Override
	public void nextTurn() {
		super.nextTurn();
		int lostTurns = ((RacerPlayer) super.getPlayers().get(super.getPlayerTurn())).getLostTurns();
		while (lostTurns > 0) {
			((RacerPlayer)  super.getPlayers().get(super.getPlayerTurn())).setLostTurns(lostTurns - 1);
			System.out.println(super.getPlayers().get(super.getPlayerTurn()).getName() + " perdió un turno");
			super.nextTurn();
			lostTurns = ((RacerPlayer)  super.getPlayers().get(super.getPlayerTurn())).getLostTurns();
		}
	}
	
	public void playerBackToStart(Player p) {
		p.setCurrentSquare(0);
		super.getSquares().get(0).setCurPlayer(p);
	}
	
	public void movePlayer(Player p, int number) {
		ArrayList<Square> squares = super.getSquares();
		squares.get(p.getCurrentSquare()).setCurPlayer(null);
		p.moves(number);
		if(p.getCurrentSquare() <= squares.size() - 1) {
			Player curPlayer = squares.get(p.getCurrentSquare()).getCurPlayer();
			squares.get(p.getCurrentSquare()).setCurPlayer(p);
			if(curPlayer != null) {
				if(number > 0) {
					playerBackToStart(curPlayer);
				} else playerBackToStart(p);
				
			}
		} else {
			squares.get(squares.size()-1).setCurPlayer(p);
			p.setCurrentSquare(squares.size() - 1);
		}
	}
	
	public Question getQuestion(RacerPlayer p) {
		List<Question> questionsToShow;
		
		do {
			Category catChosen = categories.get((int) Math.floor(Math.random()*categories.size()));
		
			if(p.isExpert()) {
				questionsToShow = questions.stream().filter(q -> q.getDificulty() >= 3 && q.getCategory().getDescription().equals(catChosen.getDescription())).toList();
			} else { questionsToShow = questions.stream().filter(q -> q.getDificulty() <= 3 && q.getCategory().getDescription().equals(catChosen.getDescription())).toList();;
			}
		} while(questionsToShow.size() == 0);	
		
		
		return questionsToShow.get((int) Math.floor(Math.random()*questionsToShow.size()));
	}
	
	public void executeAction(Action action, int diceValue, boolean correct) {
		action.doAction(this, (RacerPlayer) super.getPlayers().get(super.getPlayerTurn()), diceValue, correct);
	}
	
	@Override
	public void listenStartGame(StartGameEvent e) {
		e.starGame();
	}
		
		
		/*do {
			System.out.println("Choose the number of players (2-4)");
			amoPlay = scan.nextInt();
		} while(amoPlay < 2 && amoPlay > 4);
		
		rb.setAmouPlayers(amoPlay);
		
		for (int i = 1; i <= amoPlay; i++) {
			StringBuilder sb = new StringBuilder();
			int tColNum;
			
			sb.append("Enter player "); 
			sb.append(i);
			sb.append(" name");
			
			System.out.println(sb.toString());
			rb.getPlayers().add(new RacerPlayer());
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
		
			sb.delete(0, sb.lastIndexOf(sb.toString()));
			
			System.out.println("Enter the team's number");
			
			tColNum = scan.nextInt() - 1;
			
			rb.getPlayers().get(i-1).setTeamColor(rb.getTeamColors().get(tColNum));
			pickedCol.add(rb.getTeamColors().get(tColNum));
			
		}
		
		rb.getDice().diceRoll();
		System.out.println(rb.getDice().getValue());*/

}
