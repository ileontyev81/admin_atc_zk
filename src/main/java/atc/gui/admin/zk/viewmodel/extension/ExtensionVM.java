package atc.gui.admin.zk.viewmodel.extension;

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.DirectionEntry;
import atc.gui.admin.domain.model.ExtensionActionEntry;
import atc.gui.admin.domain.model.ExtensionEntry;
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

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
@Slf4j
public class ExtensionVM extends BaseEntityVM<ExtensionEntry>
{
	//@Getter
	//private ExtensionEntry extension;
	private ExtensionRepository repository;
	
	@Getter
	@Setter
	private DirectionEntry directionSelected;
	@Getter
	@Setter
	private VariableEntry variableSelected;
	@Getter
	@Setter
	private ExtensionActionEntry actionSelected;

	@Getter
	private List<DirectionEntry> directionList;
	
	@Getter
	private List<VariableEntry> variableList;
	
	@Getter
	private List<ExtensionActionEntry> actionList;
	
	@WireVariable("extensionRepository")
	public void setRepository(ExtensionRepository repository)
	{
		this.repository = repository;
	}
	
	@Init(superclass = true)
	public void init()
	{
        log.debug("ExtensionVM before getEntity().isPersisted()");
		if (getEntity().isPersisted())
		{
			directionList = repository.getDirections(getEntity().getId());
			variableList = repository.getVariables(getEntity().getId());
			actionList = repository.getActions(getEntity().getId());
		}
	}

	public Class<DirectionEntry> getDirectionClass()
	{
		return DirectionEntry.class;
	}

	public Class<ExtensionActionEntry> getActionClass()
	{
		return ExtensionActionEntry.class;
	}

	public Class<VariableEntry> getVariableClass()
	{
		return VariableEntry.class;
	}

    @Override
    protected ExtensionEntry loadEntity(String entityId)
    {
        return repository.getExtension(entityId != null ? Integer.parseInt(entityId) : 0);
    }

    @Override
    protected void saveEntity(ExtensionEntry entity)
    {
        repository.saveExtension(entity);
    }

	@Command
	public void goAddAction()
	{
		navigateTo(new PathPart("extension_action"));
	}

	@Command
	public void goAddDirection()
	{
		navigateTo(new PathPart("extension_direction"));
	}

	@Command
	public void goAddVariable()
	{
		navigateTo(new PathPart("extension_variable"));
	}

	@Command
	public void editAction()
	{
		if (actionSelected != null) {
		    navigateTo(new EditPathPart("extension_action", actionSelected.getId()));
        }
	}
	@Command
	public void editDirection()
	{
		if (directionSelected != null) {
		    navigateTo(new EditPathPart("extension_direction", directionSelected.getId()));
        }
	}
	@Command
	public void editVariable()
	{
		if (variableSelected != null) {
		    navigateTo(new EditPathPart("extension_variable", variableSelected.getId()));
        }
	}

	@NotifyChange("actionList")
	@Command
	public void deleteAction()
	{
		if (actionSelected != null) {
            repository.removeAction(actionSelected.getId());
            actionList = repository.getActions(getEntity().getId());
        }
	}

	@NotifyChange("directionList")
	@Command
	public void deleteDirection()
	{
		if (directionSelected != null) {
            repository.removeDirection(directionSelected.getId());
            directionList = repository.getDirections(getEntity().getId());
        }
	}

	@NotifyChange("variableList")
	@Command
	public void deleteVariable()
	{
		if (variableSelected != null) {
            repository.removeVariable(variableSelected.getId());
            variableList = repository.getVariables(getEntity().getId());
        }
	}

	@NotifyChange("actionList")
	@Command
	public void changeActiveAction()
	{
		if (actionSelected != null) {
            repository.changeActiveAction(actionSelected.getId());
            actionList = repository.getActions(getEntity().getId());
        }
	}

	/*
	@Command("showDiresions")
	public void showDiresions()
	{
		getTransitionManager().forward(new ListPathPart("directions", "direction"));
	}*/
    /*@Command
	public void saveExtension()
	{
		repository.saveExtension(getEntity());
		navigateBack();
	}*/
	/*@Command
	public void goEditAction()
	{
		if (actionSelected == null)
			return;

		navigateTo(new PathPart("action", EditPathPart.OBJECT_ID, actionSelected.getActionId().toString()));
	}*/

}
