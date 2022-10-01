package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Events.CreatePlayerEvent;
import Events.ExitEvent;
import Events.ResetEvent;
import Events.StartGameEvent;
import Events.StartQuestionEvent;
import Events.WinEvent;
import Listeners.CreatePlayerListener;
import Listeners.ExitListener;
import Listeners.ResetListener;
import Listeners.StartGameListener;
import Listeners.StartQuestionListener;
import Listeners.WinListener;
import RacerModel.ActionDice;
import RacerModel.Category;
import RacerModel.Dice;
import RacerModel.Option;
import RacerModel.Player;
import RacerModel.Question;
import RacerModel.TeamColor;
import RacerModel.Action.Action;
import RacerModel.RacerPlayer.RacerPlayer;
import RacerModel.RacerPlayer.RacerPlayerBegginer;
import RacerModel.RacerPlayer.RacerPlayerExpert;
import RacerModel.Square.Square;




public class RacerBoard extends Board implements StartGameListener, CreatePlayerListener, WinListener, ResetListener, ExitListener, StartQuestionListener {
	
	private final int MAX_PLAYERS = 4;
	private final int MIN_PLAYERS = 2;
	private ArrayList<Question> questions = new ArrayList<Question>();
	private ArrayList<Category> categories = new ArrayList<Category>();
	private Dice dice = new Dice();
	private ArrayList<TeamColor> teamColors = new ArrayList<TeamColor>();
	private ActionDice actionDice = new ActionDice();
	private Question curQuestion;
	private Timer timer;
	private int timeLeft;
	private RacerPlayer playerToAnswer;
	private int lastId;


	public RacerBoard() {		
	}
	
	public RacerBoard(int rows, int columns) {
		super(rows, columns);
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

	public int getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}


	public Question getCurQuestion() {
		return curQuestion;
	}

	public void setCurQuestion(Question curQuestion) {
		this.curQuestion = curQuestion;
	}
	
	public RacerPlayer getPlayerToAnswer() {
		return playerToAnswer;
	}

	public void setPlayerToAnswer(RacerPlayer playerToAnswer) {
		this.playerToAnswer = playerToAnswer;
	}


	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public int getMAX_PLAYERS() {
		return MAX_PLAYERS;
	}

	public int getMIN_PLAYERS() {
		return MIN_PLAYERS;
	}

	public int getLastId() {
		return lastId;
	}

	public void setLastId(int id) {
		this.lastId = id;
	}

	public String genPlayersStatus() {
		StringBuilder sb = new StringBuilder();
		
		for(Player p: super.getPlayers()) {
			sb.append(p.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	
	public void addPlayer(String name, TeamColor tc, boolean expert, int timePerOption) {
		RacerPlayer rp;
		rp = (expert)? new RacerPlayerExpert(name, getLastId(), tc): new RacerPlayerBegginer(name, getLastId(), tc);
		super.addPlayer(rp);
	}
	
	public boolean validateFields(String name, int id) {
		Iterator<Player> it = super.getPlayers().iterator();
		RacerPlayer rp = new RacerPlayerExpert("");
		while (it.hasNext() && !(name.equals(rp.getName()))) {
			rp = (RacerPlayer) it.next();
		}
		 
		return !(name.equals(rp.getName())) && !name.equals("") && id < MAX_PLAYERS;
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
		Question ques;
		List<Question> filteredQuestions;
		
		do {
			Category catChosen = categories.get((int) Math.floor(Math.random()*categories.size()));
		
			filteredQuestions = p.getFilteredQuestions(questions, catChosen);
			
			/*if(p.isExpert()) {
				questionsToShow = questions.stream().filter(q -> q.getDificulty() >= 3 && q.getCategory().getDescription().equals(catChosen.getDescription())).toList();
			} else { questionsToShow = questions.stream().filter(q -> q.getDificulty() <= 3 && q.getCategory().getDescription().equals(catChosen.getDescription())).toList();;
			}*/
		} while(filteredQuestions.size() == 0);	
		
		ques = filteredQuestions.get((int) Math.floor(Math.random()*filteredQuestions.size()));
		questions.remove(ques);
		setCurQuestion(ques);
		return ques;
	}
	
	public void executeAction(Action action, ArrayList<JPanel> squarePanels, int diceValue, boolean correct) {
		RacerPlayer rp = (RacerPlayer) getPlayerToAnswer();
		if(rp.getCurrentSquare() != 0) {
			squarePanels.get(rp.getCurrentSquare()-1).setBackground(new Color(255, 255, 255));
		}
		
		action.doAction(this, rp, diceValue, correct);
		if(rp.getCurrentSquare() != 0) {
			squarePanels.get(rp.getCurrentSquare()-1).setBackground(rp.getTeamColor().getCol());
		}
		
		if(timer != null) {
			timer.stop();
		}
		
	}
	
	@Override
	public void listenStartGame(StartGameEvent e) {
		e.starGame(this);
	}
	
	/*public void setTimer() {
		
		timer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timeLeft--;
				if(timeLeft == 0) {
					timer.stop();
				}
				questionPanel.updateTimeLeft(timeLeft);
			}
			
		});

	
	}*/
	
	public void startTimer() {
		timer.start();
	}
	
	public void updateDelButton(JTextPane textPanePlayersCreated, ArrayList<JButton> btnsDelete, JComboBox<TeamColor> cbTeams) {
		for(int i = 0; i < btnsDelete.size();i++) {
			if(btnsDelete.get(i).getActionListeners().length > 0) {
				btnsDelete.get(i).removeActionListener(btnsDelete.get(i).getActionListeners()[0]);
			}
			
		}
		
		for(int i = 0; i < getPlayers().size(); i++) {
			int j = i;
			btnsDelete.get(i).addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					getPlayers().remove(j);
					//btnDelete.removeActionListener(btnDelete.getActionListeners()[0]);
					lastId--;
					
					for(int k = j; k<getPlayers().size(); k++) {
						getPlayers().get(k).setId(k+1);
					}
					updateDelButton(textPanePlayersCreated, btnsDelete, cbTeams);
					updateComboBox(cbTeams);
					//System.out.println("xd");
					textPanePlayersCreated.setText(genPlayersStatus());
					
				}
				
			});
		}
			
	}
	
	public void loadQuestions() {
		String file = new String("/XMLQuestions.xml");
		final DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = dbfac.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}
		Document doc = null;
		try {
			doc = docBuilder.parse(this.getClass().getResourceAsStream(file));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Node categories = nodoRaiz.getChildNodes().item(0);
		final NodeList nlDesc = doc.getElementsByTagName("descripcion");
		final NodeList nlColor = doc.getElementsByTagName("color");
		
		for(int i = 0; i < nlDesc.getLength(); i++) {
			final Node desc = nlDesc.item(i);
			final Node color = nlColor.item(i);
			
			String[] rgb = color.getTextContent().split(" ");
			
			categories.add(new Category(desc.getTextContent(), new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]))));

		}
		
		final NodeList nlIds = doc.getElementsByTagName("id");
		final NodeList nlDificulties = doc.getElementsByTagName("dificultad");
		final NodeList nlStatement = doc.getElementsByTagName("enunciado");
		final NodeList nlCategory = doc.getElementsByTagName("categoria");
		final NodeList nlOptions = doc.getElementsByTagName("opciones");
		
		for(int i = 0; i < nlIds.getLength(); i++) {
			final Element elemOptions = (Element) nlOptions.item(i);
			final NodeList options = elemOptions.getElementsByTagName("opcion");
			ArrayList<Option> opts = new ArrayList<Option>();
			
			for(int j = 0; j<options.getLength(); j++) {
				opts.add(new Option(j, options.item(j).getTextContent()));
				//System.out.println(j);
			}
			
			
			
			questions.add(new Question(Integer.parseInt(nlIds.item(i).getTextContent()), 0, categories.get(Integer.parseInt(nlCategory.item(i).getTextContent())-1), Integer.parseInt(nlDificulties.item(i).getTextContent()), nlStatement.item(i).getTextContent(), opts));
		}
		
		
	}

	public void updateComboBox(JComboBox<TeamColor> cbTeams) {
		HashSet<TeamColor> selectedTeams = new HashSet<TeamColor>();
		
		cbTeams.removeAllItems();
		
		for(Player rp : getPlayers()) {
			selectedTeams.add(((RacerPlayer) rp).getTeamColor());
		}
		
		
		for(Iterator<TeamColor> iterator = getTeamColors().iterator(); iterator.hasNext();) {
			TeamColor tcolor = iterator.next();	
			if(!selectedTeams.contains(tcolor)) {
				cbTeams.addItem(tcolor);
			}					
		}
	}

	@Override
	public void listenCreate(CreatePlayerEvent e) {
		e.create(this);
	}

	@Override
	public void listenWin(WinEvent e) {
		e.win();
	}

	@Override
	public void listenReset(ResetEvent e) {
		e.reset();
		
	}

	@Override
	public void listenExit(ExitEvent e) {
		e.exit();
		
	}
	
	public void executeWin() {
		this.listenWin(null);
	}

	@Override
	public void listenStartQuestion(StartQuestionEvent e) {
		e.startQuestion(this);
		
	}
		
}