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
