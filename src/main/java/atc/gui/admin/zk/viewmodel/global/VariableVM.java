package atc.gui.admin.zk.viewmodel.global;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.domain.model.VariableEntry;
import atc.gui.admin.domain.service.repository.GlobalRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntityVM;
@Init(superclass = true)
public class VariableVM extends BaseEntityVM<VariableEntry>
{
	private GlobalRepository repository;

	@WireVariable("globalRepository")
	public void setRepository(GlobalRepository repository)
	{
		this.repository = repository;
	}
	@Override
	protected VariableEntry loadEntity(String entityId)
	{
		return repository.getVariable(Integer.parseInt(entityId));
	}

	@Override
	protected void saveEntity(VariableEntry entity)
	{
		repository.saveVariable(entity);
	}

}
