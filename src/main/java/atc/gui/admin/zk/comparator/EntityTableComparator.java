package atc.gui.admin.zk.comparator;

import java.lang.reflect.Field;
import java.util.Comparator;

import lombok.extern.slf4j.Slf4j;

import org.springframework.util.ReflectionUtils;

import com.google.common.collect.Ordering;

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
    		Ordering<Comparable> natural = Ordering.natural();
    		try 
    		{
				int result = natural.compare((Comparable)o1FieldValue, (Comparable)o2FieldValue);
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
