package tz.g;

import java.util.List;

import tz.g.api.components.TestComp;
import tz.g.ui.GCanvas;
import tz.g.ui.GFrame;
import tz.g.ui.GLoop;
import tz.g.ui.GState;

public class G {
	
	public static GFrame frame;
	public static GCanvas canvas;
	public static List<GState> states;
	public static GLoop loop;
	public static GState state;

	static {
		System.setProperty("sun.java2d.opengl", "true");
	}
	
	public static void main(String[] args) {
		G.init();
		G.frame.setTitle("Test");
		G.frame.setVisible(true);
	}
	
	public static void init() {
		G.frame = new GFrame();
		G.canvas = new GCanvas();
		G.loop = new GLoop();
		G.frame.add(G.canvas);
		G.state = new GState("main");
		TestComp c = new TestComp();
		c.x().setter(10, false);
		c.y().setter(10, false);
		c.width().setter(500, false);
		c.height().setter(200, false);
		G.state.add(c);
	}
	
}