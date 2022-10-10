package Controller;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import RacerModel.TeamColor;
import RacerModel.PlayerPckg.Player;
import RacerModel.PlayerPckg.RacerPlayer;
import RacerModel.PlayerPckg.RacerPlayerExpert;
import RacerModel.SquarePckg.QuestionSquare;
import RacerModel.SquarePckg.Square;
import Views.BoardPaneGUI;
import Views.ImagePanel;
import Views.PreGamePanel;
import Views.WinPanel;
import Views.CustomComponents.RacerPanel;
import Views.CustomComponents.RacerPanelCard;

public class RacerBoard extends Board implements StartGameListener, CreatePlayerListener, WinListener, ResetListener,
		ExitListener, StartQuestionListener, QuestionSquareListener, StartPreGameListener, DeletePlayerListener {

	private final int MAX_PLAYERS = 4;
	private final int MIN_PLAYERS = 2;
	private static final Color PRIMARY_COLOR = new Color(214, 164, 12);
	private static final Color SECONDARY_COLOR = new Color(184, 0, 0);
	private static final Color TERCIARY_COLOR = new Color(0, 0, 0);
	private static final String PRIMARY_FONT_FAMILY = "Swis721 Hv BT";
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
	private PreGamePanel preGamePanel;
	private RacerPlayer playerToAnswer;

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

	public PreGamePanel getPreGamePanel() {
		return preGamePanel;
	}

	public void setPreGamePanel(PreGamePanel racerGUI) {
		this.preGamePanel = racerGUI;
	}

	public Color getPRIMARY_COLOR() {
		return PRIMARY_COLOR;
	}

	public Color getSECONDARY_COLOR() {
		return SECONDARY_COLOR;
	}

	public Color getTERCIARY_COLOR() {
		return TERCIARY_COLOR;
	}

	public static String getPRIMARY_FONT_FAMILY() {
		return PRIMARY_FONT_FAMILY;
	}

	public void rollDices() {
		dice.diceRoll();
		actionDice.diceRoll();
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
			System.out.println(getPlayers().get(getPlayerTurn()).getName() + " perdió un turno");
			if (lostTurns - 1 == 0) {
				RacerPanelCard panelPlayerCard = boardPane.getPlayersPanelCards().get(getPlayerTurn());
				panelPlayerCard.getTeamLogoPanel()
						.setImg(new ImageIcon(FileSystems.getDefault()
								.getPath("img", "logo" + panelPlayerCard.getTeamId() + "F1.jpg").toString())
								.getImage());
			}
			super.nextTurn();
			lostTurns = ((RacerPlayer) getPlayers().get(getPlayerTurn())).getLostTurns();
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

		if (curPlayer != null) {
			if (number > 0) {
				playerBackToStart(curPlayer);
			} else
				playerBackToStart(p);

		}
	}

	public Question genQuestionForPlayer(RacerPlayer p) {
		Question ques;
		List<Question> filteredQuestions;

		do {
			Category catChosen = categories.get((int) Math.floor(Math.random() * categories.size()));

			filteredQuestions = p.getFilteredQuestions(questions, catChosen);

			/*
			 * if(p.isExpert()) { questionsToShow = questions.stream().filter(q ->
			 * q.getDificulty() >= 3 &&
			 * q.getCategory().getDescription().equals(catChosen.getDescription())).toList()
			 * ; } else { questionsToShow = questions.stream().filter(q -> q.getDificulty()
			 * <= 3 &&
			 * q.getCategory().getDescription().equals(catChosen.getDescription())).toList()
			 * ;; }
			 */
		} while (filteredQuestions.size() == 0);

		ques = filteredQuestions.get((int) Math.floor(Math.random() * filteredQuestions.size()));
		questions.remove(ques);
		ques = p.getQuestionAdapted(ques);
		setCurQuestion(ques);
		return ques;
	}

	public void executeAction(ArrayList<JPanel> squarePanels, boolean correct) {
		RacerPlayer rp = (RacerPlayer) getPlayerToAnswer();
		if (rp.getCurrentSquare() != 0) {
			squarePanels.get(rp.getCurrentSquare() - 1).setBackground(null);
		}

		getActionDice().getAction().doAction(this, rp, getDice().getValue(), correct);
		if (rp.getCurrentSquare() != 0) {
			squarePanels.get(rp.getCurrentSquare() - 1).setBackground(rp.getTeamColor().getCol());
		}

		if (timer != null) {
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

	/*
	 * public void updateDelButton(RacerPanel panelCreatedPlayers,
	 * ArrayList<RacerButton> btnsDelete, JComboBox<TeamColor> cbTeams, JLabel
	 * title) { for(int i = 0; i < btnsDelete.size();i++) {
	 * if(btnsDelete.get(i).getActionListeners().length > 0) {
	 * btnsDelete.get(i).removeActionListener(btnsDelete.get(i).getActionListeners()
	 * [0]); }
	 * 
	 * }
	 * 
	 * for(int i = 0; i < getPlayers().size(); i++) { int j = i;
	 * btnsDelete.get(i).addActionListener(new ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) {
	 * getPlayers().remove(j);
	 * //btnDelete.removeActionListener(btnDelete.getActionListeners()[0]);
	 * lastId--; title.setText("Jugador " + (getLastId()+1)); for(int k = j;
	 * k<getPlayers().size(); k++) { getPlayers().get(k).setId(k+1); }
	 * //updateDelButton(textPanePlayersCreated, btnsDelete, cbTeams, title);
	 * updateComboBox(cbTeams); //System.out.println("xd");
	 * //textPanePlayersCreated.setText(genPlayersStatus());
	 * 
	 * }
	 * 
	 * }); }
	 * 
	 * }
	 */

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

		// Node categories = nodoRaiz.getChildNodes().item(0);
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

	public void updateComboBox(JComboBox<TeamColor> cbTeams) {
		HashSet<TeamColor> selectedTeams = new HashSet<TeamColor>();

		cbTeams.removeAllItems();

		for (Player rp : getPlayers()) {
			selectedTeams.add(((RacerPlayer) rp).getTeamColor());
		}

		for (Iterator<TeamColor> iterator = getTeamColors().iterator(); iterator.hasNext();) {
			TeamColor tcolor = iterator.next();
			if (!selectedTeams.contains(tcolor)) {
				cbTeams.addItem(tcolor);
			}
		}
	}

	/*
	 * public void updatePanelCreatedPlayers() { ArrayList<Player> players =
	 * super.getPlayers(); RacerPanel newPanelCreatedPlayers = new RacerPanel(new
	 * MigLayout("fill", "[][][][]", "[28.00][]"), null, getSECONDARY_COLOR(),
	 * getPRIMARY_COLOR().brighter());
	 * 
	 * 
	 * for(Player p : players) { RacerPlayer rp = (RacerPlayer) p; RacerPanelCard
	 * rpc = new RacerPanelCard(rp.getTeamColor().getCol().darker(),
	 * rp.getTeamColor().getCol().brighter(), rp.getTeamColor().getCol(),
	 * rp.getName(), rp.getTeamColor().getTeamName(), rp.getTeamColor().getTeamId(),
	 * rp.getClass().getSimpleName().equals("RacerPlayerExpert"));
	 * rpc.setDelPlayerListener(this); newPanelCreatedPlayers.add(rpc, "cell " +
	 * rp.getId() + " 0"); }
	 * 
	 * 
	 * racerGUI.setCreatedPlayersPanel(newPanelCreatedPlayers);
	 * 
	 * }
	 */

	@Override
	public void listenCreate(CreatePlayerEvent e) {
		RacerPlayer rp = e.getPlayer();

		
		setLastId(getLastId() + 1);
		

		rp.setId(getLastId());
		rp.setCurrentSquare(getBegginingSquareId());

		RacerPanelCard rpc = new RacerPanelCard(rp.getTeamColor().getCol().darker(),
				rp.getTeamColor().getCol().brighter(), rp.getTeamColor().getCol(), rp.getName(),
				rp.getTeamColor().getTeamName(), rp.getTeamColor().getTeamId(), rp.getId(), rp.typeToString());
		rpc.setDelPlayerListener(this);
		
		rpc.addBtnDel(rpc.getBtnDel());
		
		preGamePanel.getPlayersPanelCards().add(rpc);
		// System.out.println("listenCre:" + racerGUI.getPlayersPanelCards().size());
		// rpc.addDeleteButton(racerGUI.getBtnsDelete().get(getLastId()-1));
		// racerGUI.getPlayersPanelCards().get(getLastId()-
		// 1).setDelPlayerListener(this);
		super.addPlayer(rp);
		preGamePanel.getCreatedPlayersPanel().add(preGamePanel.getPlayersPanelCards().get(getLastId() - 1),
				"cell " + (rp.getId() - 1) + " 0, center");
		// racerGUI.createRacerPanelCard(e.getName(), e.getTc().getTeamName(),
		// e.getTc().getTeamId(), e.getTc().getCol(), e.isExpert(), getLastId(), this);
	}

	@Override
	public void listenWin(WinEvent e) {
		e.win();
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

	public void executeWin(RacerPlayer winner) {
		listenWin(new WinEvent(new WinPanel(winner.getName(), this, getBoardPane())));
	}

	@Override
	public void listenStartQuestion(StartQuestionEvent e) {

		e.startQuestion(this);
		setTimer(e.getTimer());

	}

	public void concludesTurnAction(boolean correct, ArrayList<JPanel> squarePanels, JButton btnStartQuestion,
			JButton btnEndTurn, JTextPane textPaneAction) {
		executeAction(squarePanels, correct);
		if (getActionDice().getAction().isQuestionNeeded() && correct) {
			System.out.println("Correct");
		} else {
			System.out.println("Incorrect");
		}

		// rb.movePlayer(player, diceValue);

		getSquares().get(getPlayerToAnswer().getCurrentSquare()).doSquareAction(this, correct);

		if (((RacerPlayer) getPlayers().get(getPlayerTurn())).getLostTurns() > 0) {
			ImagePanel logoPanel = boardPane.getPlayersPanelCards().get(getPlayerTurn()).getTeamLogoPanel();
			logoPanel.setImg(new ImageIcon(FileSystems.getDefault().getPath("img", "boxes.jpg").toString()).getImage());
			logoPanel.repaint();
		}

	}

	@Override
	public void listenQuestionSquare(QuestionSquareEvent e) {
		e.setActDice(getActionDice());
		e.setBtnStartQuestion(boardPane.getBtnStartQuestion());
		e.setTextPaneAction(boardPane.getTextPaneAction());

		e.activateQuestionSquare();

	}

	public void finishTurn() {
		boardPane.getBtnEndTurn().setEnabled(true);
	}

	public void genOptionButtonGroup(ButtonGroup bg, ArrayList<JRadioButton> rdbtnOptions, RacerPanel questionContentPane) {

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
			rdbtn.setFont(new Font(getPRIMARY_FONT_FAMILY(), Font.BOLD | Font.ITALIC, 18));
			bg.add(rdbtn);
			rdbtnOptions.add(rdbtn);
			questionContentPane.add(rdbtn, "cell 0 " + (i + 2));
		}

	}

	@Override
	public void listenStarPreGame(StartPreGameEvent e) {
		e.getWelPanel().dispose();
		e.getRacerGUI().setVisible(true);
		setPreGamePanel(e.getRacerGUI());
		setPlayers(new ArrayList<Player>());
		

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

	@Override
	public void listenDeletePlayer(DeletePlayerEvent e) {
		// racerGUI.getCreatedPlayersPanel().remove(racerGUI.getPlayersPanelCards().get(e.getPlayerId()-1));
		preGamePanel.getPlayersPanelCards().remove(e.getPlayerId() - 1);
		preGamePanel.addPlayersPanelCards();
		getPlayers().remove(e.getPlayerId() - 1);
		updateIds();
		
		updateComboBox(preGamePanel.getComboBoxTeams());

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
		return new RacerPanelCard(rp.getTeamColor().getCol().darker(), rp.getTeamColor().getCol().brighter(),
				rp.getTeamColor().getCol(), rp.getName(), rp.getTeamColor().getTeamName(),
				rp.getTeamColor().getTeamId(), rp.getId(), rp.typeToString());
	}

}