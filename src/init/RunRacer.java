package init;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import Controller.RacerBoard;
import RacerModel.TeamColor;
import RacerModel.ActionPckg.ActionBlue;
import RacerModel.ActionPckg.ActionFucsia;
import RacerModel.ActionPckg.ActionGreen;
import RacerModel.ActionPckg.ActionOrange;
import RacerModel.ActionPckg.ActionRed;
import RacerModel.ActionPckg.ActionYellow;
import RacerModel.SquarePckg.BegginingSquare;
import RacerModel.SquarePckg.FinishSquare;
import RacerModel.SquarePckg.QuestionSquare;
import RacerModel.SquarePckg.Square;
import Views.WelcomePanel;

public class RunRacer {

	public RunRacer() {
		
	}

	public static void main(String[] args) {

		RacerBoard rb = new RacerBoard(6, 7);
		rb.setBegginingSquareId(0);
		rb.setFinalSquareId(rb.getColumns()*rb.getRows());
		
	
		ArrayList<Integer> questionIndexes = new ArrayList<Integer>();
		
		//HashSet<TeamColor> pickedCol = new HashSet<TeamColor>();
		int LastSquareIndex = rb.getFinalSquareId();
		//int amoPlay = 0;
		
		
		rb.getTeamColors().add(new TeamColor("Alpha_Romeo", new Color(160, 12, 46))); //AlphaRomeo - 0
		rb.getTeamColors().add(new TeamColor("Alpha_Tauri", new Color(83,100,120))); //AlphaTauri - 1
		rb.getTeamColors().add(new TeamColor("Alpine", new Color(242,148,198)));//Alpine - 2
		rb.getTeamColors().add(new TeamColor("Aston_Martin", new Color(0, 89, 79)));//AstonMartin - 3
		rb.getTeamColors().add(new TeamColor("Ferrari", new Color(212, 0,0)));//Ferrari - 4
		rb.getTeamColors().add(new TeamColor("Haas", new Color(255, 255, 255)));//Haas - 5
		rb.getTeamColors().add(new TeamColor("McLaren", new Color(244,133, 0)));//McLaren - 6
		rb.getTeamColors().add(new TeamColor("Mercedes", new Color(0, 0,0)));//Mercedes - 7
		rb.getTeamColors().add(new TeamColor("Red_Bull", new Color(252,216,0)));//RedBull - 8
		rb.getTeamColors().add(new TeamColor("Williams", new Color(0,160,222)));//Williams - 9
		
		rb.getActionDice().getActions().add(new ActionRed("Avanza el valor obtenido en el dado num�rico, pero pierde el pr�ximo turno (no podr� lanzar los dados en el siguiente turno)", new Color(255, 0, 0), false, false));
		rb.getActionDice().getActions().add(new ActionBlue("Duplica casillas a avanzar si contesta bien. Si contesta mal, no avanza y pierde el pr�ximo turno.", new Color(0, 0, 255), true, false));
		rb.getActionDice().getActions().add(new ActionOrange("El jugador que lanz� el dado no avanza ni retrocede. El jugador de �la derecha� (el del turno siguiente) deber� responder una pregunta. Si contesta bien, dicho jugador avanza el n�mero obtenido en el dado num�rico. Si contesta mal, dicho jugador retrocede esa cantidad de casillas. ", new Color(255, 128, 0), true, true));
		rb.getActionDice().getActions().add(new ActionYellow("Avanza si contesta bien. Si contesta mal, no avanza.", new Color(255, 255, 0), true, false));
		rb.getActionDice().getActions().add(new ActionGreen("Avanza directamente la cantidad de casillas indicada por el dado num�rico.", new Color(0, 255, 0), false, false));
		rb.getActionDice().getActions().add(new ActionFucsia("Avanza si contesta bien. Retrocede si contesta mal.", new Color(255, 0, 255), true, false));
		
		
		for(int i = 0; i<= LastSquareIndex; i++) {
			rb.getSquares().add(new Square(i, "Normal", new Color(0,0,0), null));
		}
		
		rb.getSquares().set(rb.getBegginingSquareId(),new BegginingSquare(rb.getBegginingSquareId(), "Inicio", new Color(0,0,0), null));
		rb.getSquares().set(rb.getFinalSquareId(), new FinishSquare(rb.getFinalSquareId(), "Fin", new Color(0,0,0), null));
		
		while (questionIndexes.size() < 4) {
			int ind;
			do {
				ind = (int) Math.floor(Math.random()*41 + 1);
			} while(!validIndex(questionIndexes, ind));
			questionIndexes.add(ind);
			System.out.println(ind);
		}
		
		rb.getSquares().get(0).setTag("Inicio");
		rb.getSquares().get(LastSquareIndex).setTag("Fin");
		
		for(Integer i: questionIndexes) {
			rb.getSquares().set(i, new QuestionSquare(i, "Pregunta", new Color(0,0,0), null));
		}
		
		/*for(Square sq: rb.getSquares()) {
			System.out.println(sq.toString());
		}*/
		
		
	
		
		/*RacerGUI frame = new RacerGUI(rb);
		frame.setStartGameListener(rb);
		frame.setCreatePlayerListener(rb);
		frame.setSize(500, 500);
		frame.setVisible(true);*/
		WelcomePanel frame = new WelcomePanel(rb);
		frame.setSize(1280, 720);
		frame.setVisible(true);
		//frame.setMinimumSize(new Dimension(500, 500));
		frame.setMaximumSize(new Dimension(WelcomePanel.HEIGHT, WelcomePanel.WIDTH));
	}
	
	public static boolean validIndex(ArrayList<Integer> questionIndexes, int ind) {
		boolean valid = true;
		int i = 0;
		while(i < questionIndexes.size() && valid) {
			valid = Math.abs(questionIndexes.get(i) - ind) > 6;
			i++;
		}
		return valid;
	}
	
	

}
