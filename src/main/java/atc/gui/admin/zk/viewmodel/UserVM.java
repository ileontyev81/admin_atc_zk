package atc.gui.admin.zk.viewmodel;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.domain.model.UserEntry;
import atc.gui.admin.domain.service.repository.UserRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntityVM;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
@Init(superclass=true)
public class UserVM extends BaseEntityVM<UserEntry>
{
	private UserRepository repository;
	
	@WireVariable("userRepository")
	public void setRepository(UserRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected UserEntry loadEntity(String entityId)
	{
		return repository.getUser(Integer.valueOf(entityId));
	}

	@Override
	protected void saveEntity(UserEntry entity)
	{
		repository.saveUser(entity);
	}

}
