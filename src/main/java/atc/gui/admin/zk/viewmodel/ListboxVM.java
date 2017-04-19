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
