package Views;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;
	private int height;
	//private int i = 0;

	  public ImagePanel(String img, int h) {
	    this(new ImageIcon(img).getImage(),h);
	    
	  }

	  public ImagePanel(Image img, int h) {
		height = h;
	    setImg(img);
	  }

	  public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
		//System.out.println(b + " " + (++i));
	  }

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		BufferedImage bi = new BufferedImage(img.getWidth(null)*height/img.getHeight(null), height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D)bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(img, 0, 0, bi.getWidth(), bi.getHeight(), null);
	    Dimension size = new Dimension(bi.getWidth(null), bi.getHeight(null));
	    this.img = bi;
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    
		//this.img = img;
	}


}


