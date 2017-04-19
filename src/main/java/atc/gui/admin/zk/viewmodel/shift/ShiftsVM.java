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

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.ShiftEntry;
import atc.gui.admin.domain.service.repository.ShiftRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntitiesVM;

@Slf4j
@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
@Init(superclass = true)
public class ShiftsVM extends BaseEntitiesVM<ShiftEntry>
{
	private ShiftRepository repository;

	@WireVariable("shiftRepository")
	public void setRepository(ShiftRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected PathPart getEditPathPart(ShiftEntry entity)
	{
		return new EditPathPart("shift", entity.getId());
	}

	@Override
	protected void remove(ShiftEntry entity)
	{
		repository.deleteShift(entity.getId());
	}

	@Override
	protected void initDataList(List<ShiftEntry> dataList)
	{
		dataList.addAll(repository.getShiftList());
		log.info("total list " + dataList.size());
	}

}
