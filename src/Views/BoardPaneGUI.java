package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import Controller.RacerBoard;
import Events.StartQuestionEvent;
import Listeners.StartQuestionListener;
import Views.CustomComponents.RacerButton;
import Views.CustomComponents.RacerLabel;
import Views.CustomComponents.RacerPanel;
import Views.CustomComponents.RacerPanelCard;
//import RacerModel.RacerPlayer.RacerPlayer;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class BoardPaneGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RacerPanel contentPane;
	private RacerButton btnStartQuestion;
	private RacerButton btnEndTurn;
	private JTextPane textPaneAction;
	private RacerPanel playerStatusPanel;
	private StartQuestionListener startQuestionListener;
	private RacerPanel dicesPanel;
	private ArrayList<RacerPanelCard> playersPanelCards;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public BoardPaneGUI(RacerBoard rb) {
		setTitle("Racer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new RacerPanel(new MigLayout("fill", "[][][]", "[][][][]"), rb.getPRIMARY_COLOR(),
				rb.getSECONDARY_COLOR().darker(), rb.getTERCIARY_COLOR());//

		Path path;
		path = FileSystems.getDefault().getPath("img", "RACER_LOGO_MINI.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(path.toString()));

		setContentPane(contentPane);

		setStartQuestionListener(rb);

		rb.loadQuestions();

		rb.setPlayerTurn(0);

		RacerButton btnRollDice = new RacerButton("Tirar los dados", rb.getSECONDARY_COLOR(), rb.getPRIMARY_COLOR());
		btnRollDice.setFont(new Font("Swis721 Hv BT", Font.BOLD | Font.ITALIC, 16));
		// contentPane.add(btnRollDice, "flowx,cell 1 0,growx,aligny center");

		// RacerLabel lblAction = new RacerLabel("Acción:", 16, rb.getPRIMARY_COLOR(),
		// rb.getSECONDARY_COLOR());
		// lblAction.setHorizontalAlignment(SwingConstants.CENTER);
		// contentPane.add(lblAction, "cell 1 0,growx");

		JScrollPane scrollPaneAction = new JScrollPane();
		scrollPaneAction.setBorder(new LineBorder(rb.getSECONDARY_COLOR().darker(), 3));
		scrollPaneAction.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// contentPane.add(scrollPaneAction, "cell 2 3,grow");

		playerStatusPanel = new RacerPanel(new MigLayout("fill", "[]", "[]"), rb.getPRIMARY_COLOR(),
				rb.getSECONDARY_COLOR().brighter(), rb.getPRIMARY_COLOR().brighter());

		contentPane.add(playerStatusPanel, "cell 0 0 1 2,grow");
		playersPanelCards = rb.getPreGamePanel().getPlayersPanelCards();
		addPlayersPanelCards();

		textPaneAction = new JTextPane();
		textPaneAction.setEditable(false);
		textPaneAction.setMinimumSize(new Dimension(20, 70));
		textPaneAction.setSelectionColor(rb.getPRIMARY_COLOR().brighter());
		textPaneAction.setSelectedTextColor(rb.getSECONDARY_COLOR().darker());
		textPaneAction.setForeground(rb.getTERCIARY_COLOR());
		textPaneAction.setFont(new Font(RacerBoard.getPRIMARY_FONT_FAMILY(), Font.ITALIC, 14));
		textPaneAction.setText("Acción:");
		textPaneAction.setBackground(rb.getSECONDARY_COLOR().brighter());
		scrollPaneAction.setViewportView(textPaneAction);

		RacerLabel lblDice = new RacerLabel("Dado: ", 16, rb.getPRIMARY_COLOR(), rb.getSECONDARY_COLOR());
		lblDice.setHorizontalAlignment(SwingConstants.CENTER);
		// contentPane.add(lblDice, "cell 1 1,grow, center");

		JPanel panelActionColor = new JPanel();
		// contentPane.add(panelActionColor, "cell 2 2,growx,aligny top");

		btnStartQuestion = new RacerButton("Iniciar Pregunta", rb.getSECONDARY_COLOR(),rb.getPRIMARY_COLOR());
		btnStartQuestion.setFont(new Font(RacerBoard.getPRIMARY_FONT_FAMILY(), Font.BOLD | Font.ITALIC, 20));
		contentPane.add(btnStartQuestion, "flowx,cell 1 0,growx,aligny center");
		btnStartQuestion.setEnabled(false);

		JPanel panelStart = new JPanel();
		panelStart.setBorder(new LineBorder(new Color(0, 0, 0)));

		ImagePanel panelBoardGrid = new ImagePanel(
				FileSystems.getDefault().getPath("img", "racerCircuitBoard.png").toString(), 450);
		panelBoardGrid.setLayout(new MigLayout("", "[]0[]0[]0[]0[]0[]", "[]0[]0[]0[]0[]0[]0[]"));
		contentPane.add(panelBoardGrid, "cell 0 2 2 2,grow");

		ArrayList<JPanel> squarePanels = new ArrayList<JPanel>();

		panelBoardGrid.add(panelStart, "cell 0 0, width 80px, height 80px");

		btnEndTurn = new RacerButton("Finalizar Turno", rb.getSECONDARY_COLOR(), rb.getPRIMARY_COLOR());
		btnEndTurn.setFont(new Font(RacerBoard.getPRIMARY_FONT_FAMILY(), Font.BOLD | Font.ITALIC, 20));
		contentPane.add(btnEndTurn, "cell 1 1,growx,aligny center");

		// RacerLabel lblPlayerTurn = new RacerLabel("Turno de:" +
		// rb.getPlayers().get(rb.getPlayerTurn()).getName(), 18, rb.getPRIMARY_COLOR(),
		// rb.getSECONDARY_COLOR());
		// lblPlayerTurn.setHorizontalAlignment(SwingConstants.CENTER);
		// contentPane.add(lblPlayerTurn, "cell 0 1,growx");

		btnEndTurn.setEnabled(false);

		btnEndTurn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// rb.getSquares().get(rb.getPlayerToAnswer().getCurrentSquare()).doSquareAction(rb);

				if (rb.getPlayerToAnswer().getCurrentSquare() == rb.getSquares().size() - 1) {
					// winListener.listenWin(new WinEvent(new
					// WinPanel(rb.getPlayerToAnswer().getName(), rb, rb, (BoardPaneGUI)
					// SwingUtilities.getWindowAncestor(contentPane))));
					// lblPlayerTurn.setText("Turn: " + rb.getPlayerToAnswer().getName() + "
					// wins!!!");
				} else {
					btnRollDice.setEnabled(true);
					// playersPanelCards.get(rb.getPlayerTurn()).setBackground((!toggleCardColor)?
					// playersPanelCards.get(rb.getPlayerTurn()).getBackground().darker():
					// playersPanelCards.get(rb.getPlayerTurn()).getBackground());
					playersPanelCards.get(rb.getPlayerTurn()).stopTurnAnimation();
					rb.nextTurn();
					playersPanelCards.get(rb.getPlayerTurn()).startTurnAnimation();
					// lblNextTurn.setText("Next Turn: " +
					// rb.getPlayers().get(rb.getPlayerTurn()).getName());
					lblDice.setText("Dado: --");
					// lblPlayerTurn.setText("Turn: " +
					// rb.getPlayers().get(rb.getPlayerTurn()).getName());
					btnEndTurn.setEnabled(false);
					textPaneAction.setText("Acción:");

					/*
					 * if(rb.getCurQuestion() != null) {
					 * 
					 * 
					 * for(int j = 0; j < rb.getCurQuestion().getOptions().size(); j++) {
					 * 
					 * } }
					 */

				}

			}

		});

		for (int i = 0; i < rb.getRows(); i++) {
			for (int j = 1; j <= rb.getColumns(); j++) {
				JPanel jp = new JPanel();
				jp.setBorder(new LineBorder(new Color(0, 0, 0)));
				// jp.setSize(10, 10);
				panelBoardGrid.add(jp, "cell " + j + " " + i + ",width 80px, height 80px");
				squarePanels.add(jp);
			}
		}

		btnRollDice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				rb.rollDices();
				panelActionColor.setEnabled(true);
				panelActionColor.setBackground(rb.getCurrentActionColor());
				textPaneAction.setText("Acción: " + rb.getCurrentActionDesc());

				lblDice.setText("Dice: " + rb.getDice().getValue());

				btnRollDice.setEnabled(false);

				rb.setPlayerToAnswer();

				if (rb.getActionDice().getAction().isQuestionNeeded()) {
					btnStartQuestion.setEnabled(true);

				} else {
					rb.concludesTurnAction(true, squarePanels, btnStartQuestion, btnEndTurn, textPaneAction);
					/*
					 * rb.executeAction(squarePanels, true);
					 * if(rb.getSquares().get(rb.getPlayerToAnswer().getCurrentSquare()).getTag().
					 * equalsIgnoreCase("Pregunta")) { btnStartQuestion.setVisible(true);
					 * 
					 * rb.getActionDice().setValue(4);
					 * textPaneAction.setText("Casilla de Pregunta!");
					 * 
					 * } else { btnEndTurn.setVisible(true); }
					 */

				}

				// textPaneGameStatus.setText(rb.genPlayersStatus());
			}

		});

		btnStartQuestion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startQuestionListener.listenStartQuestion(new StartQuestionEvent(rb.getPlayerToAnswer(),
						rb.genQuestionForPlayer(rb.getPlayerToAnswer()), new QuestionPanel(rb, squarePanels,
								btnStartQuestion, btnEndTurn, textPaneAction, rb.getPlayerToAnswerPanelCard())));

				btnStartQuestion.setEnabled(false);

			}
		});

		dicesPanel = new RacerPanel(new MigLayout(), rb.getPRIMARY_COLOR(), rb.getSECONDARY_COLOR().darker(),
				rb.getSECONDARY_COLOR());
		contentPane.add(dicesPanel, "cell 2 0 1 4, grow");
		dicesPanel.add(btnRollDice, "cell 0 0, grow");
		dicesPanel.add(lblDice, "cell 0 1, grow");
		dicesPanel.add(panelActionColor, "cell 0 2,grow");
		dicesPanel.add(scrollPaneAction, "cell 0 3,grow");

		playersPanelCards.get(rb.getPlayerTurn()).startTurnAnimation();

	}

	public void setStartQuestionListener(StartQuestionListener I) {
		startQuestionListener = I;
	}

	public JButton getBtnStartQuestion() {
		return btnStartQuestion;
	}

	public void setBtnStartQuestion(RacerButton btnStartQuestion) {
		this.btnStartQuestion = btnStartQuestion;
	}

	public JTextPane getTextPaneAction() {
		return textPaneAction;
	}

	public void setTextPaneAction(JTextPane textPaneAction) {
		this.textPaneAction = textPaneAction;
	}

	public JButton getBtnEndTurn() {
		return btnEndTurn;
	}

	public void setBtnEndTurn(RacerButton btnEndTurn) {
		this.btnEndTurn = btnEndTurn;
	}

	public ArrayList<RacerPanelCard> getPlayersPanelCards() {
		return playersPanelCards;
	}

	public void setPlayersPanelCards(ArrayList<RacerPanelCard> playersPanelCards) {
		this.playersPanelCards = playersPanelCards;
	}

	public void addPlayersPanelCards() {
		for (int i = 0; i < playersPanelCards.size(); i++) {
			RacerPanelCard rpc = playersPanelCards.get(i);
			rpc.remove(rpc.getBtnDel());
			rpc.getLblName().setFont(new Font("Swis721 Hv BT", Font.BOLD | Font.ITALIC, 16));
			rpc.getLblTeam().setFont(new Font("Swis721 Hv BT", Font.ITALIC, 12));
			rpc.getLblExpert().setFont(new Font("Swis721 Hv BT", Font.ITALIC, 12));
			rpc.getTeamLogoPanel().setHeight(70);
			rpc.getTeamLogoPanel().setImg(rpc.getTeamLogoPanel().getImg());
			;
			playerStatusPanel.add(rpc, "cell " + i + " 0, center");
		}

	}

}
