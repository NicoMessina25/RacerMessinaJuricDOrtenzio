package Controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import RacerModel.SquarePckg.BegginingSquare;
import RacerModel.SquarePckg.FinishSquare;
import RacerModel.SquarePckg.QuestionSquare;
import RacerModel.SquarePckg.Square;
import Views.BoardPaneGUI;
import Views.GridBoard;
import Views.GridBoardCoordinate;
import Views.ImagePanel;
import Views.PreGamePanel;
import Views.SoundClip;
import Views.WinPanel;
import Views.CustomComponents.RacerLabel;
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

	public ArrayList<Team> getTeamColors() {
		return teams;
	}

	public void setTeamColors(ArrayList<Team> teamColors) {
		this.teams = teamColors;
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
	
	
	
	public void setPlayerToAnswer() {
		playerToAnswer = (RacerPlayer) (getActionDice().getAction().isActionToNextPlayer() ? super.getNextPLayer()
				: super.getCurrentPlayer());
	}

	public RacerPlayer getPlayerToAnswer() {
		return playerToAnswer;
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
	
	public BoardPaneGUI getBoardPane() {
		return boardPane;
	}

	public void setBoardPane(BoardPaneGUI boardPane) {
		this.boardPane = boardPane;
	}

	

	public PreGamePanel getPreGamePanel() {
		return preGamePanel;
	}

	public void setPreGamePanel(PreGamePanel racerGUI) {
		this.preGamePanel = racerGUI;
	}
	
	public HashMap<String, SoundClip> getSounds(){
		return soundEffectsMap;
	}
	


	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	public String getCurrentActionDesc() {
		return getActionDice().getAction().getDesc();
	}

	public String getCurrentActionImgPath() {
		return getActionDice().getAction().getImg();
	}

	public void rollDices() {
		
		dice.diceRoll();
		actionDice.diceRoll();
		soundEffectsMap.get("diceSound.wav").play();
	}

	public String genPlayersStatus() {
		StringBuilder sb = new StringBuilder();

		for (Player p : super.getPlayers()) {
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

		return !(name.equals(rp.getName())) && !name.trim().equals("") && id < MAX_PLAYERS && name.length() < 20;
	}

	@Override
	public void nextTurn() {
		super.nextTurn();
		int lostTurns = ((RacerPlayer) getPlayers().get(getPlayerTurn())).getLostTurns();
		while (lostTurns > 0) {
			((RacerPlayer) getPlayers().get(getPlayerTurn())).setLostTurns(lostTurns - 1);
			System.out.println(getPlayers().get(getPlayerTurn()).getName() + " perdio un turno");
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
			lostTurns = ((RacerPlayer) getPlayers().get(getPlayerTurn())).getLostTurns();
		}
	}

	public void playerBackToStart(Player p) {
		int lastSquare = p.getCurrentSquare();
		p.setCurrentSquare(getBeginningSquareId());
		super.getSquares().get(getBeginningSquareId()).setCurPlayer(p);
		Timer delay = new Timer(2000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boardPane.getPanelBoardGrid().movePlayer(p.getId(), getBeginningSquareId(), lastSquare);
				boardPane.getPlayersPanelCards().get(p.getId()-1).getLblExpert().setText(((RacerPlayer) p).typeToString() + " - Vuelta " + boardPane.getPanelBoardGrid().getPlayerLap(p.getCurrentSquare()));
			}
		});
		delay.setRepeats(false);
		delay.start();
		
	}

	public void movePlayer(Player p, int number) {

		
		ArrayList<Square> squares = super.getSquares();
		squares.get(p.getCurrentSquare()).setCurPlayer(null);
		p.moves(number, super.getFinalSquareId());

		Player curPlayer = squares.get(p.getCurrentSquare()).getCurPlayer();
		squares.get(p.getCurrentSquare()).setCurPlayer(p);

		

		soundEffectsMap.get((number > 0) ? "accelerateSound.wav" : "brakeSound.wav").play();
	
		
		if (curPlayer != null) {
			soundEffectsMap.get("crashSound.wav").play();
			playerBackToStart((number > 0)? curPlayer: p);
		}
	
		
	}

	public Question genQuestionForPlayer(RacerPlayer p) {
		Question ques;
		List<Question> filteredQuestions;

		do {
			Category catChosen = categories.get((int) Math.floor(Math.random() * categories.size()));

			filteredQuestions = p.getFilteredQuestions(questions, catChosen);

		} while (filteredQuestions.size() == 0);

		ques = filteredQuestions.get((int) Math.floor(Math.random() * filteredQuestions.size()));
		questions.remove(ques);
		ques = p.getQuestionAdapted(ques);
		setCurQuestion(ques);
		return ques;
	}

	public void executeAction(GridBoard panelGridBoard, boolean correct) {
		RacerPlayer rp = (RacerPlayer) getPlayerToAnswer();
		int oldSquare = rp.getCurrentSquare();

		getActionDice().getAction().doAction(this, rp, getDice().getValue(), correct);
		
		
		
		panelGridBoard.movePlayer(rp.getId(), rp.getCurrentSquare(), oldSquare);
		boardPane.getPlayersPanelCards().get(rp.getId()-1).getLblExpert().setText(rp.typeToString() + " - Vuelta " + panelGridBoard.getPlayerLap(rp.getCurrentSquare()));

		if (timer != null) {
			timer.stop();
		}

	}

	

	public void startTimer() {
		timer.start();
	}

	public void loadQuestions() {
		questions = new ArrayList<Question>();
		
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

		final NodeList nlDesc = doc.getElementsByTagName("descripcion");
		final NodeList nlColor = doc.getElementsByTagName("color");

		for (int i = 0; i < nlDesc.getLength(); i++) {
			final Node desc = nlDesc.item(i);
			final Node color = nlColor.item(i);

			String[] rgb = color.getTextContent().split(" ");

			categories.add(new Category(desc.getTextContent(),
					new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]))));

		}

		final NodeList nlIds = doc.getElementsByTagName("id");
		final NodeList nlDificulties = doc.getElementsByTagName("dificultad");
		final NodeList nlStatement = doc.getElementsByTagName("enunciado");
		final NodeList nlCategory = doc.getElementsByTagName("categoria");
		final NodeList nlOptions = doc.getElementsByTagName("opciones");

		for (int i = 0; i < nlIds.getLength(); i++) {
			final Element elemOptions = (Element) nlOptions.item(i);
			final NodeList options = elemOptions.getElementsByTagName("opcion");
			ArrayList<Option> opts = new ArrayList<Option>();

			for (int j = 0; j < options.getLength(); j++) {
				opts.add(new Option(j, options.item(j).getTextContent()));
				// System.out.println(j);
			}

			questions.add(new Question(Integer.parseInt(nlIds.item(i).getTextContent()), 0,
					categories.get(Integer.parseInt(nlCategory.item(i).getTextContent()) - 1),
					Integer.parseInt(nlDificulties.item(i).getTextContent()), nlStatement.item(i).getTextContent(),
					opts));
		}

	}

	public void updateComboBox(JComboBox<Team> cbTeams) {
		HashSet<Team> selectedTeams = new HashSet<Team>();

		cbTeams.removeAllItems();

		for (Player rp : getPlayers()) {
			selectedTeams.add(((RacerPlayer) rp).getTeam());
		}

		for (Iterator<Team> iterator = getTeamColors().iterator(); iterator.hasNext();) {
			Team tcolor = iterator.next();
			if (!selectedTeams.contains(tcolor)) {
				cbTeams.addItem(tcolor);
			}
		}
	}
	
	public void executeWin(RacerPlayer winner) {
		listenWin(new WinEvent(new WinPanel(winner.getName(), this, getBoardPane())));
	}
	
	public void concludesTurnAction(boolean correct, GridBoard panelGridBoard, JButton btnStartQuestion,
			JButton btnEndTurn, JTextPane textPaneAction) {
		

		if (getActionDice().getAction().isQuestionNeeded()) {
			
			soundEffectsMap.get((correct)? "correctSound.wav":"incorrectSound.wav").play(0);
			
		}
		
		executeAction(panelGridBoard, correct);

		getSquares().get(getPlayerToAnswer().getCurrentSquare()).doSquareAction(this, correct);
		
		System.out.println(genPlayersStatus());

		if (((RacerPlayer) getPlayers().get(getPlayerTurn())).getLostTurns() > 0) {
			ImagePanel logoPanel = boardPane.getPlayersPanelCards().get(getPlayerTurn()).getTeamLogoPanel();
			logoPanel.setImg(new ImageIcon(FileSystems.getDefault().getPath("img", "boxes.jpg").toString()).getImage());
			logoPanel.updateUI();
			soundEffectsMap.get("boxesSound.wav").play();
		}

	}
	
public void finishTurn() {
		
		/*Timer t = new Timer(getActiveSoundEffectsDelay(),new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		t.setRepeats(false);
		t.start();*/
		boardPane.getBtnEndTurn().setEnabled(true);
		
	}

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
			rdbtn.setFont(new Font(RacerPanel.getPrimaryFontFamily(), Font.BOLD | Font.ITALIC, 18));
			bg.add(rdbtn);
			rdbtnOptions.add(rdbtn);
			questionContentPane.add(rdbtn, "cell 0 " + (i + 2));
		}

	}
	
	public void updateIds() {
		int i;
		for (i = 0; i < getPlayers().size(); i++) {
			getPlayers().get(i).setId(i + 1);
			preGamePanel.getPlayersPanelCards().get(i).setPlayerId(i + 1);
		}
		setLastId(i);
	}

	public ArrayList<RacerPanelCard> getPlayersPanelCards() {
		return preGamePanel.getPlayersPanelCards();
	}

	public boolean validIndex(ArrayList<Integer> questionIndexes, int ind) {
		boolean valid = true;
		int i = 0;
		while (i < questionIndexes.size() && valid) {
			valid = Math.abs(questionIndexes.get(i) - ind) > 6;
			i++;
		}
		return valid;
	}

	public RacerPanelCard getPlayerToAnswerPanelCard() {
		RacerPlayer rp = getPlayerToAnswer();
		return new RacerPanelCard(rp.getTeam().getCol().darker(), rp.getTeam().getCol().brighter(),
				rp.getTeam().getCol(), rp.getName(), rp.getTeam().getTeamName(),
				rp.getTeam().getTeamId(), rp.getId(), rp.typeToString(), getSounds().get("buttonSound.wav"));
	}
	
	public void loadSoundEffects() {
		
		File[] soundEffectFiles = new File(FileSystems.getDefault().getPath("soundEffects").toString()).listFiles();
		
		for(File f : soundEffectFiles) {
			if(f.isFile()) {
				soundEffectsMap.put(f.getName(), new SoundClip(f.getPath().toString()));
			}
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
		e.win();
		RacerPlayer winner = getPlayerToAnswer();
		boardPane.getPlayersPanelCards().get(winner.getId()-1).getLblExpert().setText(winner.typeToString());
	}

	@Override
	public void listenReset(ResetEvent e) {
		e.reset();
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
	}

	

	@Override
	public void listenStartQuestion(StartQuestionEvent e) {

		e.startQuestion(this);
		setTimer(e.getTimer());

	}

	

	@Override
	public void listenQuestionSquare(QuestionSquareEvent e) {
		e.setActDice(getActionDice());
		e.setBtnStartQuestion(boardPane.getBtnStartQuestion());
		e.setTextPaneAction(boardPane.getTextPaneAction());

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
		// racerGUI.getCreatedPlayersPanel().remove(racerGUI.getPlayersPanelCards().get(e.getPlayerId()-1));
		preGamePanel.getPlayersPanelCards().remove(e.getPlayerId() - 1);
		preGamePanel.addPlayersPanelCards();
		getPlayers().remove(e.getPlayerId() - 1);
		updateIds();

		updateComboBox(preGamePanel.getComboBoxTeams());

	}
	public ArrayList<RacerLabel> genPlayersRacerLabel() {
		ArrayList<RacerLabel> playersRacerLabel = new ArrayList<RacerLabel>();
		for (Player rp : getPlayers()) {
			RacerLabel rl = new RacerLabel();
			
			rl.setIcon(new ImageIcon(FileSystems.getDefault().getPath("img/f1Cars", "car" + ((RacerPlayer) rp).getTeam().getTeamId() + ".png").toString()), 40);

			playersRacerLabel.add(rl);
		}
		
		return playersRacerLabel;
		
	}
	
	public void loadSquares() {
		setSquares(new ArrayList<Square>());
		
		for (int i = 0; i <= getFinalSquareId(); i++) {
			getSquares().add(new Square(i, "Normal", new Color(0, 0, 0), null));
		}

		getSquares().set(getBeginningSquareId(),
				new BegginingSquare(getBeginningSquareId(), "Inicio", new Color(0, 0, 0), null));
		getSquares().set(getFinalSquareId(),
				new FinishSquare(getFinalSquareId(), "Fin", new Color(0, 0, 0), null));

		getSquares().get(getBeginningSquareId()).setTag("Inicio");
		getSquares().get(getFinalSquareId()).setTag("Fin");
		
		ArrayList<Integer> questionIndexes = new ArrayList<Integer>();

		while (questionIndexes.size() < 4) {
			int ind;
			do {
				ind = (int) Math.floor(Math.random() * 41 + 1);
			} while (!validIndex(questionIndexes, ind));
			questionIndexes.add(ind);
			System.out.println(ind);
		}
		
		for (Integer i : questionIndexes) {
			getSquares().set(i, new QuestionSquare(i, "Pregunta", new Color(0, 0, 0), null));
		}
		
		
	}

	
	
	/*public int getActiveSoundEffectsDelay() {
		delay = 0;
		
		soundEffectsMap.forEach((name, sc)->{
			if(!name.equals("buttonSound.wav")) {
				delay += sc.isPlaying()? sc.getTimeRemaining():0;
			}
		});
		
		return SoundClip.getDelay();
	}*/
	
	

}