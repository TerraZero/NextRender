package tz.g.api;

import tz.g.api.components.GComponent;

public class GUtil {

	public static boolean isPointInner(GComponent<?> c, int x, int y) {
		return c.x().get() < x && c.x().get() + c.width().get() > x && c.y().get() < y && c.y().get() + c.height().get() > y;
	}
	
}
