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

import atc.gui.admin.zk.event.EventHandler;
import atc.gui.admin.zk.event.impl.PathChangedEvent;
import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.zk.viewmodel.base.BaseVM;
import lombok.Getter;
import org.apache.commons.lang3.text.WordUtils;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;

import java.util.Deque;

public class BreadcrumbVM extends BaseVM implements EventHandler<PathChangedEvent>
{
    @Getter
	private Deque<PathPart> pathsVector;

	@Init
	public void init()
	{
		getTransitionManager().addPathChangeHandler(this);
	}

    // TODO: use i18n
    public String getLabel(PathPart pathPart)
    {
        String label = Labels.getLabel("breadcrumbs." + pathPart.getId());
        if (label == null) {
            label = WordUtils.capitalize(pathPart.getId().replace("_", " "));
        }
    	if (isEditPathPart(pathPart))
    	{
            label = label + getEditPathPartPostfix(new EditPathPart(pathPart));
    	}
    	return label;
    }
    
    private boolean isEditPathPart(PathPart pathPart)
    {
    	return pathPart.hasParam(EditPathPart.OBJECT_ID);
    }
    
    private String getEditPathPartPostfix(EditPathPart pathPart)
    {
    	int entityId = pathPart.getIntObjectId();
		return " [" + ((entityId > 0 ) ? entityId : Labels.getLabel("breadcrumbs.NEW")) + "]";
    }
    
    @Command("choose")
    public void choose(@BindingParam("pathPart") PathPart pathPart)
    {
    	getTransitionManager().select(pathPart);
    }

	@Override
	public void handleEvent(PathChangedEvent event)
	{
    	this.pathsVector = event.getPathParts();
    	BindUtils.postNotifyChange(null, null, this, "pathsVector");
	}

}