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

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecurityUtil
{
	/**
	 * Used in tld
	 */
	public static boolean isGranted(String tag)
	{
		if (tag == null || tag.length() == 0)
			return false;
		
		final Collection<? extends GrantedAuthority> granted = getPrincipalAuthorities();

		for (GrantedAuthority grantedAuthority : granted)
		{
			Pattern p = Pattern.compile(grantedAuthority.getAuthority());
			Matcher m = p.matcher(tag);
			if (m.matches())
				return true;
		}
		return false;
	}
	private static Collection<? extends GrantedAuthority> getPrincipalAuthorities()
	{
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		if (null == currentUser)
		{
			return Collections.emptyList();
		}
		if ((null == currentUser.getAuthorities()) || (currentUser.getAuthorities().isEmpty()))
		{
			return Collections.emptyList();
		}
		Collection<? extends GrantedAuthority> granted = currentUser.getAuthorities();
		return granted;
	}
}
