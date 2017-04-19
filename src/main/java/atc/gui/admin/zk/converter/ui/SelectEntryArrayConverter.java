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

import atc.gui.admin.domain.model.appobjects.SelectEntity;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.zul.Listcell;

@Slf4j
public class SelectEntryArrayConverter implements Converter<String, SelectEntity[], Listcell>
{
	@Override
	public SelectEntity[] coerceToBean(String compAttr, Listcell component, BindContext ctx)
	{
		return null;
	}

	@Override
	public String coerceToUi(SelectEntity[] beanProp, Listcell component, BindContext ctx)
	{
		StringBuilder value = new StringBuilder();
	    if (beanProp != null)
	    {
	        for (SelectEntity selectEntry : beanProp)
	        {
	            if (selectEntry.getSelected())
	            {
	            	value.append(selectEntry.getLabel()).append(", ");
	            }
	        }
	    }
	    return value.toString().replaceAll("\\,\\s$", "");
	    /*if (value.length() > 0 && (value.lastIndexOf(",") == (value.length() - 1)))
	    {
	    	value.setLength(value.length() - 1);
	    }
		return value.toString();*/
	} 

}
