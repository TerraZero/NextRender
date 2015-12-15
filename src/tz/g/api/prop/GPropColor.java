package tz.g.api.prop;

import java.awt.Color;

import tz.g.api.components.GComponent;
import tz.g.timing.CurveBezier;

public class GPropColor implements GProp<Color> {
	
	private GComponent<?> host;
	
	private float rStart;
	private float gStart;
	private float bStart;
	private float aStart;
	
	private float rDiff;
	private float gDiff;
	private float bDiff;
	private float aDiff;
	
	private float rCurrent;
	private float gCurrent;
	private float bCurrent;
	private float aCurrent;
	
	private float x;
	
	private String name;
	private CurveBezier timing;
	
	public GPropColor(String name, GComponent<?> host) {
		this.name = name;
		this.host(host);
		this.rCurrent = 0f;
		this.gCurrent = 0f;
		this.bCurrent = 0f;
		this.aCurrent = 0f;
		this.rStart = 0f;
		this.gStart = 0f;
		this.bStart = 0f;
		this.aStart = 0f;
		this.rDiff = 0f;
		this.gDiff = 0f;
		this.bDiff = 0f;
		this.aDiff = 0f;
		this.x = 0f;
		this.timing = new CurveBezier(CurveBezier.LINEAR);
	}

	@Override
	public String type() {
		return "color";
	}

	@Override
	public String name() {
		return this.name;
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
	public void style(String style) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setter(Color set, boolean ani) {
		if (ani) {
			Color c = this.current();
			this.rStart = c.getRed();
			this.gStart = c.getGreen();
			this.bStart = c.getBlue();
			this.aStart = c.getAlpha();
			this.rCurrent = 0f;
			this.gCurrent = 0f;
			this.bCurrent = 0f;
			this.aCurrent = 0f;
			this.rDiff = set.getRed() - this.rStart;
			this.gDiff = set.getGreen() - this.gStart;
			this.bDiff = set.getBlue() - this.bStart;
			this.aDiff = set.getAlpha() - this.aStart;
			this.x = 0f;
		} else {
			this.rStart = set.getRed();
			this.gStart = set.getGreen();
			this.bStart = set.getBlue();
			this.aStart = set.getAlpha();
			this.rCurrent = 1f;
			this.gCurrent = 1f;
			this.bCurrent = 1f;
			this.aCurrent = 1f;
			this.rDiff = 0f;
			this.gDiff = 0f;
			this.bDiff = 0f;
			this.aDiff = 0f;
			this.x = 1f;
		}
	}

	@Override
	public Color getter(boolean current) {
		if (current) {
			return this.current();
		} else {
			return new Color(
				this.rStart + this.rDiff,
				this.gStart + this.gDiff,
				this.bStart + this.bDiff,
				this.aStart + this.aDiff
			);
		}
	}

	@Override
	public void host(GComponent<?> host) {
		this.host = host;
	}

	@Override
	public GComponent<?> host() {
		return this.host;
	}

	@Override
	public void update(float delta) {
		if (this.x != 1f) {
			this.x += delta / 1000;
			if (this.x > 1f) this.x = 1f;
			this.rCurrent = this.timing.get(this.x);
			this.gCurrent = this.timing.get(this.x);
			this.bCurrent = this.timing.get(this.x);
			this.aCurrent = this.timing.get(this.x);
		}
	}
	
	public Color current() {
		return new Color(
			(int)(this.rStart + this.rDiff * this.rCurrent),
			(int)(this.gStart + this.gDiff * this.gCurrent),
			(int)(this.bStart + this.bDiff * this.bCurrent),
			(int)(this.aStart + this.aDiff * this.aCurrent)
		);
	}

}
