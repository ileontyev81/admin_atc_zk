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

package atc.gui.admin.zk.viewmodel.action;

import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.ActionEntry;
import atc.gui.admin.domain.service.repository.ActionRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntitiesVM;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
@Init(superclass=true)
public class ActionsVM extends BaseEntitiesVM<ActionEntry>
{
	private ActionRepository repository;
	
	@WireVariable("actionRepository")
	public void setRepository(ActionRepository repository)
	{
		this.repository = repository;
	}

    public List<ActionEntry> getData()
    {
    	return repository.findActions();
    }

	@Override
	protected PathPart getEditPathPart(ActionEntry entity)
	{
		return new EditPathPart("action", entity.getId());
	}

	@Override
	protected void remove(ActionEntry entity)
	{
		repository.removeAction(entity.getId());
	}

	@Override
	protected void initDataList(List<ActionEntry> dataList)
	{
		dataList.addAll(repository.findActions());
	}
    
}
