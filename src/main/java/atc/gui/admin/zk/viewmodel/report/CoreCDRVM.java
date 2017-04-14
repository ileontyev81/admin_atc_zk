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
