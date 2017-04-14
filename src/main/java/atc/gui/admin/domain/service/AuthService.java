package atc.gui.admin.domain.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import atc.gui.admin.domain.model.UserEntry;

public interface AuthService
{

	public abstract <T extends GrantedAuthority> Collection<T> loadPremissions(UserEntry user);
	
	//public abstract <T extends GrantedAuthority> Collection<T> loadRolesAsPremissions(UserEntry user);
	
//	public abstract <T extends GrantedAuthority> Collection<T> loadAllRolesAsPermissions();
	
}
