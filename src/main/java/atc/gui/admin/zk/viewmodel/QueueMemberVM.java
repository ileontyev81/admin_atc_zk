package atc.gui.admin.zk.viewmodel;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.QueueMemberEntry;
import atc.gui.admin.domain.service.repository.QueueRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntityVM;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
@Init(superclass = true)
public class QueueMemberVM extends BaseEntityVM<QueueMemberEntry>
{
	private QueueRepository repository;

	@WireVariable("queueRepository")
	public void setRepository(QueueRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected QueueMemberEntry loadEntity(String entityId)
	{
		String parentId = getParentAttribute(EditPathPart.OBJECT_ID);
		return repository.getMember(entityId != null ? Integer.parseInt(entityId) : 0, Integer.parseInt(parentId));
	}

	@Override
	protected void saveEntity(QueueMemberEntry entity)
	{
		repository.saveMember(entity);
	}

}
