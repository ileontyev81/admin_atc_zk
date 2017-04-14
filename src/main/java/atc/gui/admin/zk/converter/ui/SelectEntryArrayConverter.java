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
