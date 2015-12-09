package tz.g.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GGraphics {

	private Graphics g;
	
	public GGraphics(Graphics g) {
		this.g = g;
	}
	
	public Graphics g() {
		return this.g;
	}
	
	public Graphics2D g2d() {
		return (Graphics2D)this.g;
	}
	
}
