package atc.gui.admin.zk.viewmodel;

import atc.gui.admin.domain.model.appobjects.SelectEntity;
import lombok.Getter;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Default;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import java.util.*;

public class ListboxVM  
{
	@Getter
	private List<SelectEntity> dataList = new ArrayList<SelectEntity>();
	
    @Getter
    private Set<SelectEntity> selectedItems = new HashSet<SelectEntity>();
    
    @Init
    public void init(@BindingParam("model") SelectEntity[] data, 
    				 @BindingParam("noSelection") @Default("false") boolean containsNoSelection)
    {
    	fillDataList(data, containsNoSelection);
    	initSelection();
    }
    
    private void fillDataList(SelectEntity[] data, boolean containsNoSelection)
    {
    	if (containsNoSelection)
    	{
    		dataList.add(emptySelection());
    	}
    	if (data != null)
    	{
    		dataList.addAll(Arrays.asList(data));
    	}
    }
    
    @NotifyChange("selectedItems")
    private void initSelection()
    {
    	for (SelectEntity entity : dataList)
    	{
    		if (entity.getSelected())
    		{
    			selectedItems.add(entity);
    		}
    	}
    	
    	if (noSelectionOnlyData())
    	{
    		selectedItems.add(emptySelection());
    	}
    }
    
    private SelectEntity emptySelection()
    {
    	return new SelectEntity(0, "", false);
    }
    
    private boolean noSelectionOnlyData() 
    {
    	return (dataList.size() == 1 && dataList.contains(emptySelection()));
    }

    @NotifyChange("selectedItems")
    public void setSelectedItems(Set<SelectEntity> selectedUnits)
    {
        for (SelectEntity entry : dataList)
        {
            if (selectedUnits.contains(entry))
            {
                entry.setSelected(true);
            }
            else 
            {
                entry.setSelected(false);
            }
        }
        this.selectedItems = selectedUnits;
    }
    
}
