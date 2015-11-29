package tz.timing;

import tz.sys.reflect.annot.Program;
import tz.sys.vui.VUI;
import tz.sys.vui.input.VUISelect;
import tz.sys.vui.string.VUIStringLoad;

@Program(name="Timing Test")
public class TimingTest {

	public static void program() {
		VUI.clear();
		VUIStringLoad l = VUI.write(new VUIStringLoad());
		VUISelect s = VUI.write(new VUISelect("Timing Function:", CurveBezier.defineds()));
		CurveBezier cb = new CurveBezier(s.input());
		VUI.write("Function: " + s.input());
		for (int i = 0; i <= 100; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			l.load(cb.get(i / 100f) * 100);
		}
	}
	
}