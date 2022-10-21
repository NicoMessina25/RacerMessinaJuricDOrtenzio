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
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import Controller.RacerBoard;
import Events.CreatePlayerEvent;
import Events.ExitEvent;
import Events.ResetEvent;
import Events.StartGameEvent;
import Listeners.CreatePlayerListener;
import Listeners.ExitListener;
import Listeners.ResetListener;
import Listeners.StartGameListener;
import RacerModel.Team;
import Views.CustomComponents.RacerButton;
import Views.CustomComponents.RacerLabel;
import Views.CustomComponents.RacerPanel;
import Views.CustomComponents.RacerPanelCard;
import net.miginfocom.swing.MigLayout;

public class PreGamePanel extends JFrame {

	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
			//----------------------------------------->|CONSTANTS|<-----------------------------------------------\\
	
	private static final long serialVersionUID = 1L;
		
			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	private RacerPanel playersPane;
	private RacerPanel panelCreatedPlayers;
	private StartGameListener startGameListener;
	private CreatePlayerListener createPlayerListener;
	private final JTextField textFieldName = new JTextField();
	private ArrayList<RacerPanelCard> playersPanelCards = new ArrayList<RacerPanelCard>();
	private ArrayList<RacerButton> btnsDelete;
	private RacerButton btnConfirm;
	private RacerButton btnCreate;
	private RacerButton btnExit;
	private RacerLabel lblTitlePlayer;
	private ResetListener resetListener;
	private JComboBox<Team> comboBoxTeams;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	public PreGamePanel(RacerBoard rb) {
		setTitle("PreGame - Racer");
		setCreatePlayerListener(rb);
		setStartGameListener(rb);
		setResetListener(rb);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Path path;
		path = FileSystems.getDefault().getPath("img", "RACER_LOGO_MINI.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(path.toString()));

		playersPane = new RacerPanel(new MigLayout("fill", "[45.00]", "[pref!][60.00]"), RacerPanel.getTerciaryColor(),
				RacerPanel.getSecondaryColor().darker(), RacerPanel.getTerciaryColor());

		setContentPane(playersPane);
		;

		JPanel panelPlayer = new RacerPanel(new MigLayout("fill", "[][][]", "[pref!][58.00][][150.00]"),
				RacerPanel.getSecondaryColor(), RacerPanel.getSecondaryColor(), RacerPanel.getSecondaryColor().darker());
		panelPlayer.setBackground(RacerPanel.getSecondaryColor());

		playersPane.add(panelPlayer, "cell 0 0,grow");

		panelCreatedPlayers = new RacerPanel(new MigLayout("fill", "[][][][]", "[28.00][]"), null,
				RacerPanel.getSecondaryColor(), RacerPanel.getPrimaryColor().brighter());//

		playersPane.add(panelCreatedPlayers, "cell 0 1,grow");

		btnConfirm = new RacerButton("Confirmar", RacerPanel.getSecondaryColor(), RacerPanel.getPrimaryColor(), rb.getSounds().get("buttonSound.wav"));
		panelCreatedPlayers.add(btnConfirm, "cell 0 1 2 1,alignx center");

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

		lblTitlePlayer = new RacerLabel("Jugador " + (rb.getLastId() + 1), 23, RacerPanel.getPrimaryColor(),
				RacerPanel.getSecondaryColor());
		lblTitlePlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitlePlayer.setFont(RacerPanel.getPrimaryFont().deriveFont(Font.BOLD | Font.ITALIC, 23));
		lblTitlePlayer.setForeground(RacerPanel.getPrimaryColor());
		lblTitlePlayer.setBackground(RacerPanel.getSecondaryColor());
		panelPlayer.add(lblTitlePlayer, "cell 0 0 2 1,growx");

		JPanel panelTeamLogo = new RacerPanel(null, RacerPanel.getSecondaryColor(), null);
		panelTeamLogo.setBackground(RacerPanel.getSecondaryColor());
		panelTeamLogo.setBounds(11, 161, 178, 123);
		panelPlayer.add(panelTeamLogo, "cell 2 0 1 4,grow");
		



		ImagePanel imgPanel = new ImagePanel(path.toString(), 200);
		imgPanel.setBorder(new LineBorder(RacerPanel.getPrimaryColor().brighter(), 6));
		panelTeamLogo.add(imgPanel);

		RacerLabel lblName = new RacerLabel("Nombre", 19, RacerPanel.getPrimaryColor(), RacerPanel.getSecondaryColor());

		panelPlayer.add(lblName, "flowx,cell 0 1,growx");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);

		RacerLabel lblTeams = new RacerLabel("Equipo F1", 19, RacerPanel.getPrimaryColor(), RacerPanel.getSecondaryColor());
		lblTeams.setHorizontalAlignment(SwingConstants.CENTER);

		panelPlayer.add(lblTeams, "flowx,cell 1 1,growx");
		textFieldName.setSelectionColor(RacerPanel.getPrimaryColor().brighter());
		textFieldName.setSelectedTextColor(RacerPanel.getSecondaryColor().darker());
		textFieldName.setForeground(RacerPanel.getTerciaryColor());
		textFieldName.setFont(RacerPanel.getPrimaryFont().deriveFont(Font.ITALIC, 14));
		textFieldName.setBackground(RacerPanel.getSecondaryColor().brighter());
		textFieldName.setMinimumSize(new Dimension(70, 20));
		// textFieldName.setBounds(111, 21, 86, 20);
		panelPlayer.add(textFieldName, "flowx,cell 0 2,growx,aligny top");
		textFieldName.setColumns(10);

		comboBoxTeams = new JComboBox<Team>();
		comboBoxTeams.setMaximumRowCount(10);
		comboBoxTeams.setForeground(RacerPanel.getTerciaryColor());
		comboBoxTeams.setBackground(RacerPanel.getSecondaryColor().brighter());
		comboBoxTeams.setFont(RacerPanel.getPrimaryFont().deriveFont(Font.ITALIC, 14));
		comboBoxTeams.setMaximumSize(new Dimension(32767, 20));

		// comboBoxTeams.setBounds(207, 39, 172, 22);
		panelPlayer.add(comboBoxTeams, "cell 1 2,growx,aligny top");

		comboBoxTeams.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
		
				Path path = FileSystems.getDefault().getPath("img",
						"logo" + ((Team) e.getItem()).getTeamId() + "F1.jpg");
				imgPanel.setImg(new ImageIcon(path.toString()).getImage());
				panelTeamLogo.updateUI();
				panelTeamLogo.repaint();
			

			}

		});

		JCheckBox chckbxExpert = new JCheckBox("Experto");
		chckbxExpert.setFocusable(false);
		chckbxExpert.setForeground(RacerPanel.getPrimaryColor());
		chckbxExpert.setFont(RacerPanel.getPrimaryFont().deriveFont(Font.ITALIC, 14));
		chckbxExpert.setBackground(RacerPanel.getSecondaryColor());
		chckbxExpert.setBounds(11, 116, 97, 23);
		panelPlayer.add(chckbxExpert, "flowx,cell 0 3,alignx center,aligny top");

		btnCreate = new RacerButton("Crear", RacerPanel.getSecondaryColor(), RacerPanel.getPrimaryColor(), rb.getSounds().get("buttonSound.wav"));
		panelPlayer.add(btnCreate, "cell 1 3,growx,aligny top");
		btnCreate.setBounds(90, 389, 89, 23);

		btnsDelete = new ArrayList<RacerButton>();

		for (int i = 0; i < rb.getMAX_PLAYERS(); i++) {
			btnsDelete.add(new RacerButton("Borrar", Color.BLACK, Color.WHITE, rb.getSounds().get("buttonSound.wav")));
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
		
		
		btnExit = new RacerButton("Salir al Inicio", RacerPanel.getSecondaryColor(), RacerPanel.getPrimaryColor(), rb.getSounds().get("buttonSound.wav"));
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showConfirmDialog(null, "¿Volver al inicio?");
				resetListener.listenReset(new ResetEvent((JFrame) SwingUtilities.getWindowAncestor(playersPane)));
				
			}
			
		});
		
		panelCreatedPlayers.add(btnExit, "cell 2 1 2 1,alignx center");

		for (Iterator<Team> iterator = rb.getTeamColors().iterator(); iterator.hasNext();) {
			Team tc = iterator.next();
			comboBoxTeams.addItem(tc);

		}
		

	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
		

	public RacerButton getBtnCreate() {
		return btnCreate;
	}

	public ArrayList<RacerButton> getBtnsDelete() {
		return btnsDelete;
	}

	public JComboBox<Team> getComboBoxTeams() {
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

	public void setComboBoxTeams(JComboBox<Team> comboBoxTeams) {
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

	public ResetListener getResetListener() {
		return resetListener;
	}

	public void setResetListener(ResetListener resetListener) {
		this.resetListener = resetListener;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	public void addPlayersPanelCards() {
		RacerPanel createdPlayersPanel = getCreatedPlayersPanel();
		createdPlayersPanel.removeAll();
		for (int i = 0; i < playersPanelCards.size(); i++) {
			createdPlayersPanel.add(playersPanelCards.get(i), "cell " + i + " 0, center");

			// playersPanelCards.get(i).addDeleteButton(btnsDelete.get(i));
		}
		createdPlayersPanel.add(btnConfirm, "cell 0 1 2 1,alignx center");
		createdPlayersPanel.add(btnExit, "cell 2 1 2 1,alignx center");
		createdPlayersPanel.repaint();
		btnCreate.setEnabled(true);
		lblTitlePlayer.setText("Jugador " + (playersPanelCards.size() + 1));
	}
}
