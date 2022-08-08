package Views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.RacerBoard;
import RacerModel.Action;
import RacerModel.Option;
import RacerModel.Player;
import RacerModel.Question;
import RacerModel.RacerPlayer;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JRadioButton;

public class BoardPaneGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblTimeLeft;
	private boolean isTimeLeft;
	private JButton btnAnswer;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public BoardPaneGUI(RacerBoard rb) {
		setTitle("Racer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRollDice = new JButton("Roll Dice");
		btnRollDice.setBounds(21, 142, 89, 23);
		contentPane.add(btnRollDice);
		
		JScrollPane scrollPaneGameStatus = new JScrollPane();
		scrollPaneGameStatus.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneGameStatus.setBounds(10, 11, 383, 95);
		contentPane.add(scrollPaneGameStatus);
		
		JTextPane textPaneGameStatus = new JTextPane();
		scrollPaneGameStatus.setViewportView(textPaneGameStatus);
		
	
		textPaneGameStatus.setText(rb.genPlayersStatus());
		
		JScrollPane scrollPaneAction = new JScrollPane();
		scrollPaneAction.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneAction.setBounds(320, 142, 213, 71);
		contentPane.add(scrollPaneAction);
		
		JTextPane textPaneAction = new JTextPane();
		scrollPaneAction.setViewportView(textPaneAction);
		
		JLabel lblAction = new JLabel("Action");
		lblAction.setBounds(250, 148, 60, 14);
		contentPane.add(lblAction);
		
		JLabel lblDice = new JLabel("Dice: ");
		lblDice.setBounds(31, 176, 79, 14);
		contentPane.add(lblDice);
		
		JLabel lblPlayerTurn = new JLabel("Turn:");
		lblPlayerTurn.setBounds(20, 117, 119, 14);
		contentPane.add(lblPlayerTurn);
		rb.setPlayerTurn(0);
		lblPlayerTurn.setText("Turn: " + rb.getPlayers().get(rb.getPlayerTurn()).getName());
		
		JPanel panelActionColor = new JPanel();
		panelActionColor.setBounds(320, 219, 213, 10);
		contentPane.add(panelActionColor);
		
		JLabel lblQuestion = new JLabel("Pregunta");
		lblQuestion.setBounds(21, 235, 60, 23);
		contentPane.add(lblQuestion);
		lblQuestion.setVisible(false);
		
		JScrollPane scrollPaneQuestion = new JScrollPane();
		scrollPaneQuestion.setBounds(21, 262, 401, 39);
		contentPane.add(scrollPaneQuestion);
		scrollPaneQuestion.setVisible(false);
		
		JTextPane textPaneQuestion = new JTextPane();
		scrollPaneQuestion.setViewportView(textPaneQuestion);
		
		JButton btnStartQuestion = new JButton("Start Question");
		btnStartQuestion.setBounds(21, 201, 111, 23);
		contentPane.add(btnStartQuestion);
		btnStartQuestion.setVisible(false);
		
		lblTimeLeft = new JLabel("Tiempo Restante: ");
		lblTimeLeft.setBounds(255, 238, 167, 20);
		contentPane.add(lblTimeLeft);
		lblTimeLeft.setVisible(false);
		
		JLabel lblCategory = new JLabel("Categoría: ");
		lblCategory.setBounds(91, 239, 154, 19);
		contentPane.add(lblCategory);
		lblCategory.setVisible(false);
		
		JRadioButton rdbtnOp0 = new JRadioButton("");
		rdbtnOp0.setBounds(21, 308, 266, 23);
		contentPane.add(rdbtnOp0);
		rdbtnOp0.setVisible(false);
		
		JRadioButton rdbtnOp1 = new JRadioButton("");
		rdbtnOp1.setBounds(21, 337, 266, 23);
		contentPane.add(rdbtnOp1);
		rdbtnOp1.setVisible(false);
		
		JRadioButton rdbtnOp2 = new JRadioButton("");
		rdbtnOp2.setBounds(21, 365, 266, 23);
		contentPane.add(rdbtnOp2);
		rdbtnOp2.setVisible(false);
		
		JRadioButton rdbtnOp3 = new JRadioButton("");
		rdbtnOp3.setBounds(21, 391, 266, 23);
		contentPane.add(rdbtnOp3);
		rdbtnOp3.setVisible(false);
		
		JRadioButton rdbtnOp4 = new JRadioButton("");
		rdbtnOp4.setBounds(21, 420, 266, 23);
		contentPane.add(rdbtnOp4);
		rdbtnOp4.setVisible(false);
		
		btnAnswer = new JButton("Answer");
		btnAnswer.setBounds(355, 337, 89, 39);
		contentPane.add(btnAnswer);
		btnAnswer.setVisible(false);
		
		ArrayList<JRadioButton> rdbtnOptions = new ArrayList<JRadioButton>();
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnOp0); rdbtnOptions.add(rdbtnOp0);
		bg.add(rdbtnOp1); rdbtnOptions.add(rdbtnOp1);
		bg.add(rdbtnOp2); rdbtnOptions.add(rdbtnOp2);
		bg.add(rdbtnOp3); rdbtnOptions.add(rdbtnOp3);
		bg.add(rdbtnOp4); rdbtnOptions.add(rdbtnOp4);
 
		JButton btnEndTurn = new JButton("End Turn");
		btnEndTurn.setBounds(344, 391, 100, 23);
		contentPane.add(btnEndTurn);
		btnEndTurn.setVisible(false);
		
		
		JLabel lblPlayerToAnswer = new JLabel("Responde: --");
		lblPlayerToAnswer.setBounds(157, 200, 130, 24);
		contentPane.add(lblPlayerToAnswer);
		lblPlayerToAnswer.setVisible(false);
		
		
		btnRollDice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Action action;
				RacerPlayer player = (RacerPlayer) rb.getPlayers().get(rb.getPlayerTurn());
				rb.getActionDice().diceRoll();
				rb.getDice().diceRoll();
				int diceValue = rb.getDice().getValue();
				action = rb.getActionDice().getAction(rb.getActionDice().getValue());
				panelActionColor.setVisible(true);
				panelActionColor.setBackground(action.getColor());
				textPaneAction.setText(action.getDesc());
				lblDice.setText("Dice: " + diceValue);
				
				
				btnRollDice.setVisible(false);
				
				if(rb.getSquares().get(player.getCurrentSquare()).getTag() == "Pregunta") {
					rb.getActionDice().setValue(6); //provisional: la pregunta tiene prioridad al dado de accion
					btnStartQuestion.setVisible(true);
					lblPlayerToAnswer.setText("Responde: " + player.getName());
					textPaneAction.setText("");
					panelActionColor.setVisible(false);
					
				} else {
					int actionNum = rb.getActionDice().getValue();
					if(actionNum != 1 && actionNum != 5) {
						btnStartQuestion.setVisible(true);
						if(actionNum == 3) {
							lblPlayerToAnswer.setText("Responde: " + rb.getPlayers().get(rb.nextPlayer()).getName());
						} else lblPlayerToAnswer.setText("Responde: " + player.getName());
						lblPlayerToAnswer.setVisible(true);
					} else {
						rb.executeAction(rb.getActionDice().getAction(rb.getActionDice().getValue()), diceValue, true);
						btnEndTurn.setVisible(true);
					}
										
					textPaneGameStatus.setText(rb.genPlayersStatus());
					
				}
				
				
		
			}
			
		});
		
		btnStartQuestion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RacerPlayer player = (RacerPlayer) rb.getPlayers().get(rb.getPlayerTurn());
				
				scrollPaneQuestion.setVisible(true);
				lblCategory.setVisible(true);
				lblTimeLeft.setVisible(true);
				lblQuestion.setVisible(true);
				isTimeLeft = true;
				
				
				Question questionChosen = rb.getQuestion(player);
				rb.setCurQuestion(questionChosen);
				rb.setTimeLeft(player.getTimePerOption()*questionChosen.getOptions().size());
				lblTimeLeft.setText("Tiempo restante: " + rb.getTimeLeft());
				rb.startTimer();
				lblCategory.setText("Categoría: " + questionChosen.getCategory().getDescription());
				
				for(Option op: questionChosen.getOptions()) {
					op.setSortNum((int) Math.ceil(Math.random()*9));
				}
				
				Collections.sort(questionChosen.getOptions(), new Comparator<Option>() {

					@Override
					public int compare(Option o1, Option o2) {
						return o1.getSortNum() - o2.getSortNum();
					}
					
				});
			
				
				for(int i = 0; i < questionChosen.getOptions().size(); i++) {
					rdbtnOptions.get(i).setText(questionChosen.getOptions().get(i).getDescripcion());
					rdbtnOptions.get(i).setVisible(true);
				}
				btnStartQuestion.setVisible(false);
				btnAnswer.setVisible(true);
				textPaneQuestion.setText(questionChosen.getStatement());
				
				
				
			}
		});
		
		btnAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Question questionChosen = rb.getCurQuestion();
				int i = 0;
				int diceValue = rb.getDice().getValue();
				while (i < rdbtnOptions.size() && !rdbtnOptions.get(i).isSelected()) {
					i++;
				}
				if(i < questionChosen.getOptions().size()) {
					rb.executeAction(rb.getActionDice().getAction(rb.getActionDice().getValue()), diceValue, questionChosen.correctAnswer(questionChosen.getOptions().get(i).getId()) && isTimeLeft);
					if(questionChosen.correctAnswer(questionChosen.getOptions().get(i).getId()) && isTimeLeft) {						
						System.out.println("Correct");
					} else {
						System.out.println("Incorrect");
					}
						
					//rb.movePlayer(player, diceValue);
					
					
					
					textPaneGameStatus.setText(rb.genPlayersStatus());
					
					
					
					
					btnAnswer.setVisible(false);
					btnEndTurn.setVisible(true);
					
					
					
				}
				
			}
			
		});
		
		btnEndTurn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Player player = rb.getPlayers().get(rb.getPlayerTurn());
				if(player.getCurrentSquare() == rb.getSquares().size() - 1) {
					lblPlayerTurn.setText("Turn: " + player.getName() + " wins!!!");
				} else {
					btnRollDice.setVisible(true);
					rb.nextTurn();
					
					player = rb.getPlayers().get(rb.getPlayerTurn());
					//lblNextTurn.setText("Next Turn: " + rb.getPlayers().get(rb.getPlayerTurn()).getName());
					lblDice.setText("Dice: --");
					lblPlayerTurn.setText("Turn: " + player.getName());
	
					scrollPaneQuestion.setVisible(false);
					lblCategory.setVisible(false);
					lblTimeLeft.setVisible(false);
					lblQuestion.setVisible(false);
					lblPlayerToAnswer.setVisible(false);
					btnEndTurn.setVisible(false);
					
					if(rb.getCurQuestion() != null) {
						bg.clearSelection();
					
						for(int j = 0; j < rb.getCurQuestion().getOptions().size(); j++) {
							rdbtnOptions.get(j).setVisible(false);
						}
					}
					
				}
				
				
				
			}
			
		});
	}
	
	public void updateTimeLeft(int timeLeft) {
		lblTimeLeft.setText("Tiempo restante: " + timeLeft);
		isTimeLeft = timeLeft > 0;
		if(!isTimeLeft) {
			btnAnswer.doClick();
		}
		
	}
	
}
