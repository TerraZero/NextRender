package tz.g.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface GComponent {
	
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
	
	
	
	public String[] classes();
	

	
	public Map<String, List<GListener>> listener();
	
	public default List<GListener> listener(String type) {
		return this.listener().get(type);
	}

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
	
	
	
	public int x();
	
	public int y();
	
	public int width();
	
	public int height();
	
	
	
	public GComponent parent();
	
	public GComponent setParent(GComponent parent);
	
	public List<GComponent> childrens();
	
	public default GComponent add(GComponent child) {
		child.setParent(this).childrens().add(child);
		return this;
	}
	
	public default GComponent remove(GComponent child) {
		child.setParent(null).childrens().remove(child);
		return this;
	}
	
	
	
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
