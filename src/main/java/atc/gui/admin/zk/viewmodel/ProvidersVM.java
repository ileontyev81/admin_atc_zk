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

import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.ProviderEntry;
import atc.gui.admin.domain.service.repository.ProviderRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntitiesVM;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
@Init(superclass=true)
public class ProvidersVM extends BaseEntitiesVM<ProviderEntry>
{
	private ProviderRepository repository;
	
	@WireVariable("providerRepository")
	public void setRepository(ProviderRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected PathPart getEditPathPart(ProviderEntry entity)
	{
		return new EditPathPart("provider", entity.getId());
	}

	@Override
	protected void remove(ProviderEntry entity)
	{
		repository.removeProvider(Integer.valueOf(entity.getId()));
	}

	@Override
	protected void initDataList(List<ProviderEntry> dataList)
	{
		dataList.addAll(repository.getProviders());
	}
    
}
