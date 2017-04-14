package atc.gui.admin.zk.viewmodel;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.OperatorQueueEntry;
import atc.gui.admin.domain.service.repository.OperatorRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntityVM;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
@Init(superclass = true)
public class OperatorQueueVM extends BaseEntityVM<OperatorQueueEntry>
{
	private OperatorRepository repository;

	@WireVariable("operatorRepository")
	public void setRepository(OperatorRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected OperatorQueueEntry loadEntity(String entityId)
	{
		String parentId = getParentAttribute(EditPathPart.OBJECT_ID);
		return repository.getQueue(entityId != null ? Integer.parseInt(entityId) : 0, Integer.parseInt(parentId));
	}

	@Override
	protected void saveEntity(OperatorQueueEntry entity)
	{
		repository.saveQueue(entity);
	}

}
