package Views;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import Controller.RacerBoard;
import Events.CreatePlayerEvent;
import Events.StartGameEvent;
import Listeners.CreatePlayerListener;
import Listeners.StartGameListener;
import RacerModel.TeamColor;
import Views.CustomComponents.RacerButton;
import Views.CustomComponents.RacerLabel;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;

public class RacerGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RacerPanel playersPane;
	private StartGameListener startGameListener;
	private CreatePlayerListener createPlayerListener;
	private final JTextField textFieldName = new JTextField();
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RacerGUI frame = new RacerGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	
	public RacerGUI(RacerBoard rb) {
		setTitle("PreGame - Racer");
		setCreatePlayerListener(rb);
		setStartGameListener(rb);
		
		playersPane = new RacerPanel(new MigLayout("fill", "[45.00]", "[pref!][60.00]"));
		
		
		setContentPane(playersPane);
		;
		
		JPanel panelPlayer = new JPanel(new MigLayout("fill", "[][][]", "[pref!][58.00][][150.00]"));
		panelPlayer.setBackground(rb.getSECONDARY_COLOR());
		panelPlayer.setFont(new Font("Swis721 Hv BT", Font.ITALIC, 14));
		panelPlayer.setBorder(null);
		//panelPlayer.setBounds(10, 11, 216, 351);
		playersPane.add(panelPlayer, "cell 0 0,grow");
		
		JPanel panelCreatedPlayers = new JPanel(new MigLayout("fill", "[206.00][]", "[28.00][][23.00][][]"));
		panelCreatedPlayers.setBorder(new TitledBorder(null, "Players Created", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		playersPane.add(panelCreatedPlayers, "cell 0 1,grow");
		
		
		JScrollPane scrollPanePlayersCreated = new JScrollPane();
		scrollPanePlayersCreated.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//scrollPanePlayersCreated.setBounds(10, 32, 227, 91);
		panelCreatedPlayers.add(scrollPanePlayersCreated, "cell 0 0 1 4,grow");
		
		JTextPane textPanePlayersCreated = new JTextPane();
		scrollPanePlayersCreated.setViewportView(textPanePlayersCreated);
		
		RacerButton btnConfirm = new RacerButton("Confirmar", rb.getPRIMARY_COLOR(), rb.getSECONDARY_COLOR());
		panelCreatedPlayers.add(btnConfirm, "cell 0 4");
		//btnConfirm.setBounds(368, 389, 89, 23);
		
		
		btnConfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(rb.getPlayers().size() < rb.getMIN_PLAYERS()) {
					JOptionPane.showMessageDialog(null, "Ingrese los datos de todos los jugadores");
				} else {
					startGameListener.listenStartGame(new StartGameEvent(new BoardPaneGUI(rb), playersPane));
				}
				
			}
			
		});
		
		RacerLabel lblTitlePlayer = new RacerLabel("Player " + (rb.getLastId()+1), 23, rb.getPRIMARY_COLOR(), rb.getSECONDARY_COLOR());
		lblTitlePlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitlePlayer.setFont(new Font("Swis721 Hv BT", Font.BOLD | Font.ITALIC, 23));
		lblTitlePlayer.setForeground(rb.getPRIMARY_COLOR());
		lblTitlePlayer.setBackground(rb.getSECONDARY_COLOR());
		panelPlayer.add(lblTitlePlayer, "cell 0 0 2 1,growx");
		
		
		
		JPanel panelTeamLogo = new JPanel();
		panelTeamLogo.setBackground(rb.getSECONDARY_COLOR());
		panelTeamLogo.setBounds(11, 161, 178, 123);
		panelPlayer.add(panelTeamLogo, "cell 2 0 1 4,grow");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 597, 560);
		
		Path path = FileSystems.getDefault().getPath("img",  "logo"+ rb.getTeamColors().get(2).getTeamName() + "F1.jpg");
		ImagePanel imgPanel = new ImagePanel(path.toString(), 200);		
		panelTeamLogo.add(imgPanel);
		
				
		RacerLabel lblName = new RacerLabel("Name", 14, rb.getPRIMARY_COLOR(), rb.getSECONDARY_COLOR());
		//lblName.setBounds(11, 24, 69, 14);
		panelPlayer.add(lblName, "flowx,cell 0 1,growx");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		RacerLabel lblTeams = new RacerLabel("F1 Team", 14, rb.getPRIMARY_COLOR(), rb.getSECONDARY_COLOR());
		lblTeams.setHorizontalAlignment(SwingConstants.CENTER);
		//lblTeams.setBounds(230, 22, 85, 14);
		panelPlayer.add(lblTeams, "flowx,cell 1 1,growx");
		textFieldName.setSelectionColor(rb.getPRIMARY_COLOR());
		textFieldName.setSelectedTextColor(rb.getSECONDARY_COLOR());
		textFieldName.setForeground(rb.getPRIMARY_COLOR());
		textFieldName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		textFieldName.setBackground(rb.getSECONDARY_COLOR().brighter());
		textFieldName.setMinimumSize(new Dimension(70, 20));
		//textFieldName.setBounds(111, 21, 86, 20);
		panelPlayer.add(textFieldName, "flowx,cell 0 2,growx,aligny top");
		textFieldName.setColumns(10);
		
		JComboBox<TeamColor> comboBoxTeams = new JComboBox<TeamColor>();
		comboBoxTeams.setMaximumRowCount(10);
		comboBoxTeams.setForeground(rb.getPRIMARY_COLOR());
		comboBoxTeams.setBackground(rb.getSECONDARY_COLOR());
		comboBoxTeams.setFont(new Font("Swis721 Hv BT", Font.BOLD | Font.ITALIC, 14));
		comboBoxTeams.setMaximumSize(new Dimension(32767, 20));
		
		//comboBoxTeams.setBounds(207, 39, 172, 22);
		panelPlayer.add(comboBoxTeams, "cell 1 2,growx,aligny top");
		
		
		
		comboBoxTeams.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				//panelTeamColor.setBackground(((TeamColor) e.getItem()).getCol());
				Path path = FileSystems.getDefault().getPath("img",  "logo"+ ((TeamColor) e.getItem()).getTeamName() + "F1.jpg");
				imgPanel.setImg(new ImageIcon(path.toString()).getImage());
				panelTeamLogo.setSize(panelTeamLogo.getWidth(), panelTeamLogo.getHeight() + 2);
				panelTeamLogo.repaint();
				/*panelTeamLogo.remove(imgPanel);
				ImagePanel imgPanel = new ImagePanel(path.toString());
				panelTeamLogo.add(imgPanel);*/
	
			}
			
		});
		
		JCheckBox chckbxExpert = new JCheckBox("Expert");
		chckbxExpert.setFocusable(false);
		chckbxExpert.setForeground(rb.getPRIMARY_COLOR());
		chckbxExpert.setFont(new Font("Swis721 Hv BT", Font.ITALIC, 14));
		chckbxExpert.setBackground(rb.getSECONDARY_COLOR());
		chckbxExpert.setBounds(11, 116, 97, 23);
		panelPlayer.add(chckbxExpert, "flowx,cell 0 3,alignx center,aligny top");
		
		
		
		RacerButton btnCreate = new RacerButton("Create", rb.getPRIMARY_COLOR(), rb.getSECONDARY_COLOR());
		panelPlayer.add(btnCreate, "cell 1 3,growx,aligny top");
		btnCreate.setBounds(90, 389, 89, 23);
		
		ArrayList<RacerButton> btnsDelete = new ArrayList<RacerButton>();
		for(int i = 0; i<rb.getMAX_PLAYERS(); i++) {
			btnsDelete.add(new RacerButton("Delete " + (btnsDelete.size() + 1), rb.getPRIMARY_COLOR(), rb.getSECONDARY_COLOR()));
			panelCreatedPlayers.add(btnsDelete.get(btnsDelete.size()-1), "cell 1 " + (btnsDelete.size()-1));
		}
		
			
			btnCreate.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {		
					
					if(rb.validateFields(textFieldName.getText(), rb.getLastId())) {	
						
						
						createPlayerListener.listenCreate(new CreatePlayerEvent(textFieldName.getText(), comboBoxTeams.getSelectedItem(), chckbxExpert.isSelected()));
						textPanePlayersCreated.setText(rb.genPlayersStatus());
						textFieldName.setText("");
						chckbxExpert.setSelected(false);
						comboBoxTeams.setSelectedIndex(0);
						lblTitlePlayer.setText("Player " + (rb.getLastId()+1));
						
						rb.updateDelButton(textPanePlayersCreated, btnsDelete, comboBoxTeams, lblTitlePlayer);
						
						rb.updateComboBox(comboBoxTeams);
						
						
					} else {
						JOptionPane.showMessageDialog(null, "Ingrese un nombre (sin repetir) y/o seleccione un equipo sin repetir.\n Recuerda que no puedes crear más de " + rb.getMAX_PLAYERS() + " jugadores");
						
					}
					
					
					
					
				}
			});
		
		
		
		
		
		for(Iterator<TeamColor> iterator = rb.getTeamColors().iterator(); iterator.hasNext();) {
			TeamColor tc = iterator.next();
			comboBoxTeams.addItem(tc);
	
		}
		
		
				
	}
	
	public void setStartGameListener(StartGameListener I) {
		startGameListener = I;
	}
	
	public void setCreatePlayerListener(CreatePlayerListener I) {
		createPlayerListener = I;
	}
}
