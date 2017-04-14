package atc.gui.admin.zk.viewmodel;

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.GroupEntry;
import atc.gui.admin.domain.model.GroupSupervisorEntry;
import atc.gui.admin.domain.service.repository.OperatorRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntityVM;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.List;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
public class GroupVM  extends BaseEntityVM<GroupEntry>
{
	private OperatorRepository repository;
	
	@Getter
	@Setter
	private GroupSupervisorEntry supervisorSelected;
	@Getter
	private List<GroupSupervisorEntry> supervisorList;
	
	
	@WireVariable("operatorRepository")
	public void setRepository(OperatorRepository repository)
	{
		this.repository = repository;
	}
	
	@Init(superclass = true)
	public void init()
	{
		if (getEntity().isPersisted())
		{
			supervisorList = repository.getGroupSupervisorList(getEntity().getId());
		}
	}
	
	public Class<GroupSupervisorEntry> getSupervisorClass()
	{
		return GroupSupervisorEntry.class;
	}
	
	
	@Command
	public void goAddSupervisor()
	{
		navigateTo(new PathPart("group_supervisor"));
	}
	
	@Command
	public void editSupervisor()
	{
		if (supervisorSelected == null)
			return;
		navigateTo(new EditPathPart("group_supervisor", supervisorSelected.getId()));
	}
	
	@NotifyChange("supervisorList")
	@Command
	public void deleteSupervisor()
	{
		if (supervisorSelected == null)
			return;
		repository.deleteGroupSupervisor(supervisorSelected.getId());
		supervisorList = repository.getGroupSupervisorList(getEntity().getId());
	}
	
	@Override
	protected GroupEntry loadEntity(String entityId)
	{
		return repository.getGroup(Integer.parseInt(entityId));
	}

	@Override
	protected void saveEntity(GroupEntry entity)
	{
		repository.saveGroup(entity);
	}

}
