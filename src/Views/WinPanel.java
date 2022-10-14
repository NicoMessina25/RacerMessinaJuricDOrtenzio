package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Controller.RacerBoard;
import Events.ExitEvent;
import Events.ResetEvent;
import Listeners.ExitListener;
import Listeners.ResetListener;
import Views.CustomComponents.RacerButton;
import Views.CustomComponents.RacerLabel;
import Views.CustomComponents.RacerPanel;
import net.miginfocom.swing.MigLayout;

public class WinPanel extends JFrame {

	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
			//----------------------------------------->|CONSTANTS|<-----------------------------------------------\\
	
	private static final long serialVersionUID = 1L;
	
			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\

	private RacerPanel contentPane;
	private ResetListener resetListener;
	private ExitListener exitListener;

	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	public WinPanel(String winnerName, RacerBoard rb, BoardPaneGUI bp) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 376);
		contentPane = new RacerPanel(RacerPanel.getSecondaryColor(), RacerPanel.getPrimaryColor(),
				RacerPanel.getSecondaryColor().darker());
		contentPane.setLayout(new MigLayout("", "[][grow][]", "[][grow][][]"));
		setContentPane(contentPane);
		setResetListener(rb);
		setExitListener(rb);

		RacerLabel lblWinner = new RacerLabel("Ganó " + winnerName + "!!!", 70, RacerPanel.getSecondaryColor(),
				RacerPanel.getPrimaryColor());
		lblWinner.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblWinner, "cell 0 1 3 1,grow");

		RacerButton btnReset = new RacerButton("Volver a Empezar", RacerPanel.getSecondaryColor(),
				RacerPanel.getPrimaryColor(), rb.getSounds().get("buttonSound.wav"));
		contentPane.add(btnReset, "cell 1 2,growx");

		RacerButton btnExit = new RacerButton("Salir", RacerPanel.getSecondaryColor(), RacerPanel.getPrimaryColor(),
				rb.getSounds().get("buttonSound.wav"));
		contentPane.add(btnExit, "cell 1 3,growx");

		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				resetListener.listenReset(new ResetEvent((WinPanel) SwingUtilities.getWindowAncestor(contentPane), bp,
						new WelcomePanel(rb)));
			}
		});

		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exitListener.listenExit(new ExitEvent((JFrame) SwingUtilities.getWindowAncestor(contentPane)));
				exitListener.listenExit(new ExitEvent(bp));
			}
		});

	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	public void setResetListener(ResetListener I) {
		resetListener = I;
	}

	public void setExitListener(ExitListener I) {
		exitListener = I;
	}

}
