package tz.g.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferStrategy;

import tz.g.G;

public class GCanvas extends Canvas {

private static final long serialVersionUID = 1L;
	
	private static GCanvas singleton;

	protected BufferStrategy bs;
	
	public static GCanvas frame() {
		if (GCanvas.singleton == null) {
			GCanvas.singleton = new GCanvas();
		}
		return GCanvas.singleton;
	}
	
	public GCanvas() {
		this.init();
	}
	
	protected void init() {
		this.setBackground(Color.BLACK);
		G.frame.addComponentListener(new ComponentAdapter() {
			
			public void componentShown(ComponentEvent e) {
				GCanvas.this.createBuffer();
				G.loop.start();
			}
			
			public void componentResized(ComponentEvent e) {
				GCanvas.this.setSize(G.frame.getWidth(), G.frame.getHeight());
			}
			
		});
	}
	
	public void createBuffer() {
		this.createBufferStrategy(3);
		this.bs = this.getBufferStrategy();
	}
	
	public GGraphics getShowGraphics() {
		Graphics g =  this.bs.getDrawGraphics();
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		return new GGraphics(g2);
	}
	
	public void show() {
		this.bs.show();
	}
	
	public void interruptHook(boolean interrupt) {
		if (!interrupt) {
			this.createBuffer();
		}
	}
	
}
