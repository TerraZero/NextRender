package tz.g.api;

public interface GEvent {

	public String type();
	
	public void consume();
	
	public boolean consumed();
	
	public void stopPropagation();
	
	public boolean propagation();
	
}
