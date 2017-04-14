package atc.gui.admin.zk.viewmodel;

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.OperatorEntry;
import atc.gui.admin.domain.service.repository.OperatorRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntitiesVM;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.List;

@Slf4j
@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
@Init(superclass = true)
public class OperatorsVM extends BaseEntitiesVM<OperatorEntry>
{
	private OperatorRepository repository;

	
	@WireVariable("operatorRepository")
	public void setRepository(OperatorRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected PathPart getEditPathPart(OperatorEntry entity)
	{
		return new EditPathPart("operator", entity.getId());
	}

	@Override
	protected void remove(OperatorEntry entity)
	{
		repository.deleteOperator(entity.getId());
	}

	@Override
	protected void initDataList(List<OperatorEntry> dataList)
	{
		dataList.addAll(repository.getOperatorList(false));
	}
	
	@Command
	public void export()
	{
		List<OperatorEntry> list = repository.getOperatorList(true);
		exportReport(list, OperatorEntry.class);
	}
}
