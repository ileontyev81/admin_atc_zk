package atc.gui.admin.zk.viewmodel;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.GroupSupervisorEntry;
import atc.gui.admin.domain.service.repository.OperatorRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntityVM;

@Init(superclass=true)
public class GroupSupervisorVM extends BaseEntityVM<GroupSupervisorEntry>
{
	private OperatorRepository repository;
	
	@WireVariable("operatorRepository")
	public void setRepository(OperatorRepository repository)
	{
		this.repository = repository;
	}
	
	
	protected GroupSupervisorEntry loadEntity(String entityId)
	{
		return repository.getGroupSupervisor(entityId != null ? Integer.parseInt(entityId) : 0);
	}

	@Override
	protected void saveEntity(GroupSupervisorEntry entity)
	{
		entity.setGroupId(Integer.parseInt(getParentAttribute(EditPathPart.OBJECT_ID)));
		repository.saveGroupSupervisor(entity);
	}

}
