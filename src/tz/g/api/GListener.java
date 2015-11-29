package tz.g.api;

public interface GListener {
	
	public default void trigger(GEvent e) {
		if (!e.consumed()) this.function(e);
	}

	public void function(GEvent e);
	
}
