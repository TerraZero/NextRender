package tz.g.api.render;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Map;

import tz.g.api.components.GComponent;
import tz.g.api.components.TestComp;
import tz.g.api.prop.GProp;
import tz.g.ui.GGraphics;

public class TestRender implements GRender<TestComp> {

	@Override
	public void update(GComponent<TestComp> c, float delta) {
		Map<String, GProp<?>> props = c.props();
		props.forEach((s, p) -> {
			p.update(delta);
		});
	}

	@Override
	public void render(GComponent<TestComp> c, GGraphics g) {
		Graphics2D g2 = g.g2d();
		g2.setColor((Color)c.prop("color").getter());
		g2.fillRect(c.x().i(), c.y().i(), c.width().i(), c.height().i());
		g2.setColor(Color.BLACK);
	}

}
