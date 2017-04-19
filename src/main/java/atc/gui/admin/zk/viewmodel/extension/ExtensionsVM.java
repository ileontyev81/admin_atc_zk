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

package atc.gui.admin.zk.viewmodel.extension;

import atc.gui.admin.domain.model.ExtensionEntry;
import atc.gui.admin.domain.service.repository.ExtensionRepository;
import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.zk.viewmodel.base.BaseEntitiesVM;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.List;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
@Slf4j
@Init(superclass = true)
public class ExtensionsVM extends BaseEntitiesVM<ExtensionEntry>
{
	private ExtensionRepository repository;

	@WireVariable("extensionRepository")
	public void setRepository(ExtensionRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected void remove(ExtensionEntry entity)
	{
		repository.removeExtension(entity.getId());
	}

	@Override
	protected void initDataList(List<ExtensionEntry> dataList)
	{
		dataList.addAll(repository.findExtensions());
	}

	@Override
	protected PathPart getEditPathPart(ExtensionEntry entity)
	{
		return new EditPathPart("extension", entity.getId());
	}
	
	@NotifyChange("dataList")
	@Command
	public void changeActiveExtension()
	{
		if (getSelected() != null) {
            repository.changeActiveExtension(getSelected().getId());
            getDataList().clear();
            getDataList().addAll(repository.findExtensions());
        }
	}

}
