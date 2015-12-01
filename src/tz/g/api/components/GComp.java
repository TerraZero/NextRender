package tz.g.api.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tz.g.api.events.GListener;
import tz.g.api.prop.GProp;
import tz.g.api.prop.GPropInt;

public abstract class GComp implements GComponent {
	
	public static void main(String[] args) {
		GComponent c = new GComp() {};
		GComponent c2 = new GComp() {};
		c.add(c2);
		System.out.println(c);
	}
	
	protected Map<String, GProp<?>> props;
	
	protected GPropInt x;
	protected GPropInt y;
	protected GPropInt width;
	protected GPropInt height;
	
	protected GComponent parent;
	protected List<GComponent> childs;
	
	protected Map<String, List<GListener>> listeners;

	public GComp() {
		this.init();
	}
	
	@Override
	public void init() {
		this.props = new HashMap<String, GProp<?>>();
		
		this.x = new GPropInt("x", this);
		this.y = new GPropInt("y", this);
		this.width = new GPropInt("w", this);
		this.height = new GPropInt("h", this);
			
		this.childs = new ArrayList<GComponent>();
		
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
	public GPropInt x() {
		return this.x;
	}

	@Override
	public GPropInt y() {
		return this.y;
	}

	@Override
	public GPropInt width() {
		return this.width;
	}

	@Override
	public GPropInt height() {
		return this.height;
	}

	@Override
	public GComponent parent() {
		return this.parent;
	}

	@Override
	public GComponent parent(GComponent parent) {
		this.parent = parent;
		return this;
	}

	@Override
	public List<GComponent> childrens() {
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
	public String toString() {
		return "GComp[" + this.propString() + "childs(" + this.childs.size() + ")]";
	}

}
