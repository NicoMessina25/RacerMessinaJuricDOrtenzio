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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import Controller.RacerBoard;
import Events.ExitEvent;
import Events.ResetEvent;
import Events.StartQuestionEvent;
import Listeners.ExitListener;
import Listeners.ResetListener;
import Listeners.StartQuestionListener;
import Views.CustomComponents.RacerButton;
import Views.CustomComponents.RacerIcon;
import Views.CustomComponents.RacerPanel;
import Views.CustomComponents.RacerPanelCard;
import net.miginfocom.swing.MigLayout;

/**
 *
 */
public class BoardPaneGUI extends JFrame {

	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
			//----------------------------------------->|CONSTANTS|<-----------------------------------------------\\
	
	private static final long serialVersionUID = 1L;
	
			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	/**
	 *
	 */
	private RacerPanel contentPane, playerStatusPanel, dicesPanel;
	private QuestionPanel questionPanel;
	private RacerButton btnStartQuestion, btnEndTurn, btnExit;
	private JTextPane textPaneAction;
	private StartQuestionListener startQuestionListener;
	private ArrayList<RacerPanelCard> playersPanelCards;
	private ResetListener resetListener;
	private GridBoard panelBoardGrid;
	private ExitListener exitListener;
	

	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 * @param rb
	 */
	public BoardPaneGUI(RacerBoard rb) {
		RacerButton btnRollDice;
		JScrollPane scrollPaneAction;
		SoundClip music;
		RacerIcon iconDice, iconActionDice;
		JPanel panelActionColor, panelStart;
		
		setTitle("Racer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FileSystems.getDefault().getPath("img", "RACER_LOGO_MINI.png").toString()));
		
		setStartQuestionListener(rb);
		setResetListener(rb);
		setExitListener(rb);
		
		rb.loadQuestions();
		rb.setPlayerTurn(0);
		
		
		
		contentPane = new RacerPanel(new MigLayout("fill", "[][][grow]", "[][][][]"), RacerPanel.getPrimaryColor(),
				RacerPanel.getSecondaryColor().darker(), RacerPanel.getTerciaryColor());
		setContentPane(contentPane);
		
		questionPanel = null;
		
		
		music = rb.getSounds().get("gameMusic.wav");
		music.loop();
		


		btnRollDice = new RacerButton("Tirar los dados", RacerPanel.getSecondaryColor(),
				RacerPanel.getPrimaryColor(), rb.getSounds().get("buttonSound.wav"));
		btnRollDice.setFont(RacerPanel.getPrimaryFont().deriveFont(Font.BOLD | Font.ITALIC, 16));

		scrollPaneAction = new JScrollPane();
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

		iconDice = new RacerIcon(new ImageIcon(FileSystems.getDefault().getPath("img/dice", "dice1.png").toString()), 150);
		
		
		iconDice.setHorizontalAlignment(SwingConstants.CENTER);


		panelActionColor = new JPanel(new MigLayout("fill", "[grow]", "[grow]"));
		panelActionColor.setBackground(Color.BLACK);
		iconActionDice = new RacerIcon(new ImageIcon(FileSystems.getDefault().getPath("img/actionDice", "actionRed.png").toString()), 150);
		iconActionDice.setHorizontalAlignment(SwingConstants.CENTER);

		btnStartQuestion = new RacerButton("Iniciar Pregunta", RacerPanel.getSecondaryColor(),
				RacerPanel.getPrimaryColor(), rb.getSounds().get("buttonSound.wav"));
		btnStartQuestion.setFont(RacerPanel.getPrimaryFont().deriveFont(Font.BOLD | Font.ITALIC, 20));
		contentPane.add(btnStartQuestion, "flowx,cell 1 0,growx,aligny center");
		btnStartQuestion.setEnabled(false);

		panelStart = new JPanel();
		panelStart.setBorder(new LineBorder(new Color(0, 0, 0)));

		panelBoardGrid = new GridBoard(
				FileSystems.getDefault().getPath("img", "racerCircuitBoard.png").toString(), 450);
		panelBoardGrid.setLayout(new MigLayout("", "[40px]0[40px]", "[40px]0[40px]"));
		
		contentPane.add(panelBoardGrid, "cell 0 2 2 2,grow");




		btnEndTurn = new RacerButton("Finalizar Turno", RacerPanel.getSecondaryColor(), RacerPanel.getPrimaryColor(),
				rb.getSounds().get("buttonSound.wav"));
		btnEndTurn.setFont(RacerPanel.getPrimaryFont().deriveFont(Font.BOLD | Font.ITALIC, 20));	
		btnEndTurn.setEnabled(false);
		btnEndTurn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					btnRollDice.setEnabled(true);
	
					playersPanelCards.get(rb.getPlayerTurn()).stopTurnAnimation();
					rb.nextTurn();
					playersPanelCards.get(rb.getPlayerTurn()).startTurnAnimation();
					
					btnEndTurn.setEnabled(false);
					textPaneAction.setText("Acción:");

			}

		});
		
		panelBoardGrid.setPlayersRacerIcon(rb.genPlayersRacerIcon());		
		panelBoardGrid.setStartingPositions();
		contentPane.add(btnEndTurn, "cell 1 1,growx,aligny center");
		
		
		btnRollDice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				iconActionDice.setIcon(new ImageIcon(FileSystems.getDefault().getPath("img/actionDice","actionDice.gif").toString()), 150);
				iconDice.setIcon(new ImageIcon(FileSystems.getDefault().getPath("img/dice", "rollingDice.gif").toString()), 150);
				
				btnRollDice.setEnabled(false);
				
				Timer rollingAnimation = new Timer(1000, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						rb.rollDices();
						iconActionDice.setIcon(new ImageIcon(rb.getCurrentActionImgPath()), 150);
						textPaneAction.setText("Acción: " + rb.getCurrentActionDesc());
						dicesPanel.updateUI();
						iconDice.setIcon(new ImageIcon(FileSystems.getDefault().getPath("img/dice", "dice" + rb.getDice().getValue()).toString() + ".png"), 150);
						
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
			}

		});

		btnStartQuestion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				rb.genQuestionForPlayer(rb.getPlayerToAnswer());
				
				questionPanel = new QuestionPanel(rb, panelBoardGrid,
						btnStartQuestion, btnEndTurn, textPaneAction, rb.getPlayerToAnswerPanelCard());
				
				startQuestionListener.listenStartQuestion(new StartQuestionEvent(rb.getPlayerToAnswer(),
						rb.getCurQuestion(), questionPanel)); 

				btnStartQuestion.setEnabled(false);

			}
		});

		dicesPanel = new RacerPanel(new MigLayout("fill", "[grow]", "[][grow][grow][grow][]"), RacerPanel.getPrimaryColor(),
				RacerPanel.getSecondaryColor().darker(), RacerPanel.getSecondaryColor());
		contentPane.add(dicesPanel, "cell 2 0 1 4, grow");
		dicesPanel.add(btnRollDice, "cell 0 0, grow");
		dicesPanel.add(iconDice, "cell 0 1, grow");
		dicesPanel.add(panelActionColor, "cell 0 2,grow");
		panelActionColor.add(iconActionDice, "cell 0 0, growx");
		dicesPanel.add(scrollPaneAction, "cell 0 3,grow");

		playersPanelCards.get(rb.getPlayerTurn()).startTurnAnimation();

		btnExit = new RacerButton("Salir al Inicio", RacerPanel.getSecondaryColor(), RacerPanel.getPrimaryColor(),
				rb.getSounds().get("buttonSound.wav"));
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "¿Seguro que quieres salir de la partida?", "¡Cuidado!", JOptionPane.YES_NO_OPTION) == 0){
					resetListener.listenReset(new ResetEvent((JFrame) SwingUtilities.getWindowAncestor(contentPane)));
					
					if(questionPanel != null) {
						exitListener.listenExit(new ExitEvent(questionPanel));
					}
					
					music.stop();
				}
				
			}

		});

		dicesPanel.add(btnExit, "cell 0 4,grow");

	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	
	/**
	 * 
	 * @param I
	 */
	public void setStartQuestionListener(StartQuestionListener I) {
		startQuestionListener = I;
	}
	
	/**
	 * 
	 * @return
	 */
	public JButton getBtnStartQuestion() {
		return btnStartQuestion;
	}
	
	/**
	 * 
	 * @param btnStartQuestion
	 */
	public void setBtnStartQuestion(RacerButton btnStartQuestion) {
		this.btnStartQuestion = btnStartQuestion;
	}
	
	/**
	 * 
	 * @return
	 */
	public JTextPane getTextPaneAction() {
		return textPaneAction;
	}
	
	/**
	 * 
	 * @param textPaneAction
	 */
	public void setTextPaneAction(JTextPane textPaneAction) {
		this.textPaneAction = textPaneAction;
	}
	
	/**
	 * 
	 * @return
	 */
	public JButton getBtnEndTurn() {
		return btnEndTurn;
	}
	
	/**
	 * 
	 * @param btnEndTurn
	 */
	public void setBtnEndTurn(RacerButton btnEndTurn) {
		this.btnEndTurn = btnEndTurn;
	}
	
	/**
	 * 
	 * @return
	 */
	public RacerButton getBtnExit() {
		return btnExit;
	}
	
	/**
	 * 
	 * @param btnExit
	 */
	public void setBtnExit(RacerButton btnExit) {
		this.btnExit = btnExit;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<RacerPanelCard> getPlayersPanelCards() {
		return playersPanelCards;
	}
	
	/**
	 * 
	 * @param playersPanelCards
	 */
	public void setPlayersPanelCards(ArrayList<RacerPanelCard> playersPanelCards) {
		this.playersPanelCards = playersPanelCards;
	}
	
	/**
	 * 
	 * @return
	 */
	public GridBoard getPanelBoardGrid() {
		return panelBoardGrid;
	}
	
	/**
	 * 
	 * @param panelBoardGrid
	 */
	public void setPanelBoardGrid(GridBoard panelBoardGrid) {
		this.panelBoardGrid = panelBoardGrid;
	}
	
	/**
	 * 
	 * @return
	 */
	public ResetListener getResetListener() {
		return resetListener;
	}
	
	/**
	 * 
	 * @param resetListener
	 */
	public void setResetListener(ResetListener resetListener) {
		this.resetListener = resetListener;
	}
	
	/**
	 * 
	 * @return
	 */
	public ExitListener getExitListener() {
		return exitListener;
	}
	
	/**
	 * 
	 * @param exitListener
	 */
	public void setExitListener(ExitListener exitListener) {
		this.exitListener = exitListener;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	/**
	 * 
	 */
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
	
	public void playerBackToStart(int playerId, String type, int currentSquareId, int lastSquare) {
		
		Timer delay = new Timer(2000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getPanelBoardGrid().movePlayer(playerId, currentSquareId, lastSquare);
				getPlayersPanelCards().get(playerId - 1).getLblExpert().setText(type + " - Vue. " + getPanelBoardGrid().getPlayerLap(currentSquareId) + "/2");
			}
		});
		delay.setRepeats(false);
		delay.start();
	}

}
