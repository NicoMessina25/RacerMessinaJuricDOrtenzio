package com.racer.views.CustomComponents;

import java.awt.Color;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class RacerIcon extends RacerLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4244351991405367937L;

	public RacerIcon() {
		// TODO Auto-generated constructor stub
	}

	public RacerIcon(String text, int fSize, Color col, Color bgCol) {
		super(text, fSize, col, bgCol);
		// TODO Auto-generated constructor stub
	}

	public RacerIcon(Icon image, Color col, Color bgCol) {
		super(image, col, bgCol);
		// TODO Auto-generated constructor stub
	}

	public RacerIcon(String text, int horizontalAlignment, int fSize, Color col, Color bgCol) {
		super(text, horizontalAlignment, fSize, col, bgCol);
		// TODO Auto-generated constructor stub
	}

	public RacerIcon(Icon image, int horizontalAlignment, Color col, Color bgCol) {
		super(image, horizontalAlignment, col, bgCol);
		// TODO Auto-generated constructor stub
	}

	public RacerIcon(String text, Icon icon, int horizontalAlignment, int fSize, Color col, Color bgCol) {
		super(text, icon, horizontalAlignment, fSize, col, bgCol);
		// TODO Auto-generated constructor stub
	}
	
	public RacerIcon(ImageIcon imgIcon, int height) {
		setIcon(imgIcon, height);
	}
	
	/**
	 * 
	 * @param imgIcon
	 * @param height
	 */
	public void setIcon(ImageIcon imgIcon, int height) {
		super.setIcon(new ImageIcon(imgIcon.getImage().getScaledInstance(imgIcon.getIconWidth()*height/imgIcon.getIconHeight(), height, Image.SCALE_DEFAULT)));
	}

}
