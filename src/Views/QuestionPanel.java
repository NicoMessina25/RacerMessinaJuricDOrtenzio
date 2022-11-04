package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystems;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import Controller.RacerBoard;
import Views.CustomComponents.RacerButton;
import Views.CustomComponents.RacerLabel;
import Views.CustomComponents.RacerPanel;
import Views.CustomComponents.RacerPanelCard;
import net.miginfocom.swing.MigLayout;

public class QuestionPanel extends JFrame {

	// ------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

	// ----------------------------------------->|CONSTANTS|<-----------------------------------------------\\

	private static final long serialVersionUID = 1L;

	// ----------------------------------------->|VARIABLES|<-----------------------------------------------\\

	private RacerPanel contentPane;
	private RacerLabel lblTimeLeft;
	private boolean isTimeLeft;
	private RacerButton btnAnswer;
	private Color col, bgCol;

	// ------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	/**
	 * 
	 * @param rb
	 * @param panelGridBoard
	 * @param btnStartQuestion
	 * @param btnEndTurn
	 * @param textPaneAction
	 * @param playerToAnswerPanel
	 */
	public QuestionPanel(RacerBoard rb, GridBoard panelGridBoard, JButton btnStartQuestion, JButton btnEndTurn,
			JTextPane textPaneAction, RacerPanelCard playerToAnswerPanel) {
		RacerLabel lblCategory;
		JScrollPane scrollPane;
		JTextPane textPaneQuestion;
		ButtonGroup bg;
		ArrayList<JRadioButton> rdbtnOptions;

		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		col = rb.getCurQuestion().getCategory().getColor().darker();
		bgCol = rb.getCurQuestion().getCategory().getColor().brighter();

		contentPane = new RacerPanel(new MigLayout("fill", "[][grow][]", "[][][]"), col, bgCol, col.darker());

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FileSystems.getDefault().getPath("img", "RACER_LOGO_MINI.png").toString()));

		setContentPane(contentPane);

		contentPane.add(playerToAnswerPanel, "cell 2 1");

		lblCategory = new RacerLabel(rb.getCurQuestion().getCategory().getDescription(), 42, col, bgCol);
		lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCategory, "cell 0 0 3 1,growx");

		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(col.darker(), 3));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane, "flowx,cell 0 1 2 1,grow");

		textPaneQuestion = new JTextPane();
		scrollPane.setViewportView(textPaneQuestion);
		textPaneQuestion.setText(rb.getCurQuestion().getStatement());
		textPaneQuestion.setSelectionColor(col);
		textPaneQuestion.setSelectedTextColor(bgCol);
		textPaneQuestion.setForeground(col);
		textPaneQuestion.setBackground(bgCol);
		textPaneQuestion.setFont(RacerPanel.getPrimaryFont().deriveFont(Font.ITALIC, 24));
		textPaneQuestion.setEditable(false);

		bg = new ButtonGroup();
		rdbtnOptions = new ArrayList<JRadioButton>();

		rb.genOptionButtonGroup(bg, rdbtnOptions, contentPane);

		lblTimeLeft = new RacerLabel(
				"" + rb.getCurQuestion().getOptions().size() * rb.getPlayerToAnswer().getTimePerOption(), 109, col,
				bgCol);
		lblTimeLeft.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTimeLeft, "flowx,cell 2 3 1 5,growx");

		btnAnswer = new RacerButton("Responder", col, bgCol, rb.getSounds().get("buttonSound.wav"));
		contentPane.add(btnAnswer, "cell 2 2,growx");

		btnAnswer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isCorrect;

				int i = 0;

				while (i < rdbtnOptions.size() && !rdbtnOptions.get(i).isSelected()) {
					i++;
				}

				isCorrect = isTimeLeft && i < rdbtnOptions.size()
						&& rb.getCurQuestion().correctAnswer(rb.getCurQuestion().getOptions().get(i).getId());

				rb.concludesTurnAction(isCorrect, panelGridBoard, btnStartQuestion, btnEndTurn, textPaneAction);
				SwingUtilities.getWindowAncestor(contentPane).dispose();

			}

		});

		isTimeLeft = true;
	}

	// ------------------------------------------------>||GETTERS &
	// SETTERS||<--------------------------------------------------------\\

	/**
	 * 
	 * @return
	 */
	public Color getCol() {
		return col;
	}

	/**
	 * 
	 * @param col
	 */
	public void setCol(Color col) {
		this.col = col;
	}

	/**
	 * 
	 * @return
	 */
	public Color getBgCol() {
		return bgCol;
	}

	// ------------------------------------------------>||CLASS
	// METHODS||<--------------------------------------------------------\\

	/**
	 * 
	 * @param timeLeft
	 */
	public void updateTimeLeft(int timeLeft) {
		lblTimeLeft.setText(timeLeft + "");
		isTimeLeft = timeLeft > 0;
		if (!isTimeLeft) {
			btnAnswer.doClick();
		}

	}

	/**
	 * 
	 * @param bgCol
	 */
	public void setBgCol(Color bgCol) {
		this.bgCol = bgCol;
	}

}
