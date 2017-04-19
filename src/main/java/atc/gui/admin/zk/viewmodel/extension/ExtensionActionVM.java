/*
 * Copyright (C) 2017 i.leontyev81@gmail.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package atc.gui.admin.zk.viewmodel.extension;

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.*;
import atc.gui.admin.domain.service.repository.ExtensionRepository;
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
public class ExtensionActionVM  extends BaseEntityVM<ExtensionActionEntry>
{
	private ExtensionRepository repository;

	@Getter
	List<DirectionEntry> directionList;
	@Getter
	List<CallerIdEntry> calleridList;
	@Getter
	List<VariableEntry> variableList;
	@Getter
	List<AccountLinkedEntry> accountList;
	@Getter
	List<ServiceLinkedEntry> serviceList;
	
	@Getter
	@Setter
	DirectionEntry directionSelected;
	@Getter
	@Setter
	CallerIdEntry calleridSelected;
	@Getter
	@Setter
	VariableEntry variableSelected;
	@Getter
	@Setter
	AccountLinkedEntry accountSelected;
	@Getter
	@Setter
	ServiceLinkedEntry serviceSelected;

	@WireVariable("extensionRepository")
	public void setRepository(ExtensionRepository repository)
	{
		this.repository = repository;
	}
	
	@Override
	protected ExtensionActionEntry loadEntity(String entityId)
	{
		String parentId = getParentAttribute(EditPathPart.OBJECT_ID);
		return repository.getAction(entityId != null ? Integer.parseInt(entityId) : 0, Integer.parseInt(parentId));
	}

	@Override
	protected void saveEntity(ExtensionActionEntry entity)
	{
		repository.saveAction(entity);
	}

	@Init(superclass = true)
	public void init()
	{
		if (getEntity().isPersisted())
		{
			directionList = repository.getActionDirections(getEntity().getId());
			calleridList = repository.getActionCallerIds(getEntity().getId());
			variableList = repository.getActionVariables(getEntity().getId());
			accountList = repository.getActionAccounts(getEntity().getId());
			serviceList = repository.getActionServices(getEntity().getId());
		}
	}

	public Class<DirectionEntry> getDirectionClass()
	{
		return DirectionEntry.class;
	}
	public Class<CallerIdEntry> getCallerIdClass()
	{
		return CallerIdEntry.class;
	}
	public Class<VariableEntry> getVariableClass()
	{
		return VariableEntry.class;
	}
	public Class<AccountLinkedEntry> getAccountClass()
	{
		return AccountLinkedEntry.class;
	}
	public Class<ServiceLinkedEntry> getServiceClass()
	{
		return ServiceLinkedEntry.class;
	}


	@Command
	public void editDirection()
	{
		if (directionSelected == null)
			return;
		navigateTo(new EditPathPart("action_direction", directionSelected.getId()));
	}
	@Command
	public void editCallerId()
	{
		if (calleridSelected == null)
			return;
		navigateTo(new EditPathPart("action_callerid", calleridSelected.getId()));
	}
	@Command
	public void editVariable()
	{
		if (variableSelected == null)
			return;
		navigateTo(new EditPathPart("action_variable", variableSelected.getId()));
	}
	@Command
	public void editAccount()
	{
		if (accountSelected == null)
			return;
		navigateTo(new EditPathPart("action_account", accountSelected.getId()));
	}
	@Command
	public void editService()
	{
		if (serviceSelected == null)
			return;
		navigateTo(new EditPathPart("action_service", serviceSelected.getId()));
	}
	
	/*@Command
	public void save()
	{
		repository.saveAction(extensionAction);
		navigateBack();
	}*/
	
	
	@NotifyChange("serviceList")
	@Command
	public void deleteService()
	{
		if (serviceSelected == null)
			return;
		repository.removeActionService(serviceSelected.getId());
		serviceList = repository.getActionServices(getEntity().getId());
	}
	@NotifyChange("variableList")
	@Command
	public void deleteVariable()
	{
		if (variableSelected == null)
			return;
		repository.removeActionVariable(variableSelected.getId());
		variableList = repository.getActionVariables(getEntity().getId());
	}
	@NotifyChange("accountList")
	@Command
	public void deleteAccount()
	{
		if (accountSelected == null)
			return;
		repository.removeActionAccount(accountSelected.getId());
		accountList = repository.getActionAccounts(getEntity().getId());
	}
	@NotifyChange("directionList")
	@Command
	public void deleteDirection()
	{
		if (directionSelected == null)
			return;
		repository.removeActionDirection(directionSelected.getId());
		directionList = repository.getActionDirections(getEntity().getId());
	}
	@NotifyChange("calleridList")
	@Command
	public void deleteCallerId()
	{
		if (calleridSelected == null)
			return;
		repository.removeActionCallerId(calleridSelected.getId());
		calleridList = repository.getActionCallerIds(getEntity().getId());
	}
	
	@Command
	public void goAddDirection()
	{
		navigateTo(new PathPart("action_direction"));
	}
	@Command
	public void goAddCallerId()
	{
		navigateTo(new PathPart("action_callerid"));
	}
	@Command
	public void goAddVariable()
	{
		navigateTo(new PathPart("action_variable"));
	}
	@Command
	public void goAddAccount()
	{
		navigateTo(new PathPart("action_account"));
	}
	@Command
	public void goAddService()
	{
		navigateTo(new PathPart("action_service"));
	}
}
