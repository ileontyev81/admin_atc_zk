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
import atc.gui.admin.domain.model.OperatorEntry;
import atc.gui.admin.domain.model.OperatorQueueEntry;
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
public class OperatorVM extends BaseEntityVM<OperatorEntry>
{
	private OperatorRepository repository;

	@Getter
	@Setter
	private OperatorQueueEntry queueSelected;
	
	@Getter
	private List<OperatorQueueEntry> queueList;
	
	@WireVariable("operatorRepository")
	public void setRepository(OperatorRepository repository)
	{
		this.repository = repository;
	}
	
	public Class<OperatorQueueEntry> getQueueClass()
	{
		return OperatorQueueEntry.class;
	}
	
	@Init(superclass = true)
	public void init()
	{
		if (getEntity().isPersisted())
		{
			queueList = repository.getQueueList(getEntity().getId());
		}
	}
	
	@Override
	protected OperatorEntry loadEntity(String entityId)
	{
		return repository.getOperator(Integer.parseInt(entityId));
	}

	@Override
	protected void saveEntity(OperatorEntry entity)
	{
		repository.saveOperator(entity);
	}

	@NotifyChange("queueList")
	@Command
	public void deleteQueue()
	{
		if (queueSelected == null)
			return;
		repository.deleteQueue(queueSelected.getId());
		
		queueList = repository.getQueueList(getEntity().getId());
	}
	
	@Command
	public void editQueue()
	{
		if (queueSelected == null)
			return;
		navigateTo(new EditPathPart("operator_queue", queueSelected.getId()));
		
	}
	@Command
	public void goAddQueue()
	{
		navigateTo(new PathPart("operator_queue"));
	}
	
}
