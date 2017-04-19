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

package atc.gui.admin.security;

import atc.gui.admin.domain.model.UserEntry;
import atc.gui.admin.domain.service.AuthService;
import atc.gui.admin.domain.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service("authService")
public class AuthServiceImpl implements AuthService
{
	private UserRepository repository;
	
	@Autowired
	public AuthServiceImpl(UserRepository roleRepository)
	{
		this.repository = roleRepository;
	}
	
    @SuppressWarnings("unchecked")
	@Override
	public <T extends GrantedAuthority> Collection<T> loadPremissions(UserEntry user)
    {
    	Collection<T> permissions = new HashSet<T>();
    	/*for (SelectEntity selectedRole : user.getRoles()) 
    	{
    		if (selectedRole.getSelected())
    		{
    			permissions.addAll((Collection<? extends T>) repository.getPermissions(selectedRole.getId()));
    		}
    	}*/
    	permissions.addAll((Collection<? extends T>) repository.getUserPermissions(user.getId()));
    	return permissions;
	}

    /*@SuppressWarnings("unchecked")
	@Override
	public <T extends GrantedAuthority> Collection<T> loadRolesAsPremissions(UserEntry user)
    {
    	Collection<T> rolesAsPremissions = new HashSet<T>();
    	for (SelectEntity selectedRole : user.getRoles())
    	{
    		if (selectedRole.getSelected())
    		{
    			rolesAsPremissions.add((T) new SimpleGrantedAuthority(selectedRole.getLabel()));
    		}
    	}
    	return rolesAsPremissions;
    }*/
    
//  @Override
//	@SuppressWarnings("unchecked")
//	public <T extends GrantedAuthority> Collection<T> loadAllRolesAsPermissions()
//  {
//  	Collection<T> allRolesAsPremissions = new HashSet<T>();
//  	for (RoleEntry role : roleRepository.findRoles())
//  	{
//  		allRolesAsPremissions.add((T) new SimpleGrantedAuthority(role.getName()));
//  	}
//  	return allRolesAsPremissions;
//  }
    
}
