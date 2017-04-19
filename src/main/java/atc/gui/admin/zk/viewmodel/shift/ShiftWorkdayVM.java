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

package atc.gui.admin.zk.viewmodel.shift;

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.ShiftWorkdayEntry;
import atc.gui.admin.domain.model.ShiftWorkdayTimeEntry;
import atc.gui.admin.domain.service.repository.ShiftRepository;
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
public class ShiftWorkdayVM extends BaseEntityVM<ShiftWorkdayEntry>
{
	private ShiftRepository repository;
	
	@Getter
	private List<ShiftWorkdayTimeEntry> timeList;
	
	@Getter @Setter
	private ShiftWorkdayTimeEntry timeSelected;
	
	@WireVariable("shiftRepository")
	public void setRepository(ShiftRepository repository)
	{
		this.repository = repository;
	}
	
	@Init(superclass = true)
	public void init()
	{
		if (getEntity().isPersisted())
		{
			timeList = repository.getWorkdayTimeList(getEntity().getId());
		}
	}
	
	
	public Class<ShiftWorkdayTimeEntry> getTimeClass()
	{
		return ShiftWorkdayTimeEntry.class;
	}

    @Override
	protected ShiftWorkdayEntry loadEntity(String entityId)
	{
		String parentId = getParentAttribute(EditPathPart.OBJECT_ID);
		return repository.getWorkday(entityId != null ? Integer.parseInt(entityId) : 0, Integer.parseInt(parentId));
	}

	@Override
	protected void saveEntity(ShiftWorkdayEntry entity)
	{
		repository.saveWorkday(getEntity());
	}
	
	@Command
	public void goAddTime()
	{
		navigateTo(new PathPart("workday_time"));
	}

	@Command
	public void editTime()
	{
		if (timeSelected == null)
			return;
		navigateTo(new EditPathPart("workday_time", timeSelected.getId()));
	}
	@NotifyChange("timeList")
	@Command
	public void deleteTime()
	{
		if (timeSelected == null)
			return;
		repository.deleteWorkdayTime(timeSelected.getId());
		
		timeList = timeList = repository.getWorkdayTimeList(getEntity().getId());
	}
}
