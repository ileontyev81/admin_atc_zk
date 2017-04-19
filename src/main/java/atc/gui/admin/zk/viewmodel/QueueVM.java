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
import atc.gui.admin.domain.model.QueueEntry;
import atc.gui.admin.domain.model.QueueMemberEntry;
import atc.gui.admin.domain.service.repository.QueueRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntityVM;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.List;

@Slf4j
@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
public class QueueVM extends BaseEntityVM<QueueEntry>
{
	@Getter
	@Setter
	private QueueMemberEntry memberSelected;
	
	@Getter
	private List<QueueMemberEntry> memberList;
	
	private QueueRepository repository;

	@Init(superclass = true)
	public void init()
	{
		if (getEntity().isPersisted())
		{
			memberList = repository.getMembers(getEntity().getId());
		}
	}
	
	@WireVariable("queueRepository")
	public void setRepository(QueueRepository repository)
	{
		this.repository = repository;
	}

	public Class<QueueMemberEntry> getMemberClass()
	{
		return QueueMemberEntry.class;
	}
	
	@Override
	protected QueueEntry loadEntity(String entityId)
	{
		log.info("get queue " + entityId);
		QueueEntry qe = repository.getQueue(Integer.valueOf(entityId));
		return qe;
	}

	@Override
	protected void saveEntity(QueueEntry entity)
	{
		repository.saveQueue(entity);
	}
    
	
	
	@NotifyChange("memberList")
	@Command
	public void deleteMember()
	{
		if (memberSelected == null)
			return;
		
		repository.deleteMember(memberSelected.getId());
		
		memberList = repository.getMembers(getEntity().getId());
	}
	
	@Command
	public void goAddMember()
	{
		navigateTo(new PathPart("queue_member"));
	}
	
	@Command
	public void editMember()
	{
		if (memberSelected == null)
			return;
		navigateTo(new EditPathPart("queue_member", memberSelected.getId()));
		
	}
}
