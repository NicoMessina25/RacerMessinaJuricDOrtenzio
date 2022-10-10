package Views.CustomComponents;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class RacerPanel extends JPanel {

	public RacerPanel(Color col, Color bgCol, Color borderCol) {
		setDefaultConfiguration(col, bgCol, borderCol);
	}

	public RacerPanel(LayoutManager layout, Color col, Color bgCol, Color borderCol) {
		super(layout);
		setDefaultConfiguration(col, bgCol, borderCol);
	}
	
	public void setDefaultConfiguration(Color col, Color bgCol, Color borderCol) {
		setForeground(col);
		setBackground(bgCol);
		setBorder(new LineBorder(borderCol, 6));
	}

}
