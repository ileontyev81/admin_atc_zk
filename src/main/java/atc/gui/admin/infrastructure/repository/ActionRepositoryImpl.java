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

package atc.gui.admin.infrastructure.repository;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import atc.gui.admin.security.SpringSecurityHelper;
import atc.gui.admin.domain.model.ActionEntry;
import atc.gui.admin.domain.service.repository.ActionRepository;
import atc.gui.admin.infrastructure.dao.DbRequest;

@Repository("actionRepository")
public class ActionRepositoryImpl implements ActionRepository
{
	private JdbcTemplate template;
	
	@Autowired
	public ActionRepositoryImpl(JdbcTemplate template)
	{
		this.template = template;
	}
	
	@Override
	public List<ActionEntry> findActions()
	{
		DbRequest<ActionEntry> listDbRequest = new DbRequest<ActionEntry>(template, "wadm_action_get_list", ActionEntry.class);
		return listDbRequest.execute();
	}

	@Override
	public void removeAction(int actionId)
	{
		// TODO: remove all related children entries before 
		DbRequest<ActionEntry> saveDbRequest = new DbRequest<ActionEntry>(template, "wadm_action_delete", ActionEntry.class);
		saveDbRequest.execute(new SqlParameterValue(Types.OTHER, actionId));
	}

	@Override
	public void saveAction(ActionEntry action)
	{
		DbRequest<ActionEntry> saveDbRequest = new DbRequest<ActionEntry>(template, "wadm_action_save", ActionEntry.class);
		saveDbRequest.execute(new SqlParameterValue(Types.OTHER, action),
							  new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public ActionEntry getAction(int accountId)
	{
		DbRequest<ActionEntry> getDbRequest = new DbRequest<ActionEntry>(template, "wadm_action_get", ActionEntry.class);
		getDbRequest.execute(new SqlParameterValue(Types.INTEGER, accountId), new SqlParameterValue(Types.BOOLEAN, false));
		return getDbRequest.singleResult();
	}

	/*@Override
	public List<ActionAccountEntry> findAccountsForAction(int parentActionId)
	{
		throw new NullPointerException("unimplemented method!"); 
	}

	@Override
	public List<ActionCallerIdEntry> findCallerIdForAction(int parentActionId)
	{
		throw new NullPointerException("unimplemented method!");
	}

	@Override
	public List<ActionDirectionEntry> getDirections(int actionId)
	{
		DbRequest<ActionDirectionEntry> listDbRequest = new DbRequest<ActionDirectionEntry>(template, "wadm_action_direction_get_list", ActionDirectionEntry.class);
		return listDbRequest.execute(new SqlParameterValue(Types.INTEGER, actionId));
	}

	@Override
	public List<ActionIpAddressEntry> findIpAddressesForAction(int parentActionId)
	{
		throw new NullPointerException("unimplemented method!");
	}

	@Override
	public void saveDirection(ActionDirectionEntry direction)
	{
		DbRequest<ActionDirectionEntry> req = new DbRequest<ActionDirectionEntry>(template, "wadm_action_direction_save", ActionDirectionEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, direction),
							  new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public ActionDirectionEntry getDirection(int id, int actionId)
	{
		DbRequest<ActionDirectionEntry> req = new DbRequest<ActionDirectionEntry>(template, "wadm_action_direction_get", ActionDirectionEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id), new SqlParameterValue(Types.INTEGER, actionId));
		return req.singleResult();
	}

	@Override
	public void removeDirection(int entityId)
	{
		DbRequest<ActionDirectionEntry> deleteDbRequest = new DbRequest<ActionDirectionEntry>(template, "wadm_action_direction_delete", ActionDirectionEntry.class);
		deleteDbRequest.execute(new SqlParameterValue(Types.INTEGER, entityId), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}*/
	
}
