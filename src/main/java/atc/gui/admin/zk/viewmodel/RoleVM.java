package atc.gui.admin.zk.viewmodel;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.domain.model.AccessRoleEntry;
import atc.gui.admin.domain.service.repository.UserRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntityVM;

@Init(superclass = true)
public class RoleVM extends BaseEntityVM<AccessRoleEntry>
{
	private UserRepository repository;

	@WireVariable("userRepository")
	public void setRepository(UserRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected AccessRoleEntry loadEntity(String entityId)
	{
		return repository.getRole(Integer.valueOf(entityId));
	}

	@Override
	protected void saveEntity(AccessRoleEntry entity)
	{
		repository.saveRole(entity);
	}
}
