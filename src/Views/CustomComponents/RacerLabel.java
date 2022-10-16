package Views.CustomComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class RacerLabel extends JLabel {

	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
			
			//----------------------------------------->|CONSTANTS|<-----------------------------------------------\\
	
	private static final long serialVersionUID = 9154301448000745661L;
	
			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	private int fontSize;
	private int spin = 45;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

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
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	
	public int getSpin() {
		return spin;
	}

	public void setSpin(int spin) {
		this.spin = spin;
	}

	public void setIcon(ImageIcon imgIcon, int height) {
		super.setIcon(new ImageIcon(imgIcon.getImage().getScaledInstance(imgIcon.getIconWidth()*height/imgIcon.getIconHeight(), height, Image.SCALE_DEFAULT)));
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	public void setDefaultConfiguration(int fSize, Color col, Color bgCol) {
			setFont(new Font(RacerPanel.getPrimaryFontFamily(), Font.ITALIC, fSize));
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
