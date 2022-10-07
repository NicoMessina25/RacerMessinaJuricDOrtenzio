package Views.CustomComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import Interfaces.colorable;

public class RacerButton extends JButton implements colorable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2699829771131612801L;
	private Color col, bgCol;
	private Font font;

	public RacerButton(Color col, Color bgCol) throws HeadlessException {
		setDefaultConfiguration(col, bgCol);
		
		
	}

	public RacerButton(String label, Color col, Color bgCol) throws HeadlessException {
		super(label);
		setDefaultConfiguration(col, bgCol);
		
		// TODO Auto-generated constructor stub
	}

	public Color getCol() {
		return col;
	}

	public Color getBgCol() {
		return bgCol;
	}
	
	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
	
	public void setDefaultConfiguration(Color col, Color bgCol) {
		setCol(col);
		setBgCol(bgCol);
		addMouseListener(new EventButton());
		setBorderPainted(false);
		setFocusable(false);
		font = new Font("Swis721 Hv BT", Font.BOLD | Font.ITALIC, 26);
		setFont(font);
	}

	public class EventButton extends MouseAdapter{
		
		@Override
		public void mouseEntered(MouseEvent e) {
			setBackground(col);
			setForeground(bgCol);
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			setForeground(col);
			setBackground(bgCol);
		}
		
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

	

}
