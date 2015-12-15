package tz.g.api.prop;

import tz.g.api.components.GComponent;
import tz.g.timing.CurveBezier;

public class GPropFloat implements GProp<Float> {
	
	private GComponent<?> host;
	
	private float current;
	private float x;
	private float diff;
	private float start;
	
	private String name;
	private CurveBezier timing;
	
	public GPropFloat(String name, GComponent<?> host) {
		this.name = name;
		this.host(host);
		this.current = 0f;
		this.x = 0f;
		this.start = 0f;
		this.diff = 0f;
		this.timing = new CurveBezier(CurveBezier.LINEAR);
	}

	@Override
	public void setter(Float set, boolean ani) {
		this.set(set, ani);
	}

	@Override
	public Float getter(boolean current) {
		return this.get(current);
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
	
	public void set(float set, boolean ani) {
		if (ani) {
			this.start = this.current();
			this.current = 0f;
			this.x = 0f;
			this.diff = set - this.start;
		} else {
			this.x = 1f;
			this.start = set;
			this.current = 1f;
			this.diff = 0f;
		}
	}
	
	public void set(float set) {
		this.set(set, true);
	}
	
	public float get() {
		return this.get(true);
	}
	
	public float get(boolean current) {
		if (current) {
			return this.current();
		} else {
			return this.start + this.diff;
		}
	}
	
	public int i() {
		return (int)this.get(true);
	}
	
	public float current() {
		return this.start + this.diff * this.current;
	}

	@Override
	public CurveBezier timing() {
		return this.timing;
	}

	@Override
	public void timing(CurveBezier timing) {
		this.timing = timing;
	}

	@Override
	public void update(float delta) {
		if (this.x != 1f) {
			this.x += delta / 1000;
			if (this.x > 1f) this.x = 1f;
			this.current = this.timing.get(this.x);
		}
	}

	@Override
	public void style(String style) {
		
	}

}
