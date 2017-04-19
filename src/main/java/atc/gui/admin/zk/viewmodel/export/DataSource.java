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

package atc.gui.admin.zk.viewmodel.export;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.springframework.util.ReflectionUtils;

@Slf4j 
public class DataSource<TT> implements JRDataSource
{
	private List<TT> entities;
	private int index = -1;

	public DataSource(List<TT> entities)
	{
		super();
		this.entities = entities;
		log.debug("export count: " + entities.size());
	}
	
	private Object getValueFromField(TT entity, String name)
	{
		Field cellField = ReflectionUtils.findField(entity.getClass(), name);
		if (cellField != null)
		{ 
			ReflectionUtils.makeAccessible(cellField);
			return ReflectionUtils.getField(cellField, entity);
		}
		return null;
	}
	
	private Object getValueUsingMethod(TT entity, String name) throws IntrospectionException
	{
		BeanInfo beanInfo = Introspector.getBeanInfo(entity.getClass());
		for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors())
		{
			if (descriptor.getName().equals(name))
			{
				return ReflectionUtils.invokeMethod(descriptor.getReadMethod(), entity);
			}
		}
		return null;
	}
	
	private boolean isMultiValue(Object cellValue)
	{
		return (cellValue.getClass().isArray() || Collection.class.isAssignableFrom(cellValue.getClass()));
	}
	
	private Object convertMultiValue(Object cellValue)
	{
		if (cellValue.getClass().isArray())
		{
			cellValue = Arrays.asList(cellValue);
		}
		StringBuffer buffer = new StringBuffer();
		Iterator<?> iterator = ((Iterable<?>)cellValue).iterator();
		while (iterator.hasNext())
		{
			Object nextValue = iterator.next();
			buffer.append(nextValue.toString());
			if (iterator.hasNext())
			{
				buffer.append(",");
			}
		}
		log.debug(buffer.toString());
		return buffer.toString();
	}
	
	private Object getValue(TT entity, String name)
	{
		Object cellValue = getValueFromField(entity, name);
		if (cellValue == null)
		{
			try
			{
				cellValue = getValueUsingMethod(entity, name);
			}
			catch (IntrospectionException e)
			{
				log.error(e.getMessage());
			}
		}
		return cellValue;
	}
	
	private Object postProcessedValue(Object cellValue)
	{
		if (isMultiValue(cellValue))
		{
			return convertMultiValue(cellValue);
		}
		return cellValue;
	}
	
	public Object getFieldValue(JRField field)
	{
		if (!entities.isEmpty())
		{
			final TT entity = entities.get(index);
			Object cellValue = getValue(entity, field.getName());
			if (cellValue != null)
			{
				return postProcessedValue(cellValue);
			}
		}
		log.debug(field.getName() + " : null");
		return null;
	}
	
	public boolean next() throws JRException
	{
		return ++index < entities.size();
	}
}