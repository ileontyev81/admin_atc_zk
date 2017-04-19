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

import atc.gui.admin.domain.model.AccessPermissionEntity;
import atc.gui.admin.domain.model.AccessRoleEntry;
import atc.gui.admin.domain.model.UserEntry;
import atc.gui.admin.domain.service.repository.UserRepository;
import atc.gui.admin.infrastructure.dao.DbRequest;
import atc.gui.admin.security.SpringSecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository
{
	private JdbcTemplate template;
	
	@Autowired
	public UserRepositoryImpl(JdbcTemplate template)
	{
		this.template = template;
	}
	
	@Override
	public List<UserEntry> getUserList()
	{
		return (new DbRequest<UserEntry>(template, "wadm_user_get_list", UserEntry.class)).execute();
	}

	@Override
	public void saveUser(UserEntry user)
	{
		DbRequest<UserEntry> req = new DbRequest<UserEntry>(template, "wadm_user_save", UserEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, user),
							  new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId())); 
	}

	@Override
	public UserEntry getUserByLogin(String login)
	{
		DbRequest<UserEntry> req = new DbRequest<UserEntry>(template, "wadm_user_get_by_login", UserEntry.class);
		req.execute(new SqlParameterValue(Types.VARCHAR, login));
		return req.singleResult();
	}

	@Override
	public UserEntry getUser(int userId)
	{
		DbRequest<UserEntry> req = new DbRequest<UserEntry>(template, "wadm_user_get", UserEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, userId));
		return req.singleResult();
	}

	@Override
	public void removeUser(int userId)
	{
		DbRequest<UserEntry> req = new DbRequest<UserEntry>(template, "wadm_user_delete", UserEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, userId));
	}

	
	
	
	
	@Override
	public List<AccessRoleEntry> getRoles()
	{
		return (new DbRequest<AccessRoleEntry>(template, "wadm_access_role_get_list", AccessRoleEntry.class)).execute();
	}

	@Override
	public void saveRole(AccessRoleEntry role)
	{
		DbRequest<AccessRoleEntry> req = new DbRequest<AccessRoleEntry>(template, "wadm_access_role_save", AccessRoleEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, role),
							  new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public Set<AccessPermissionEntity> getPermissions(int roleId)
	{
		DbRequest<AccessPermissionEntity> req = new DbRequest<AccessPermissionEntity>(template, "wadm_access_permission_get_list", AccessPermissionEntity.class);
		req.execute(new SqlParameterValue(Types.INTEGER, roleId));
		return new HashSet<AccessPermissionEntity>(req.result());
	}

	@Override
	public AccessRoleEntry getRole(int roleId)
	{
		DbRequest<AccessRoleEntry> req = new DbRequest<AccessRoleEntry>(template, "wadm_access_role_get", AccessRoleEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, roleId));
		return req.singleResult();
	}

	@Override
	public Collection<AccessPermissionEntity> getUserPermissions(int userId) 
	{
		return (new DbRequest<AccessPermissionEntity>(template, "wadm_user_permission_get_list", AccessPermissionEntity.class)).execute(new SqlParameterValue(Types.OTHER, userId));
	}

	@Override
	public void removeRole(int id)
	{
		DbRequest<AccessRoleEntry> req = new DbRequest<AccessRoleEntry>(template, "wadm_access_role_delete", AccessRoleEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, id));
	}
	
}
