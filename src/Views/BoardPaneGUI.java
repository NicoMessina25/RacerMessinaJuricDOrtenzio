package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Controller.RacerBoard;
import Events.ExitEvent;
import Events.ResetEvent;
import Events.StartQuestionEvent;
import Listeners.ExitListener;
import Listeners.ResetListener;
import Listeners.StartQuestionListener;
import Views.CustomComponents.RacerButton;
import Views.CustomComponents.RacerLabel;
import Views.CustomComponents.RacerPanel;
import Views.CustomComponents.RacerPanelCard;
//import RacerModel.RacerPlayer.RacerPlayer;
import net.miginfocom.swing.MigLayout;

public class BoardPaneGUI extends JFrame {

	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
			//----------------------------------------->|CONSTANTS|<-----------------------------------------------\\
	
	private static final long serialVersionUID = 1L;
	
			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	private RacerPanel contentPane;
	private RacerButton btnStartQuestion;
	private RacerButton btnEndTurn;
	private JTextPane textPaneAction;
	private RacerPanel playerStatusPanel;
	private StartQuestionListener startQuestionListener;
	private RacerPanel dicesPanel;
	private ArrayList<RacerPanelCard> playersPanelCards;
	private ResetListener resetListener;
	private GridBoard panelBoardGrid;
	

	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	public BoardPaneGUI(RacerBoard rb) {
		setTitle("Racer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new RacerPanel(new MigLayout("fill", "[][][grow]", "[][][][]"), RacerPanel.getPrimaryColor(),
				RacerPanel.getSecondaryColor().darker(), RacerPanel.getTerciaryColor());//

		Path path;
		path = FileSystems.getDefault().getPath("img", "RACER_LOGO_MINI.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(path.toString()));
		
		
		
		



		setContentPane(contentPane);

		setStartQuestionListener(rb);
		setResetListener(rb);

		try {
			rb.loadQuestions();
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			e1.printStackTrace();
		}

		rb.setPlayerTurn(0);

		RacerButton btnRollDice = new RacerButton("Tirar los dados", RacerPanel.getSecondaryColor(),
				RacerPanel.getPrimaryColor(), rb.getSounds().get("buttonSound.wav"));
		btnRollDice.setFont(RacerPanel.getPrimaryFont().deriveFont(Font.BOLD | Font.ITALIC, 16));

		JScrollPane scrollPaneAction = new JScrollPane();
		scrollPaneAction.setBorder(new LineBorder(RacerPanel.getSecondaryColor().darker(), 3));
		scrollPaneAction.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


		playerStatusPanel = new RacerPanel(new MigLayout("fill", "[60px]", "[]"), RacerPanel.getPrimaryColor(),
				RacerPanel.getSecondaryColor().brighter(), RacerPanel.getPrimaryColor().brighter());

		contentPane.add(playerStatusPanel, "cell 0 0 1 2,grow");
		playersPanelCards = rb.getPreGamePanel().getPlayersPanelCards();
		addPlayersPanelCards();

		textPaneAction = new JTextPane();
		textPaneAction.setEditable(false);
		textPaneAction.setMinimumSize(new Dimension(20, 70));
		textPaneAction.setSelectionColor(RacerPanel.getPrimaryColor().brighter());
		textPaneAction.setSelectedTextColor(RacerPanel.getSecondaryColor().darker());
		textPaneAction.setForeground(RacerPanel.getTerciaryColor());
		textPaneAction.setFont(RacerPanel.getPrimaryFont().deriveFont(Font.ITALIC, 14));
		textPaneAction.setText("Acción:");
		textPaneAction.setBackground(RacerPanel.getSecondaryColor().brighter());
		scrollPaneAction.setViewportView(textPaneAction);

		RacerLabel lblDice = new RacerLabel("", 16, RacerPanel.getPrimaryColor(), RacerPanel.getSecondaryColor());
		
		lblDice.setIcon(new ImageIcon(FileSystems.getDefault().getPath("img/dice", "dice1.png").toString()), 150);
		lblDice.setHorizontalAlignment(SwingConstants.CENTER);
		
		//ImagePanel gifDice = new ImagePanel(FileSystems.getDefault().getPath("img", "rollingDice.gif").toString(), 150);


		JPanel panelActionColor = new JPanel(new MigLayout("fill", "[grow]", "[grow]"));
		panelActionColor.setBackground(Color.BLACK);
		RacerLabel lblActionDice = new RacerLabel();
		lblActionDice.setHorizontalAlignment(SwingConstants.CENTER);
		lblActionDice.setIcon(new ImageIcon(FileSystems.getDefault().getPath("img/actionDice", "actionRed.png").toString()), 150);

		btnStartQuestion = new RacerButton("Iniciar Pregunta", RacerPanel.getSecondaryColor(),
				RacerPanel.getPrimaryColor(), rb.getSounds().get("buttonSound.wav"));
		btnStartQuestion.setFont(RacerPanel.getPrimaryFont().deriveFont(Font.BOLD | Font.ITALIC, 20));
		contentPane.add(btnStartQuestion, "flowx,cell 1 0,growx,aligny center");
		btnStartQuestion.setEnabled(false);

		JPanel panelStart = new JPanel();
		panelStart.setBorder(new LineBorder(new Color(0, 0, 0)));

		panelBoardGrid = new GridBoard(
				FileSystems.getDefault().getPath("img", "racerCircuitBoard.png").toString(), 450);
		panelBoardGrid.setLayout(new MigLayout("", "[40px]0[40px]", "[40px]0[40px]"));
		
		contentPane.add(panelBoardGrid, "cell 0 2 2 2,grow");



		//panelBoardGrid.add(panelStart, "cell 0 0, width 80px, height 80px");

		btnEndTurn = new RacerButton("Finalizar Turno", RacerPanel.getSecondaryColor(), RacerPanel.getPrimaryColor(),
				rb.getSounds().get("buttonSound.wav"));
		btnEndTurn.setFont(RacerPanel.getPrimaryFont().deriveFont(Font.BOLD | Font.ITALIC, 20));
		contentPane.add(btnEndTurn, "cell 1 1,growx,aligny center");


		btnEndTurn.setEnabled(false);

		btnEndTurn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					btnRollDice.setEnabled(true);
	
					playersPanelCards.get(rb.getPlayerTurn()).stopTurnAnimation();
					rb.nextTurn();
					playersPanelCards.get(rb.getPlayerTurn()).startTurnAnimation();
			
					//lblDice.setIcon(new ImageIcon(FileSystems.getDefault().getPath("img/dice", "dice1.png").toString()), 200);
					//lblActionDice.setIcon(new ImageIcon(FileSystems.getDefault().getPath("img/actionDice","actionRed.png").toString()), 200);
					
					btnEndTurn.setEnabled(false);
					textPaneAction.setText("Acción:");

			}

		});

		/*for (int i = 0; i < 11; i++) {
			for (int j = 0; j <= 19; j++) {
				RacerLabel rl = new RacerLabel();
				rl.setIcon(new ImageIcon(FileSystems.getDefault().getPath("img/f1Cars", "alpha romeo.png").toString()), 40);
				//rl.setText("xd");
				//rl.setBorder(new LineBorder(new Color(0, 0, 0)));
				//panelBoardGrid.add(rl, "cell " + j + " " + i);
				squareLabels.add(rl);
			}
		}*/
		
		panelBoardGrid.setPlayersRacerLabel(rb.genPlayersRacerLabel());		
		panelBoardGrid.setStartingPositions();

		btnRollDice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				lblActionDice.setIcon(new ImageIcon(FileSystems.getDefault().getPath("img/actionDice","actionDice.gif").toString()), 150);
				lblDice.setIcon(new ImageIcon(FileSystems.getDefault().getPath("img/dice", "rollingDice.gif").toString()), 150);
				
				btnRollDice.setEnabled(false);
				
				Timer rollingAnimation = new Timer(1000, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						rb.rollDices();
						//panelActionColor.setEnabled(true);
						//panelActionColor.setBackground(rb.getCurrentActionColor());
						lblActionDice.setIcon(new ImageIcon(rb.getCurrentActionImgPath()), 150);
						textPaneAction.setText("Acción: " + rb.getCurrentActionDesc());
						dicesPanel.updateUI();
						lblDice.setIcon(new ImageIcon(FileSystems.getDefault().getPath("img/dice", "dice" + rb.getDice().getValue()).toString() + ".png"), 150);
						
						rb.setPlayerToAnswer();
						
						
						if (rb.getActionDice().getAction().isQuestionNeeded()) {
							btnStartQuestion.setEnabled(true);

						} else {
							rb.concludesTurnAction(true, panelBoardGrid, btnStartQuestion, btnEndTurn, textPaneAction); 

						}
						
					}
				});
				
				rollingAnimation.setRepeats(false);
				rollingAnimation.start();


				// textPaneGameStatus.setText(rb.genPlayersStatus());
			}

		});

		btnStartQuestion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startQuestionListener.listenStartQuestion(new StartQuestionEvent(rb.getPlayerToAnswer(),
						rb.genQuestionForPlayer(rb.getPlayerToAnswer()), new QuestionPanel(rb, panelBoardGrid,
								btnStartQuestion, btnEndTurn, textPaneAction, rb.getPlayerToAnswerPanelCard()))); 

				btnStartQuestion.setEnabled(false);

			}
		});

		dicesPanel = new RacerPanel(new MigLayout("fill", "[grow]", "[][grow][grow][grow][]"), RacerPanel.getPrimaryColor(),
				RacerPanel.getSecondaryColor().darker(), RacerPanel.getSecondaryColor());
		contentPane.add(dicesPanel, "cell 2 0 1 4, grow");
		dicesPanel.add(btnRollDice, "cell 0 0, grow");
		dicesPanel.add(lblDice, "cell 0 1, grow");
		//dicesPanel.add(gifDice, "cell 0 1, grow");
		dicesPanel.add(panelActionColor, "cell 0 2,grow");
		panelActionColor.add(lblActionDice, "cell 0 0, growx");
		//dicesPanel.add(lblActionDice, "cell 0 2,grow");
		dicesPanel.add(scrollPaneAction, "cell 0 3,grow");

		playersPanelCards.get(rb.getPlayerTurn()).startTurnAnimation();

		RacerButton btnExit = new RacerButton("Salir al Inicio", RacerPanel.getSecondaryColor(), RacerPanel.getPrimaryColor(),
				rb.getSounds().get("buttonSound.wav"));
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				resetListener.listenReset(new ResetEvent((JFrame) SwingUtilities.getWindowAncestor(contentPane)));

			}

		});

		dicesPanel.add(btnExit, "cell 0 4,grow");

	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

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

	public GridBoard getPanelBoardGrid() {
		return panelBoardGrid;
	}

	public void setPanelBoardGrid(GridBoard panelBoardGrid) {
		this.panelBoardGrid = panelBoardGrid;
	}

	public ResetListener getResetListener() {
		return resetListener;
	}

	public void setResetListener(ResetListener resetListener) {
		this.resetListener = resetListener;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\

	public void addPlayersPanelCards() {
		for (int i = 0; i < playersPanelCards.size(); i++) {
			RacerPanelCard rpc = playersPanelCards.get(i);
			rpc.remove(rpc.getBtnDel());
			rpc.getLblName().setFont(RacerPanel.getPrimaryFont().deriveFont(Font.BOLD | Font.ITALIC, 16));
			rpc.getLblTeam().setFont(RacerPanel.getPrimaryFont().deriveFont(Font.ITALIC, 12));//
			rpc.getLblExpert().setFont(RacerPanel.getPrimaryFont().deriveFont(Font.ITALIC, 12));
			rpc.getTeamLogoPanel().setHeight(70);
			rpc.getTeamLogoPanel().setImg(rpc.getTeamLogoPanel().getImg());
			;
			playerStatusPanel.add(rpc, "cell " + i + " 0, center");
		}

	}

}
