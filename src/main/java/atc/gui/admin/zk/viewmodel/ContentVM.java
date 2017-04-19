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
import atc.gui.admin.zk.viewmodel.base.BaseVM;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Default;
import org.zkoss.bind.annotation.Init;

import java.util.Deque;
@Slf4j
public class ContentVM extends BaseVM implements EventHandler<PathChangedEvent>
{
	private String pathPattern;
	
    @Getter
    private String url;
    
	@Init
	public void init(@BindingParam("pathPattern") @Default("subpage/%s.zul") String pathPattern)
	{
		this.pathPattern = pathPattern;
		getTransitionManager().addPathChangeHandler(this);
	}
	
	@Override
	public void handleEvent(PathChangedEvent event)
	{
		Deque<PathPart> pathParts = event.getPathParts();
		this.url = constructUrl(pathParts);
		log.debug("new content " + url);
		BindUtils.postNotifyChange(null, null, this, "url");
	}

    private String constructUrl(Deque<PathPart> pathParts)
    {
        PathPart last = pathParts.getLast();
        return String.format(pathPattern, last.getId());
    }

}