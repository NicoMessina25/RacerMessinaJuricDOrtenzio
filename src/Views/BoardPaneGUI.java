package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import Controller.RacerBoard;
import Events.StartQuestionEvent;
import Listeners.StartQuestionListener;
//import RacerModel.RacerPlayer.RacerPlayer;
import net.miginfocom.swing.MigLayout;

public class BoardPaneGUI extends JFrame {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnStartQuestion;
	private JButton btnEndTurn;
	private JTextPane textPaneAction;
	private StartQuestionListener startQuestionListener;



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
		contentPane = new JPanel(new MigLayout("fill", "[108.00][][][][][][][][]", "[][42.00][][][][][26.00][28.00][92.00][][]"));
	
		
		setContentPane(contentPane);
		
		setStartQuestionListener(rb);
		
		rb.loadQuestions();
		
		JScrollPane scrollPaneGameStatus = new JScrollPane();
		scrollPaneGameStatus.setBounds(10, 11, 383, 95);
		contentPane.add(scrollPaneGameStatus, "flowx,cell 0 0 2 3,grow");
		
		rb.setPlayerTurn(0);
		
		JTextPane textPaneGameStatus = new JTextPane();
		textPaneGameStatus.setEditable(false);
		textPaneGameStatus.setMinimumSize(new Dimension(20, 70));
		scrollPaneGameStatus.setViewportView(textPaneGameStatus);
	
		textPaneGameStatus.setText(rb.genPlayersStatus());
		
		JLabel lblAction = new JLabel("Action");
		lblAction.setBounds(250, 148, 60, 14);
		contentPane.add(lblAction, "cell 3 1");
		
		JScrollPane scrollPaneAction = new JScrollPane();
		scrollPaneAction.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneAction.setBounds(320, 142, 213, 71);
		contentPane.add(scrollPaneAction, "flowy,cell 4 1 5 2,grow");
		
		textPaneAction = new JTextPane();
		textPaneAction.setEditable(false);
		textPaneAction.setMinimumSize(new Dimension(20, 70));
		scrollPaneAction.setViewportView(textPaneAction);
		
		
		JButton btnRollDice = new JButton("Roll Dice");
		btnRollDice.setBounds(21, 142, 89, 23);
		contentPane.add(btnRollDice, "cell 0 3,grow");
		
		JLabel lblPlayerTurn = new JLabel("Turn:");
		lblPlayerTurn.setBounds(20, 117, 119, 14);
		contentPane.add(lblPlayerTurn, "cell 1 3");
		lblPlayerTurn.setText("Turn: " + rb.getPlayers().get(rb.getPlayerTurn()).getName());
		
		JPanel panelActionColor = new JPanel();
		panelActionColor.setBounds(320, 219, 213, 10);
		contentPane.add(panelActionColor, "cell 4 3 5 1,growx,aligny top");
		
		JLabel lblDice = new JLabel("Dice: ");
		lblDice.setBounds(31, 176, 79, 14);
		contentPane.add(lblDice, "cell 0 4");
		
		
		
		btnStartQuestion = new JButton("Start Question");
		btnStartQuestion.setBounds(21, 201, 111, 23);
		contentPane.add(btnStartQuestion, "cell 0 5,grow");
		btnStartQuestion.setVisible(false);
		
		btnEndTurn = new JButton("End Turn");
		btnEndTurn.setBounds(344, 391, 100, 23);
		contentPane.add(btnEndTurn, "cell 1 5,grow");
		
		btnEndTurn.setVisible(false);
	
		
		
		
		btnEndTurn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//rb.getSquares().get(rb.getPlayerToAnswer().getCurrentSquare()).doSquareAction(rb);
				
				if(rb.getPlayerToAnswer().getCurrentSquare() == rb.getSquares().size() - 1) {
					//winListener.listenWin(new WinEvent(new WinPanel(rb.getPlayerToAnswer().getName(), rb, rb, (BoardPaneGUI) SwingUtilities.getWindowAncestor(contentPane))));
					//lblPlayerTurn.setText("Turn: " + rb.getPlayerToAnswer().getName() + " wins!!!");
				} else {
					btnRollDice.setVisible(true);
					rb.nextTurn();
					//lblNextTurn.setText("Next Turn: " + rb.getPlayers().get(rb.getPlayerTurn()).getName());
					lblDice.setText("Dice: --");
					lblPlayerTurn.setText("Turn: " + rb.getPlayers().get(rb.getPlayerTurn()).getName());
					btnEndTurn.setVisible(false);
					textPaneAction.setText("");
					
					/*if(rb.getCurQuestion() != null) {
					
					
						for(int j = 0; j < rb.getCurQuestion().getOptions().size(); j++) {
							
						}
					}*/
					
				}
				
				
				
			}
			
		});
		
		JPanel panelStart = new JPanel();
		//contentPane.add(panelStart, "cell 0 7,alignx right,aligny bottom");
		panelStart.setBorder(new LineBorder(new Color(0,0,0)));
		
		JPanel panelBoardGrid = new JPanel(new MigLayout("", "[]0[]0[]0[]0[]0[]", "[]0[]0[]0[]0[]0[]0[]"));
		contentPane.add(panelBoardGrid, "cell 0 7 9 3,grow");
		
		ArrayList<JPanel> squarePanels = new ArrayList<JPanel>();
		
		
		panelBoardGrid.add(panelStart, "cell 0 0, width 80px, height 80px");
		
		for(int i = 0; i < rb.getRows(); i++) {
			for(int j = 1; j <= rb.getColumns(); j++) {
				JPanel jp = new JPanel();
				jp.setBorder(new LineBorder(new Color(0, 0, 0)));
				//jp.setSize(10, 10);
				panelBoardGrid.add(jp, "cell " + j + " " + i + ",width 80px, height 80px");
				squarePanels.add(jp);
			}
		}
	
		btnRollDice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				rb.rollDices();				
				panelActionColor.setVisible(true);
				panelActionColor.setBackground(rb.getCurrentActionColor());
				textPaneAction.setText(rb.getCurrentActionDesc());
				lblDice.setText("Dice: " + rb.getDice().getValue());
				
			
				btnRollDice.setVisible(false);
				
			
				if(rb.getActionDice().getAction().isQuestionNeeded()) {
					btnStartQuestion.setVisible(true);

				} else {
					rb.concludesTurnAction(true, squarePanels, btnStartQuestion, btnEndTurn, textPaneAction);
					/*rb.executeAction(squarePanels, true);
					if(rb.getSquares().get(rb.getPlayerToAnswer().getCurrentSquare()).getTag().equalsIgnoreCase("Pregunta")) {
						btnStartQuestion.setVisible(true);
				
						rb.getActionDice().setValue(4);
						textPaneAction.setText("Casilla de Pregunta!");
		
					} else {
						btnEndTurn.setVisible(true);
					}*/
					
				}
									
				textPaneGameStatus.setText(rb.genPlayersStatus());		
			}
			
		});
		
		
		
		
		btnStartQuestion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				startQuestionListener.listenStartQuestion(new StartQuestionEvent(rb.getPlayerToAnswer(),rb.genQuestionForPlayer(rb.getPlayerToAnswer()), new QuestionPanel(rb, squarePanels, btnStartQuestion, btnEndTurn, textPaneAction, textPaneGameStatus)));
				
				btnStartQuestion.setVisible(false);
				
			}
		});
		
	
	}
	
	
	
	public void setStartQuestionListener(StartQuestionListener I) {
		startQuestionListener = I;
	}



	public JButton getBtnStartQuestion() {
		return btnStartQuestion;
	}



	public void setBtnStartQuestion(JButton btnStartQuestion) {
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



	public void setBtnEndTurn(JButton btnEndTurn) {
		this.btnEndTurn = btnEndTurn;
	}
	
}
