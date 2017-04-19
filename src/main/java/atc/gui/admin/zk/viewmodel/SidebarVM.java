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
import atc.gui.admin.zk.viewmodel.base.BaseVM;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Init;

@Slf4j
public class SidebarVM extends BaseVM implements EventHandler<PathChangedEvent>
{
    @Getter
    @Setter
    private String selected = "";

	@Init
    public void init()
    {
        log.debug("SidebarVM init");
		getTransitionManager().addPathChangeHandler(this);
		getTransitionManager().refresh();
    }

    @Override
	public void handleEvent(PathChangedEvent event)
	{
		if (!event.pathIsEmpty())
		{
			this.selected = event.getLastPathPart().getId();
		}
        log.debug("current selected path id: " + selected);
		BindUtils.postNotifyChange(null, null, this, "selected");// програмная генерация события оповещения об изменении selected-поля для обновления UI текущей VM.
	}
}
