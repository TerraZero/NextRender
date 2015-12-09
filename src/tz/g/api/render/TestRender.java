package tz.g.api.render;

import java.awt.Color;
import java.awt.Graphics2D;

import tz.g.api.components.GComponent;
import tz.g.api.components.TestComp;
import tz.g.ui.GGraphics;

public class TestRender implements GRender<TestComp> {

	@Override
	public void update(GComponent<TestComp> c, float delta) {
		
	}

	@Override
	public void render(GComponent<TestComp> c, GGraphics g) {
		Graphics2D g2 = g.g2d();
		g2.setColor(Color.BLUE);
		g2.fillRect(c.x().get(), c.y().get(), c.width().get(), c.height().get());
		g2.setColor(Color.BLACK);
	}

}
