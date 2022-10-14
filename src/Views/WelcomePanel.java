package Views;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Controller.RacerBoard;
import Events.ExitEvent;
import Events.StartPreGameEvent;
import Listeners.ExitListener;
import Listeners.StartPreGameListener;
import Views.CustomComponents.RacerButton;
import Views.CustomComponents.RacerPanel;
import net.miginfocom.swing.MigLayout;

public class WelcomePanel extends JFrame {

	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
			//----------------------------------------->|CONSTANTS|<-----------------------------------------------\\
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	private static final long serialVersionUID = 1L;
	
			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\

	
	private JPanel contentPane;
	private StartPreGameListener sPreGameL;
	private ExitListener exitListener;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	public WelcomePanel(RacerBoard rb) {
		SoundClip music = rb.getSounds().get("introMusic.wav");
		music.loop();
		setsPreGameL(rb);
		setExitListener(rb);
		setTitle("RACER!");
		setResizable(false);
		Path path;
		path = FileSystems.getDefault().getPath("img", "RACER_LOGO_MINI.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(path.toString()));
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		path = FileSystems.getDefault().getPath("img", "Start.jpg");
		ImagePanel background = new ImagePanel(path.toString(), 720);

		contentPane.add(background, "cell 0 0,alignx center,growy");
		background.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));

		RacerButton btnStart = new RacerButton("Comenzar", RacerPanel.getSecondaryColor(), RacerPanel.getPrimaryColor(), rb.getSounds().get("buttonSound.wav"));

		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				music.stop();
				PreGamePanel createPlayersPanel = new PreGamePanel(rb);
				createPlayersPanel.setSize(1280, 720);
				sPreGameL.listenStarPreGame(new StartPreGameEvent(
						(WelcomePanel) SwingUtilities.getWindowAncestor(contentPane), createPlayersPanel));

				rb.getSounds().get("engineSound.wav").play();


			}
		});
		
		RacerButton btnExit = new RacerButton("Salir", RacerPanel.getSecondaryColor(), RacerPanel.getPrimaryColor(), rb.getSounds().get("buttonSound.wav"));
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exitListener.listenExit(new ExitEvent((JFrame) SwingUtilities.getWindowAncestor(contentPane)));
				
			}
			
		});
		
		background.add(btnStart, "cell 0 1,alignx center,aligny center");
		background.add(btnExit, "cell 0 2,alignx center,aligny center");
		
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	public StartPreGameListener getsPreGameL() {
		return sPreGameL;
	}

	public void setsPreGameL(StartPreGameListener sPreGameL) {
		this.sPreGameL = sPreGameL;
	}

	public ExitListener getExitListener() {
		return exitListener;
	}

	public void setExitListener(ExitListener exitListener) {
		this.exitListener = exitListener;
	}

}
