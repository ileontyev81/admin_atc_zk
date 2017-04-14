package atc.gui.admin.security;

import atc.gui.admin.domain.model.UserEntry;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.zkoss.zk.ui.Sessions;

import javax.servlet.http.HttpSession;

public class SpringSecurityHelper
{
	
    public static boolean hasRole(String role)
    {
    	UserEntry user = getCurrentUser();
    	if (user != null)
    	{
    		return user.getAuthorities().contains(new SimpleGrantedAuthority(role));
    	}
    	return true;
    }
    
    public static UserEntry getCurrentUser()
    {
    	Authentication authentication = getAuthentication();
    	if (authentication != null)
    	{
    		return (UserEntry) authentication.getPrincipal();
    	}
    	throw new NullPointerException("cant detect current user");
    }
    
    private static Authentication getAuthentication()
    {  
    	return ((SecurityContext) ((HttpSession) Sessions.getCurrent().getNativeSession()).getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication();
    }
    
}
