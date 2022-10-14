package Views.CustomComponents;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class RacerPanel extends JPanel {

	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

		//----------------------------------------->|CONSTANTS|<-----------------------------------------------\\

	private static final long serialVersionUID = 6203212781772904880L;

		//----------------------------------------->|VARIABLES|<-----------------------------------------------\\

	/** primary color of the game */
	private static final Color PRIMARY_COLOR = new Color(214, 164, 12);
	private static final Color SECONDARY_COLOR = new Color(184, 0, 0);
	private static final Color TERCIARY_COLOR = new Color(0, 0, 0);
	private static final String PRIMARY_FONT_FAMILY = "Swis721 Hv BT";

	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	public RacerPanel(Color col, Color bgCol, Color borderCol) {
		setDefaultConfiguration(col, bgCol, borderCol);
	}

	public RacerPanel(LayoutManager layout, Color col, Color bgCol, Color borderCol) {
		super(layout);
		setDefaultConfiguration(col, bgCol, borderCol);
	}

	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	public static Color getPrimaryColor() {
		return PRIMARY_COLOR;
	}

	public static Color getSecondaryColor() {
		return SECONDARY_COLOR;
	}

	public static Color getTerciaryColor() {
		return TERCIARY_COLOR;
	}

	public static String getPrimaryFontFamily() {
		return PRIMARY_FONT_FAMILY;
	}

	// ------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\

	public void setDefaultConfiguration(Color col, Color bgCol, Color borderCol) {
		setForeground(col);
		setBackground(bgCol);
		setBorder(new LineBorder(borderCol, 6));
	}

}
