package tz.timing;

public class CurveBezier {
	
	// linear
	public static final String LINEAR = "linear";
	
	// ease
	public static final String EASE = "ease";
	public static final String EASE_IN = "ease-in";
	public static final String EASE_OUT = "ease-out";
	public static final String EASE_IN_OUT = "ease-in-out";
	
	// bounce
	public static final String BOUNCE_IN = "bounce-in";
	public static final String BOUNCE_OUT = "bounce-out";
	public static final String BOUNCE_IN_OUT = "bounce-in-out";
	
	// extra
	public static final String EXTRA_EASE = "extra-ease";
	
	public static void main(String[] args) {
		CurveBezier cb = new CurveBezier(CurveBezier.LINEAR);
		float last = 0f;
		float next = 0f;
		for (int i = 0; i < 11; i++) {
			next = cb.get(i / 10f);
			System.out.println(next - last);
			last = next;
		}
	}
	
	public static String[] defineds() {
		return new String[] {
			CurveBezier.EASE,
			CurveBezier.EASE_IN,
			CurveBezier.EASE_OUT,
			CurveBezier.EASE_IN_OUT,
			CurveBezier.LINEAR,
			CurveBezier.BOUNCE_IN,
			CurveBezier.BOUNCE_OUT,
			CurveBezier.BOUNCE_IN_OUT,
			CurveBezier.EXTRA_EASE,
		};
	}
	
	private float mX1;
	private float mY1;
	private float mX2;
	private float mY2;
	
	public CurveBezier() {
		this(null);
	}
	
	public CurveBezier(String name) {
		if (name == null || name.equals(CurveBezier.EASE)) {
			this.mX1 = 0.25f;
			this.mY1 = 0.1f;
			this.mX2 = 0.25f;
			this.mY2 = 1f;
		} else if (name.equals(CurveBezier.EASE_IN)) {
			this.mX1 = 0.42f;
			this.mY1 = 0f;
			this.mX2 = 1f;
			this.mY2 = 1f;
		} else if (name.equals(CurveBezier.EASE_OUT)) {
			this.mX1 = 0f;
			this.mY1 = 0f;
			this.mX2 = 0.58f;
			this.mY2 = 1f;
		} else if (name.equals(CurveBezier.EASE_IN_OUT)) {
			this.mX1 = 0.42f;
			this.mY1 = 0f;
			this.mX2 = 0.58f;
			this.mY2 = 1f;
		} else if (name.equals(CurveBezier.LINEAR)) {
			this.mX1 = 0f;
			this.mY1 = 0f;
			this.mX2 = 1f;
			this.mY2 = 1f;
		} else if (name.equals(CurveBezier.BOUNCE_IN)) {
			this.mX1 = 0.5f;
			this.mY1 = -0.5f;
			this.mX2 = 0.5f;
			this.mY2 = 1f;
		} else if (name.equals(CurveBezier.BOUNCE_OUT)) {
			this.mX1 = 0.5f;
			this.mY1 = 0f;
			this.mX2 = 0.5f;
			this.mY2 = 1.5f;
		} else if (name.equals(CurveBezier.BOUNCE_IN_OUT)) {
			this.mX1 = 0.5f;
			this.mY1 = -0.5f;
			this.mX2 = 0.5f;
			this.mY2 = 1.5f;
		} else if (name.equals(CurveBezier.EXTRA_EASE)) {
			this.mX1 = 0.6f;
			this.mY1 = 0.1f;
			this.mX2 = 0f;
			this.mY2 = 1f;
		}
	}

	public CurveBezier(float mX1, float mY1, float mX2, float mY2) {
		this.mX1 = mX1;
		this.mY1 = mY1;
		this.mX2 = mX2;
		this.mY2 = mY2;
	}
	
	public float get(float x) {
		if (this.mX1 == this.mY1 && this.mX2 == this.mY2) return x; // linear
	    return this.calcBezier(this.getTForX(x), this.mY1, this.mY2);
	}

    // Newton raphson iteration
	public float getTForX(float x) {
	    float aGuessT = x;
	    for (int i = 0; i < 4; ++i) {
	    	float currentSlope = this.getSlope(aGuessT, this.mX1, this.mX2);
	      	if (currentSlope == 0f) return aGuessT;
	      	float currentX = this.calcBezier(aGuessT, this.mX1, this.mX2) - x;
	      	aGuessT -= currentX / currentSlope;
	    }
	    return aGuessT;
	}
	
	public float calcA(float aA1, float aA2) { 
		return 1f - 3f * aA2 + 3f * aA1;
	}
	
	public float calcB(float aA1, float aA2) { 
		return 3f * aA2 - 6f * aA1; 
	}
	
	public float calcC(float aA1) { 
		return 3f * aA1; 
	}
	
	// Returns dx/dt given t, x1, and x2, or dy/dt given t, y1, and y2.
	public float getSlope(float aT, float aA1, float aA2) {
		return 3f * this.calcA(aA1, aA2) * aT * aT + 2f * this.calcB(aA1, aA2) * aT + this.calcC(aA1);
	}
	
	// Returns x(t) given t, x1, and x2, or y(t) given t, y1, and y2.
	public float calcBezier(float aT, float aA1, float aA2) {
		return ((this.calcA(aA1, aA2) * aT + this.calcB(aA1, aA2)) * aT + this.calcC(aA1)) * aT;
	}
	
}
