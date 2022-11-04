package Controller;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Events.CreatePlayerEvent;
import Events.DeletePlayerEvent;
import Events.ExitEvent;
import Events.QuestionSquareEvent;
import Events.ResetEvent;
import Events.StartGameEvent;
import Events.StartPreGameEvent;
import Events.StartQuestionEvent;
import Events.WinEvent;
import Listeners.CreatePlayerListener;
import Listeners.DeletePlayerListener;
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
import RacerModel.Team;
import RacerModel.PlayerPckg.Player;
import RacerModel.PlayerPckg.RacerPlayer;
import RacerModel.PlayerPckg.RacerPlayerExpert;
import RacerModel.SquarePckg.BeginningSquare;
import RacerModel.SquarePckg.FinishSquare;
import RacerModel.SquarePckg.QuestionSquare;
import RacerModel.SquarePckg.Square;
import Views.BoardPaneGUI;
import Views.GridBoard;
import Views.ImagePanel;
import Views.PreGamePanel;
import Views.SoundClip;
import Views.WinPanel;
import Views.CustomComponents.RacerIcon;
import Views.CustomComponents.RacerPanel;
import Views.CustomComponents.RacerPanelCard;

/**
 * This class extends from the Board class. Implements the listeners of the events that happen in the game, so 
 * this class provides methods to handle all this events.
 */

public class RacerBoard extends Board implements StartGameListener, CreatePlayerListener, WinListener, ResetListener,
		ExitListener, StartQuestionListener, QuestionSquareListener, StartPreGameListener, DeletePlayerListener {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------------\\
	
			//----------------------------------------->|CONSTANTS|<-----------------------------------------------\\
	
	/** maximum number of players in the game*/
	private final int MAX_PLAYERS = 4;
	/** minimum number of players in the game*/
	private final int MIN_PLAYERS = 2;
	
	
			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	/** array of questions to display during the game*/
	private ArrayList<Question> questions = new ArrayList<Question>();
	/** array of categories to classify the questions*/
	private ArrayList<Category> categories = new ArrayList<Category>();
	/** array of teams the players can choose before the game starts*/
	private ArrayList<Team> teams = new ArrayList<Team>();
	
	
	
	/** dice to roll every time a player starts a turn*/
	private Dice dice = new Dice();
	/** array of teams the players can choose before the game starts*/
	private ActionDice actionDice = new ActionDice();
	
	/** the id of the last player created */
	private int lastId;
	
	/** the current question the player has to answer */
	private Question curQuestion;
	
	/** timer that controls the time left the player has to answer the question */
	private Timer timer;
	
	/** panel of the game's board */
	private BoardPaneGUI boardPane;
	/** panel of the game's player selector */
	private PreGamePanel preGamePanel;
	
	/** player that has to answer the current question */
	private RacerPlayer playerToAnswer;
	
	/** hashMap of the different sounds effects the game implements */
	private HashMap<String, SoundClip> soundEffectsMap = new HashMap<String, SoundClip>();
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	public RacerBoard() {
	}
	/**
	 * @param squares: an integer of the number of squares of the board
	 */
	public RacerBoard(int squares) {
		super(squares);

	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	
	
	/**
	 * returns the array of questions that are available to be answered by the players
	 * @return the array of questions
	 */
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	
	/**
	 * sets the array of questions the game will contain
	 * @param questions: an array of questions
	 */
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	/**
	 * returns the array of categories the game has in order to classify the questions
	 * @return the array of categories
	 */
	public ArrayList<Category> getCategories() {
		return categories;
	}
	
	/**
	 * sets the categories the game has in order to classify the questions
	 * @param categories: array of categories
	 */
	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}
	
	/**
	 * returns the numeric dice of the game
	 * @return the dice
	 */
	public Dice getDice() {
		return dice;
	}
	
	/**
	 * sets the numeric dice of the game
	 * @param dice: a Dice
	 */
	public void setDice(Dice dice) {
		this.dice = dice;
	}
	
	/**
	 * returns the array of teams that are available for the players to choose
	 * @return the array of teams
	 */
	public ArrayList<Team> getTeams() {
		return teams;
	}
	
	/**
	 * sets the array of teams of the game
	 * @param teams: array of teams
	 */
	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}
	
	/**
	 * returns the action dice of the game
	 * @return the action dice
	 */
	public ActionDice getActionDice() {
		return actionDice;
	}
	
	/**
	 * sets the action dice of the game
	 * @param actionDice: an Action Dice
	 */
	public void setActionDice(ActionDice actionDice) {
		this.actionDice = actionDice;
	}
	
	/**
	 * returns the current question the playerToAnswer has to answer
	 * @return the current question
	 */
	public Question getCurQuestion() {
		return curQuestion;
	}
	
	/**
	 * sets the current question of the game
	 * @param curQuestion: Question
	 */
	public void setCurQuestion(Question curQuestion) {
		this.curQuestion = curQuestion;
	}
	
	
	/**
	 * sets the player who has to answer the current question
	 */
	public void setPlayerToAnswer() {
		playerToAnswer = (RacerPlayer) (getActionDice().getAction().isActionToNextPlayer() ? super.getNextPLayer()
				: super.getCurrentPlayer());
	}
	
	/**
	 * 
	 * @return
	 */
	public RacerPlayer getPlayerToAnswer() {
		return playerToAnswer;
	}
	
	/**
	 * 
	 * @return
	 */
	public Timer getTimer() {
		return timer;
	}
	
	/**
	 * 
	 * @param timer
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getMAX_PLAYERS() {
		return MAX_PLAYERS;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getMIN_PLAYERS() {
		return MIN_PLAYERS;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getLastId() {
		return lastId;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void setLastId(int id) {
		this.lastId = id;
	}
	
	/**
	 * 
	 * @return
	 */
	public BoardPaneGUI getBoardPane() {
		return boardPane;
	}
	
	/**
	 * 
	 * @param boardPane
	 */
	public void setBoardPane(BoardPaneGUI boardPane) {
		this.boardPane = boardPane;
	}

	
	/**
	 * 
	 * @return
	 */
	public PreGamePanel getPreGamePanel() {
		return preGamePanel;
	}
	
	/**
	 * 
	 * @param racerGUI
	 */
	public void setPreGamePanel(PreGamePanel racerGUI) {
		this.preGamePanel = racerGUI;
	}
	
	/**
	 * 
	 * @return
	 */
	public HashMap<String, SoundClip> getSounds(){
		return soundEffectsMap;
	}
	


	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	/**
	 * returns current action description that corresponds to the current player
	 * @return the current action description  
	 */
	public String getCurrentActionDesc() {
		return getActionDice().getAction().getDesc();
	}
	
	/**
	 * returns the string path of the image that represents the current action
	 * @return the string path
	 */
	public String getCurrentActionImgPath() {
		return getActionDice().getAction().getImg();
	}
	
	/**
	 * this method rolls both, the numeric and the action, dices
	 */
	public void rollDices() {
		dice.diceRoll();
		actionDice.diceRoll();
		soundEffectsMap.get("diceSound.wav").play();
	}
	
	/**
	 * 
	 * @return
	 */
	public String genPlayersStatus() {
		StringBuilder sb = new StringBuilder();

		for (Player p : super.getPlayers()) {
			sb.append(p.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/**
	 * this method validates the name field input of the player selector panel
	 * @param name
	 * @param id
	 * @return
	 */
	public boolean validateFields(String name) {
		Iterator<Player> it = super.getPlayers().iterator();
		RacerPlayer rp = new RacerPlayerExpert("");
		while (it.hasNext() && !(name.equals(rp.getName()))) {
			rp = (RacerPlayer) it.next();
		}

		return !(name.equals(rp.getName())) && !name.trim().equals("") && name.length() < 20;
	}
	
	/**
	 * this method overrides the nextTurn() method of the Board Class, and sets the playerTurn to the next player, 
	 * if a player has lost a turn, this method decrements the lostTurns counter of the player and goes to the next 
	 * player until a player has no lostTurns
	 */
	@Override
	public void nextTurn() {
		super.nextTurn();
		int lostTurns = ((RacerPlayer) getCurrentPlayer()).getLostTurns();
		while (lostTurns > 0) {
			((RacerPlayer) getCurrentPlayer()).setLostTurns(lostTurns - 1);
			if (lostTurns - 1 == 0) {
				RacerPanelCard panelPlayerCard = boardPane.getPlayersPanelCards().get(getPlayerTurn());
				panelPlayerCard.getTeamLogoPanel()
							.setImg(new ImageIcon(FileSystems.getDefault()
								.getPath("img", "logo" + panelPlayerCard.getTeamId() + "F1.jpg").toString())
								.getImage());
				panelPlayerCard.updateUI();
				soundEffectsMap.get("boxesSound.wav").play();
			}
			super.nextTurn();
			lostTurns = ((RacerPlayer) getCurrentPlayer()).getLostTurns();
		}
	}
	
	/**
	 * this method takes the player p back to the beginning square
	 * @param p
	 */
	public void playerBackToStart(RacerPlayer p) {
		int lastSquare = p.getCurrentSquare();
		p.setCurrentSquare(getBeginningSquareId());
		super.getSquares().get(getBeginningSquareId()).setCurPlayer(p);
		
		boardPane.playerBackToStart(p.getId(),p.typeToString(), getBeginningSquareId(), lastSquare);
		
	}
	
	/**
	 * this method moves a player p a specified number of positions
	 * @param p
	 * @param number
	 * 
	 *
	 */
	public void movePlayer(Player p, int number) {

		ArrayList<Square> squares = getSquares();
		Square currentSquare;
		Player curPlayer;
		
		
		squares.get(p.getCurrentSquare()).removePlayer(p);;
		p.moves(number, squares.size()-1);
		
		currentSquare = squares.get(p.getCurrentSquare());
		
		soundEffectsMap.get((number > 0) ? "accelerateSound.wav" : "brakeSound.wav").play();
		
		curPlayer = currentSquare.getCurPlayer();
		
		if(!currentSquare.isMoreThanOnePlayer() && curPlayer != null) {

			soundEffectsMap.get("crashSound.wav").play();
			
			if(number>0) {
				playerBackToStart((RacerPlayer) curPlayer);
				currentSquare.addPlayer(p);
			} else {
				playerBackToStart((RacerPlayer) p);
			}
	
		} else {
			currentSquare.addPlayer(p);
		}
		
	
		
	}
	
	/**
	 * this method gets a special question for the player p and sets it as the current question of the game
	 * @param p
	 */
	public void genQuestionForPlayer(RacerPlayer p) {
		Question ques = null;
		List<Question> filteredQuestions;
		List<Category> categoriesCopy = (List<Category>) categories.clone();

		do {
			Category catChosen = categoriesCopy.get((int) Math.floor(Math.random() * categoriesCopy.size()));
			categoriesCopy.remove(catChosen);

			filteredQuestions = p.getFilteredQuestions(questions, catChosen);

		} while (filteredQuestions.size() == 0 && categoriesCopy.size() > 0);
		
		if(categoriesCopy.size() > 0) {
			ques = filteredQuestions.get((int) Math.floor(Math.random() * filteredQuestions.size()));
			questions.remove(ques);
			ques = p.getQuestionAdapted(ques);
			setCurQuestion(ques);
			
		
		}
	}
	
	/**
	 * this method executes the action of the action dice depending on the player's answer
	 * @param panelGridBoard
	 * @param correct
	 */
	public void executeAction(GridBoard panelGridBoard, boolean correct) {
		RacerPlayer rp = (RacerPlayer) getPlayerToAnswer();
		int oldSquare = rp.getCurrentSquare();

		getActionDice().getAction().doAction(this, rp, getDice().getValue(), correct);
		
		
		
		panelGridBoard.movePlayer(rp.getId(), rp.getCurrentSquare(), oldSquare);
		boardPane.getPlayersPanelCards().get(rp.getId()-1).getLblExpert().setText(rp.typeToString() + " - Vue. " + panelGridBoard.getPlayerLap(rp.getCurrentSquare()) + "/2");

		if (timer != null) {
			timer.stop();
		}

	}

	
	/**
	 * 
	 */
	public void startTimer() {
		timer.start();
	}
	
	
	/**
	 * this method loads all the question and categories from the xml file into the arrays
	 */
	public void loadQuestions() {
		questions = new ArrayList<Question>();
		categories = new ArrayList<Category>();
		
		String file = new String("/racerQuestions.xml");
		final DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		Document doc = null;
		
		try {
			docBuilder = dbfac.newDocumentBuilder();
			doc = docBuilder.parse(this.getClass().getResourceAsStream(file));
		} catch (SAXException | IOException | ParserConfigurationException e) {
			JOptionPane.showMessageDialog(null, "Se produjo un error en la lectura del archivo xml. \nError: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} 
		
		final NodeList categoryList = ((Element) doc.getElementsByTagName("ListaCategorias").item(0)).getElementsByTagName("Categoria");

		for (int i = 0; i < categoryList.getLength(); i++) {
			final Element c = (Element) categoryList.item(i);

			String[] rgb = c.getElementsByTagName("color").item(0).getTextContent().split(" ");

			categories.add(new Category(c.getElementsByTagName("descripcion").item(0).getTextContent(),
					new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]))));
			
		}
		
		//System.out.println(categories.size());
		
		final NodeList questionList = ((Element) doc.getElementsByTagName("ListaPreguntas").item(0)).getElementsByTagName("Pregunta");
		

		for (int i = 0; i < questionList.getLength(); i++) {
			final Element q = (Element) questionList.item(i);
			
			
			final Element elemOptions = (Element) q.getElementsByTagName("opciones").item(0);
			final NodeList options = elemOptions.getElementsByTagName("opcion");
			ArrayList<Option> opts = new ArrayList<Option>();

			for (int j = 0; j < options.getLength(); j++) {
				opts.add(new Option(j, options.item(j).getTextContent()));
				// System.out.println(j);
			}

			questions.add(new Question(Integer.parseInt(q.getElementsByTagName("id").item(0).getTextContent()), 0,
					categories.get(Integer.parseInt(q.getElementsByTagName("categoria").item(0).getTextContent()) - 1),
					Integer.parseInt(q.getElementsByTagName("dificultad").item(0).getTextContent()), q.getElementsByTagName("enunciado").item(0).getTextContent(),
					opts));
		}

	}
	
	/**
	 * this method updates the set of teams available to choose in the preGame panel
	 * @param cbTeams
	 */
	public void updateComboBox(JComboBox<Team> cbTeams) {
		HashSet<Team> selectedTeams = new HashSet<Team>();

		cbTeams.removeAllItems();

		for (Player rp : getPlayers()) {
			selectedTeams.add(((RacerPlayer) rp).getTeam());
		}

		for (Iterator<Team> iterator = getTeams().iterator(); iterator.hasNext();) {
			Team tcolor = iterator.next();
			if (!selectedTeams.contains(tcolor)) {
				cbTeams.addItem(tcolor);
			}
		}
	}
	
	/**
	 * this method concludes the turn after the player answers the 
	 * question or throws the dices, depending on the action and executes 
	 * the doAction method. Also, depending on the square he is standing 
	 * on after his move, executes the square action
	 * @param correct
	 * @param panelGridBoard
	 * @param btnStartQuestion
	 * @param btnEndTurn
	 * @param textPaneAction
	 */
	public void concludesTurnAction(boolean correct, GridBoard panelGridBoard, JButton btnStartQuestion,
			JButton btnEndTurn, JTextPane textPaneAction) {
		

		if (getActionDice().getAction().isQuestionNeeded()) {
			
			soundEffectsMap.get((correct)? "correctSound.wav":"incorrectSound.wav").play(0);
			
		}
		
		executeAction(panelGridBoard, correct);

		getSquares().get(getPlayerToAnswer().getCurrentSquare()).doSquareAction(this, getDice().getValue());
		
		//System.out.println(genPlayersStatus());

		if (((RacerPlayer) getCurrentPlayer()).getLostTurns() > 0) {
			ImagePanel logoPanel = boardPane.getPlayersPanelCards().get(getPlayerTurn()).getTeamLogoPanel();
			logoPanel.setImg(new ImageIcon(FileSystems.getDefault().getPath("img", "boxes.jpg").toString()).getImage());
			logoPanel.updateUI();
			soundEffectsMap.get("boxesSound.wav").play();
		}

	}
	
	/**
	 * 
	 */
	public void finishTurn() {
		boardPane.getBtnEndTurn().setEnabled(true);	
	}
	
	/**
	 * this method generates the array of radio buttons of the current question's options and its ButtonGroup
	 * @param bg
	 * @param rdbtnOptions
	 * @param questionContentPane
	 */
	public void genOptionButtonGroup(ButtonGroup bg, ArrayList<JRadioButton> rdbtnOptions,
			RacerPanel questionContentPane) {

		Question question = getCurQuestion();

		for (Option op : getCurQuestion().getOptions()) {
			op.setSortNum((int) Math.ceil(Math.random() * 9));
		}
		Collections.sort(question.getOptions(), new Comparator<Option>() {

			@Override
			public int compare(Option o1, Option o2) {
				return o1.getSortNum() - o2.getSortNum();
			}

		});

		for (Option op : question.getOptions()) {
			int i = question.getOptions().indexOf(op);
			JRadioButton rdbtn = new JRadioButton(op.getDescripcion());
			rdbtn.setForeground(questionContentPane.getForeground());
			rdbtn.setBackground(questionContentPane.getBackground());
			rdbtn.setFont(RacerPanel.getPrimaryFont().deriveFont(Font.BOLD | Font.ITALIC, 18));
			bg.add(rdbtn);
			rdbtnOptions.add(rdbtn);
			questionContentPane.add(rdbtn, "cell 0 " + (i + 2));
		}

	}
	
	/**
	 * this method updates the created players ids
	 */
	public void updateIds() {
		int i;
		for (i = 0; i < getPlayers().size(); i++) {
			getPlayers().get(i).setId(i + 1);
			preGamePanel.getPlayersPanelCards().get(i).setPlayerId(i + 1);
		}
		setLastId(i);
	}
	
	/**
	 * this method returns if the index is valid for the current question indexes
	 * @param questionIndexes
	 * @param ind
	 * @return
	 */
	public boolean validIndex(ArrayList<Integer> questionIndexes, int ind) {
		boolean valid = true;
		int i = 0;
		while (i < questionIndexes.size() && valid) {
			valid = Math.abs(questionIndexes.get(i) - ind) > 6;
			i++;
		}
		return valid;
	}
	
	/**
	 * this method generates the racerPanelCard of the player who has to answer the question
	 * @return
	 */
	public RacerPanelCard getPlayerToAnswerPanelCard() {
		RacerPlayer rp = getPlayerToAnswer();
		return new RacerPanelCard(rp.getTeam().getCol().darker(), rp.getTeam().getCol().brighter(),
				rp.getTeam().getCol(), rp.getName(), rp.getTeam().getTeamName(),
				rp.getTeam().getTeamId(), rp.getId(), rp.typeToString(), getSounds().get("buttonSound.wav"));
	}
	
	/**
	 * 
	 */
	public void loadSoundEffects() {
		
		File[] soundEffectFiles = new File(FileSystems.getDefault().getPath("soundEffects").toString()).listFiles();
		
		for(File f : soundEffectFiles) {
			if(f.isFile()) {
				soundEffectsMap.put(f.getName(), new SoundClip(f.getPath().toString()));
			}
		}
	}
	
	/**
	 * this method generate the players racerLabels with their respective f1 car images
	 * @return
	 */
	public ArrayList<RacerIcon> genPlayersRacerIcon() {
		ArrayList<RacerIcon> playersRacerIcon = new ArrayList<RacerIcon>();
		
		for (Player rp : getPlayers()) {
			playersRacerIcon.add(new RacerIcon(new ImageIcon(FileSystems.getDefault().getPath("img/f1Cars", "car" + ((RacerPlayer) rp).getTeam().getTeamId() + ".png").toString()), 40));
		}
		
		return playersRacerIcon;
		
	}
	
	/**
	 * this methods loads the array of squares, setting the question, beginning and finish squares
	 */
	public void loadSquares() {
		setSquares(new ArrayList<Square>());
		
		for (int i = 0; i <= getFinalSquareId(); i++) {
			getSquares().add(new Square(i, "Normal",  null, false));
		}

		getSquares().set(getBeginningSquareId(),
				new BeginningSquare(getBeginningSquareId(), "Inicio", null, true));
		getSquares().set(getFinalSquareId(),
				new FinishSquare(getFinalSquareId(), "Fin", null, false));
		
		ArrayList<Integer> questionIndexes = new ArrayList<Integer>();

		while (questionIndexes.size() < 4) {
			int ind;
			do {
				ind = (int) Math.floor(Math.random() * 41 + 1);
			} while (!validIndex(questionIndexes, ind));
			questionIndexes.add(ind);
			//System.out.println(ind);
		}
		
		for (Integer i : questionIndexes) {
			getSquares().set(i, new QuestionSquare(i, "Pregunta",  null, false));
		}
		
		
	}
	
	//------------------------------------------------>||LISTENER METHODS||<--------------------------------------------------------\\
	
	
	@Override
	public void listenCreate(CreatePlayerEvent e) {
		RacerPlayer rp = e.getPlayer();

		setLastId(getLastId() + 1);

		rp.setId(getLastId());
		rp.setCurrentSquare(getBeginningSquareId());

		RacerPanelCard rpc = new RacerPanelCard(rp.getTeam().getCol().darker(),
				rp.getTeam().getCol().brighter(), rp.getTeam().getCol(), rp.getName(),
				rp.getTeam().getTeamName(), rp.getTeam().getTeamId(), rp.getId(), rp.typeToString(), getSounds().get("buttonSound.wav"));
		rpc.setDelPlayerListener(this);

		rpc.addBtnDel(rpc.getBtnDel());

		preGamePanel.getPlayersPanelCards().add(rpc);
		

		super.addPlayer(rp);
		preGamePanel.getCreatedPlayersPanel().add(preGamePanel.getPlayersPanelCards().get(getLastId() - 1),
				"cell " + (rp.getId() - 1) + " 0, center");
	}
	
	
	@Override
	public void listenWin(WinEvent e) {
		RacerPlayer winner = getPlayerToAnswer();
		e.setWp(new WinPanel(winner.getName(), winner.getTeam().getTeamId(), this, getBoardPane()));
		e.win();
		
		boardPane.getPlayersPanelCards().get(winner.getId()-1).getLblExpert().setText(winner.typeToString());
		boardPane.getBtnExit().setEnabled(false);
	}

	@Override
	public void listenReset(ResetEvent e) {
		e.reset(this);
		setLastId(0);
	}

	@Override
	public void listenExit(ExitEvent e) {
		e.exit();

	}
	
	@Override
	public void listenStartGame(StartGameEvent e) {
		e.starGame();
		setBoardPane(e.getBoardPane());
		((BeginningSquare) getSquares().get(getBeginningSquareId())).setCurPlayers((ArrayList<Player>) getPlayers().clone());
	}

	

	@Override
	public void listenStartQuestion(StartQuestionEvent e) {
		if(e.getQuestion() != null) {
			e.startQuestion(this);
			setTimer(e.getTimer());
		} else JOptionPane.showMessageDialog(null, "No hay más preguntas para los jugadores " + getPlayerToAnswer().typeToString() + ". No es posible continuar el juego");

	}

	

	@Override
	public void listenQuestionSquare(QuestionSquareEvent e) {
		e.setActDice(getActionDice());
		e.setBtnStartQuestion(boardPane.getBtnStartQuestion());
		e.setTextPaneAction(boardPane.getTextPaneAction());
		soundEffectsMap.get("questionSquareSound.wav").play();
		e.activateQuestionSquare();

	}

	

	@Override
	public void listenStarPreGame(StartPreGameEvent e) {
		e.getWelPanel().dispose();
		e.getRacerGUI().setVisible(true);
		setPreGamePanel(e.getRacerGUI());
		setPlayers(new ArrayList<Player>());
		loadSquares();

	}

	@Override
	public void listenDeletePlayer(DeletePlayerEvent e) {
		preGamePanel.getPlayersPanelCards().remove(e.getPlayerId() - 1);
		preGamePanel.addPlayersPanelCards();
		getPlayers().remove(e.getPlayerId() - 1);
		updateIds();

		updateComboBox(preGamePanel.getComboBoxTeams());

	}
	
	

}