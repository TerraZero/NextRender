package tz.g.api.components;

import tz.g.api.render.TestRender;

public class TestComp extends GComp<TestComp> {

	public TestComp() {
		this.render = new TestRender();
	}
	
}
