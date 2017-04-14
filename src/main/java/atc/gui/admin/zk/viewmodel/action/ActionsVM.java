package atc.gui.admin.zk.viewmodel.action;

import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.ActionEntry;
import atc.gui.admin.domain.service.repository.ActionRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntitiesVM;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
@Init(superclass=true)
public class ActionsVM extends BaseEntitiesVM<ActionEntry>
{
	private ActionRepository repository;
	
	@WireVariable("actionRepository")
	public void setRepository(ActionRepository repository)
	{
		this.repository = repository;
	}

    public List<ActionEntry> getData()
    {
    	return repository.findActions();
    }

	@Override
	protected PathPart getEditPathPart(ActionEntry entity)
	{
		return new EditPathPart("action", entity.getId());
	}

	@Override
	protected void remove(ActionEntry entity)
	{
		repository.removeAction(entity.getId());
	}

	@Override
	protected void initDataList(List<ActionEntry> dataList)
	{
		dataList.addAll(repository.findActions());
	}
    
}
