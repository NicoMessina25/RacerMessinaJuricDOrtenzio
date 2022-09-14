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
	//private int i = 0;

	  public ImagePanel(String img) {
	    this(new ImageIcon(img).getImage());
	  }

	  public ImagePanel(Image img) {
        /*BufferedImage bi = new BufferedImage(img.getWidth(null)*100/img.getHeight(null), 200, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D)bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY));
        boolean b = g2d.drawImage(img, 0, 0, bi.getWidth(), bi.getHeight(), null);
        System.out.println(b);
	    Dimension size = new Dimension(bi.getWidth(null), bi.getHeight(null));
	    this.img = bi;
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);*/
	    setImg(img);
	  }

	  public void paintComponent(Graphics g) {
		boolean b = g.drawImage(img, 0, 0, null);
		//System.out.println(b + " " + (++i));
	  }

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		BufferedImage bi = new BufferedImage(img.getWidth(null)*200/img.getHeight(null), 200, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D)bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY));
        boolean b = g2d.drawImage(img, 0, 0, bi.getWidth(), bi.getHeight(), null);
        //System.out.println(b);
	    Dimension size = new Dimension(bi.getWidth(null), bi.getHeight(null));
	    this.img = bi;
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    
		//this.img = img;
	}

}


