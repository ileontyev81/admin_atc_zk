package atc.gui.admin.zk.viewmodel.action;

import lombok.Getter;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.ActionEntry;
import atc.gui.admin.domain.service.repository.ActionRepository;
import atc.gui.admin.zk.viewmodel.base.BaseVM;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
public class ActionVM extends BaseVM// extends BaseEntityVM<ActionEntry>
{
	private ActionRepository repository;

	@Getter
	private ActionEntry action;

	/*@Getter
	@Setter
	private ActionDirectionEntry directionSelected;

	private List<ActionDirectionEntry> directionList;
*/
	@Init
	public void init()
	{
		String id = getAttribute(EditPathPart.OBJECT_ID);

		action = repository.getAction(id != null ? Integer.parseInt(id) : 0);

		/*if (action.isPersisted())
		{
			directionList = repository.getDirections(action.getId());
		}*/
	}

	@WireVariable("actionRepository")
	public void setRepository(ActionRepository repository)
	{
		this.repository = repository;
	}
	
	@Command
	public void saveAction()
	{
		repository.saveAction(action);
		navigateBack();
	}
	
	/*public List<ActionDirectionEntry> getDirectionList()
	{
		return directionList;
	}
	

	public Class<ActionDirectionEntry> getDirectionClass()
	{
		return ActionDirectionEntry.class;
	}

	@Command
	public void goAddDirection()
	{
		navigateTo(new PathPart("action_direction"));
	}

	@Command
	public void editDirection()
	{
		if (directionSelected != null)
		{
			navigateTo(new EditPathPart("action_direction", directionSelected.getId()));
		}
	}*/

	//@NotifyChange("getDirectionList")
	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	@Command
	public void deleteDirection()
	{
		if (directionSelected == null)
			return;
		
		Messagebox.show("Are you sure?", "Delete entity", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, 
				new org.zkoss.zk.ui.event.EventListener() 
		{
			@Override
			public void onEvent(Event event) throws Exception
			{
				if (event.getName().equals("onOK")) 
				{
					repository.removeDirection(directionSelected.getId());
					directionList = repository.getDirections(action.getId());
					
					BindUtils.postNotifyChange(null, null, ActionVM.this, "directionList");
				}
			}
		});
	}*/
}
