package init;

import java.awt.Color;
import java.awt.Dimension;

import Controller.RacerBoard;
import RacerModel.Team;
import RacerModel.ActionPckg.ActionBlue;
import RacerModel.ActionPckg.ActionFucsia;
import RacerModel.ActionPckg.ActionGreen;
import RacerModel.ActionPckg.ActionOrange;
import RacerModel.ActionPckg.ActionRed;
import RacerModel.ActionPckg.ActionYellow;
import RacerModel.SquarePckg.BegginingSquare;
import RacerModel.SquarePckg.FinishSquare;
import RacerModel.SquarePckg.Square;
import Views.WelcomePanel;

public class RunRacer {
	
	/**
	 * Main Class*/

	public RunRacer() {

	}

	public static void main(String[] args) {

		RacerBoard rb = new RacerBoard(43);
		rb.setBeginningSquareId(0);
		rb.setFinalSquareId(rb.getAmountSquares() - 1);
		rb.setLastId(0);

		rb.loadSoundEffects();

		int LastSquareIndex = rb.getFinalSquareId();
	

		rb.getTeamColors().add(new Team("Alpha Romeo", "AlphaR", new Color(160, 12, 46))); // AlphaRomeo - 0
		rb.getTeamColors().add(new Team("Alpha Tauri", "AlphaT", new Color(83, 100, 120))); // AlphaTauri - 1
		rb.getTeamColors().add(new Team("Alpine", "Alp", new Color(242, 148, 198)));// Alpine - 2
		rb.getTeamColors().add(new Team("Aston Martin", "AstonM", new Color(0, 89, 79)));// AstonMartin - 3
		rb.getTeamColors().add(new Team("Ferrari", "Frri", new Color(230, 10, 0)));// Ferrari - 4
		rb.getTeamColors().add(new Team("Haas", "Haas", new Color(205, 205, 205)));// Haas - 5
		rb.getTeamColors().add(new Team("McLaren", "McLrn", new Color(244, 133, 0)));// McLaren - 6
		rb.getTeamColors().add(new Team("Mercedes", "Mer", new Color(50, 50, 50)));// Mercedes - 7
		rb.getTeamColors().add(new Team("Red Bull", "RBull", new Color(252, 216, 0)));// RedBull - 8
		rb.getTeamColors().add(new Team("Williams", "Wills", new Color(0, 160, 222)));// Williams - 9

		rb.getActionDice().getActions().add(new ActionRed(
				"Avanza el valor obtenido en el dado num�rico, pero pierde el pr�ximo turno (no podr� lanzar los dados en el siguiente turno)",
				new Color(255, 0, 0), false, false));
		rb.getActionDice().getActions().add(new ActionBlue(
				"Duplica casillas a avanzar si contesta bien. Si contesta mal, no avanza y pierde el pr�ximo turno.",
				new Color(0, 0, 255), true, false));
		rb.getActionDice().getActions().add(new ActionOrange(
				"El jugador que lanz� el dado no avanza ni retrocede. El jugador de �la derecha� (el del turno siguiente) deber� responder una pregunta. Si contesta bien, dicho jugador avanza el n�mero obtenido en el dado num�rico. Si contesta mal, dicho jugador retrocede esa cantidad de casillas. ",
				new Color(255, 128, 0), true, true));
		rb.getActionDice().getActions().add(new ActionYellow("Avanza si contesta bien. Si contesta mal, no avanza.",
				new Color(255, 255, 0), true, false));
		rb.getActionDice().getActions()
				.add(new ActionGreen("Avanza directamente la cantidad de casillas indicada por el dado num�rico.",
						new Color(0, 255, 0), false, false));
		rb.getActionDice().getActions().add(new ActionFucsia("Avanza si contesta bien. Retrocede si contesta mal.",
				new Color(255, 0, 255), true, false));

		for (int i = 0; i <= LastSquareIndex; i++) {
			rb.getSquares().add(new Square(i, "Normal", new Color(0, 0, 0), null));
		}

		rb.getSquares().set(rb.getBeginningSquareId(),
				new BegginingSquare(rb.getBeginningSquareId(), "Inicio", new Color(0, 0, 0), null));
		rb.getSquares().set(rb.getFinalSquareId(),
				new FinishSquare(rb.getFinalSquareId(), "Fin", new Color(0, 0, 0), null));

	

		rb.getSquares().get(rb.getBeginningSquareId()).setTag("Inicio");
		rb.getSquares().get(LastSquareIndex).setTag("Fin");


		WelcomePanel frame = new WelcomePanel(rb);
		frame.setSize(1280, 720);
		frame.setVisible(true);
		// frame.setMinimumSize(new Dimension(500, 500));
		frame.setMaximumSize(new Dimension(WelcomePanel.HEIGHT, WelcomePanel.WIDTH));
	}

}
