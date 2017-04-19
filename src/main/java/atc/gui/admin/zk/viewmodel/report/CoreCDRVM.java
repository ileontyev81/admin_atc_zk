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

package atc.gui.admin.zk.viewmodel.report;

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.domain.model.CDREntry;
import atc.gui.admin.domain.model.CDRSearchEntry;
import atc.gui.admin.domain.service.repository.ReportRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntitiesVM;
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
public class CoreCDRVM extends BaseEntitiesVM<CDREntry>
{
	@Getter @Setter
	private CDRSearchEntry searchEntry;
	
	private ReportRepository repository;

	@Init(superclass = true)
	public void init()
	{
		searchEntry = repository.getCoreCDRSearch();
	}

	@WireVariable("reportRepository")
	public void setRepository(ReportRepository r)
	{
		repository = r;
	}
	
	public Class<CDREntry> getCdrClass()
	{
		return CDREntry.class;
	}
	
	@NotifyChange("dataList")
	@Command("search")
	public void search()
	{
		List<CDREntry> list = repository.coreCDRSearch(searchEntry);
		if (searchEntry.isExportEnabled())
		{
			exportReport(list, CDREntry.class);
		} else {
			getDataList().clear();
			getDataList().addAll(list);
		}
	}

	@Override
	protected PathPart getEditPathPart(CDREntry entity)
	{
		return null;
	}
	@Override
	protected void remove(CDREntry entity)
	{
	}
	@Override
	protected void initDataList(List<CDREntry> dataList)
	{
	}
	
	@Command("edit")
	@Override
	public void edit()
	{

	}
}
