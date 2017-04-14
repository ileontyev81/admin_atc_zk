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
