package Views;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class RacerPanel extends JPanel {

	public RacerPanel() {
		// TODO Auto-generated constructor stub
	}

	public RacerPanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public RacerPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public RacerPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
	
	public void Paint(Graphics2D g, Color c1, Color c2)
	{
		  GradientPaint gp = new GradientPaint((float) 0.0, (float )0.0,c1,(float)getWidth(),(float)getHeight(),c2); 
		  g.setPaint(gp);
		  //g.fill();
		}

}
