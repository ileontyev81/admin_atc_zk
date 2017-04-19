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

package atc.gui.admin.infrastructure.repository;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import atc.gui.admin.security.SpringSecurityHelper;
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
import atc.gui.admin.domain.service.repository.ReportRepository;
import atc.gui.admin.infrastructure.dao.DbRequest;

@Repository("reportRepository")
public class ReportRepositoryImpl implements ReportRepository
{
	private JdbcTemplate template;

	@Autowired
	public ReportRepositoryImpl(JdbcTemplate template)
	{
		this.template = template;
	}

	@Override
	public List<CDREntry> coreCDRSearch(CDRSearchEntry entry)
	{
		return new DbRequest<CDREntry>(template, "wadm_cdr_core_search", CDREntry.class).execute(new SqlParameterValue(Types.OTHER, entry));
	}
	@Override
	public CDRSearchEntry getCoreCDRSearch()
	{
		DbRequest<CDRSearchEntry> req = new DbRequest<CDRSearchEntry>(template, "wadm_cdr_core_search_get", CDRSearchEntry.class);
		req.execute();
		return req.singleResult();
	}
	
	@Override
	public CDRRouteSearchEntry getRouteCDRSearch()
	{
		DbRequest<CDRRouteSearchEntry> req = new DbRequest<CDRRouteSearchEntry>(template, "wadm_cdr_route_search_get", CDRRouteSearchEntry.class);
		req.execute();
		return req.singleResult();
	}
	@Override
	public List<CDRRouteEntry> routeCDRSearch(CDRRouteSearchEntry entry)
	{
		return new DbRequest<CDRRouteEntry>(template, "wadm_cdr_route_search", CDRRouteEntry.class).execute(new SqlParameterValue(Types.OTHER, entry));
	}
	
	
	
	
	
	@Override
	public QueueAnsweredSearchEntry getQueueAnsweredSearch()
	{
		DbRequest<QueueAnsweredSearchEntry> req = new DbRequest<QueueAnsweredSearchEntry>(template, "wadm_queue_answered_search_get", QueueAnsweredSearchEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
		return req.singleResult();
	}

	@Override
	public List<QueueAnsweredEntry> queueAnsweredSearch(QueueAnsweredSearchEntry entry)
	{
		return new DbRequest<QueueAnsweredEntry>(template, "wadm_queue_answered_search", QueueAnsweredEntry.class).execute(new SqlParameterValue(Types.OTHER, entry), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public QueueRawSearchEntry getQueueRawSearch()
	{
		DbRequest<QueueRawSearchEntry> req = new DbRequest<QueueRawSearchEntry>(template, "wadm_queue_raw_search_get", QueueRawSearchEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
		return req.singleResult();
	}

	@Override
	public List<QueueRawEntry> queueRawSearch(QueueRawSearchEntry entry)
	{
		return new DbRequest<QueueRawEntry>(template, "wadm_queue_raw_search", QueueRawEntry.class).execute(new SqlParameterValue(Types.OTHER, entry));
	}

	@Override
	public QueueAbandonedSearchEntry getQueueAbandonedSearch()
	{
		DbRequest<QueueAbandonedSearchEntry> req = new DbRequest<QueueAbandonedSearchEntry>(template, "wadm_queue_abandoned_search_get", QueueAbandonedSearchEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
		return req.singleResult();
	}

	@Override
	public List<QueueAbandonedEntry> queueAbandonedSearch(QueueAbandonedSearchEntry entry)
	{
		return new DbRequest<QueueAbandonedEntry>(template, "wadm_queue_abandoned_search", QueueAbandonedEntry.class).execute(
				new SqlParameterValue(Types.OTHER, entry), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public QueueNoansweredSearchEntry getQueueNoansweredSearch()
	{
		DbRequest<QueueNoansweredSearchEntry> req = new DbRequest<QueueNoansweredSearchEntry>(template, "wadm_queue_noanswered_search_get", QueueNoansweredSearchEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
		return req.singleResult();
	}

	@Override
	public List<QueueNoansweredEntry> queueNoansweredSearch(QueueNoansweredSearchEntry entry)
	{
		return new DbRequest<QueueNoansweredEntry>(template, "wadm_queue_noanswered_search", QueueNoansweredEntry.class).execute(
				new SqlParameterValue(Types.OTHER, entry), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public CallRecordEntry getCallRecord(Long id)
	{
		DbRequest<CallRecordEntry> req = new DbRequest<CallRecordEntry>(template, "wadm_call_record_get", CallRecordEntry.class);
		req.execute(new SqlParameterValue(Types.BIGINT, id));
		return req.singleResult();
	}
	
	
	@Override
	public OperatorReportSearchEntry getOperatorReportSearch()
	{
		DbRequest<OperatorReportSearchEntry> req = new DbRequest<OperatorReportSearchEntry>(template, "wadm_operator_report_search_get", OperatorReportSearchEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
		return req.singleResult();
	}


	@Override
	public List<OperatorReportEntry> getOperatorReport(OperatorReportSearchEntry e)
	{
		DbRequest<OperatorReportEntry> req = new DbRequest<OperatorReportEntry>(template, "wadm_operator_report_get", OperatorReportEntry.class);
		return req.execute(new SqlParameterValue(Types.OTHER, e), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public QueueSummarySearchEntry getQueueSummarySearch()
	{
		DbRequest<QueueSummarySearchEntry> req = new DbRequest<QueueSummarySearchEntry>(template, "wadm_queue_summary_search_get", QueueSummarySearchEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
		return req.singleResult();
	}

	@Override
	public List<QueueSummaryEntry> queueSummarySearch(QueueSummarySearchEntry e)
	{
		DbRequest<QueueSummaryEntry> req = new DbRequest<QueueSummaryEntry>(template, "wadm_queue_summary_get", QueueSummaryEntry.class);
		return req.execute(new SqlParameterValue(Types.OTHER, e), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public OperatorPauseSearchEntry getOperatorPauseSearch()
	{
		DbRequest<OperatorPauseSearchEntry> req = new DbRequest<OperatorPauseSearchEntry>(template, "wadm_operator_pause_search_get", OperatorPauseSearchEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
		return req.singleResult();
	}

	@Override
	public List<OperatorPauseEntry> getOperatorPause(OperatorPauseSearchEntry e)
	{
		DbRequest<OperatorPauseEntry> req = new DbRequest<OperatorPauseEntry>(template, "wadm_operator_pause_get", OperatorPauseEntry.class);
		return req.execute(new SqlParameterValue(Types.OTHER, e), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public OperatorOperationSearchEntry getOperatorOperationSearch()
	{
		DbRequest<OperatorOperationSearchEntry> req = new DbRequest<OperatorOperationSearchEntry>(template, "wadm_operator_operation_search_get", OperatorOperationSearchEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
		return req.singleResult();
	}

	@Override
	public List<OperatorOperationEntry> getOperatorOperation(OperatorOperationSearchEntry e)
	{
		DbRequest<OperatorOperationEntry> req = new DbRequest<OperatorOperationEntry>(template, "wadm_operator_operation_get", OperatorOperationEntry.class);
		return req.execute(new SqlParameterValue(Types.OTHER, e), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	
	@Override
	public OperatorLoginSearchEntry getOperatorLoginSearch()
	{
		DbRequest<OperatorLoginSearchEntry> req = new DbRequest<OperatorLoginSearchEntry>(template, "wadm_operator_login_search_get", OperatorLoginSearchEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
		return req.singleResult();
	}

	@Override
	public List<OperatorLoginEntry> getOperatorLogin(OperatorLoginSearchEntry e)
	{
		DbRequest<OperatorLoginEntry> req = new DbRequest<OperatorLoginEntry>(template, "wadm_operator_login_search", OperatorLoginEntry.class);
		return req.execute(new SqlParameterValue(Types.OTHER, e), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}
	
	
	
	
	@Override
	public QueueGeoSearchEntry getQueueGeoSearch()
	{
		DbRequest<QueueGeoSearchEntry> req = new DbRequest<QueueGeoSearchEntry>(template, "wadm_queue_geo_search_get", QueueGeoSearchEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
		return req.singleResult();
	}

	@Override
	public List<QueueGeoEntry> queueGeoSearch(QueueGeoSearchEntry e)
	{
		DbRequest<QueueGeoEntry> req = new DbRequest<QueueGeoEntry>(template, "wadm_queue_geo_get", QueueGeoEntry.class);
		return req.execute(new SqlParameterValue(Types.OTHER, e), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	
	
	@Override
	public QueueHourlySearchEntry getQueueHourlySearch()
	{
		DbRequest<QueueHourlySearchEntry> req = new DbRequest<QueueHourlySearchEntry>(template, "wadm_queue_hourly_search_get", QueueHourlySearchEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
		return req.singleResult();
	}

	@Override
	public List<QueueHourlyEntry> queueHourlySearch(QueueHourlySearchEntry e)
	{
		DbRequest<QueueHourlyEntry> req = new DbRequest<QueueHourlyEntry>(template, "wadm_queue_hourly_get", QueueHourlyEntry.class);
		return req.execute(new SqlParameterValue(Types.OTHER, e), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}


}
