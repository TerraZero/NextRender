package tz.g.api.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import tz.g.api.GUtil;
import tz.g.api.events.GEvent;
import tz.g.api.events.GListener;
import tz.g.api.functions.GComponentFunction;
import tz.g.api.prop.GProp;
import tz.g.api.prop.GPropInt;

public interface GComponent {	
	
	// PROPS DECLARATION
	
	public String[] classes();
	
	public Map<String, GProp<?>> props();
	
	
	
	// PROPS DIMENSION
	
	public GPropInt x();
	
	public GPropInt y();
	
	public GPropInt width();
	
	public GPropInt height();
	
	
	
	// PROPS NODES
	
	public GComponent parent();
	
	public GComponent parent(GComponent parent);
	
	public List<GComponent> childrens();
	

	
	// LISTENER
	
	public Map<String, List<GListener>> listener();
	
	public List<GListener> listener(String type);
	
	
	
	// CALL FUNCTIONS
	
	public void init();
	
	
	
	// DEFAULT PROPS

	public default GProp<?> prop(String name) {
		return this.props().get(name);
	}
	
	public default GComponent addProp(GProp<?> prop) {
		this.props().put(prop.name(), prop);
		return this;
	}
	
	public default GComponent removeProp(GProp<?> prop) {
		this.props().remove(prop.name());
		return this;
	}
	
	public default String propString() {
		String string = "";
		for (GProp<?> prop : this.props().values()) {
			string += prop + ", "; 
		}
		return string;
	}
	
	
	
	// DEFAULT FUNCTIONS
	
	public default GComponent function(GComponentFunction gf) {
		return this.functionComponent(gf).functionChildrens(gf);
	}
	
	public default GComponent functionComponent(GComponentFunction gf) {
		gf.trigger(this);
		return this;
	}
	
	public default GComponent functionChildrens(GComponentFunction gf) {
		for (GComponent child : this.childrens()) {
			if (!gf.consumed()) child.function(gf);
		}
		return this;
	}
	
	
	
	// DEFAULT EVENTS
	
	public default GComponent bind(String type, GListener listener) {
		this.listener(type).add(listener);
		return this;
	}
	
	public default GComponent unbind(String type) {
		return this.unbind(type, null);
	}
	
	public default GComponent unbind(String type, GListener listener) {
		if (listener == null) {
			this.listener(type).clear();
		} else {
			this.listener(type).remove(listener);
		}
		return this;
	}
	
	public default GComponent fire(GEvent e) {
		return this.fireComponent(e).fireParents(e);
	}
	
	public default GComponent fireComponent(GEvent e) {
		for (GListener listener : this.listener(e.type())) {
			listener.trigger(e);
		}
		return this;
	}
	
	public default GComponent fireParents(GEvent e) {
		if (e.propagation() && this.parent() != null) {
			this.parent().fire(e);
		}
		return this;
	}
	
	
	
	// DEFAULT CHILDS
	
	public default GComponent add(GComponent child) {
		child.parent(this);
		this.childrens().add(child);
		return this;
	}
	
	public default GComponent remove(GComponent child) {
		child.parent(null);
		this.childrens().remove(child);
		return this;
	}
	
	
	
	// DEFAULT INFO
	
	public default List<GComponent> componentsAt(int x, int y) {
		return this.componentsAt(new ArrayList<GComponent>(), x, y);
	}
	
	public default List<GComponent> componentsAt(List<GComponent> list, int x, int y) {
		this.componentsAtComponent(list, x, y).componentsAtChildrens(list, x, y);
		return list;
	}
	
	public default GComponent componentsAtComponent(List<GComponent> list, int x, int y) {
		if (GUtil.isPointInner(this, x, y)) {
			list.add(this);
		}
		return this;
	} 
	
	public default GComponent componentsAtChildrens(List<GComponent> list, int x, int y) {
		for (GComponent c : this.childrens()) {
			c.componentsAt(list, x, y);
		}
		return this;
	}
	
}
