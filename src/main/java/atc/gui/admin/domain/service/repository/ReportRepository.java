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

package atc.gui.admin.domain.service.repository;

import java.util.List;

import atc.gui.admin.domain.QueueGeoEntry;
import atc.gui.admin.domain.model.CDREntry;
import atc.gui.admin.domain.model.CDRRouteEntry;
import atc.gui.admin.domain.model.CDRRouteSearchEntry;
import atc.gui.admin.domain.model.CDRSearchEntry;
import atc.gui.admin.domain.model.CallRecordEntry;
import atc.gui.admin.domain.model.OperatorLoginEntry;
import atc.gui.admin.domain.model.OperatorLoginSearchEntry;
import atc.gui.admin.domain.model.OperatorOperationEntry;
import atc.gui.admin.domain.model.OperatorOperationSearchEntry;
import atc.gui.admin.domain.model.OperatorPauseEntry;
import atc.gui.admin.domain.model.OperatorPauseSearchEntry;
import atc.gui.admin.domain.model.OperatorReportEntry;
import atc.gui.admin.domain.model.OperatorReportSearchEntry;
import atc.gui.admin.domain.model.QueueAbandonedEntry;
import atc.gui.admin.domain.model.QueueAbandonedSearchEntry;
import atc.gui.admin.domain.model.QueueAnsweredEntry;
import atc.gui.admin.domain.model.QueueAnsweredSearchEntry;
import atc.gui.admin.domain.model.QueueGeoSearchEntry;
import atc.gui.admin.domain.model.QueueHourlyEntry;
import atc.gui.admin.domain.model.QueueHourlySearchEntry;
import atc.gui.admin.domain.model.QueueNoansweredEntry;
import atc.gui.admin.domain.model.QueueNoansweredSearchEntry;
import atc.gui.admin.domain.model.QueueRawEntry;
import atc.gui.admin.domain.model.QueueRawSearchEntry;
import atc.gui.admin.domain.model.QueueSummaryEntry;
import atc.gui.admin.domain.model.QueueSummarySearchEntry;

public interface ReportRepository
{
	public CallRecordEntry getCallRecord(Long id);
	
	
	public CDRSearchEntry getCoreCDRSearch();
	public List<CDREntry> coreCDRSearch(CDRSearchEntry entry);
	
	public CDRRouteSearchEntry getRouteCDRSearch();
	public List<CDRRouteEntry> routeCDRSearch(CDRRouteSearchEntry entry);
	
	
	public QueueRawSearchEntry getQueueRawSearch();
	public List<QueueRawEntry> queueRawSearch(QueueRawSearchEntry entry);
	
	public QueueAnsweredSearchEntry getQueueAnsweredSearch();
	public List<QueueAnsweredEntry> queueAnsweredSearch(QueueAnsweredSearchEntry entry);
	
	public QueueNoansweredSearchEntry getQueueNoansweredSearch();
	public List<QueueNoansweredEntry> queueNoansweredSearch(QueueNoansweredSearchEntry entry);
	
	public QueueAbandonedSearchEntry getQueueAbandonedSearch();
	public List<QueueAbandonedEntry> queueAbandonedSearch(QueueAbandonedSearchEntry entry);
	
	public OperatorReportSearchEntry getOperatorReportSearch();
	public List<OperatorReportEntry> getOperatorReport(OperatorReportSearchEntry e);
	
	public OperatorPauseSearchEntry getOperatorPauseSearch();
	public List<OperatorPauseEntry> getOperatorPause(OperatorPauseSearchEntry e);
	
	public OperatorOperationSearchEntry getOperatorOperationSearch();
	public List<OperatorOperationEntry> getOperatorOperation(OperatorOperationSearchEntry e);
	
	
	public OperatorLoginSearchEntry getOperatorLoginSearch();
	public List<OperatorLoginEntry> getOperatorLogin(OperatorLoginSearchEntry e);
	
	
	public QueueSummarySearchEntry getQueueSummarySearch();
	public List<QueueSummaryEntry> queueSummarySearch(QueueSummarySearchEntry entry);
	
	public QueueGeoSearchEntry getQueueGeoSearch();
	public List<QueueGeoEntry> queueGeoSearch(QueueGeoSearchEntry entry);
	
	public QueueHourlySearchEntry getQueueHourlySearch();
	public List<QueueHourlyEntry> queueHourlySearch(QueueHourlySearchEntry entry);
	
//	public void addCDR(CDREntry cdr);
//
//	public void removeCDR(int cdrId);
//
//	public CDREntry getOpenedCDR(int cdrId);

}
