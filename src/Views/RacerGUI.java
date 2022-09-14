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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Controller.RacerBoard;
import Events.CreatePlayerEvent;
import Events.StartGameEvent;
import Listeners.CreatePlayerListener;
import Listeners.StartGameListener;
import RacerModel.TeamColor;
import net.miginfocom.swing.MigLayout;

public class RacerGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel playersPane;
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
	
		
		playersPane = new JPanel(new MigLayout("fill", "[45.00][96.00][84.00][][][]", "[46.00][][60.00][][]"));
		
		setContentPane(playersPane);
		;
		
		JPanel panelPlayer = new JPanel(new MigLayout("fill", "[][][][][][][][]", "[][][]"));
		panelPlayer.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Player", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		//panelPlayer.setBounds(10, 11, 216, 351);
		playersPane.add(panelPlayer, "cell 0 0 4 2,grow");

		
		JLabel lblName = new JLabel("Name");
		//lblName.setBounds(11, 24, 69, 14);
		panelPlayer.add(lblName, "cell 0 0");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldName.setMinimumSize(new Dimension(70, 20));
		//textFieldName.setBounds(111, 21, 86, 20);
		panelPlayer.add(textFieldName, "cell 1 0");
		textFieldName.setColumns(10);
		
		
		JLabel lblTeams = new JLabel("F1 Team");
		//lblTeams.setBounds(230, 22, 85, 14);
		panelPlayer.add(lblTeams, "cell 3 0");
		
		JComboBox<TeamColor> comboBoxTeams = new JComboBox<TeamColor>();
		comboBoxTeams.setMaximumSize(new Dimension(32767, 20));
		
		//comboBoxTeams.setBounds(207, 39, 172, 22);
		panelPlayer.add(comboBoxTeams, "cell 4 0 2 1,grow");
		
		JPanel panelCreatedPlayers = new JPanel(new MigLayout("fill", "[206.00][]", "[28.00][][23.00][][]"));
		panelCreatedPlayers.setBorder(new TitledBorder(null, "Players Created", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		playersPane.add(panelCreatedPlayers, "cell 0 2 5 2,grow");
		
		
		JScrollPane scrollPanePlayersCreated = new JScrollPane();
		scrollPanePlayersCreated.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//scrollPanePlayersCreated.setBounds(10, 32, 227, 91);
		panelCreatedPlayers.add(scrollPanePlayersCreated, "cell 0 0 1 4,grow");
		
		JTextPane textPanePlayersCreated = new JTextPane();
		scrollPanePlayersCreated.setViewportView(textPanePlayersCreated);
		
		JButton btnConfirm = new JButton("Confirm");
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
		
		
		
		JPanel panelTeamColor = new JPanel();
		panelTeamColor.setBounds(11, 96, 172, 14);
		panelPlayer.add(panelTeamColor, "cell 3 1 3 1,grow");
		panelTeamColor.setBackground(rb.getTeamColors().get(0).getCol());
		
		
		
		JPanel panelTeamLogo = new JPanel();
		panelTeamLogo.setBounds(11, 161, 178, 123);
		panelPlayer.add(panelTeamLogo, "cell 4 0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 597, 560);
		
		Path path = FileSystems.getDefault().getPath("img",  "logo"+ rb.getTeamColors().get(2).getTeamName() + "F1.jpg");
		ImagePanel imgPanel = new ImagePanel(path.toString());		
		panelTeamLogo.add(imgPanel);
		
		comboBoxTeams.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				panelTeamColor.setBackground(((TeamColor) e.getItem()).getCol());
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
		chckbxExpert.setBounds(11, 116, 97, 23);
		panelPlayer.add(chckbxExpert, "cell 0 1");
		
		
		
		JButton btnCreate = new JButton("Create");
		panelPlayer.add(btnCreate, "cell 0 2");
		btnCreate.setBounds(90, 389, 89, 23);
		
		ArrayList<JButton> btnsDelete = new ArrayList<JButton>();
		for(int i = 0; i<rb.getMAX_PLAYERS(); i++) {
			btnsDelete.add(new JButton("Delete " + (btnsDelete.size() + 1)));
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
					
					rb.updateDelButton(textPanePlayersCreated, btnsDelete, comboBoxTeams);
					
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
