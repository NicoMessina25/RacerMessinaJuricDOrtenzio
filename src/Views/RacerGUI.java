package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.RacerBoard;
import Events.StartGameEvent;
import Listeners.StartGameListener;
import RacerModel.Player;
import RacerModel.RacerPlayer;
import RacerModel.TeamColor;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import java.awt.Dimension;
public class RacerGUI extends JFrame {

	private JPanel playersPane;
	private StartGameListener startGameListener;
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
	
		
		playersPane = new JPanel();
		playersPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(playersPane);
		playersPane.setLayout(null);
		
		JLabel lblAmountOfPlayers = new JLabel("Amount of Players");
		lblAmountOfPlayers.setBounds(493, 11, 88, 14);
		lblAmountOfPlayers.setHorizontalAlignment(SwingConstants.CENTER);
		playersPane.add(lblAmountOfPlayers);
		
		JSpinner spnAmountOfPlayers = new JSpinner();
		spnAmountOfPlayers.setBounds(525, 36, 31, 20);
		spnAmountOfPlayers.setModel(new SpinnerNumberModel(2, 2, 4, 1));
		playersPane.add(spnAmountOfPlayers);
		
		JPanel panelPlayer = new JPanel();
		panelPlayer.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Player", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelPlayer.setBounds(10, 11, 216, 351);
		playersPane.add(panelPlayer);
		panelPlayer.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(11, 24, 69, 14);
		panelPlayer.add(lblName);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldName.setBounds(111, 21, 86, 20);
		panelPlayer.add(textFieldName);
		textFieldName.setColumns(10);
		
		JComboBox<TeamColor> comboBoxTeams = new JComboBox<TeamColor>();
		
		comboBoxTeams.setBounds(11, 63, 172, 22);
		panelPlayer.add(comboBoxTeams);
		
		
		JLabel lblTeams = new JLabel("F1 Team");
		lblTeams.setBounds(34, 46, 85, 14);
		panelPlayer.add(lblTeams);
		
		JCheckBox chckbxExpert = new JCheckBox("Expert");
		chckbxExpert.setBounds(11, 116, 97, 23);
		panelPlayer.add(chckbxExpert);
		
		JPanel panelTeamColor = new JPanel();
		panelTeamColor.setBounds(11, 96, 172, 14);
		panelPlayer.add(panelTeamColor);
		panelTeamColor.setBackground(rb.getTeamColors().get(0).getCol());
		
		JPanel panelTeamLogo = new JPanel();
		panelTeamLogo.setBounds(11, 161, 178, 123);
		panelPlayer.add(panelTeamLogo);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(368, 389, 89, 23);
		playersPane.add(btnConfirm);
		
		JPanel panelCreatedPlayers = new JPanel();
		panelCreatedPlayers.setBorder(new TitledBorder(null, "Players Created", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCreatedPlayers.setBounds(236, 11, 247, 347);
		playersPane.add(panelCreatedPlayers);
		panelCreatedPlayers.setLayout(null);
		
		JScrollPane scrollPanePlayersCreated = new JScrollPane();
		scrollPanePlayersCreated.setBounds(10, 32, 227, 219);
		panelCreatedPlayers.add(scrollPanePlayersCreated);
		
		JTextArea textAreaPlayersCreated = new JTextArea();
		scrollPanePlayersCreated.setViewportView(textAreaPlayersCreated);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(90, 389, 89, 23);
		playersPane.add(btnCreate);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 560);
		
		Path path = FileSystems.getDefault().getPath("img",  "logo"+ rb.getTeamColors().get(0).getTeamName() + "F1.jpg");
		ImagePanel panel = new ImagePanel(new ImageIcon(path.toString()).getImage());		
		panelTeamLogo.add(panel);
		
		spnAmountOfPlayers.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int amountOfPlayers = (int) spnAmountOfPlayers.getValue();
				if(amountOfPlayers < rb.getPlayers().size()) {
					rb.removePlayer();
				}
				textAreaPlayersCreated.setText(rb.genPlayersStatus());
				
			}
				
		});
		
		for(Iterator<TeamColor> iterator = rb.getTeamColors().iterator(); iterator.hasNext();) {
			TeamColor tc = iterator.next();
			comboBoxTeams.addItem(tc);
	
		}
		
		comboBoxTeams.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				panelTeamColor.setBackground(((TeamColor) e.getItem()).getCol());
				Path path = FileSystems.getDefault().getPath("img",  "logo"+ ((TeamColor) e.getItem()).getTeamName() + "F1.png");
				//ImageIcon img = new ImageIcon(path.toString());
				
			
	
			}
			
		});
		
		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id;
				int timePerOption;

				
				String name = textFieldName.getText();
				boolean expert = chckbxExpert.isSelected();
				if(expert) {
					timePerOption = 10;
				} else timePerOption = 15;
				TeamColor tc = (TeamColor) comboBoxTeams.getSelectedItem();
				if (rb.getPlayers().size() > 0) {
						id = rb.getPlayers().get(rb.getPlayers().size() - 1).getId() + 1;
					} else {
						id = 1;
					}
				
				if(rb.validateFields(name, tc, (int) spnAmountOfPlayers.getValue(), id)) {
					rb.addPlayer(new RacerPlayer(name, id, tc, expert, timePerOption));
					textAreaPlayersCreated.setText(rb.genPlayersStatus());
					textFieldName.setText("");
					chckbxExpert.setSelected(false);
					comboBoxTeams.setSelectedIndex(0);
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese un nombre (sin repetir) y/o seleccione un equipo sin repetir.\n Recuerda que no puedes crear más de " + spnAmountOfPlayers.getValue() + " jugadores");
				}
				
				
			}
		});
		
		btnConfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(rb.getPlayers().size() < (int) spnAmountOfPlayers.getValue()) {
					JOptionPane.showMessageDialog(null, "Ingrese los datos de todos los jugadores");
				} else {
					startGameListener.listenStartGame(new StartGameEvent(new BoardPaneGUI(rb), playersPane));
				}
				
			}
			
		});
		
		
				
	}
	
	public void setStartGameListener(StartGameListener I) {
		startGameListener = I;
	}
}
