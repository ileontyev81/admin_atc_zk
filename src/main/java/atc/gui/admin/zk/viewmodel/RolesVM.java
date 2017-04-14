package atc.gui.admin.zk.viewmodel;

import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.AccessRoleEntry;
import atc.gui.admin.domain.service.repository.UserRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntitiesVM;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
@Init(superclass=true)
public class RolesVM extends BaseEntitiesVM<AccessRoleEntry>
{
	private UserRepository repository;
	
	@WireVariable("userRepository")
	public void setUserRepository(UserRepository repository)
	{
		this.repository = repository;
	}
	@Override
	protected PathPart getEditPathPart(AccessRoleEntry entity)
	{
		return new EditPathPart("role", entity.getId());
	}

	@Override
	protected void remove(AccessRoleEntry entity)
	{
		repository.removeRole(entity.getId());
	}

	@Override
	protected void initDataList(List<AccessRoleEntry> dataList)
	{
		dataList.addAll(repository.getRoles());
	}

	
}



