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
