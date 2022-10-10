package Views;


import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import Controller.RacerBoard;
import Views.CustomComponents.RacerButton;
import Views.CustomComponents.RacerLabel;
import Views.CustomComponents.RacerPanel;
import Views.CustomComponents.RacerPanelCard;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class QuestionPanel extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RacerPanel contentPane;
	private RacerLabel lblTimeLeft;
	private boolean isTimeLeft;
	private RacerButton btnAnswer;
	private Color col, bgCol;

	/**
	 * Create the frame.
	 */
	public QuestionPanel(RacerBoard rb, ArrayList<JPanel> squarePanels, JButton btnStartQuestion, JButton btnEndTurn, JTextPane textPaneAction, RacerPanelCard playerToAnswerPanel) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		col = rb.getCurQuestion().getCategory().getColor().darker();
		bgCol = rb.getCurQuestion().getCategory().getColor().brighter();
		
		contentPane = new RacerPanel(new MigLayout("fill", "[][grow][]", "[][][]"), col, bgCol, col.darker());
		//contentPane.setSize(800, 500);
		Path path;
		path = FileSystems.getDefault().getPath("img",  "RACER_LOGO_MINI.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(path.toString()));
		
		
				
		setContentPane(contentPane);
		
		//JLabel lblPlayerToAnswer = new JLabel("Responde: " + rb.getPlayerToAnswer().getName());
		//contentPane.add(lblPlayerToAnswer, "cell 0 0");
		contentPane.add(playerToAnswerPanel, "cell 2 1");
		
		RacerLabel lblCategory = new RacerLabel(rb.getCurQuestion().getCategory().getDescription(), 42, col, bgCol);
		lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCategory, "cell 0 0 3 1,growx");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(col.darker(), 3));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane, "flowx,cell 0 1 2 1,grow");
		
		JTextPane textPaneQuestion = new JTextPane();
		scrollPane.setViewportView(textPaneQuestion);
		textPaneQuestion.setText(rb.getCurQuestion().getStatement());
		textPaneQuestion.setSelectionColor(col);
		textPaneQuestion.setSelectedTextColor(bgCol);
		textPaneQuestion.setForeground(rb.getTERCIARY_COLOR());
		textPaneQuestion.setBackground(bgCol);
		textPaneQuestion.setFont(new Font(RacerBoard.getPRIMARY_FONT_FAMILY(), Font.ITALIC, 24));
		textPaneQuestion.setEditable(false);
		
		
		
		ButtonGroup bg = new ButtonGroup();
		ArrayList<JRadioButton> rdbtnOptions = new ArrayList<JRadioButton>();
		
		rb.genOptionButtonGroup(bg, rdbtnOptions, contentPane);
		
		lblTimeLeft = new RacerLabel("" + rb.getCurQuestion().getOptions().size()*rb.getPlayerToAnswer().getTimePerOption(), 109, col,  bgCol);
		lblTimeLeft.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTimeLeft, "flowx,cell 2 3 1 5,growx");
		
		btnAnswer = new RacerButton("Responder", col, bgCol);
		contentPane.add(btnAnswer, "cell 2 2,growx");
		
		btnAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isCorrect;
				int i = 0;
				
				while (i < rdbtnOptions.size() && !rdbtnOptions.get(i).isSelected()) {
					i++;
				}
				
				isCorrect = isTimeLeft &&  i < rdbtnOptions.size() && rb.getCurQuestion().correctAnswer(rb.getCurQuestion().getOptions().get(i).getId());
				
				rb.concludesTurnAction(isCorrect, squarePanels, btnStartQuestion, btnEndTurn, textPaneAction);
				
				/*rb.executeAction(rb.getActionDice().getAction(rb.getActionDice().getValue()), squarePanels, diceValue, isCorrect);
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
				} else {
					btnEndTurn.setVisible(true);
				}*/
					
				
				//textPaneGameStatus.setText(rb.genPlayersStatus());
				Window w = SwingUtilities.getWindowAncestor(contentPane);
				w.dispose();
				
					
					
				
			}
			
		});
		
		
		/*for(Option op: rb.getCurQuestion().getOptions()) {
			op.setSortNum((int) Math.ceil(Math.random()*9));
		}
		Collections.sort(rb.getCurQuestion().getOptions(), new Comparator<Option>() {

			@Override
			public int compare(Option o1, Option o2) {
				return o1.getSortNum() - o2.getSortNum();
			}
			
		});
		
		for(Option op : rb.getCurQuestion().getOptions()) {
			int i = rb.getCurQuestion().getOptions().indexOf(op);
			JRadioButton rdbtn = new JRadioButton(op.getDescripcion());
			bg.add(rdbtn);
			rdbtnOptions.add(rdbtn);
			contentPane.add(rdbtn, "cell 0 "+ (i+3));
		}*/
		
		
		
		isTimeLeft = true;
	}
	
	public void updateTimeLeft(int timeLeft) {
		lblTimeLeft.setText(timeLeft + "");
		isTimeLeft = timeLeft > 0;
		if(!isTimeLeft) {
			btnAnswer.doClick();
		}
		
	}

	public Color getCol() {
		return col;
	}

	public void setCol(Color col) {
		this.col = col;
	}

	public Color getBgCol() {
		return bgCol;
	}

	public void setBgCol(Color bgCol) {
		this.bgCol = bgCol;
	}

}
