package atc.gui.admin.zk.viewmodel.export;

public interface DataSourceFactory<T>
{
	
	public DataSource<T> createDataSource();
	
	public Class<T> getType();
	
}