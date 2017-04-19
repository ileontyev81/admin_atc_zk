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

import atc.gui.admin.domain.QueueGeoEntry;
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
import atc.gui.admin.zk.viewmodel.base.BaseVM;
import atc.gui.admin.zk.viewmodel.export.DataSource;
import atc.gui.admin.zk.viewmodel.export.DataSourceFactory;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.ArrayList;
import java.util.List;

@Data
public class QueuesVM extends BaseVM
{
	private QueueSummarySearchEntry queueSummarySearch;
	private QueueHourlySearchEntry queueHourlySearch;
	private QueueGeoSearchEntry queueGeoSearch;
	private QueueAnsweredSearchEntry queueAnsweredSearch;
	private QueueNoansweredSearchEntry queueNoansweredSearch;
	private QueueAbandonedSearchEntry queueAbandonedSearch;
	private QueueRawSearchEntry queueRawSearch;

	@Getter
	private List<QueueSummaryEntry> queueSummaryList;
	@Getter
	@Setter
	private QueueSummaryEntry selectedQueueSummary;
	
	@Getter
	private List<QueueHourlyEntry> queueHourlyList;
	@Getter
	@Setter
	private QueueHourlyEntry selectedQueueHourly;

	@Getter
	private List<QueueGeoEntry> queueGeoList;
	@Getter
	@Setter
	private QueueGeoEntry selectedQueueGeo;

	@Getter
	private List<QueueAnsweredEntry> queueAnsweredList;
	@Getter
	@Setter
	private QueueAnsweredEntry selectedQueueAnswered;

	@Getter
	private List<QueueNoansweredEntry> queueNoansweredList;
	@Getter
	@Setter
	private QueueNoansweredEntry selectedQueueNoanswered;

	@Getter
	private List<QueueAbandonedEntry> queueAbandonedList;
	@Getter
	@Setter
	private QueueAbandonedEntry selectedQueueAbandoned;

	@Getter
	private List<QueueRawEntry> queueRawList;
	@Getter
	@Setter
	private QueueRawEntry selectedQueueRaw;

	private ReportRepository repository;

	@Init
	public void init()
	{
		queueSummaryList = new ArrayList<QueueSummaryEntry>();
		queueHourlyList = new ArrayList<QueueHourlyEntry>();
		queueGeoList = new ArrayList<QueueGeoEntry>();
		queueAnsweredList = new ArrayList<QueueAnsweredEntry>();
		queueNoansweredList = new ArrayList<QueueNoansweredEntry>();
		queueRawList = new ArrayList<QueueRawEntry>();
		queueAbandonedList = new ArrayList<QueueAbandonedEntry>();

		queueSummarySearch = repository.getQueueSummarySearch();
		queueHourlySearch = repository.getQueueHourlySearch();
		queueGeoSearch = repository.getQueueGeoSearch();
		queueAnsweredSearch = repository.getQueueAnsweredSearch();
		queueNoansweredSearch = repository.getQueueNoansweredSearch();
		queueRawSearch = repository.getQueueRawSearch();
		queueAbandonedSearch = repository.getQueueAbandonedSearch();
	}

	@WireVariable("reportRepository")
	public void setRepository(ReportRepository r)
	{
		repository = r;
	}

	public Class<QueueSummaryEntry> getQueueSummaryClass()
	{
		return QueueSummaryEntry.class;
	}
	
	public Class<QueueHourlyEntry> getQueueHourlyClass()
	{
		return QueueHourlyEntry.class;
	}
	
	public Class<QueueGeoEntry> getQueueGeoClass()
	{
		return QueueGeoEntry.class;
	}
	
	public Class<QueueAnsweredEntry> getQueueAnsweredClass()
	{
		return QueueAnsweredEntry.class;
	}

	public Class<QueueNoansweredEntry> getQueueNoansweredClass()
	{
		return QueueNoansweredEntry.class;
	}

	public Class<QueueRawEntry> getQueueRawClass()
	{
		return QueueRawEntry.class;
	}

	public Class<QueueAbandonedEntry> getQueueAbandonedClass()
	{
		return QueueAbandonedEntry.class;
	}

	@NotifyChange("queueSummaryList")
	@Command("queueSummarySearch")
	public void searchQueueSummary()
	{
		List<QueueSummaryEntry> list = repository.queueSummarySearch(queueSummarySearch);
		if (queueSummarySearch.isExportEnabled())
		{
			exportReport(list, QueueSummaryEntry.class);
		} else {
			queueSummaryList.clear();
			queueSummaryList.addAll(list);
		}
	}
	
	@NotifyChange("queueHourlyList")
	@Command("queueHourlySearch")
	public void searchHourlySummary()
	{
		List<QueueHourlyEntry> list = repository.queueHourlySearch(queueHourlySearch);
		if(queueHourlySearch.isExportEnabled())
		{
			exportReport(list, QueueHourlyEntry.class);
		} else {
            queueHourlyList.clear();
            queueHourlyList.addAll(list);
        }
	}
	
	@NotifyChange("queueGeoList")
	@Command("queueGeoSearch")
	public void searchGeoSummary()
	{
		List<QueueGeoEntry> list = repository.queueGeoSearch(queueGeoSearch);
		/*if (queueGeoSearch.isExportEnabled())
		{
			BaseVM.exportReport(list, QueueSummaryEntry.class);
			return;
		}*/
		queueGeoList.clear();
		queueGeoList.addAll(list);
	}
	
	
	@NotifyChange("queueAnsweredList")
	@Command("queueAnsweredSearch")
	public void searchQueueAnswered()
	{
		List<QueueAnsweredEntry> list = repository.queueAnsweredSearch(queueAnsweredSearch);
		if (queueAnsweredSearch.isExportEnabled())
		{
			exportReport(list, QueueAnsweredEntry.class);
		} else {
            queueAnsweredList.clear();
            queueAnsweredList.addAll(list);
        }
	}

	@NotifyChange("queueNoansweredList")
	@Command("queueNoansweredSearch")
	public void searchQueueNoanswered()
	{
		List<QueueNoansweredEntry> list = repository.queueNoansweredSearch(queueNoansweredSearch);
		if (queueNoansweredSearch.isExportEnabled())
		{
			exportReport(list, QueueNoansweredEntry.class);
		} else {
            queueNoansweredList.clear();
            queueNoansweredList.addAll(list);
        }
	}

	@NotifyChange("queueAbandonedList")
	@Command("queueAbandonedSearch")
	public void searchQueueAbandoned()
	{
		List<QueueAbandonedEntry> list = repository.queueAbandonedSearch(queueAbandonedSearch);
		if (queueAbandonedSearch.isExportEnabled())
		{
			exportReport(list, QueueAbandonedEntry.class);
		} else {
            queueAbandonedList.clear();
            queueAbandonedList.addAll(list);
        }
	}

	@NotifyChange("queueRawList")
	@Command("queueRawSearch")
	public void searchQueueRaw()
	{
		List<QueueRawEntry> list = repository.queueRawSearch(queueRawSearch);
		if (queueRawSearch.getExport())
		{
			exportReport(list, QueueRawEntry.class);
		} else {
            queueRawList.clear();
            queueRawList.addAll(list);
        }
	}

	public DataSourceFactory<QueueRawEntry> getQueueRawDataFactory()
	{
		return new DataSourceFactory<QueueRawEntry>()
		{

			@Override
			public DataSource<QueueRawEntry> createDataSource()
			{
				return new DataSource<QueueRawEntry>(repository.queueRawSearch(queueRawSearch));
			}

			@Override
			public Class<QueueRawEntry> getType()
			{
				return QueueRawEntry.class;
			}

		};
	}
	@Command("editQueueSummary")
	public void editQueueSummary()
	{

	}
	
	@Command("editQueueHourly")
	public void editQueueHourly()
	{

	}
	
	@Command("editQueueGeo")
	public void editQueueGeo()
	{

	}
	
	@Command("editQueueAnswered")
	public void editQueueAnswered()
	{

	}

	@Command("editQueueNoanswered")
	public void editQueueNoanswered()
	{

	}

	@Command("editQueueAbandoned")
	public void editQueueAbandoned()
	{

	}

	@Command("editQueueRaw")
	public void editQueueRaw()
	{

	}

}
