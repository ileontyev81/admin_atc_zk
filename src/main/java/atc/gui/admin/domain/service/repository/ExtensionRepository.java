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

package atc.gui.admin.domain.service.repository;

import java.util.List;

import atc.gui.admin.domain.model.AccountLinkedEntry;
import atc.gui.admin.domain.model.CallerIdEntry;
import atc.gui.admin.domain.model.DirectionEntry;
import atc.gui.admin.domain.model.ExtensionActionEntry;
import atc.gui.admin.domain.model.ExtensionEntry;
import atc.gui.admin.domain.model.ServiceLinkedEntry;
import atc.gui.admin.domain.model.VariableEntry;

public interface ExtensionRepository
{

	public List<ExtensionEntry> findExtensions();
	public void saveExtension(ExtensionEntry extension);
	public void removeExtension(int extensionId);
	public void changeActiveExtension(int extensionId);
	public ExtensionEntry getExtension(int extensionId);// return extension with full data inside
	
	//public List<ExtensionActionEntry> getActions(int extensionId);
	
	//DIRECTIONS
	public DirectionEntry getDirection(int id, int extensionId);
	public List<DirectionEntry> getDirections(int extensionId);
	public void saveDirection(DirectionEntry entry);
	public void removeDirection(int entityId);

	
	//ACTIONS
	public List<ExtensionActionEntry> getActions(int extensionId);
	public ExtensionActionEntry getAction(int id, int extensionId);
	public void saveAction(ExtensionActionEntry entry);
	public void removeAction(int id);
	public void changeActiveAction(int id);
	//public void saveDirection(DirectionEntry direction);
	
	
	//ACTION CALLER ID
	public List<CallerIdEntry> getActionCallerIds(int actionId);
	public CallerIdEntry getActionCallerId(int id, int actionId);
	public void saveActionCallerId(CallerIdEntry entry);
	public void removeActionCallerId(int id);
	
	//ACTION DIRECTION
	public List<DirectionEntry> getActionDirections(int actionId);
	public DirectionEntry getActionDirection(int id, int actionId);
	public void saveActionDirection(DirectionEntry entry);
	public void removeActionDirection(int id);
	
	//ACTION VARIABLE
	public List<VariableEntry> getActionVariables(int actionId);
	public VariableEntry getActionVariable(int id, int actionId);
	public void saveActionVariable(VariableEntry entry);
	public void removeActionVariable(int id);
	
	//ACTION ACCOUNT
	public List<AccountLinkedEntry> getActionAccounts(int actionId);
	public AccountLinkedEntry getActionAccount(int id, int actionId);
	public void saveActionAccount(AccountLinkedEntry entry);
	public void removeActionAccount(int id);
	
	//ACTION SERVICE
	public List<ServiceLinkedEntry> getActionServices(int actionId);
	public ServiceLinkedEntry getActionService(int id, int actionId);
	public void saveActionService(ServiceLinkedEntry entry);
	public void removeActionService(int id);
	
	//SERVICE VARIABLE
	public List<VariableEntry> getServiceVariables(int parentId);
	public VariableEntry getServiceVariable(int id, int parentId);
	public void saveServiceVariable(VariableEntry entry);
	public void removeServiceVariable(int id);
	
	
	
	//VARIABLE
	public List<VariableEntry> getVariables(int extensionId);
	public VariableEntry getVariable(int id, int extensionId);
	public void saveVariable(VariableEntry entry);
	public void removeVariable(int id);
	

}
