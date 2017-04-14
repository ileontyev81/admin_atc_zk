package atc.gui.admin.zk.viewmodel.report;

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.domain.model.CDRRouteEntry;
import atc.gui.admin.domain.model.CDRRouteSearchEntry;
import atc.gui.admin.domain.service.repository.ReportRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntitiesVM;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.List;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
public class RouteCDRVM extends BaseEntitiesVM<CDRRouteEntry>
{
	@Getter @Setter
	private CDRRouteSearchEntry searchEntry;
	
	private ReportRepository repository;

	@Init(superclass = true)
	public void init()
	{
		searchEntry = repository.getRouteCDRSearch();
	}

	@WireVariable("reportRepository")
	public void setRepository(ReportRepository r)
	{
		repository = r;
	}

	public Class<CDRRouteEntry> getRouteCdrClass()
	{
		return CDRRouteEntry.class;
	}

	@NotifyChange("dataList")
	@Command("search")
	public void search()
	{
		List<CDRRouteEntry> list = repository.routeCDRSearch(searchEntry);
		if (searchEntry.isExportEnabled())
		{
			exportReport(list, CDRRouteEntry.class);
		} else {
			getDataList().clear();
			getDataList().addAll(list);
		}
	}
	
	@Override
	protected PathPart getEditPathPart(CDRRouteEntry entity)
	{
		return null;
	}

	@Override
	protected void remove(CDRRouteEntry entity)
	{
	}

	@Override
	protected void initDataList(List<CDRRouteEntry> dataList)
	{
	}
	
	@Command("edit")
	@Override
	public void edit()
	{

	}
}
