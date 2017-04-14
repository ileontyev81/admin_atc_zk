package atc.gui.admin.zk.tld;

import atc.gui.admin.domain.FormField;
import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.SortableField;
import atc.gui.admin.zk.comparator.EntityTableComparator;

import java.lang.reflect.Field;
import java.util.Comparator;

public class GeneralList 
{
	
	public static <T extends SerializedEntity> Comparator<Object> getSimpleComparator(final String columnName, final boolean asc, Class<T> entityType)
    {
    	return new EntityTableComparator(entityType, columnName, asc);
    }
    
	public static boolean isMultirowField(Field field)
    {
    	return field.getAnnotation(FormField.class).type().isMultiRow();
    }

	public static boolean isSortable(Field field)
	{
		return field.isAnnotationPresent(SortableField.class);
	}
	
}
