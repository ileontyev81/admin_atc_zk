package atc.gui.admin.zk.event;

public interface EventHandler<T extends Event> 
{
	
	public void handleEvent(T event);

}
