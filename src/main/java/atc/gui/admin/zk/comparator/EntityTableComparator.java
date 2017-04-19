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

package atc.gui.admin.zk.comparator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Comparator;

@Slf4j
public class EntityTableComparator implements Comparator<Object> 
{
	private final Class<?> entityType;
	private final String columnName;
	private final boolean asc;
	
	public EntityTableComparator(Class<?> entityType, String columnName, boolean asc)
	{
		this.entityType = entityType;;
		this.columnName = columnName;
		this.asc = asc;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public int compare(Object o1, Object o2)
	{
		Field entryField = ReflectionUtils.findField(entityType, columnName);
		ReflectionUtils.makeAccessible(entryField);
		Class<?> columnType = entryField.getType();
		Object o1FieldValue = ReflectionUtils.getField(entryField, o1);
		Object o2FieldValue = ReflectionUtils.getField(entryField, o2);
    	if (columnType.isArray())
    	{
    		return (new SelectEntryArrayComparator(asc)).compare(o1FieldValue, o2FieldValue);
    	}
    	else 
    	{
    		try
    		{
				int result = ((Comparable)o1FieldValue).compareTo(o2FieldValue);
				return asc ? result : -result;
			} 
    		catch (Exception e) 
    		{
				log.error("error while comparing", e);
			}
    	}
    	return -1;
	}

}
