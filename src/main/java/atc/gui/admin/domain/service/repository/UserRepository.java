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
