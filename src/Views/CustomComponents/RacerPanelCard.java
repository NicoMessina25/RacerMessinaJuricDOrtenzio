package Views.CustomComponents;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystems;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Controller.RacerBoard;
import Events.DeletePlayerEvent;
import Listeners.DeletePlayerListener;
import RacerModel.TeamColor;
import Views.ImagePanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.JPanel;

public class RacerPanelCard extends RacerPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4929183436574942390L;
	private RacerLabel lblName, lblTeam, lblExpert;
	private ImagePanel teamLogoPanel;
	private int playerId;
	private RacerButton btnDel;
	private DeletePlayerListener delPlayerListener;
	private Timer playerTurnAnimation;
	private Color col, bgCol;
	private String teamId;
	//private boolean toggleBgCol = true;
	

	public RacerPanelCard(Color col, Color bgCol, Color borderCol, String name, String teamName, String teamId, int playerId, String type) {
		super(col, bgCol, borderCol);
		this.col = col;
		this.bgCol = bgCol;
		this.setTeamId(teamId);
		setLayout(new MigLayout("", "[217.00]", "[][][]"));
		this.playerId = playerId;
		btnDel = new RacerButton("Borrar", col.darker(), bgCol.brighter());
		
		lblName = new RacerLabel(name, 20, col, bgCol);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		//lblName.setBounds(86, 24, 46, 14);
		add(lblName, "cell 0 0,growx");
		
		teamLogoPanel = new ImagePanel(new ImageIcon((FileSystems.getDefault().getPath("img",  "logo"+ teamId + "F1.jpg").toString())).getImage(), 100);
		//teamLogoPanel.setBounds(58, 49, 110, 70);
		add(teamLogoPanel, "cell 0 1,alignx center");
		
		lblTeam = new RacerLabel(teamName, 16, col, bgCol);
		lblTeam.setHorizontalAlignment(SwingConstants.CENTER);
		//lblTeam.setBounds(86, 130, 46, 14);
		add(lblTeam, "cell 0 2,growx");
		
		lblExpert = new RacerLabel(type, 18, col, bgCol);
		lblExpert.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeam.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblExpert, "cell 0 3,growx");
		
		
	
		
		
		/*btnDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				delPlayerListener.listenDeletePlayer(new DeletePlayerEvent(playerId));
				
			}
		});*/
		
		playerTurnAnimation = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//RacerPanelCard playerTurnCard = playersPanelCards.get(rb.getPlayerTurn());
				//toggleCardColor = !toggleCardColor;
				//playerTurnCard.setBackground((toggleCardColor)? playerTurnCard.getBackground():playerTurnCard.getBackground());
				invertColors();
				
			}
		});
	
		
	}



	/*public void addDeleteButton(RacerButton delBtn) {
		delBtn.setForeground(getForeground().darker());
		delBtn.setBackground(getBackground().brighter());
		this.add(delBtn, "cell 0 4,alignx center");
		
		if(delBtn.getActionListeners().length > 0) {
			delBtn.removeActionListener(delBtn.getActionListeners()[0]);
		}
		
		delBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				delPlayerListener.listenDeletePlayer(new DeletePlayerEvent(playerId));
				//System.out.println(playerId);
			}
		});
	}*/



	public DeletePlayerListener getDelPlayerListener() {
		return delPlayerListener;
	}



	public void setDelPlayerListener(DeletePlayerListener delPlayerListener) {
		this.delPlayerListener = delPlayerListener;
	}



	public int getPlayerId() {
		return playerId;
	}



	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
	public RacerButton getBtnDel() {
		return btnDel;
	}



	public void setBtnDel(RacerButton btnDel) {
		this.btnDel = btnDel;
	}



	public RacerLabel getLblName() {
		return lblName;
	}



	public void setLblName(RacerLabel lblName) {
		this.lblName = lblName;
	}



	public RacerLabel getLblTeam() {
		return lblTeam;
	}



	public void setLblTeam(RacerLabel lblTeam) {
		this.lblTeam = lblTeam;
	}



	public RacerLabel getLblExpert() {
		return lblExpert;
	}



	public void setLblExpert(RacerLabel lblExpert) {
		this.lblExpert = lblExpert;
	}



	public ImagePanel getTeamLogoPanel() {
		return teamLogoPanel;
	}



	public void setTeamLogoPanel(ImagePanel teamLogoPanel) {
		this.teamLogoPanel = teamLogoPanel;
	}



	public String getTeamId() {
		return teamId;
	}



	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}



	public void addBtnDel(RacerButton btnDel) {
		add(btnDel, "cell 0 4,alignx center");
		
		btnDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				delPlayerListener.listenDeletePlayer(new DeletePlayerEvent(getPlayerId()));
				
			}
		});
	}
	
	public void invertColors() {
		Color col1 = super.getForeground();
		Color col2 = super.getBackground();
		super.setForeground(col2);
		super.setBackground(col1);
		lblName.setForeground(col2);
		lblExpert.setForeground(col2);
		lblTeam.setForeground(col2);
		
	}
	
	public void startTurnAnimation() {
		playerTurnAnimation.start();
	}
	
	public void stopTurnAnimation() {
		playerTurnAnimation.stop();
		super.setBackground(bgCol);
		super.setForeground(col);
		lblName.setForeground(col);
		lblExpert.setForeground(col);
		lblTeam.setForeground(col);
	}
	
}
