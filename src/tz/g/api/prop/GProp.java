package tz.g.api.prop;

import tz.g.api.components.GComponent;
import tz.g.timing.CurveBezier;

public interface GProp<type> {
	
	public String type();
	
	public String name();
	
	public CurveBezier timing();
	
	public void timing(CurveBezier timing);
	
	public default void timing(String timing) {
		this.timing(new CurveBezier(timing));
	}
	
	public void style(String style);
	
	
	
	public void setter(type set, boolean ani);
	
	public type getter(boolean current);
	
	public void host(GComponent<?> host);
	
	public GComponent<?> host();
	
	
	
	public default void setter(type set) {
		this.setter(set, true);
	}
	
	public default type getter() {
		return this.getter(true);
	}
	
	
	
	public void update(float delta);

}
