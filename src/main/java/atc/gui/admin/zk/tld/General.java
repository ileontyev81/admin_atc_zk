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

package atc.gui.admin.zk.tld;

import atc.gui.admin.domain.FormField;
import atc.gui.admin.domain.FormFieldFlag;
import atc.gui.admin.domain.PersistentEntity;
import atc.gui.admin.domain.SerializedEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;
import org.springframework.util.ReflectionUtils.FieldFilter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class General
{
	private abstract static class ExFieldFilter implements FieldFilter
	{

		@Override
		public boolean matches(Field field)
		{
			return isVisible(field) || (getFlags(field) == FormFieldFlag.NO_FLAGS);// FormFieldFlag.NO_FLAGS - no flags in annotation
		}

		protected static long getFlags(Field field)
		{
			if (field.isAnnotationPresent(FormField.class))
			{
				return field.getAnnotation(FormField.class).flags();
			}
			return FormFieldFlag.HIDDEN;
		}

		protected abstract boolean isVisible(Field field);
	}

	public static <T extends SerializedEntity> List<Field> getListFields(Class<T> entityClass)
	{
		return getFields(entityClass, new ExFieldFilter()
		{

			@Override
			protected boolean isVisible(Field field)
			{
				return ((getFlags(field) & FormFieldFlag.LIST_VISIBLE & ~FormFieldFlag.HIDDEN) != 0);
			}

		});
	}

	public static List<Field> getEditFields(final PersistentEntity entity)
	{
		return getFields(entity.getClass(), new ExFieldFilter()
		{

			@Override
			protected boolean isVisible(Field field)
			{
				long flags = getFlags(field);
				if (entity.isPersisted())
		    	{
		    		return ((flags & FormFieldFlag.EDIT_VISIBLE & ~FormFieldFlag.HIDDEN) != 0);
		    	}
		    	return ((flags & FormFieldFlag.ADD_VISIBLE & ~FormFieldFlag.HIDDEN) != 0);
			}

		});
	}

	@SuppressWarnings("rawtypes")
	private static List<Field> getFields(Class entityClass, FieldFilter filter)
	{
		final List<Field> fieldsToShow = new ArrayList<Field>();
		ReflectionUtils.doWithFields(entityClass,
				new FieldCallback()
				{
					@Override
					public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException
					{
						fieldsToShow.add(field);
					}
				},
				filter
		);
        return fieldsToShow;
	}

	public static <T extends SerializedEntity> String getFieldCaption(String fieldName, Class<T> entityType)
	{
		EntityFieldCaptionBuilder<T> captionBuilder = new EntityFieldCaptionBuilder<T>(entityType);
		return captionBuilder.getFieldCaption(fieldName);
	}

	public static <T extends SerializedEntity> List<String> getListFieldsCaptions(Class<T> entityClass)
	{
		List<String> captions = new ArrayList<String>();
		for (Field field : getListFields(entityClass))
		{
			captions.add(getFieldCaption(field.getName(), entityClass));
		}
		return captions;
	}

	public static <T extends SerializedEntity> String getFormFieldType(Field entityField)
	{
		return entityField.getAnnotation(FormField.class).type().name();
	}

}



