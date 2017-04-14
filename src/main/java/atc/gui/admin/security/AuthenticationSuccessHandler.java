package atc.gui.admin.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.zkoss.web.Attributes;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.TimeZone;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler 
{
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException 
    {
    	String clientTimeZoneName = getClientTimeZone(request);
    	if (clientTimeZoneName != null)
    	{
    		TimeZone clientTimeZone = TimeZone.getTimeZone(clientTimeZoneName);
    		setZkPrefferedTimeZone(request.getSession(), clientTimeZone);
    	}
        super.onAuthenticationSuccess(request, response, authentication);
    }
    
    private void setZkPrefferedTimeZone(HttpSession session, TimeZone preferredTimeZone)
    {
    	session.setAttribute(Attributes.PREFERRED_TIME_ZONE, preferredTimeZone);
    }
    
	private String getClientTimeZone(HttpServletRequest request)
	{
		Cookie[] cookies = request.getCookies();
		String timeOffset = null;
		if (cookies != null)
		{
			for (Cookie cookie : cookies)
			{
				if (cookie.getName().equals("TIMEZONE_COOKIE"))
				{
					timeOffset = cookie.getValue();
					break;
				}
			}
		}
		return timeOffset;
	}
}
