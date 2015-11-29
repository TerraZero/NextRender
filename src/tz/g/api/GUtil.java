package tz.g.api;

public class GUtil {

	public static boolean isPointInner(GComponent c, int x, int y) {
		return c.x() < x && c.x() + c.width() > x && c.y() < y && c.y() + c.height() > y;
	}
	
}
