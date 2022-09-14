package Views;


import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import Controller.RacerBoard;
import RacerModel.Option;
import RacerModel.Question;
import RacerModel.RacerPlayer;
import net.miginfocom.swing.MigLayout;

public class QuestionPanel extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTimeLeft;
	private boolean isTimeLeft;
	private JButton btnAnswer;

	/**
	 * Create the frame.
	 */
	public QuestionPanel(Question question, RacerBoard rb, ArrayList<JPanel> squarePanels, JButton btnStartQuestion, JButton btnEndTurn, JTextPane textPaneAction, JTextPane textPaneGameStatus) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel(new MigLayout("fill", "[][grow][][]", "[][][][]"));
		//contentPane.setSize(800, 500);
				
		setContentPane(contentPane);
		
		JLabel lblPlayerToAnswer = new JLabel("Responde: " + rb.getPlayerToAnswer().getName());
		contentPane.add(lblPlayerToAnswer, "cell 0 0");
		
		JPanel panelCategoryCol = new JPanel();
		contentPane.add(panelCategoryCol, "cell 1 0,growx,aligny bottom");
		panelCategoryCol.setBackground(question.getCategory().getColor());
		
		JLabel lblQuestion = new JLabel("Pregunta:");
		contentPane.add(lblQuestion, "cell 0 1");
		
		JLabel lblCategory = new JLabel("Categoría: " + question.getCategory().getDescription());
		contentPane.add(lblCategory, "cell 1 1");
		
		lblTimeLeft = new JLabel("Tiempo Restante: " + question.getOptions().size()*rb.getPlayerToAnswer().getTimePerOption());
		contentPane.add(lblTimeLeft, "cell 2 1");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane, "cell 0 2 3 1,grow");
		
		JTextPane textPaneQuestion = new JTextPane();
		textPaneQuestion.setText(question.getStatement());
		scrollPane.setViewportView(textPaneQuestion);
		
		btnAnswer = new JButton("Answer");
		contentPane.add(btnAnswer, "cell 2 3");
		
		ButtonGroup bg = new ButtonGroup();
		ArrayList<JRadioButton> rdbtnOptions = new ArrayList<JRadioButton>();
		
		for(Option op: question.getOptions()) {
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
			contentPane.add(rdbtn, "cell 0 "+ (i+3));
		}
		
		
		
		isTimeLeft = true;
		
		btnAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isCorrect;
				int i = 0;
				int diceValue = rb.getDice().getValue();
				while (i < rdbtnOptions.size() && !rdbtnOptions.get(i).isSelected()) {
					i++;
				}
				
				isCorrect = isTimeLeft && question.correctAnswer(question.getOptions().get(i).getId());
				
				rb.executeAction(rb.getActionDice().getAction(rb.getActionDice().getValue()), squarePanels, diceValue, isCorrect);
				if(isCorrect) {						
					System.out.println("Correct");
				} else {
					System.out.println("Incorrect");
				}
					
				//rb.movePlayer(player, diceValue);
				
				rb.setPlayerToAnswer((RacerPlayer) rb.getPlayers().get((rb.getActionDice().getValue() == 3)? rb.nextPlayer(): rb.getPlayerTurn()));			
				if((rb.getActionDice().getValue() != 4 || isCorrect) && rb.getSquares().get(rb.getPlayerToAnswer().getCurrentSquare()).getTag().equalsIgnoreCase("Pregunta")) {
					btnStartQuestion.setVisible(true);
					textPaneAction.setText("Casilla de Pregunta!");
					
					rb.getActionDice().setValue(4); //amarillo
					/*for(int j = 0; j < rb.getCurQuestion().getOptions().size(); j++) {
						rdbtnOptions.get(j).setVisible(false);
					}
					bg.clearSelection();*/
				} else {
					btnEndTurn.setVisible(true);
				}
					
				
				textPaneGameStatus.setText(rb.genPlayersStatus());
				Window w = SwingUtilities.getWindowAncestor(contentPane);
				w.dispose();
				
					
					
				
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
