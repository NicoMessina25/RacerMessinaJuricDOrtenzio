package Views.CustomComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.LayoutManager;
import java.io.IOException;
import java.nio.file.FileSystems;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class RacerPanel extends JPanel {

	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

		//----------------------------------------->|CONSTANTS|<-----------------------------------------------\\

	private static final long serialVersionUID = 6203212781772904880L;

		//----------------------------------------->|VARIABLES|<-----------------------------------------------\\

	/** primary color of the game */
	private static final Color PRIMARY_COLOR =  new Color(251, 0, 0);//new Color(214, 164, 12);
	private static final Color SECONDARY_COLOR = new Color(42, 37, 81); // new Color(184, 0, 0);
	private static final Color TERCIARY_COLOR = new Color(255, 255, 255);
	private static Font primaryFont;

	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	/**
	 * 
	 * @param col
	 * @param bgCol
	 * @param borderCol
	 */
	public RacerPanel(Color col, Color bgCol, Color borderCol) {
		setDefaultConfiguration(col, bgCol, borderCol);
	}

	/**
	 * 
	 * @param layout
	 * @param col
	 * @param bgCol
	 * @param borderCol
	 */
	public RacerPanel(LayoutManager layout, Color col, Color bgCol, Color borderCol) {
		super(layout);
		setDefaultConfiguration(col, bgCol, borderCol);
	}

	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	/**
	 * 
	 * @return
	 */
	public static Color getPrimaryColor() {
		return PRIMARY_COLOR;
	}

	/**
	 * 
	 * @return
	 */
	public static Color getSecondaryColor() {
		return SECONDARY_COLOR;
	}

	/**
	 * 
	 * @return
	 */
	public static Color getTerciaryColor() {
		return TERCIARY_COLOR;
	}

	/**
	 * 
	 * @return
	 */
	public static Font getPrimaryFont() {
		return primaryFont;
	}
	
	/**
	 * 
	 */
	public static void setPrimaryFont() {
		try {
			primaryFont = Font.createFont(Font.TRUETYPE_FONT, FileSystems.getDefault().getPath("fonts", "Swis721_Hv_BT_Heavy.ttf").toFile());
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\

	/**
	 * 
	 * @param col
	 * @param bgCol
	 * @param borderCol
	 */
	public void setDefaultConfiguration(Color col, Color bgCol, Color borderCol) {
		setForeground(col);
		setBackground(bgCol);
		setBorder(new LineBorder(borderCol, 6));
	}

}
