 package atc.gui.admin.zk.viewmodel;

import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.UserEntry;
import atc.gui.admin.domain.service.repository.UserRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntitiesVM;
import atc.gui.admin.zk.viewmodel.export.DataSource;
import atc.gui.admin.zk.viewmodel.export.DataSourceFactory;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
@Init(superclass=true)
public class UsersVM extends BaseEntitiesVM<UserEntry> implements DataSourceFactory<UserEntry>
{
	private UserRepository repository;
	
	@WireVariable("userRepository")
	public void setUserRepository(UserRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected PathPart getEditPathPart(UserEntry entity)
	{
		return new EditPathPart("user", entity.getId());
	}

	@Override
	protected void remove(UserEntry entity)
	{
		repository.removeUser(entity.getId());
	}

	@Override
	protected void initDataList(List<UserEntry> dataList)
	{
		dataList.addAll(repository.getUserList());
	}

	@Override
	public DataSource<UserEntry> createDataSource()
	{
		return new DataSource<UserEntry>(repository.getUserList());
	}

	@Override
	public Class<UserEntry> getType()
	{
		return getEntityClass();
	}

}
