package tz.g.api.prop;

import tz.g.api.components.GComponent;

public interface GProp<type> {
	
	public String type();
	
	public String name();
	
	
	
	public void setter(type set, boolean ani);
	
	public type getter(boolean current);
	
	public void host(GComponent host);
	
	public GComponent host();
	
	
	
	public default void setter(type set) {
		this.setter(set, true);
	}
	
	public default type getter() {
		return this.getter(true);
	}

}
