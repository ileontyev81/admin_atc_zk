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
