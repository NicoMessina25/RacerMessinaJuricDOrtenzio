package Views.CustomComponents;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystems;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Events.DeletePlayerEvent;
import Listeners.DeletePlayerListener;
import Views.ImagePanel;
import Views.SoundClip;
import net.miginfocom.swing.MigLayout;

public class RacerPanelCard extends RacerPanel {

	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
			//----------------------------------------->|CONSTANTS|<-----------------------------------------------\\
	
	private static final long serialVersionUID = -4929183436574942390L;
	
			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	private RacerLabel lblName, lblTeam, lblExpert;
	private ImagePanel teamLogoPanel;
	private int playerId;
	private RacerButton btnDel;
	private DeletePlayerListener delPlayerListener;
	private Timer playerTurnAnimation;
	private Color col, bgCol;
	private String teamId;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	/**
	 * 
	 * @param col
	 * @param bgCol
	 * @param borderCol
	 * @param name
	 * @param teamName
	 * @param teamId
	 * @param playerId
	 * @param type
	 * @param buttonSound
	 */
	public RacerPanelCard(Color col, Color bgCol, Color borderCol, String name, String teamName, String teamId,
			int playerId, String type, SoundClip buttonSound) {
		super(col, bgCol, borderCol);
		this.col = col;
		this.bgCol = bgCol;
		this.setTeamId(teamId);
		setLayout(new MigLayout("", "[217.00]", "[][][]"));
		this.playerId = playerId;
		btnDel = new RacerButton("Borrar", col.darker(), bgCol.brighter(), buttonSound);

		lblName = new RacerLabel(name, 20, col, bgCol);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblName, "cell 0 0,growx");

		teamLogoPanel = new ImagePanel(
				new ImageIcon((FileSystems.getDefault().getPath("img", "logo" + teamId + "F1.jpg").toString()))
						.getImage(),
				100);
	
		add(teamLogoPanel, "cell 0 1,alignx center");

		lblTeam = new RacerLabel(teamName, 16, col, bgCol);
		lblTeam.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTeam, "cell 0 2,growx");

		lblExpert = new RacerLabel(type, 16, col, bgCol);
		lblExpert.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeam.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblExpert, "cell 0 3,growx");

		playerTurnAnimation = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				invertColors();

			}
		});

	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	/**
	 * 
	 * @return
	 */
	public DeletePlayerListener getDelPlayerListener() {
		return delPlayerListener;
	}

	/**
	 * 
	 * @param delPlayerListener
	 */
	public void setDelPlayerListener(DeletePlayerListener delPlayerListener) {
		this.delPlayerListener = delPlayerListener;
	}

	/**
	 * 
	 * @return
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * 
	 * @param playerId
	 */
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	/**
	 * 
	 * @return
	 */
	public RacerButton getBtnDel() {
		return btnDel;
	}

	/**
	 * 
	 * @param btnDel
	 */
	public void setBtnDel(RacerButton btnDel) {
		this.btnDel = btnDel;
	}

	/**
	 * 
	 * @return
	 */
	public RacerLabel getLblName() {
		return lblName;
	}

	/**
	 * 
	 * @param lblName
	 */
	public void setLblName(RacerLabel lblName) {
		this.lblName = lblName;
	}

	/**
	 * 
	 * @return
	 */
	public RacerLabel getLblTeam() {
		return lblTeam;
	}

	/**
	 * 
	 * @param lblTeam
	 */
	public void setLblTeam(RacerLabel lblTeam) {
		this.lblTeam = lblTeam;
	}

	/**
	 * 
	 * @return
	 */
	public RacerLabel getLblExpert() {
		return lblExpert;
	}

	/**
	 * 
	 * @param lblExpert
	 */
	public void setLblExpert(RacerLabel lblExpert) {
		this.lblExpert = lblExpert;
	}

	/**
	 * 
	 * @return
	 */
	public ImagePanel getTeamLogoPanel() {
		return teamLogoPanel;
	}

	/**
	 * 
	 * @param teamLogoPanel
	 */
	public void setTeamLogoPanel(ImagePanel teamLogoPanel) {
		this.teamLogoPanel = teamLogoPanel;
	}

	/**
	 * 
	 * @return
	 */
	public String getTeamId() {
		return teamId;
	}

	/**
	 * 
	 * @param teamId
	 */
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\

	/**
	 * 
	 * @param btnDel
	 */
	public void addBtnDel(RacerButton btnDel) {
		add(btnDel, "cell 0 4,alignx center");

		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				delPlayerListener.listenDeletePlayer(new DeletePlayerEvent(getPlayerId()));

			}
		});
	}

	/**
	 * 
	 */
	public void invertColors() {
		Color col1 = super.getForeground();
		Color col2 = super.getBackground();
		super.setForeground(col2);
		super.setBackground(col1);
		lblName.setForeground(col2);
		lblExpert.setForeground(col2);
		lblTeam.setForeground(col2);

	}

	/**
	 * 
	 */
	public void startTurnAnimation() {
		playerTurnAnimation.start();
	}

	/**
	 * 
	 */
	public void stopTurnAnimation() {
		playerTurnAnimation.stop();
		super.setBackground(bgCol);
		super.setForeground(col);
		lblName.setForeground(col);
		lblExpert.setForeground(col);
		lblTeam.setForeground(col);
	}

}
