package atc.gui.admin.security;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import atc.gui.admin.domain.model.UserEntry;
import atc.gui.admin.domain.service.AuthService;
import atc.gui.admin.domain.service.repository.UserRepository;

@Service("userDetailsService")
@Slf4j
public class AuthUserDetailsService implements UserDetailsService  
{
	private final UserRepository userRepository; 
	private final AuthService authService; 
	
	@Autowired
	public AuthUserDetailsService(UserRepository userRepository, AuthService authService)
	{
		this.userRepository = userRepository; 
		this.authService = authService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException 
	{
        UserEntry user = userRepository.getUserByLogin(login);
        if (user == null)
        {
            log.debug("query returned no results for user '" + login + "'");
            MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
            throw new UsernameNotFoundException(messages.getMessage("JdbcDaoImpl.notFound", new Object[]{login}));
        }
		user.addAuthorities(authService.loadPremissions(user));
		//user.addAuthorities(authService.loadRolesAsPremissions(user));
		return user;
	}
	
}



