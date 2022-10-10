package Views.CustomComponents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;

import Controller.RacerBoard;
import Interfaces.colorable;

public class RacerLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9154301448000745661L;
	private int fontSize;

	public RacerLabel() {
		// TODO Auto-generated constructor stub
	}

	public RacerLabel(String text, int fSize, Color col, Color bgCol) {
		super(text);
		setDefaultConfiguration(fSize, col, bgCol);
	}

	public RacerLabel(Icon image, Color col, Color bgCol) {
		super(image);
		setDefaultConfiguration(1, col, bgCol);
		// TODO Auto-generated constructor stub
	}

	public RacerLabel(String text, int horizontalAlignment, int fSize, Color col, Color bgCol) {
		super(text, horizontalAlignment);
		setDefaultConfiguration(fSize, col, bgCol);
		// TODO Auto-generated constructor stub
	}

	public RacerLabel(Icon image, int horizontalAlignment, Color col, Color bgCol) {
		super(image, horizontalAlignment);
		setDefaultConfiguration(1, col, bgCol);
		// TODO Auto-generated constructor stub
	}

	public RacerLabel(String text, Icon icon, int horizontalAlignment, int fSize, Color col, Color bgCol) {
		super(text, icon, horizontalAlignment);
		setDefaultConfiguration(fSize, col, bgCol);
		// TODO Auto-generated constructor stub
	}
	
	public void setDefaultConfiguration(int fSize, Color col, Color bgCol) {
		setFont(new Font(RacerBoard.getPRIMARY_FONT_FAMILY(), Font.ITALIC, fSize));
		setForeground(col);
		setBackground(bgCol);
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

}
