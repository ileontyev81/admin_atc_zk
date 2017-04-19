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
import atc.gui.admin.zk.validator.DigitTextboxValidator;
import atc.gui.admin.zk.validator.Jsr303Validator;
import org.apache.commons.lang3.ClassUtils;
import org.zkoss.bind.Validator;

import java.lang.reflect.Field;

public class GeneralEdit 
{
	
	public static <T extends SerializedEntity> Validator getAppValidator(Class<T> entityType)
	{
		return new Jsr303Validator<T>(entityType);
	}
	
	public static Validator getDigitTextboxValidator(int digitsCount)
	{
		return new DigitTextboxValidator(digitsCount);
	}
	
	public static <T extends SerializedEntity> boolean isDisabled(Field entityField, PersistentEntity entity)
	{
		if (entityField.isAnnotationPresent(FormField.class))
    	{
    		long flags = entityField.getAnnotation(FormField.class).flags();
    		return isDisabledForEntity(flags, entity);
    	}
    	return false;
	}

	private static <T extends SerializedEntity> boolean isDisabledForEntity(long flags, PersistentEntity entity)
	{
    	if (entity.isPersisted())
    	{
    		return ((flags & FormFieldFlag.EDIT_ENABLED) != 0);
    	}
    	return ((flags & FormFieldFlag.ADD_ENABLED) != 0); 
	}

	public static boolean isDigitField(java.lang.reflect.Field field)
	{
		return ClassUtils.isAssignable(field.getType(), Number.class, true);
	}

}
