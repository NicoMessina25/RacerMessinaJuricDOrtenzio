package Views.CustomComponents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;

import Interfaces.colorable;

public class RacerLabel extends JLabel implements colorable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9154301448000745661L;
	private int fontSize;
	private Color bgCol;
	private Color col;

	public RacerLabel() {
		// TODO Auto-generated constructor stub
	}

	public RacerLabel(String text, int fSize, Color col, Color bgCol) {
		super(text);
		setConfiguration(fSize, col, bgCol);
	}

	public RacerLabel(Icon image, Color col, Color bgCol) {
		super(image);
		setConfiguration(1, col, bgCol);
		// TODO Auto-generated constructor stub
	}

	public RacerLabel(String text, int horizontalAlignment, int fSize, Color col, Color bgCol) {
		super(text, horizontalAlignment);
		setConfiguration(fSize, col, bgCol);
		// TODO Auto-generated constructor stub
	}

	public RacerLabel(Icon image, int horizontalAlignment, Color col, Color bgCol) {
		super(image, horizontalAlignment);
		setConfiguration(1, col, bgCol);
		// TODO Auto-generated constructor stub
	}

	public RacerLabel(String text, Icon icon, int horizontalAlignment, int fSize, Color col, Color bgCol) {
		super(text, icon, horizontalAlignment);
		setConfiguration(fSize, col, bgCol);
		// TODO Auto-generated constructor stub
	}
	
	public void setConfiguration(int fSize, Color col, Color bgCol) {
		setFont(new Font("Swis721 Hv BT", Font.ITALIC, fSize));
		setCol(col);
		setBgCol(bgCol);
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	@Override
	public void setCol(Color col) {
		this.col = col;
		setForeground(col);
	}

	@Override
	public void setBgCol(Color bgCol) {
		this.bgCol = bgCol;
		setBackground(bgCol);
		
	}

	@Override
	public Color getCol() {
		// TODO Auto-generated method stub
		return col;
	}

	@Override
	public Color getBgCol() {
		// TODO Auto-generated method stub
		return bgCol;
	}

}
