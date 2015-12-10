package tz.g.api.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tz.g.api.events.GListener;
import tz.g.api.prop.GProp;
import tz.g.api.prop.GPropFloat;
import tz.g.api.render.GRender;

public abstract class GComp<component extends GComp<component>> implements GComponent<component> {
	
	protected Map<String, GProp<?>> props;
	
	protected GPropFloat x;
	protected GPropFloat y;
	protected GPropFloat width;
	protected GPropFloat height;
	
	protected GComponent<?> parent;
	protected List<GComponent<?>> childs;
	
	protected Map<String, List<GListener>> listeners;
	
	protected GRender<component> render;

	public GComp() {
		this.init();
	}
	
	@Override
	public void init() {
		this.props = new HashMap<String, GProp<?>>();
		
		this.x = new GPropFloat("x", this);
		this.y = new GPropFloat("y", this);
		this.width = new GPropFloat("w", this);
		this.height = new GPropFloat("h", this);
			
		this.childs = new ArrayList<GComponent<?>>();
		
		this.listeners = new HashMap<String, List<GListener>>();
	}

	@Override
	public String[] classes() {
		return null;
	}
	
	@Override
	public Map<String, GProp<?>> props() {
		return this.props;
	}

	@Override
	public GPropFloat x() {
		return this.x;
	}

	@Override
	public GPropFloat y() {
		return this.y;
	}

	@Override
	public GPropFloat width() {
		return this.width;
	}

	@Override
	public GPropFloat height() {
		return this.height;
	}

	@Override
	public GComponent<?> parent() {
		return this.parent;
	}

	@Override
	public GComponent<component> parent(GComponent<?> parent) {
		this.parent = parent;
		return this;
	}

	@Override
	public List<GComponent<?>> childrens() {
		return this.childs;
	}

	@Override
	public Map<String, List<GListener>> listener() {
		return this.listeners;
	}

	@Override
	public List<GListener> listener(String type) {
		if (this.listeners.containsKey(type)) {
			return this.listeners.get(type);
		} else {
			List<GListener> listeners = new ArrayList<GListener>();
			this.listeners.put(type, listeners);
			return listeners;
		}
	}
	
	@Override
	public GComponent<component> render(GRender<component> render) {
		this.render = render;
		return this;
	}

	@Override
	public GRender<component> render() {
		return this.render;
	}
	
	@Override
	public String toString() {
		return "GComp[" + this.propString() + "childs(" + this.childs.size() + ")]";
	}

}
