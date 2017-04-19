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
import atc.gui.admin.domain.model.GroupEntry;
import atc.gui.admin.domain.model.GroupSupervisorEntry;
import atc.gui.admin.domain.model.OperatorEntry;
import atc.gui.admin.domain.model.OperatorQueueEntry;
import atc.gui.admin.domain.service.repository.OperatorRepository;
import atc.gui.admin.infrastructure.dao.DbRequest;

@Repository("operatorRepository")
public class OperatorRepositoryImpl implements OperatorRepository
{
	private JdbcTemplate template;

	@Autowired
	public OperatorRepositoryImpl(JdbcTemplate template)
	{
		this.template = template;
	}
	
	
	@Override
	public void saveOperator(OperatorEntry e)
	{
		DbRequest<OperatorEntry> req = new DbRequest<OperatorEntry>(template, "wadm_operator_save", OperatorEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, e),
				new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public OperatorEntry getOperator(int id)
	{
		DbRequest<OperatorEntry> req = new DbRequest<OperatorEntry>(template, "wadm_operator_get", OperatorEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
		return req.singleResult();
	}

	@Override
	public List<OperatorEntry> getOperatorList(Boolean export)
	{
		DbRequest<OperatorEntry> req = new DbRequest<OperatorEntry>(template, "wadm_operator_get_list", OperatorEntry.class);
		return req.execute(new SqlParameterValue(Types.BOOLEAN, export), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}
	
	@Override
	public List<OperatorEntry> searchOperators(String key)
	{
		DbRequest<OperatorEntry> req = new DbRequest<OperatorEntry>(template, "wadm_operator_search", OperatorEntry.class);
		return req.execute(new SqlParameterValue(Types.VARCHAR, key),
				  new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public void deleteOperator(int id)
	{
		DbRequest<OperatorEntry> req = new DbRequest<OperatorEntry>(template, "wadm_operator_delete", OperatorEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}


	
	
	
	@Override
	public void saveQueue(OperatorQueueEntry e)
	{
		DbRequest<OperatorQueueEntry> req = new DbRequest<OperatorQueueEntry>(template, "wadm_operator_queue_save", OperatorQueueEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, e),
				new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}


	@Override
	public OperatorQueueEntry getQueue(int id, int operatorId)
	{
		DbRequest<OperatorQueueEntry> req = new DbRequest<OperatorQueueEntry>(template, "wadm_operator_queue_get", OperatorQueueEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id), new SqlParameterValue(Types.INTEGER, operatorId), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
		return req.singleResult();
	}


	@Override
	public List<OperatorQueueEntry> getQueueList(int operatorId)
	{
		DbRequest<OperatorQueueEntry> req = new DbRequest<OperatorQueueEntry>(template, "wadm_operator_queue_get_list", OperatorQueueEntry.class);
		return req.execute(new SqlParameterValue(Types.INTEGER, operatorId), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}


	@Override
	public void deleteQueue(int id)
	{
		DbRequest<OperatorQueueEntry> req = new DbRequest<OperatorQueueEntry>(template, "wadm_operator_queue_delete", OperatorQueueEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}


	
	
	
	
	@Override
	public void saveGroup(GroupEntry e)
	{
		DbRequest<GroupEntry> req = new DbRequest<GroupEntry>(template, "wadm_group_save", GroupEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, e),
				new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public GroupEntry getGroup(int id)
	{
		DbRequest<GroupEntry> req = new DbRequest<GroupEntry>(template, "wadm_group_get", GroupEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id));
		return req.singleResult();
	}

	@Override
	public List<GroupEntry> getGroupList()
	{
		DbRequest<GroupEntry> req = new DbRequest<GroupEntry>(template, "wadm_group_get_list", GroupEntry.class);
		return req.execute();
	}
	
	
	
	


	@Override
	public void saveGroupSupervisor(GroupSupervisorEntry e)
	{
		DbRequest<GroupSupervisorEntry> req = new DbRequest<GroupSupervisorEntry>(template, "wadm_group_supervisor_save", GroupSupervisorEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, e),
				new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}


	@Override
	public GroupSupervisorEntry getGroupSupervisor(int id)
	{
		DbRequest<GroupSupervisorEntry> req = new DbRequest<GroupSupervisorEntry>(template, "wadm_group_supervisor_get", GroupSupervisorEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id));
		return req.singleResult();
	}


	@Override
	public List<GroupSupervisorEntry> getGroupSupervisorList(int id)
	{
		DbRequest<GroupSupervisorEntry> req = new DbRequest<GroupSupervisorEntry>(template, "wadm_group_supervisor_get_list", GroupSupervisorEntry.class);
		return req.execute(new SqlParameterValue(Types.INTEGER, id));
	}


	@Override
	public void deleteGroupSupervisor(int id)
	{
		DbRequest<GroupSupervisorEntry> req = new DbRequest<GroupSupervisorEntry>(template, "wadm_group_supervisor_delete", GroupSupervisorEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id));
	}
}
