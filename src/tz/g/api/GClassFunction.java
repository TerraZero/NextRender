package tz.g.api;

import tz.sys.Sys;

public interface GClassFunction extends GComponentFunction {
	
	public String[] classes();

	@Override
	public default void trigger(GComponent component) {
		if (Sys.isIntern(component.classes(), this.classes())) {
			this.function(component);
		}
	}
	
}
