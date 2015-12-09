package tz.g.api.render;

import tz.g.api.components.GComponent;
import tz.g.ui.GGraphics;

public interface GRender<component extends GComponent<component>> {

	public void update(GComponent<component> c, float delta);
	
	public void render(GComponent<component> c, GGraphics g);
	
}
