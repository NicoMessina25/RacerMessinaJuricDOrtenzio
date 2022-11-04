package init;

import java.awt.Color;
import java.awt.Dimension;
import java.nio.file.FileSystems;

import javax.swing.JOptionPane;

import Controller.RacerBoard;
import RacerModel.Team;
import RacerModel.ActionPckg.ActionBlue;
import RacerModel.ActionPckg.ActionFucsia;
import RacerModel.ActionPckg.ActionGreen;
import RacerModel.ActionPckg.ActionOrange;
import RacerModel.ActionPckg.ActionRed;
import RacerModel.ActionPckg.ActionYellow;
import Views.WelcomePanel;
import Views.CustomComponents.RacerPanel;

public class RunRacer {
	
	/**
	 * Main Class*/

	public RunRacer() {

	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		RacerBoard rb = new RacerBoard(43);
		rb.setBeginningSquareId(0);
		rb.setFinalSquareId(rb.getAmountSquares() - 1);
		rb.setLastId(0);

		rb.loadSoundEffects();

		RacerPanel.setPrimaryFont();
	

		rb.getTeams().add(new Team("Alpha Romeo", "AlphaR", new Color(160, 12, 46))); // AlphaRomeo - 0
		rb.getTeams().add(new Team("Alpha Tauri", "AlphaT", new Color(83, 100, 120))); // AlphaTauri - 1
		rb.getTeams().add(new Team("Alpine", "Alp", new Color(242, 148, 198)));// Alpine - 2
		rb.getTeams().add(new Team("Aston Martin", "AstonM", new Color(0, 89, 79)));// AstonMartin - 3
		rb.getTeams().add(new Team("Ferrari", "Frri", new Color(230, 10, 0)));// Ferrari - 4
		rb.getTeams().add(new Team("Haas", "Haas", new Color(205, 205, 205)));// Haas - 5
		rb.getTeams().add(new Team("McLaren", "McLrn", new Color(244, 133, 0)));// McLaren - 6
		rb.getTeams().add(new Team("Mercedes", "Mer", new Color(50, 50, 50)));// Mercedes - 7
		rb.getTeams().add(new Team("Red Bull", "RBull", new Color(252, 216, 0)));// RedBull - 8
		rb.getTeams().add(new Team("Williams", "Wills", new Color(0, 160, 222)));// Williams - 9

		rb.getActionDice().getActions().add(new ActionRed(
				"Avanza el valor obtenido en el dado numérico, pero pierde el próximo turno (no podrá lanzar los dados en el siguiente turno)",
				FileSystems.getDefault().getPath("img/actionDice", "actionRed.png").toString(), false, false));
		rb.getActionDice().getActions().add(new ActionBlue(
				"Duplica casillas a avanzar si contesta bien. Si contesta mal, no avanza y pierde el próximo turno.",
				FileSystems.getDefault().getPath("img/actionDice", "actionBlue.png").toString(), true, false));
		rb.getActionDice().getActions().add(new ActionOrange(
				"El jugador que lanzó el dado no avanza ni retrocede. El jugador de del turno siguiente deberá responder una pregunta. Si contesta bien, dicho jugador avanza el número obtenido en el dado numérico. Si contesta mal, dicho jugador retrocede esa cantidad de casillas. ",
				FileSystems.getDefault().getPath("img/actionDice", "actionOrange.png").toString(), true, true));
		rb.getActionDice().getActions().add(new ActionYellow("Avanza si contesta bien. Si contesta mal, no avanza.",
				FileSystems.getDefault().getPath("img/actionDice", "actionYellow.png").toString(), true, false));
		rb.getActionDice().getActions()
				.add(new ActionGreen("Avanza directamente la cantidad de casillas indicada por el dado numérico.",
						FileSystems.getDefault().getPath("img/actionDice", "actionGreen.png").toString(), false, false));
		rb.getActionDice().getActions().add(new ActionFucsia("Avanza si contesta bien. Retrocede si contesta mal.",
				FileSystems.getDefault().getPath("img/actionDice", "actionFuchsia.png").toString(), true, false));
		
		
		

		WelcomePanel frame = new WelcomePanel(rb);
		frame.setSize(1280, 720);
		frame.setVisible(true);
		// frame.setMinimumSize(new Dimension(500, 500));
		frame.setMaximumSize(new Dimension(WelcomePanel.HEIGHT, WelcomePanel.WIDTH));
	}

}
