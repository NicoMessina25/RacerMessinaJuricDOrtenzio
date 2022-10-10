package Views.CustomComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import Controller.RacerBoard;
import Interfaces.colorable;

public class RacerButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2699829771131612801L;
	private Font font;

	public RacerButton(Color col, Color bgCol) throws HeadlessException {
		setDefaultConfiguration(col, bgCol);
		
		
	}

	public RacerButton(String label, Color col, Color bgCol) throws HeadlessException {
		super(label);
		setDefaultConfiguration(col, bgCol);
		
		// TODO Auto-generated constructor stub
	}
	
	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
	
	public void setDefaultConfiguration(Color col, Color bgCol) {
		setForeground(col.darker());
		setBackground(bgCol.brighter());
		addMouseListener(new EventButton());
		setBorderPainted(false);
		setFocusable(false);
		font = new Font(RacerBoard.getPRIMARY_FONT_FAMILY(), Font.BOLD | Font.ITALIC, 26);
	}

	public class EventButton extends MouseAdapter{
		
		@Override
		public void mouseEntered(MouseEvent e) {
			invertColors();
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			invertColors();
		}
		
	}


	public void invertColors() {
		Color aux = getForeground();
		setForeground(getBackground());
		setBackground(aux);
	}

}
