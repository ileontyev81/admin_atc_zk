package atc.gui.admin.zk.viewmodel;

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.ProviderEntry;
import atc.gui.admin.domain.model.VariableEntry;
import atc.gui.admin.domain.service.repository.ProviderRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntityVM;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.List;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
public class ProviderVM extends BaseEntityVM<ProviderEntry>
{
	@Getter
	List<VariableEntry> variableList;
	
	@Getter
	@Setter
	VariableEntry variableSelected;
	
	private ProviderRepository repository;
	
	@WireVariable("providerRepository")
	public void setRepository(ProviderRepository repository)
	{
		this.repository = repository;
	}

    @Init(superclass = true)
	public void init()
	{
		if(getEntity().isPersisted())
		{
			variableList = repository.getVariables(getEntity().getId());
		}
	}
	
	public Class<VariableEntry> getVariableClass()
	{
		return VariableEntry.class;
	}

	@Override
	protected ProviderEntry loadEntity(String entityId)
	{
		return repository.getProvider(Integer.valueOf(entityId));
	}

	@Override
	protected void saveEntity(ProviderEntry entity)
	{
		repository.saveProvider(entity);
	}
	
	@Command
	public void editVariable()
	{
		if (variableSelected == null)
			return;
		navigateTo(new EditPathPart("provider_variable", variableSelected.getId()));
	}
	@Command
	public void goAddVariable()
	{
		navigateTo(new PathPart("provider_variable"));
	}
	
	@NotifyChange("variableList")
	@Command
	public void deleteVariable()
	{
		if (variableSelected == null)
			return;
		repository.removeVariable(variableSelected.getId());
		variableList = repository.getVariables(getEntity().getId());
	}
}
