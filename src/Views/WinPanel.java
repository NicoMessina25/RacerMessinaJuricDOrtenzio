package Views;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Listeners.ExitListener;
import Listeners.ResetListener;
import Views.CustomComponents.RacerButton;
import Views.CustomComponents.RacerLabel;
import Views.CustomComponents.RacerPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Controller.RacerBoard;
import Events.ExitEvent;
import Events.ResetEvent;

import javax.swing.JButton;

public class WinPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RacerPanel contentPane;
	private ResetListener resetListener;
	private ExitListener exitListener;
	

	/**
	 * Create the frame.
	 */
	public WinPanel(String winnerName, RacerBoard rb, BoardPaneGUI bp) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 376);
		contentPane = new RacerPanel(rb.getSECONDARY_COLOR(), rb.getPRIMARY_COLOR(), rb.getSECONDARY_COLOR().darker());
		contentPane.setLayout(new MigLayout("", "[][grow][]", "[][grow][][]"));
		setContentPane(contentPane);
		setResetListener(rb);
		setExitListener(rb);
		
		RacerLabel lblWinner = new RacerLabel("Ganó " + winnerName + "!!!", 70, rb.getSECONDARY_COLOR(), rb.getPRIMARY_COLOR());
		lblWinner.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblWinner, "cell 0 1 3 1,grow");
		
		RacerButton btnReset = new RacerButton("Volver a Empezar", rb.getSECONDARY_COLOR(), rb.getPRIMARY_COLOR());
		contentPane.add(btnReset, "cell 1 2,growx");
		
		RacerButton btnExit = new RacerButton("Salir", rb.getSECONDARY_COLOR(), rb.getPRIMARY_COLOR());
		contentPane.add(btnExit, "cell 1 3,growx");
		
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resetListener.listenReset(new ResetEvent((WinPanel) SwingUtilities.getWindowAncestor(contentPane), bp, new WelcomePanel(rb)));
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exitListener.listenExit(new ExitEvent((JFrame) SwingUtilities.getWindowAncestor(contentPane), bp));
			}
		});
		
	}
	
	public void setResetListener(ResetListener I) {
		resetListener = I;
	}
	
	public void setExitListener(ExitListener I) {
		exitListener = I;
	}

}
