package Views.CustomComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import Views.SoundClip;

public class RacerButton extends JButton {
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
			//----------------------------------------->|CONSTANTS|<-----------------------------------------------\\
	
	private static final long serialVersionUID = -2699829771131612801L;

			//----------------------------------------->|VARIABLES|<-----------------------------------------------\\
	
	private Font font;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
		

	public RacerButton(Color col, Color bgCol, SoundClip soundEffect) throws HeadlessException {
		setDefaultConfiguration(col, bgCol, soundEffect);

	}

	public RacerButton(String label, Color col, Color bgCol, SoundClip soundEffect) throws HeadlessException {
		super(label);
		setDefaultConfiguration(col, bgCol, soundEffect);

		// TODO Auto-generated constructor stub
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\

	public void setDefaultConfiguration(Color col, Color bgCol, SoundClip soundEffect) {
		setForeground(col.darker());
		setBackground(bgCol.brighter());
		addMouseListener(new EventButton());
		setBorderPainted(false);
		setFocusable(false);
		font = RacerPanel.getPrimaryFont().deriveFont(Font.BOLD | Font.ITALIC, 26); //Font.BOLD | Font.ITALIC, 26
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				soundEffect.play();
			}

		});
	}
	
	//------------------------------------------------>||INNER CLASSES||<--------------------------------------------------------\\

	private class EventButton extends MouseAdapter {

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
