package com.racer.views;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.racer.controller.RacerBoard;

import com.racer.events.ExitEvent;
import com.racer.events.StartPreGameEvent;
import com.racer.listeners.ExitListener;
import com.racer.listeners.StartPreGameListener;
import com.racer.views.CustomComponents.RacerButton;
import com.racer.views.CustomComponents.RacerIcon;
import com.racer.views.CustomComponents.RacerPanel;

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
	
	/**
	 * 
	 * @param rb
	 */
	public WelcomePanel(RacerBoard rb) {
		SoundClip music = rb.getSounds().get("introMusic.wav");
		music.loop();
		setsPreGameL(rb);
		setExitListener(rb);
		setTitle("RACER!");
		Path path;
		path = FileSystems.getDefault().getPath("img", "RACER_LOGO_MINI.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(path.toString()));
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		path = FileSystems.getDefault().getPath("img", "fondo.jpg");
		ImagePanel background = new ImagePanel(path.toString(), 720);

		contentPane.add(background, "cell 0 0,alignx center,growy");
		background.setLayout(new MigLayout("", "[grow]", "[][][]"));
		
		RacerIcon iconLogo = new RacerIcon(new ImageIcon(FileSystems.getDefault().getPath("img","RACER_LOGO.png").toString()), 500);
		//iconLogo.setIcon();

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
		
		RacerButton btnExit = new RacerButton("Salir al Escritorio", RacerPanel.getSecondaryColor(), RacerPanel.getPrimaryColor(), rb.getSounds().get("buttonSound.wav"));
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exitListener.listenExit(new ExitEvent((JFrame) SwingUtilities.getWindowAncestor(contentPane)));
				
			}
			
		});
		
		background.add(iconLogo, "cell 0 0, center");
		background.add(btnStart, "cell 0 1,alignx center,aligny center");
		background.add(btnExit, "cell 0 2,alignx center,aligny center");
		
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	/**
	 * 
	 * @return
	 */
	public StartPreGameListener getsPreGameL() {
		return sPreGameL;
	}

	/**
	 * 
	 * @param sPreGameL
	 */
	public void setsPreGameL(StartPreGameListener sPreGameL) {
		this.sPreGameL = sPreGameL;
	}

	/**
	 * 
	 * @return
	 */
	public ExitListener getExitListener() {
		return exitListener;
	}

	/**
	 * 
	 * @param exitListener
	 */
	public void setExitListener(ExitListener exitListener) {
		this.exitListener = exitListener;
	}

}
