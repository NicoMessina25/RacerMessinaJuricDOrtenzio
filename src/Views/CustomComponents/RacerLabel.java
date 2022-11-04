package Views.CustomComponents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;

public class RacerLabel extends JLabel {

	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
			
			//----------------------------------------->|CONSTANTS|<-----------------------------------------------\\
	
	private static final long serialVersionUID = 9154301448000745661L;
	
			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	private int fontSize;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	/**
	 * 
	 */
	public RacerLabel() {
		
	}

	/**
	 * 
	 * @param text
	 * @param fSize
	 * @param col
	 * @param bgCol
	 */
	public RacerLabel(String text, int fSize, Color col, Color bgCol) {
		super(text);
		setDefaultConfiguration(fSize, col, bgCol);
	}

	/**
	 * 
	 * @param image
	 * @param col
	 * @param bgCol
	 */
	public RacerLabel(Icon image, Color col, Color bgCol) {
		super(image);
		setDefaultConfiguration(1, col, bgCol);
		
	}

	/**
	 * 
	 * @param text
	 * @param horizontalAlignment
	 * @param fSize
	 * @param col
	 * @param bgCol
	 */
	public RacerLabel(String text, int horizontalAlignment, int fSize, Color col, Color bgCol) {
		super(text, horizontalAlignment);
		setDefaultConfiguration(fSize, col, bgCol);
	}

	/**
	 * 
	 * @param image
	 * @param horizontalAlignment
	 * @param col
	 * @param bgCol
	 */
	public RacerLabel(Icon image, int horizontalAlignment, Color col, Color bgCol) {
		super(image, horizontalAlignment);
		setDefaultConfiguration(1, col, bgCol);
	}

	/**
	 * 
	 * @param text
	 * @param icon
	 * @param horizontalAlignment
	 * @param fSize
	 * @param col
	 * @param bgCol
	 */
	public RacerLabel(String text, Icon icon, int horizontalAlignment, int fSize, Color col, Color bgCol) {
		super(text, icon, horizontalAlignment);
		setDefaultConfiguration(fSize, col, bgCol);
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	/**
	 * 
	 * @return
	 */
	public int getFontSize() {
		return fontSize;
	}

	/**
	 * 
	 * @param fontSize
	 */
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	/**
	 * 
	 * @param fSize
	 * @param col
	 * @param bgCol
	 */
	public void setDefaultConfiguration(int fSize, Color col, Color bgCol) {
			setFont(RacerPanel.getPrimaryFont().deriveFont(Font.ITALIC, fSize));
			setForeground(col);
			setBackground(bgCol);
		}
	
	/*@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		//g2d.translate(this.getX(), this.getY());
		g2d.rotate(Math.toRadians(180));
		this.repaint();
	}*/
	
}
