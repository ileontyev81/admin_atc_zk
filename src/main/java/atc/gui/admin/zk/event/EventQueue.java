package atc.gui.admin.zk.event;

public interface EventQueue
{

	public void init();
	
	public <T extends Event> void addHandler(Class<T> eventType, EventHandler<T> handler);
	
	public <T extends Event> void publishEvent(T event);
	
}
