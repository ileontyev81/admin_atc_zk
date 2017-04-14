package atc.gui.admin.zk.viewmodel.global;

import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.VariableEntry;
import atc.gui.admin.domain.service.repository.GlobalRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntitiesVM;
@Init(superclass = true)
public class VariablesVM extends BaseEntitiesVM<VariableEntry>
{
	private GlobalRepository repository;

	@WireVariable("globalRepository")
	public void setRepository(GlobalRepository repository)
	{
		this.repository = repository;
	}
	@Override
	protected PathPart getEditPathPart(VariableEntry entity)
	{
		return new EditPathPart("global_variable", entity.getId());
	}

	@Override
	protected void remove(VariableEntry entity)
	{
		repository.removeVariable(entity.getId());
	}

	@Override
	protected void initDataList(List<VariableEntry> dataList)
	{
		dataList.addAll(repository.getVariables());
	}

}
