package Views;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Listeners.ExitListener;
import Listeners.ResetListener;
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
	private JPanel contentPane;
	private ResetListener resetListener;
	private ExitListener exitListener;
	

	/**
	 * Create the frame.
	 */
	public WinPanel(String winnerName, RacerBoard rb, BoardPaneGUI bp) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 167);
		contentPane = new JPanel();
		contentPane.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][]", "[][][][][]"));
		setContentPane(contentPane);
		setResetListener(rb);
		setExitListener(rb);
		
		JLabel lblWinner = new JLabel("Ganó " + winnerName + "!!!");
		lblWinner.setHorizontalAlignment(SwingConstants.CENTER);
		lblWinner.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		contentPane.add(lblWinner, "cell 6 1 3 1,grow");
		
		JButton btnReset = new JButton("Volver a Empezar");
		contentPane.add(btnReset, "cell 6 3 3 1,growx");
		
		JButton btnExit = new JButton("Salir");
		contentPane.add(btnExit, "cell 6 4 3 1,growx");
		
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resetListener.listenReset(new ResetEvent((WinPanel) SwingUtilities.getWindowAncestor(contentPane), bp, new WelcomePanel(rb)));
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exitListener.listenExit(new ExitEvent((JFrame) SwingUtilities.getWindowAncestor(contentPane)));
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
