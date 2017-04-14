package atc.gui.admin.domain.service.repository;

import java.util.List;

import atc.gui.admin.domain.model.ActionEntry;

public interface ActionRepository
{

	public List<ActionEntry> findActions();
	
	public void removeAction(int actionId);
	
	public void saveAction(ActionEntry action);
	
	public ActionEntry getAction(int accountId);

	/*public List<ActionAccountEntry> findAccountsForAction(int parentActionId);
	
	public List<ActionCallerIdEntry> findCallerIdForAction(int parentActionId);
	
	public List<ActionDirectionEntry> getDirections(int actionId);
	
	public List<ActionIpAddressEntry> findIpAddressesForAction(int parentActionId);

	public void saveDirection(ActionDirectionEntry entity);

	public ActionDirectionEntry getDirection(int id, int actionId);

	public void removeDirection(int directionId);
	*/
}
