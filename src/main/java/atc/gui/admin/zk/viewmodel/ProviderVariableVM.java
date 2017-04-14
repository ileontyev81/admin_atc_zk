package atc.gui.admin.zk.viewmodel;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.VariableEntry;
import atc.gui.admin.domain.service.repository.ProviderRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntityVM;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
@Init(superclass = true)
public class ProviderVariableVM extends BaseEntityVM<VariableEntry>
{
	private ProviderRepository repository;

	@WireVariable("providerRepository")
	public void setRepository(ProviderRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected VariableEntry loadEntity(String entityId)
	{
		String parentId = getParentAttribute(EditPathPart.OBJECT_ID); 
		return repository.getVariable(entityId != null ? Integer.parseInt(entityId) : 0, Integer.parseInt(parentId));
	}

	@Override
	protected void saveEntity(VariableEntry entity)
	{
		repository.saveVariable(entity);
	}

}