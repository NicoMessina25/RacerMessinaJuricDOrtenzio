package Views;

import java.awt.Color;
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
import Events.StartPreGameEvent;
import Listeners.StartPreGameListener;
import Views.CustomComponents.RacerButton;
import net.miginfocom.swing.MigLayout;


public class WelcomePanel extends JFrame {

	/**
	 * 
	 */
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private StartPreGameListener sPreGameL;

	/**
	 * Create the frame.
	 */
	public WelcomePanel(RacerBoard rb) {
		//setBackground(Color.BLACK);
		//setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		//setBackground(Color.WHITE);
		setsPreGameL(rb);
		setTitle("RACER!");
		setResizable(false);
		Path path;
		path = FileSystems.getDefault().getPath("img",  "RACER_LOGO_MINI.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(path.toString()));
		//setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 593, 587);
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		path = FileSystems.getDefault().getPath("img",  "fondo.jpg");
		ImagePanel background = new ImagePanel(path.toString(), 720);
		
	


		contentPane.add(background, "cell 0 0,alignx center,growy");
		background.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		RacerButton btnStart = new RacerButton("Comenzar", rb.getPRIMARY_COLOR(), rb.getSECONDARY_COLOR());
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RacerGUI createPlayersPanel = new RacerGUI(rb);
				createPlayersPanel.setSize(1280, 720);
				sPreGameL.listenStarPreGame(new StartPreGameEvent((WelcomePanel) SwingUtilities.getWindowAncestor(contentPane), createPlayersPanel));
			}
		});
		

		
		
		JPanel titleLogo = new ImagePanel(FileSystems.getDefault().getPath("img",  "RACER_LOGO.png").toString(), 500);
		background.add(titleLogo, "cell 0 0,alignx center,growy");
		background.add(btnStart, "cell 0 1,alignx center,aligny center");
	}

	public StartPreGameListener getsPreGameL() {
		return sPreGameL;
	}

	public void setsPreGameL(StartPreGameListener sPreGameL) {
		this.sPreGameL = sPreGameL;
	}

}
