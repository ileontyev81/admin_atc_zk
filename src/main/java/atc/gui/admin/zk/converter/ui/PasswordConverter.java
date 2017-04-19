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

package atc.gui.admin.zk.converter.ui;

import org.springframework.security.authentication.encoding.BasePasswordEncoder;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.zul.Textbox;

public class PasswordConverter implements Converter<String, String, Textbox>
{
	private String savedPassword = null;
	
	@Override
	public String coerceToUi(String beanProp, Textbox component, BindContext ctx)
	{
		this.savedPassword = beanProp;
		return beanProp;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String coerceToBean(String compAttr, Textbox component, BindContext ctx)
	{
		if (!savedPassword.equals(compAttr))
		{
			return ((BasePasswordEncoder)ctx.getConverterArg("encoder")).encodePassword(compAttr, null);
		}
		return savedPassword;
	}

}
