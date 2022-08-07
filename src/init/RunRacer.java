package init;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;

import Controller.RacerBoard;
import RacerModel.Action;
import RacerModel.ActionBlue;
import RacerModel.ActionFucsia;
import RacerModel.ActionGreen;
import RacerModel.ActionOrange;
import RacerModel.ActionRed;
import RacerModel.ActionYellow;
import RacerModel.Category;
import RacerModel.Option;
import RacerModel.Question;
import RacerModel.Square;
import RacerModel.TeamColor;
import Views.RacerGUI;

public class RunRacer {

	public RunRacer() {
		
	}

	public static void main(String[] args) {

		RacerBoard rb = new RacerBoard(6, 7);
		
	
		ArrayList<Integer> questionIndexes = new ArrayList<Integer>();
		
		HashSet<TeamColor> pickedCol = new HashSet<TeamColor>();
		int LastSquareIndex = rb.getRows()*rb.getColumns();
		int amoPlay = 0;
		
		
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
		
		rb.getActionDice().getActions().add(new ActionRed("Avanza el valor obtenido en el dado numérico, pero pierde el próximo turno (no podrá lanzar los dados en el siguiente turno)", new Color(255, 0, 0)));
		rb.getActionDice().getActions().add(new ActionBlue("Duplica casillas a avanzar si contesta bien. Si contesta mal, no avanza y pierde el próximo turno.", new Color(0, 0, 255)));
		rb.getActionDice().getActions().add(new ActionOrange("El jugador que lanzó el dado no avanza ni retrocede. El jugador de “la derecha” (el del turno siguiente) deberá responder una pregunta. Si contesta bien, dicho jugador avanza el número obtenido en el dado numérico. Si contesta mal, dicho jugador retrocede esa cantidad de casillas. ", new Color(255, 128, 0)));
		rb.getActionDice().getActions().add(new ActionYellow("Avanza si contesta bien. Si contesta mal, no avanza.", new Color(255, 255, 0)));
		rb.getActionDice().getActions().add(new ActionGreen("Avanza directamente la cantidad de casillas indicada por el dado numérico.", new Color(0, 255, 0)));
		rb.getActionDice().getActions().add(new ActionFucsia("Avanza si contesta bien. Retrocede si contesta mal.", new Color(255, 0, 255)));
		
		rb.getCategories().add(new Category("Deportes", new Color(0, 147, 0)));
		rb.getCategories().add(new Category("Arte", new Color(244, 116, 0)));
		rb.getCategories().add(new Category("Entretenimiento", new Color(244, 26, 90)));
		rb.getCategories().add(new Category("Geografía", new Color(116, 0, 92)));
		rb.getCategories().add(new Category("Historia", new Color(255, 222, 0)));
		rb.getCategories().add(new Category("Ciencia", new Color(49, 176, 255)));
		
		ArrayList<Option> options = new ArrayList<Option>();
		options.add(new Option(0, "Nueva Zelanda"));
		options.add(new Option(1, "Noruega"));
		options.add(new Option(2, "Inglaterra"));
		options.add(new Option(3, "Estados Unidos"));
		rb.getQuestions().add(new Question(0, 0, rb.getCategories().get(4), 3, "¿Cuál fue el primer país en aprobar el sufragio femenino?", options));
		
		options = new ArrayList<Option>();
		options.add(new Option(0, "1969"));
		options.add(new Option(1, "1965"));
		options.add(new Option(2, "1973"));
		options.add(new Option(3, "1974"));
		rb.getQuestions().add(new Question(1, 0, rb.getCategories().get(4), 2, "¿En qué año llegó el hombre a la Luna?", options));
		
		options = new ArrayList<Option>();
		options.add(new Option(0, "Manila"));
		options.add(new Option(1, "Dávao"));
		options.add(new Option(2, "Baguio"));
		rb.getQuestions().add(new Question(2, 0, rb.getCategories().get(3), 5, "¿Cuál es la capital de Filipinas?", options));
		
		options = new ArrayList<Option>();
		options.add(new Option(0, "Ecuador"));
		options.add(new Option(1, "Uruguay"));
		options.add(new Option(2, "Venezuela"));
		options.add(new Option(3, "Bolivia"));
		rb.getQuestions().add(new Question(3, 0, rb.getCategories().get(3), 1, "¿Qué país está entre Perú y Colombia?", options));
		
		options = new ArrayList<Option>();
		options.add(new Option(0, "Vincent"));
		options.add(new Option(1, "Carlos"));
		options.add(new Option(2, "Roberto"));
		rb.getQuestions().add(new Question(4, 0, rb.getCategories().get(1), 1, "¿Con qué nombre firmaba Van Gogh sus obras?", options));
		
		options = new ArrayList<Option>();
		options.add(new Option(0, "Van Gogh"));
		options.add(new Option(1, "Picasso"));
		options.add(new Option(2, "Dalí"));
		options.add(new Option(3, "Munch"));
		options.add(new Option(4, "Vermeer"));
		rb.getQuestions().add(new Question(5, 1, rb.getCategories().get(1), 5, "¿Quién pintó el “Guernica”?", options));
		
		options = new ArrayList<Option>();
		options.add(new Option(0, "O3"));
		options.add(new Option(1, "NO3"));
		options.add(new Option(2, "NO2"));
		options.add(new Option(3, "SO2"));
		rb.getQuestions().add(new Question(6, 0, rb.getCategories().get(5), 3, "¿Qué gas de la atmósfera nos protege de la radiación ultravioleta?", options));
		
		options = new ArrayList<Option>();
		options.add(new Option(0, "300000km/s"));
		options.add(new Option(1, "3000km/s"));
		options.add(new Option(2, "300000km/h"));
		options.add(new Option(3, "300m/s"));
		options.add(new Option(4, "300000m/s"));
		rb.getQuestions().add(new Question(7, 0, rb.getCategories().get(5), 4, "¿Cuál es la velocidad de la luz?", options));
		
		options = new ArrayList<Option>();
		options.add(new Option(0, "Blancanieves"));
		options.add(new Option(1, "Cenicienta"));
		options.add(new Option(2, "Dumbo"));
		options.add(new Option(3, "Bambi"));
		rb.getQuestions().add(new Question(8, 0, rb.getCategories().get(2), 3, "¿Cuál fue la primera película de Disney?", options));
		
		options = new ArrayList<Option>();
		options.add(new Option(0, "La Ciudad Esmeralda"));
		options.add(new Option(1, "La Ciudad Rubí"));
		options.add(new Option(2, "La Ciudad Zafiro"));
		rb.getQuestions().add(new Question(9, 0, rb.getCategories().get(2), 4, "¿En qué ciudad vive el mago de Oz?", options));
		
		options = new ArrayList<Option>();
		options.add(new Option(0, "Atletismo"));
		options.add(new Option(1, "Golf"));
		options.add(new Option(2, "Tennis"));
		options.add(new Option(3, "Futbol"));
		rb.getQuestions().add(new Question(10, 0, rb.getCategories().get(0), 5, "¿En qué deporte destacó Carl Lewis?", options));
		
		options = new ArrayList<Option>();
		options.add(new Option(0, "Bolt"));
		options.add(new Option(1, "Stones"));
		options.add(new Option(2, "Black"));
		options.add(new Option(3, "Messi"));
		rb.getQuestions().add(new Question(11, 0, rb.getCategories().get(0), 1, "¿Qué atleta ostenta el récord de los 100 metros lisos?", options));
		
		options = new ArrayList<Option>();
		options.add(new Option(0, "6"));
		options.add(new Option(1, "5"));
		options.add(new Option(2, "7"));
		options.add(new Option(3, "8"));
		rb.getQuestions().add(new Question(12, 0, rb.getCategories().get(0), 2, " ¿Cuántos jugadores hay en un equipo de voleibol?", options));
		
		options = new ArrayList<Option>();
		options.add(new Option(0, "Medusa Avispa de Mar"));
		options.add(new Option(1, "Viuda Negra Nashe"));
		options.add(new Option(2, "Cobra Gay"));
		options.add(new Option(3, "Avispa Australiana"));
		rb.getQuestions().add(new Question(13, 0, rb.getCategories().get(5), 5, "¿Cuál es el animal más venenoso del mundo?", options));
		
		options = new ArrayList<Option>();
		options.add(new Option(0, "China y Nepal"));
		options.add(new Option(1, "Mongolia y Rusia"));
		options.add(new Option(2, "Rusia y China"));
		options.add(new Option(3, "Rusia y Kazajistán"));
		rb.getQuestions().add(new Question(14, 0, rb.getCategories().get(3), 4, "¿En qué países se encuentra el Everest?", options));
		
		options = new ArrayList<Option>();
		options.add(new Option(0, "Haití"));
		options.add(new Option(1, "Cuba"));
		options.add(new Option(2, "México"));
		options.add(new Option(3, "Nicaragua"));
		rb.getQuestions().add(new Question(15, 0, rb.getCategories().get(4), 2, "¿Cuál fue el segundo país americano en conseguir la independencia?", options));
		
		for(int i = 0; i<= LastSquareIndex; i++) {
			rb.getSquares().add(new Square(i, "Normal", new Color(0,0,0), null));
		}
		
		while (questionIndexes.size() < 4) {
			int ind;
			do {
				ind = (int) Math.floor(Math.random()*41 + 1);
			} while(!validIndex(questionIndexes, ind));
			questionIndexes.add(ind);
		}
		
		rb.getSquares().get(0).setTag("Inicio");
		rb.getSquares().get(LastSquareIndex).setTag("Fin");
		
		for(Integer i: questionIndexes) {
			rb.getSquares().get(i).setTag("Pregunta");
		}
		
		/*for(Square sq: rb.getSquares()) {
			System.out.println(sq.toString());
		}*/
		
		RacerGUI frame = new RacerGUI(rb);
		frame.setStartGameListener(rb);
		
		frame.setVisible(true);

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
