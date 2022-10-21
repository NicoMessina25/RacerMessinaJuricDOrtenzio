package Views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystems;

import javax.swing.ImageIcon;
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
	
	public WinPanel(String winnerName, String teamId, RacerBoard rb, BoardPaneGUI bp) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new RacerPanel(RacerPanel.getSecondaryColor(), RacerPanel.getPrimaryColor(),
				RacerPanel.getSecondaryColor().darker());
		contentPane.setLayout(new MigLayout("fill", "[grow]", "[grow]"));
		setContentPane(contentPane);
		setResetListener(rb);
		setExitListener(rb);
		//inicio musica
		SoundClip music = rb.getSounds().get("WinMusic.wav");
		music.loop();
		
		RacerLabel lblBackground = new RacerLabel();
		lblBackground.setIcon(new ImageIcon(FileSystems.getDefault().getPath("img", "winnerPanel.gif").toString()), 500);
		lblBackground.setLayout(new MigLayout("", "[][grow][]", "[][grow][][][]"));
		
		
		contentPane.add(lblBackground, "cell 0 0, center");
		
		RacerLabel lblWinner = new RacerLabel(winnerName, 100, RacerPanel.getSecondaryColor(),
				RacerPanel.getPrimaryColor());
		lblWinner.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackground.add(lblWinner, "cell 0 1 3 1,grow");
		
		RacerLabel lblWinnerCar = new RacerLabel();
		lblWinnerCar.setIcon(new ImageIcon(FileSystems.getDefault().getPath("img/f1Cars", "car" + teamId + ".png").toString()), 100);
		
		lblBackground.add(lblWinnerCar, "cell 1 2, center");

		RacerButton btnReset = new RacerButton("Volver a Empezar", RacerPanel.getSecondaryColor(),
				RacerPanel.getPrimaryColor(), rb.getSounds().get("buttonSound.wav"));
		lblBackground.add(btnReset, "cell 1 3,growx");

		RacerButton btnExit = new RacerButton("Salir al Escritorio", RacerPanel.getSecondaryColor(), RacerPanel.getPrimaryColor(),
				rb.getSounds().get("buttonSound.wav"));
		lblBackground.add(btnExit, "cell 1 4,growx");
		
		setSize(new Dimension(1280, 720));

		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resetListener.listenReset(new ResetEvent((WinPanel) SwingUtilities.getWindowAncestor(contentPane)));
				exitListener.listenExit(new ExitEvent(bp));
				music.stop();
			}
		});

		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exitListener.listenExit(new ExitEvent((JFrame) SwingUtilities.getWindowAncestor(contentPane)));
				exitListener.listenExit(new ExitEvent(bp));
				music.stop();
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
