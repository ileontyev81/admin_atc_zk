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
