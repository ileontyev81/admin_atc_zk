package atc.gui.admin.zk.viewmodel.extension.action;

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.ServiceLinkedEntry;
import atc.gui.admin.domain.model.VariableEntry;
import atc.gui.admin.domain.service.repository.ExtensionRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntityVM;
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
public class ServiceVM extends BaseEntityVM<ServiceLinkedEntry>
{
	private ExtensionRepository repository;

	@Getter
	private List<VariableEntry> variableList;

	@Getter
	@Setter
	private VariableEntry variableSelected;

	@WireVariable("extensionRepository")
	public void setRepository(ExtensionRepository repository)
	{
		this.repository = repository;
	}

	@Init(superclass = true)
	public void init()
	{
		if (getEntity().isPersisted())
		{
			variableList = repository.getServiceVariables(getEntity().getId());
		}
	}

	public Class<VariableEntry> getVariableClass()
	{
		return VariableEntry.class;
	}

	@Override
	protected ServiceLinkedEntry loadEntity(String entityId)
	{
		String parentId = getParentAttribute(EditPathPart.OBJECT_ID);
		return repository.getActionService(entityId != null ? Integer.parseInt(entityId) : 0, Integer.parseInt(parentId));
	}

	@Override
	protected void saveEntity(ServiceLinkedEntry entity)
	{
		repository.saveActionService(entity);
	}

	@Command
	public void goAddVariable()
	{
		navigateTo(new PathPart("service_variable"));
	}

	@NotifyChange("variableList")
	@Command
	public void deleteVariable()
	{
		if (variableSelected == null)
			return;
		repository.removeServiceVariable(variableSelected.getId());
		variableList = repository.getServiceVariables(getEntity().getId());
	}

}
