package tz.g.ui;

import java.util.ArrayList;
import java.util.List;

import tz.g.api.components.GComponent;

public class GState {

	private List<GComponent<?>> components;
	private String name;
	
	public GState(String name) {
		this.name = name;
		this.components = new ArrayList<GComponent<?>>();
	}
	
	public String name() {
		return this.name;
	}
	
	public void add(GComponent<?> c) {
		this.components.add(c);
	}
	
	public void remove(GComponent<?> c) {
		this.components.remove(c);
	}
	
	public List<GComponent<?>> components() {
		return this.components;
	}
	
}
