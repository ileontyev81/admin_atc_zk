package atc.gui.admin.zk;

public interface Command<T>
{

	public void execute(T entity);
	
}
