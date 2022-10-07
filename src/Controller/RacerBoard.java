package Controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
import Events.QuestionSquareEvent;
import Events.ResetEvent;
import Events.StartGameEvent;
import Events.StartPreGameEvent;
import Events.StartQuestionEvent;
import Events.WinEvent;

import Listeners.CreatePlayerListener;
import Listeners.ExitListener;
import Listeners.QuestionSquareListener;
import Listeners.ResetListener;
import Listeners.StartGameListener;
import Listeners.StartPreGameListener;
import Listeners.StartQuestionListener;
import Listeners.WinListener;

import RacerModel.ActionDice;
import RacerModel.Category;
import RacerModel.Dice;
import RacerModel.Option;
import RacerModel.Question;
import RacerModel.TeamColor;
import RacerModel.PlayerPckg.Player;
import RacerModel.PlayerPckg.RacerPlayer;
import RacerModel.PlayerPckg.RacerPlayerExpert;
import RacerModel.SquarePckg.Square;
import Views.BoardPaneGUI;
import Views.WinPanel;
import Views.CustomComponents.RacerButton;




public class RacerBoard extends Board implements StartGameListener, CreatePlayerListener, WinListener, ResetListener, ExitListener, StartQuestionListener,QuestionSquareListener, StartPreGameListener {
	
	private final int MAX_PLAYERS = 4;
	private final int MIN_PLAYERS = 2;
	private final Color PRIMARY_COLOR = new Color(255, 28, 0);
	private final Color SECONDARY_COLOR = new Color(51, 6, 0);
	private final Font PRIMARY_FONT = new Font("Swis721 Hv BT", Font.BOLD | Font.ITALIC, 23);
	private ArrayList<Question> questions = new ArrayList<Question>();
	private ArrayList<Category> categories = new ArrayList<Category>();
	private Dice dice = new Dice();
	private ArrayList<TeamColor> teamColors = new ArrayList<TeamColor>();
	private ActionDice actionDice = new ActionDice();
	private Question curQuestion;
	private Timer timer;
	private int timeLeft;
	private int lastId;
	private BoardPaneGUI boardPane;


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
		return (RacerPlayer) (getActionDice().getAction().isActionToNextPlayer()? super.getNextPLayer(): super.getCurrentPlayer());
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
	
	public String getCurrentActionDesc() {
		return getActionDice().getAction().getDesc();
	}
	
	public Color getCurrentActionColor() {
		return getActionDice().getAction().getColor();
	}

	public BoardPaneGUI getBoardPane() {
		return boardPane;
	}

	public void setBoardPane(BoardPaneGUI boardPane) {
		this.boardPane = boardPane;
	}

	public void setLastId(int id) {
		this.lastId = id;
	}
	
	public Color getPRIMARY_COLOR() {
		return PRIMARY_COLOR;
	}

	public Color getSECONDARY_COLOR() {
		return SECONDARY_COLOR;
	}

	public Font getPRIMARY_FONT() {
		return PRIMARY_FONT;
	}

	public void rollDices() {
		dice.diceRoll();
		actionDice.diceRoll();
	}

	public String genPlayersStatus() {
		StringBuilder sb = new StringBuilder();
		
		for(Player p: super.getPlayers()) {
			sb.append(p.toString());
			sb.append("\n");
		}
		return sb.toString();
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
		p.setCurrentSquare(getBegginingSquareId());
		super.getSquares().get(getBegginingSquareId()).setCurPlayer(p);
	}
	
	public void movePlayer(Player p, int number) {
		ArrayList<Square> squares = super.getSquares();
		squares.get(p.getCurrentSquare()).setCurPlayer(null);
		p.moves(number, super.getFinalSquareId());
		
		Player curPlayer = squares.get(p.getCurrentSquare()).getCurPlayer();
		squares.get(p.getCurrentSquare()).setCurPlayer(p);
		
		if(curPlayer != null) {
			if(number > 0) {
				playerBackToStart(curPlayer);
			} else playerBackToStart(p);
			
		}
	}
	
	public Question genQuestionForPlayer(RacerPlayer p) {
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
	
	public void executeAction(ArrayList<JPanel> squarePanels, boolean correct) {
		RacerPlayer rp = (RacerPlayer) getPlayerToAnswer();
		if(rp.getCurrentSquare() != 0) {
			squarePanels.get(rp.getCurrentSquare()-1).setBackground(null);
		}
		
		getActionDice().getAction().doAction(this, rp, getDice().getValue(), correct);
		if(rp.getCurrentSquare() != 0) {
			squarePanels.get(rp.getCurrentSquare()-1).setBackground(rp.getTeamColor().getCol());
		}
		
		if(timer != null) {
			timer.stop();
		}
		
	}
	
	@Override
	public void listenStartGame(StartGameEvent e) {
		e.starGame();
		setBoardPane(e.getBoardPane());
	}
	
	public void startTimer() {
		timer.start();
	}
	
	public void updateDelButton(JTextPane textPanePlayersCreated, ArrayList<RacerButton> btnsDelete, JComboBox<TeamColor> cbTeams, JLabel title) {
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
					title.setText("Player " + (getLastId()+1));
					for(int k = j; k<getPlayers().size(); k++) {
						getPlayers().get(k).setId(k+1);
					}
					updateDelButton(textPanePlayersCreated, btnsDelete, cbTeams, title);
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
		if (getPlayers().size() > 0) {
			setLastId(getLastId() + 1);
		} else {
			setLastId(1);
		}
		
		super.addPlayer(e.generatePlayer(getLastId(), getBegginingSquareId()));
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
	
	public void executeWin(RacerPlayer winner) {
		listenWin(new WinEvent(new WinPanel(winner.getName(), this, getBoardPane())));
	}

	@Override
	public void listenStartQuestion(StartQuestionEvent e) {
		
		e.startQuestion(this);
		setTimer(e.getTimer());
		
	}
	
	public void concludesTurnAction(boolean correct, ArrayList<JPanel> squarePanels, JButton btnStartQuestion, JButton btnEndTurn, JTextPane textPaneAction) {
		executeAction(squarePanels, correct);
		if(getActionDice().getAction().isQuestionNeeded() && correct) {						
			System.out.println("Correct");
		} else {
			System.out.println("Incorrect");
		}
			
		//rb.movePlayer(player, diceValue);

		getSquares().get(getPlayerToAnswer().getCurrentSquare()).doSquareAction(this, correct); 

		
		
	
	}

	@Override
	public void listenQuestionSquare(QuestionSquareEvent e) {
		e.setActDice(getActionDice());
		e.setBtnStartQuestion(boardPane.getBtnStartQuestion());
		e.setTextPaneAction(boardPane.getTextPaneAction());
		
		
		e.activateQuestionSquare();
		
	}
	
	public void finishTurn() {
		boardPane.getBtnEndTurn().setVisible(true);
	}
	
	public void genOptionButtonGroup(ButtonGroup bg, ArrayList<JRadioButton> rdbtnOptions, JPanel questionContentPane) {
		
		Question question = getCurQuestion();
		
		for(Option op: getCurQuestion().getOptions()) {
			op.setSortNum((int) Math.ceil(Math.random()*9));
		}
		Collections.sort(question.getOptions(), new Comparator<Option>() {

			@Override
			public int compare(Option o1, Option o2) {
				return o1.getSortNum() - o2.getSortNum();
			}
			
		});
		
		for(Option op : question.getOptions()) {
			int i = question.getOptions().indexOf(op);
			JRadioButton rdbtn = new JRadioButton(op.getDescripcion());
			bg.add(rdbtn);
			rdbtnOptions.add(rdbtn);
			questionContentPane.add(rdbtn, "cell 0 "+ (i+3));
		}
	
	}

	@Override
	public void listenStarPreGame(StartPreGameEvent e) {
		e.startPreGame();		
	}
		
}