package atc.gui.admin.zk.viewmodel.extension.action;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.CallerIdEntry;
import atc.gui.admin.domain.service.repository.ExtensionRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntityVM;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
@Init(superclass = true)
public class CallerIdVM extends BaseEntityVM<CallerIdEntry>
{
	private ExtensionRepository repository;
	
	@WireVariable("extensionRepository")
	public void setRepository(ExtensionRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected CallerIdEntry loadEntity(String entityId)
	{
		String parentId = getParentAttribute(EditPathPart.OBJECT_ID);
		return repository.getActionCallerId(entityId != null ? Integer.parseInt(entityId) : 0, Integer.parseInt(parentId));
	}

	@Override
	protected void saveEntity(CallerIdEntry entity)
	{
		repository.saveActionCallerId(entity);
	}
}
