package tz.g.api.prop;

import tz.g.api.components.GComponent;

public class GPropInt implements GProp<Integer> {
	
	private GComponent<?> host;
	private Integer current;
	private Integer target;
	private String name;
	
	public GPropInt(String name, GComponent<?> host) {
		this.name = name;
		this.host(host);
		this.current = 0;
		this.target = null;
	}

	@Override
	public void setter(Integer set, boolean ani) {
		if (ani) {
			this.target = set;
		} else {
			this.target = null;
			this.current = set;
		}
	}

	@Override
	public Integer getter(boolean current) {
		if (current) {
			return this.current;
		} else {
			return this.target;
		}
	}

	@Override
	public void host(GComponent<?> host) {
		if (this.host != null) {
			this.host.removeProp(this);
		}
		this.host = host;
		this.host.addProp(this);
	}

	@Override
	public GComponent<?> host() {
		return this.host;
	}
	
	 @Override
	public String toString() {
		 return this.name + ":" + this.get();
	}

	@Override
	public String type() {
		return "int";
	}
	
	@Override
	public String name() {
		return this.name;
	}
	
	public int get() {
		return this.get(true);
	}
	
	public int get(boolean current) {
		if (current) {
			return this.current;
		} else {
			return this.target;
		}
	}

}
