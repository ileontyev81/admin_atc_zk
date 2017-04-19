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

import atc.gui.admin.domain.model.OperatorLoginEntry;
import atc.gui.admin.domain.model.OperatorLoginSearchEntry;
import atc.gui.admin.domain.model.OperatorOperationEntry;
import atc.gui.admin.domain.model.OperatorOperationSearchEntry;
import atc.gui.admin.domain.model.OperatorPauseEntry;
import atc.gui.admin.domain.model.OperatorPauseSearchEntry;
import atc.gui.admin.domain.model.OperatorReportEntry;
import atc.gui.admin.domain.model.OperatorReportSearchEntry;
import atc.gui.admin.domain.service.repository.ReportRepository;
import atc.gui.admin.zk.viewmodel.base.BaseVM;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.ArrayList;
import java.util.List;

public class OperatorsVM extends BaseVM
{
	private ReportRepository repository;
	
	
	@Getter @Setter
	OperatorReportSearchEntry operatorReportSearch;
	@Getter @Setter
	OperatorOperationSearchEntry operatorOperationSearch;
	@Getter @Setter
	OperatorPauseSearchEntry operatorPauseSearch;
	@Getter @Setter
	OperatorLoginSearchEntry operatorLoginSearch;
	
	
	
	
	
	@Getter
	List<OperatorReportEntry> operatorReportList;
	@Getter
	List<OperatorOperationEntry> operatorOperationList;
	@Getter
	List<OperatorPauseEntry> operatorPauseList;
	@Getter
	List<OperatorLoginEntry> operatorLoginList;
	
	
	
	
	@Getter @Setter
	private OperatorReportEntry selectedOperatorReport;
	@Getter @Setter
	private OperatorOperationEntry selectedOperatorOperation;
	@Getter @Setter
	private OperatorPauseEntry selectedOperatorPause;
	@Getter @Setter
	private OperatorLoginEntry selectedOperatorLogin;
	
	@WireVariable("reportRepository")
	public void setRepository(ReportRepository r)
	{
		repository = r;
	}
	@Init
	public void init()
	{
		operatorReportSearch = repository.getOperatorReportSearch();
		operatorReportList = new ArrayList<OperatorReportEntry>();
		
		operatorOperationSearch = repository.getOperatorOperationSearch();
		operatorOperationList = new ArrayList<OperatorOperationEntry>();
		
		operatorPauseSearch = repository.getOperatorPauseSearch();
		operatorPauseList = new ArrayList<OperatorPauseEntry>();
		
		operatorLoginSearch = repository.getOperatorLoginSearch();
		operatorLoginList = new ArrayList<OperatorLoginEntry>();
	}
	public Class<OperatorReportEntry> getOperatorReportClass()
	{
		return OperatorReportEntry.class;
	}
	public Class<OperatorOperationEntry> getOperatorOperationClass()
	{
		return OperatorOperationEntry.class;
	}
	public Class<OperatorPauseEntry> getOperatorPauseClass()
	{
		return OperatorPauseEntry.class;
	}
	public Class<OperatorLoginEntry> getOperatorLoginClass()
	{
		return OperatorLoginEntry.class;
	}
	
	@NotifyChange("operatorReportList")
	@Command("operatorReportSearch")
	public void searchOperatorReport()
	{
		List<OperatorReportEntry> list = repository.getOperatorReport(operatorReportSearch);
		if (operatorReportSearch.isExportEnabled())
		{
			exportReport(list, OperatorReportEntry.class);
		} else {
			operatorReportList.clear();
			operatorReportList.addAll(list);
		}
	}

	@NotifyChange("operatorOperationList")
	@Command("operatorOperationSearch")
	public void searchOperatorOperation()
	{
		List<OperatorOperationEntry> list = repository.getOperatorOperation(operatorOperationSearch);
		if (operatorOperationSearch.isExportEnabled())
		{
			exportReport(list, OperatorOperationEntry.class);
		} else {
            operatorOperationList.clear();
            operatorOperationList.addAll(list);
        }
	}

	@NotifyChange("operatorPauseList")
	@Command("operatorPauseSearch")
	public void searchOperatorPause()
	{
		List<OperatorPauseEntry> list = repository.getOperatorPause(operatorPauseSearch);
		if (operatorPauseSearch.isExportEnabled())
		{
			exportReport(list, OperatorPauseEntry.class);
		} else {
            operatorPauseList.clear();
            operatorPauseList.addAll(list);
        }
	}

	@NotifyChange("operatorLoginList")
	@Command("operatorLoginSearch")
	public void searchOperatorLogin()
	{
		List<OperatorLoginEntry> list = repository.getOperatorLogin(operatorLoginSearch);
		if (operatorLoginSearch.isExportEnabled())
		{
			exportReport(list, OperatorLoginEntry.class);
		} else {
            operatorLoginList.clear();
            operatorLoginList.addAll(list);
        }
	}

	@Command
	public void editOperatorReport()
	{

	}
	@Command
	public void deleteOperatorReport()
	{

	}
	
	@Command
	public void editOperatorOperation()
	{

	}
	@Command
	public void deleteOperatorOperation()
	{

	}
	
	@Command
	public void editOperatorPause()
	{

	}
	@Command
	public void deleteOperatorPause()
	{

	}
	
	@Command
	public void editOperatorLogin()
	{

	}
	@Command
	public void deleteOperatorLogin()
	{

	}
}
