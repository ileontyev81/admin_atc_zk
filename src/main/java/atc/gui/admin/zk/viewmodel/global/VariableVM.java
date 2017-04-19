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

package atc.gui.admin.zk.viewmodel.global;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.domain.model.VariableEntry;
import atc.gui.admin.domain.service.repository.GlobalRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntityVM;
@Init(superclass = true)
public class VariableVM extends BaseEntityVM<VariableEntry>
{
	private GlobalRepository repository;

	@WireVariable("globalRepository")
	public void setRepository(GlobalRepository repository)
	{
		this.repository = repository;
	}
	@Override
	protected VariableEntry loadEntity(String entityId)
	{
		return repository.getVariable(Integer.parseInt(entityId));
	}

	@Override
	protected void saveEntity(VariableEntry entity)
	{
		repository.saveVariable(entity);
	}

}
