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

import java.util.Collection;
import java.util.List;
import java.util.Set;

import atc.gui.admin.domain.model.AccessPermissionEntity;
import atc.gui.admin.domain.model.AccessRoleEntry;
import atc.gui.admin.domain.model.UserEntry;

public interface UserRepository
{

	public List<UserEntry> getUserList();
	public void saveUser(UserEntry user);
	public UserEntry getUserByLogin(String login);
	public UserEntry getUser(int userId);
	public void removeUser(int userId);

	
	public List<AccessRoleEntry> getRoles();
	public void saveRole(AccessRoleEntry role);
	public AccessRoleEntry getRole(int roleId);
	public Set<AccessPermissionEntity> getPermissions(int roleId);
	public Collection<AccessPermissionEntity> getUserPermissions(int userId);
	public void removeRole(int id);
}
