package tz.g.api.functions;

import tz.g.api.components.GComponent;

public interface GComponentFunction {
	
	public boolean consumed();
	
	public void consume();

	public default void trigger(GComponent component) {
		this.function(component);
	}
	
	public void function(GComponent component);
	
}
