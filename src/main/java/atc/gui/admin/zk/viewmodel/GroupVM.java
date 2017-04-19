/*
 * Copyright (C) 2017 i.leontyev81@gmail.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

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
