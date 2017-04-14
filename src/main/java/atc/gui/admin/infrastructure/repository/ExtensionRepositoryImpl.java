package atc.gui.admin.infrastructure.repository;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import atc.gui.admin.security.SpringSecurityHelper;
import atc.gui.admin.domain.model.AccountLinkedEntry;
import atc.gui.admin.domain.model.CallerIdEntry;
import atc.gui.admin.domain.model.DirectionEntry;
import atc.gui.admin.domain.model.ExtensionActionEntry;
import atc.gui.admin.domain.model.ExtensionEntry;
import atc.gui.admin.domain.model.ServiceLinkedEntry;
import atc.gui.admin.domain.model.VariableEntry;
import atc.gui.admin.domain.service.repository.ExtensionRepository;
import atc.gui.admin.infrastructure.dao.DbRequest;

@Repository("extensionRepository")
public class ExtensionRepositoryImpl implements ExtensionRepository
{
	private JdbcTemplate template;

	@Autowired
	public ExtensionRepositoryImpl(JdbcTemplate template)
	{
		this.template = template;
	}
	
	@Override
	public List<ExtensionEntry> findExtensions()// lazy loaded extensions in list
	{
		DbRequest<ExtensionEntry> req = new DbRequest<ExtensionEntry>(template, "wadm_extension_get_list", ExtensionEntry.class);
		return req.execute();
	}

	@Override
	public void saveExtension(ExtensionEntry extension)
	{
		DbRequest<ExtensionEntry> req = new DbRequest<ExtensionEntry>(template, "wadm_extension_save", ExtensionEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, extension),
							  new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}
	@Override
	public void changeActiveExtension(int extensionId)
	{
		DbRequest<ExtensionEntry> req = new DbRequest<ExtensionEntry>(template, "wadm_extension_change_active", ExtensionEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, extensionId));
	}
	public void removeExtension(int extensionId)
	{
		DbRequest<ExtensionEntry> req = new DbRequest<ExtensionEntry>(template, "wadm_extension_delete", ExtensionEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, extensionId));
	}

	@Override
	public ExtensionEntry getExtension(int extensionId)// eager loaded extension
	{
		DbRequest<ExtensionEntry> req = new DbRequest<ExtensionEntry>(template, "wadm_extension_get", ExtensionEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, extensionId), new SqlParameterValue(Types.BOOLEAN, false));
		return req.singleResult();
	}
	
	/*@Override
	public List<ExtensionActionEntry> getActions(int extensionId)
	{
		DbRequest<ExtensionActionEntry> listDbRequest = new DbRequest<ExtensionActionEntry>(template, "wadm_extension_action_get_list", ExtensionActionEntry.class);
		return listDbRequest.execute(new SqlParameterValue(Types.INTEGER, extensionId));
	}*/
	@Override
	public DirectionEntry getDirection(int id, int extensionId)
	{
		DbRequest<DirectionEntry> req = new DbRequest<DirectionEntry>(template, "wadm_extension_direction_get", DirectionEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id), new SqlParameterValue(Types.INTEGER, extensionId));
		return req.singleResult();
	}
	@Override
	public List<DirectionEntry> getDirections(int extensionId)
	{
		DbRequest<DirectionEntry> req = new DbRequest<DirectionEntry>(template, "wadm_extension_direction_get_list", DirectionEntry.class);
		return req.execute(new SqlParameterValue(Types.INTEGER, extensionId));
	}

	@Override
	public void removeDirection(int entityId)
	{
		DbRequest<DirectionEntry> req = new DbRequest<DirectionEntry>(template, "wadm_extension_direction_delete", DirectionEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, entityId));
	}
	@Override
	public void saveDirection(DirectionEntry entry)
	{
		DbRequest<DirectionEntry> req = new DbRequest<DirectionEntry>(template, "wadm_extension_direction_save", DirectionEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, entry), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}
	
	/*@Override
	public DirectionEntry getDirection(int directionId)
	{
		DbRequest<DirectionEntry> getDbRequest = new DbRequest<DirectionEntry>(template, "wadm_extension_direction_get", DirectionEntry.class);
		getDbRequest.execute(new SqlParameterValue(Types.INTEGER, directionId), new SqlParameterValue(Types.BOOLEAN, false));
		return getDbRequest.singleResult();
	}
	
	@Override
	public void saveDirection(DirectionEntry direction)
	{
		DbRequest<DirectionEntry> saveDbRequest = new DbRequest<DirectionEntry>(template, "wadm_extension_direction_save", DirectionEntry.class);
		saveDbRequest.execute(new SqlParameterValue(Types.OTHER, direction),
							  new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}*/
	
	@Override
	public ExtensionActionEntry getAction(int actionId, int extensionId)
	{
		DbRequest<ExtensionActionEntry> req = new DbRequest<ExtensionActionEntry>(template, "wadm_extension_action_get", ExtensionActionEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, actionId), new SqlParameterValue(Types.INTEGER, extensionId));
		return req.singleResult();
	}

	@Override
	public List<ExtensionActionEntry> getActions(int extensionId)
	{
		DbRequest<ExtensionActionEntry> req = new DbRequest<ExtensionActionEntry>(template, "wadm_extension_action_get_list", ExtensionActionEntry.class);
		return req.execute(new SqlParameterValue(Types.INTEGER, extensionId));
	}

	@Override
	public void saveAction(ExtensionActionEntry entry)
	{
		DbRequest<ExtensionActionEntry> req = new DbRequest<ExtensionActionEntry>(template, "wadm_extension_action_save", ExtensionActionEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, entry),
				new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public void removeAction(int id)
	{
		DbRequest<ExtensionActionEntry> req = new DbRequest<ExtensionActionEntry>(template, "wadm_extension_action_delete", ExtensionActionEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id));
	}
	@Override
	public void changeActiveAction(int id)
	{
		DbRequest<ExtensionActionEntry> req = new DbRequest<ExtensionActionEntry>(template, "wadm_extension_action_change_active", ExtensionActionEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id));
	}

	//VARIABLES
	@Override
	public VariableEntry getVariable(int id, int extensionId)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_extension_variable_get", VariableEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id), new SqlParameterValue(Types.INTEGER, extensionId));
		return req.singleResult();
	}

	@Override
	public List<VariableEntry> getVariables(int extensionId)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_extension_variable_get_list", VariableEntry.class);
		return req.execute(new SqlParameterValue(Types.INTEGER, extensionId));
	}

	@Override
	public void saveVariable(VariableEntry entry)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_extension_variable_save", VariableEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, entry),
				new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public void removeVariable(int id)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_extension_variable_delete", VariableEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id));
	}
	
	
	//ACTION CALLER ID
	@Override
	public List<CallerIdEntry> getActionCallerIds(int actionId)
	{
		DbRequest<CallerIdEntry> req = new DbRequest<CallerIdEntry>(template, "wadm_extension_action_callerid_get_list", CallerIdEntry.class);
		return req.execute(new SqlParameterValue(Types.INTEGER, actionId));
	}

	@Override
	public CallerIdEntry getActionCallerId(int id, int actionId)
	{
		DbRequest<CallerIdEntry> req = new DbRequest<CallerIdEntry>(template, "wadm_extension_action_callerid_get", CallerIdEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id), new SqlParameterValue(Types.INTEGER, actionId));
		return req.singleResult();
	}

	@Override
	public void saveActionCallerId(CallerIdEntry entry)
	{
		DbRequest<CallerIdEntry> req = new DbRequest<CallerIdEntry>(template, "wadm_extension_action_callerid_save", CallerIdEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, entry),
				new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public void removeActionCallerId(int id)
	{
		DbRequest<CallerIdEntry> req = new DbRequest<CallerIdEntry>(template, "wadm_extension_action_callerid_delete", CallerIdEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}
		
		
	
	//ACTION DIRECTION
	@Override
	public List<DirectionEntry> getActionDirections(int actionId)
	{
		DbRequest<DirectionEntry> req = new DbRequest<DirectionEntry>(template, "wadm_extension_action_direction_get_list", DirectionEntry.class);
		return req.execute(new SqlParameterValue(Types.INTEGER, actionId));
	}

	@Override
	public DirectionEntry getActionDirection(int id, int actionId)
	{
		DbRequest<DirectionEntry> req = new DbRequest<DirectionEntry>(template, "wadm_extension_action_direction_get", DirectionEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id), new SqlParameterValue(Types.INTEGER, actionId));
		return req.singleResult();
	}

	@Override
	public void saveActionDirection(DirectionEntry entry)
	{
		DbRequest<DirectionEntry> req = new DbRequest<DirectionEntry>(template, "wadm_extension_action_direction_save", DirectionEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, entry),
				new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public void removeActionDirection(int id)
	{
		DbRequest<DirectionEntry> req = new DbRequest<DirectionEntry>(template, "wadm_extension_action_direction_delete", DirectionEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	
	//ACTION VARIABLES
	@Override
	public List<VariableEntry> getActionVariables(int actionId)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_extension_action_variable_get_list", VariableEntry.class);
		return req.execute(new SqlParameterValue(Types.INTEGER, actionId));
	}

	@Override
	public VariableEntry getActionVariable(int id, int actionId)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_extension_action_variable_get", VariableEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id), new SqlParameterValue(Types.INTEGER, actionId));
		return req.singleResult();
	}

	@Override
	public void saveActionVariable(VariableEntry entry)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_extension_action_variable_save", VariableEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, entry),
				new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public void removeActionVariable(int id)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_extension_action_variable_delete", VariableEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id));
	}

	//ACCOUNT VARIABLES
	@Override
	public List<AccountLinkedEntry> getActionAccounts(int actionId)
	{
		DbRequest<AccountLinkedEntry> req = new DbRequest<AccountLinkedEntry>(template, "wadm_extension_action_account_get_list", AccountLinkedEntry.class);
		return req.execute(new SqlParameterValue(Types.INTEGER, actionId));
	}

	@Override
	public AccountLinkedEntry getActionAccount(int id, int actionId)
	{
		DbRequest<AccountLinkedEntry> req = new DbRequest<AccountLinkedEntry>(template, "wadm_extension_action_account_get", AccountLinkedEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id), new SqlParameterValue(Types.INTEGER, actionId));
		return req.singleResult();
	}

	@Override
	public void saveActionAccount(AccountLinkedEntry entry)
	{
		DbRequest<AccountLinkedEntry> req = new DbRequest<AccountLinkedEntry>(template, "wadm_extension_action_account_save", AccountLinkedEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, entry),
				new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public void removeActionAccount(int id)
	{
		DbRequest<AccountLinkedEntry> req = new DbRequest<AccountLinkedEntry>(template, "wadm_extension_action_account_delete", AccountLinkedEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id));
	}

	
	
	
	//ACTION SERVICE
	@Override
	public List<ServiceLinkedEntry> getActionServices(int actionId)
	{
		DbRequest<ServiceLinkedEntry> req = new DbRequest<ServiceLinkedEntry>(template, "wadm_extension_action_service_get_list", ServiceLinkedEntry.class);
		return req.execute(new SqlParameterValue(Types.INTEGER, actionId));
	}

	@Override
	public ServiceLinkedEntry getActionService(int id, int actionId)
	{
		DbRequest<ServiceLinkedEntry> req = new DbRequest<ServiceLinkedEntry>(template, "wadm_extension_action_service_get", ServiceLinkedEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id), new SqlParameterValue(Types.INTEGER, actionId));
		return req.singleResult();
	}

	@Override
	public void saveActionService(ServiceLinkedEntry entry)
	{
		DbRequest<ServiceLinkedEntry> req = new DbRequest<ServiceLinkedEntry>(template, "wadm_extension_action_service_save", ServiceLinkedEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, entry),
				new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public void removeActionService(int id)
	{
		DbRequest<ServiceLinkedEntry> req = new DbRequest<ServiceLinkedEntry>(template, "wadm_extension_action_service_delete", ServiceLinkedEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id));
	}

	
	
	//SERVICE VARIABLE
	@Override
	public List<VariableEntry> getServiceVariables(int parentId)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_action_service_variable_get_list", VariableEntry.class);
		return req.execute(new SqlParameterValue(Types.INTEGER, parentId));
	}

	@Override
	public VariableEntry getServiceVariable(int id, int parentId)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_action_service_variable_get", VariableEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id), new SqlParameterValue(Types.INTEGER, parentId));
		return req.singleResult();
	}

	@Override
	public void saveServiceVariable(VariableEntry entry)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_action_service_variable_save", VariableEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, entry),
				new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public void removeServiceVariable(int id)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_action_service_variable_delete", VariableEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id));
	}
}
