package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Controller.RacerBoard;
import Events.CreatePlayerEvent;
import Events.StartGameEvent;
import Listeners.CreatePlayerListener;
import Listeners.StartGameListener;
import RacerModel.TeamColor;
import Views.CustomComponents.RacerButton;
import Views.CustomComponents.RacerLabel;
import Views.CustomComponents.RacerPanel;
import Views.CustomComponents.RacerPanelCard;
import net.miginfocom.swing.MigLayout;

public class PreGamePanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RacerPanel playersPane;
	private RacerPanel panelCreatedPlayers;
	private StartGameListener startGameListener;
	private CreatePlayerListener createPlayerListener;
	private final JTextField textFieldName = new JTextField();
	private ArrayList<RacerPanelCard> playersPanelCards = new ArrayList<RacerPanelCard>();
	private ArrayList<RacerButton> btnsDelete;
	private RacerButton btnConfirm;
	private RacerButton btnCreate;
	private RacerLabel lblTitlePlayer;
	private JComboBox<TeamColor> comboBoxTeams;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { RacerGUI frame = new RacerGUI();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */

	/**
	 * Create the frame.
	 */

	public PreGamePanel(RacerBoard rb) {
		setTitle("PreGame - Racer");
		setCreatePlayerListener(rb);
		setStartGameListener(rb);
		Path path;
		path = FileSystems.getDefault().getPath("img", "RACER_LOGO_MINI.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(path.toString()));

		playersPane = new RacerPanel(new MigLayout("fill", "[45.00]", "[pref!][60.00]"), rb.getTERCIARY_COLOR(),
				rb.getSECONDARY_COLOR().darker(), rb.getTERCIARY_COLOR());

		setContentPane(playersPane);
		;

		JPanel panelPlayer = new RacerPanel(new MigLayout("fill", "[][][]", "[pref!][58.00][][150.00]"),
				rb.getSECONDARY_COLOR(), rb.getSECONDARY_COLOR(), rb.getSECONDARY_COLOR().darker());
		panelPlayer.setBackground(rb.getSECONDARY_COLOR());

		playersPane.add(panelPlayer, "cell 0 0,grow");

		panelCreatedPlayers = new RacerPanel(new MigLayout("fill", "[][][][]", "[28.00][]"), null,
				rb.getSECONDARY_COLOR(), rb.getPRIMARY_COLOR().brighter());//

		playersPane.add(panelCreatedPlayers, "cell 0 1,grow");

		btnConfirm = new RacerButton("Confirmar", rb.getSECONDARY_COLOR(), rb.getPRIMARY_COLOR());
		panelCreatedPlayers.add(btnConfirm, "cell 0 1 4 1,alignx center");

		btnConfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rb.getPlayers().size() < rb.getMIN_PLAYERS()) {
					JOptionPane.showMessageDialog(null, "Ingrese los datos de todos los jugadores");
				} else {
					startGameListener.listenStartGame(new StartGameEvent(new BoardPaneGUI(rb), playersPane));
				}

			}

		});

		lblTitlePlayer = new RacerLabel("Jugador " + (rb.getLastId() + 1), 23, rb.getPRIMARY_COLOR(),
				rb.getSECONDARY_COLOR());
		lblTitlePlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitlePlayer.setFont(new Font(RacerBoard.getPRIMARY_FONT_FAMILY(), Font.BOLD | Font.ITALIC, 23));
		lblTitlePlayer.setForeground(rb.getPRIMARY_COLOR());
		lblTitlePlayer.setBackground(rb.getSECONDARY_COLOR());
		panelPlayer.add(lblTitlePlayer, "cell 0 0 2 1,growx");

		JPanel panelTeamLogo = new RacerPanel(null, rb.getSECONDARY_COLOR(), null);
		panelTeamLogo.setBackground(rb.getSECONDARY_COLOR());
		panelTeamLogo.setBounds(11, 161, 178, 123);
		panelPlayer.add(panelTeamLogo, "cell 2 0 1 4,grow");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 597, 560);

		// path = FileSystems.getDefault().getPath("img", "logo"+
		// rb.getTeamColors().get(2).getTeamId() + "F1.jpg");
		ImagePanel imgPanel = new ImagePanel(path.toString(), 200);
		imgPanel.setBorder(new LineBorder(rb.getPRIMARY_COLOR().brighter(), 6));
		panelTeamLogo.add(imgPanel);

		RacerLabel lblName = new RacerLabel("Nombre", 19, rb.getPRIMARY_COLOR(), rb.getSECONDARY_COLOR());
		// lblName.setBounds(11, 24, 69, 14);
		panelPlayer.add(lblName, "flowx,cell 0 1,growx");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);

		RacerLabel lblTeams = new RacerLabel("Equipo F1", 19, rb.getPRIMARY_COLOR(), rb.getSECONDARY_COLOR());
		lblTeams.setHorizontalAlignment(SwingConstants.CENTER);
		// lblTeams.setBounds(230, 22, 85, 14);
		panelPlayer.add(lblTeams, "flowx,cell 1 1,growx");
		textFieldName.setSelectionColor(rb.getPRIMARY_COLOR().brighter());
		textFieldName.setSelectedTextColor(rb.getSECONDARY_COLOR().darker());
		textFieldName.setForeground(rb.getTERCIARY_COLOR());
		textFieldName.setFont(new Font(RacerBoard.getPRIMARY_FONT_FAMILY(), Font.ITALIC, 14));
		textFieldName.setBackground(rb.getSECONDARY_COLOR().brighter());
		textFieldName.setMinimumSize(new Dimension(70, 20));
		// textFieldName.setBounds(111, 21, 86, 20);
		panelPlayer.add(textFieldName, "flowx,cell 0 2,growx,aligny top");
		textFieldName.setColumns(10);

		comboBoxTeams = new JComboBox<TeamColor>();
		comboBoxTeams.setMaximumRowCount(10);
		comboBoxTeams.setForeground(rb.getTERCIARY_COLOR());
		comboBoxTeams.setBackground(rb.getSECONDARY_COLOR().brighter());
		comboBoxTeams.setFont(new Font(RacerBoard.getPRIMARY_FONT_FAMILY(), Font.ITALIC, 14));
		comboBoxTeams.setMaximumSize(new Dimension(32767, 20));

		// comboBoxTeams.setBounds(207, 39, 172, 22);
		panelPlayer.add(comboBoxTeams, "cell 1 2,growx,aligny top");

		comboBoxTeams.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// panelTeamColor.setBackground(((TeamColor) e.getItem()).getCol());
				Path path = FileSystems.getDefault().getPath("img",
						"logo" + ((TeamColor) e.getItem()).getTeamId() + "F1.jpg");
				imgPanel.setImg(new ImageIcon(path.toString()).getImage());
				// panelTeamLogo.setSize(panelTeamLogo.getWidth(), panelTeamLogo.getHeight() +
				// 2);
				panelTeamLogo.repaint();
				/*
				 * panelTeamLogo.remove(imgPanel); ImagePanel imgPanel = new
				 * ImagePanel(path.toString()); panelTeamLogo.add(imgPanel);
				 */

			}

		});

		JCheckBox chckbxExpert = new JCheckBox("Experto");
		chckbxExpert.setFocusable(false);
		chckbxExpert.setForeground(rb.getPRIMARY_COLOR());
		chckbxExpert.setFont(new Font(RacerBoard.getPRIMARY_FONT_FAMILY(), Font.ITALIC, 14));
		chckbxExpert.setBackground(rb.getSECONDARY_COLOR());
		chckbxExpert.setBounds(11, 116, 97, 23);
		panelPlayer.add(chckbxExpert, "flowx,cell 0 3,alignx center,aligny top");

		btnCreate = new RacerButton("Crear", rb.getSECONDARY_COLOR(), rb.getPRIMARY_COLOR());
		panelPlayer.add(btnCreate, "cell 1 3,growx,aligny top");
		btnCreate.setBounds(90, 389, 89, 23);

		btnsDelete = new ArrayList<RacerButton>();

		for (int i = 0; i < rb.getMAX_PLAYERS(); i++) {
			btnsDelete.add(new RacerButton("Borrar", Color.BLACK, Color.WHITE));
		}
		// rb.setDelButtonsActionListeners(btnsDelete);

		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (rb.validateFields(textFieldName.getText(), rb.getLastId())) {

					createPlayerListener.listenCreate(new CreatePlayerEvent(textFieldName.getText().trim(),
							comboBoxTeams.getSelectedItem(), chckbxExpert.isSelected()));
					// textPanePlayersCreated.setText(rb.genPlayersStatus());
					textFieldName.setText("");
					chckbxExpert.setSelected(false);
					comboBoxTeams.setSelectedIndex(0);
					if (rb.getLastId() < rb.getMAX_PLAYERS()) {
						lblTitlePlayer.setText("Jugador " + (rb.getLastId() + 1));
					} else {
						lblTitlePlayer.setText("¿Listos?");
						btnCreate.setEnabled(false);
					}

					// rb.updateDelButton(textPanePlayersCreated, btnsDelete, comboBoxTeams,
					// lblTitlePlayer);

					rb.updateComboBox(comboBoxTeams);
					// panelCreatedPlayers.add(new RacerPanelCard(, getBackground(),
					// getForeground()))

				} else {
					JOptionPane.showMessageDialog(null,
							"Ingrese un nombre (sin repetir) y que no supere los 20 caracteres.");

				}

			}
		});

		for (Iterator<TeamColor> iterator = rb.getTeamColors().iterator(); iterator.hasNext();) {
			TeamColor tc = iterator.next();
			comboBoxTeams.addItem(tc);

		}

	}

	public void addPlayersPanelCards() {
		RacerPanel createdPlayersPanel = getCreatedPlayersPanel();
		createdPlayersPanel.removeAll();
		for (int i = 0; i < playersPanelCards.size(); i++) {
			createdPlayersPanel.add(playersPanelCards.get(i), "cell " + i + " 0, center");

			// playersPanelCards.get(i).addDeleteButton(btnsDelete.get(i));
		}
		createdPlayersPanel.add(btnConfirm, "cell 0 1 4 1,alignx center");
		createdPlayersPanel.repaint();
		btnCreate.setEnabled(true);
		lblTitlePlayer.setText("Jugador " + (playersPanelCards.size() + 1));
	}

	public RacerButton getBtnCreate() {
		return btnCreate;
	}

	/*
	 * public void createRacerPanelCard(String name, String teamName, String teamId,
	 * Color teamColor, boolean expert, int lastId, RacerBoard rb) { RacerPanelCard
	 * rpc = new RacerPanelCard(teamColor.darker(), teamColor.brighter(), teamColor,
	 * name, teamName, teamId, expert); rpc.setDelPlayerListener(rb);
	 * panelCreatedPlayers.add(rpc, "cell " + lastId + " 0"); }
	 */

	public ArrayList<RacerButton> getBtnsDelete() {
		return btnsDelete;
	}

	/*
	 * public void setCreatedPlayersPanel(RacerPanel panelCreatedPlayers) {
	 * playersPane.remove(getCreatedPlayersPanel());
	 * panelCreatedPlayers.add(btnConfirm, "cell 0 1 8 1,alignx center");
	 * 
	 * playersPane.add(panelCreatedPlayers, "cell 0 1,grow");
	 * this.panelCreatedPlayers = panelCreatedPlayers;
	 * 
	 * 
	 * 
	 * 
	 * }
	 */

	public JComboBox<TeamColor> getComboBoxTeams() {
		return comboBoxTeams;
	}

	public RacerPanel getCreatedPlayersPanel() {
		return panelCreatedPlayers;
	}

	public ArrayList<RacerPanelCard> getPlayersPanelCards() {
		return playersPanelCards;
	}

	public void setBtnCreate(RacerButton btnCreate) {
		this.btnCreate = btnCreate;
	}

	public void setBtnsDelete(ArrayList<RacerButton> btnsDelete) {
		this.btnsDelete = btnsDelete;
	}

	public void setComboBoxTeams(JComboBox<TeamColor> comboBoxTeams) {
		this.comboBoxTeams = comboBoxTeams;
	}

	public void setCreatePlayerListener(CreatePlayerListener I) {
		createPlayerListener = I;
	}

	public void setPlayersPanelCards(ArrayList<RacerPanelCard> playersPanelCards) {
		this.playersPanelCards = playersPanelCards;
	}

	public void setStartGameListener(StartGameListener I) {
		startGameListener = I;
	}
}
