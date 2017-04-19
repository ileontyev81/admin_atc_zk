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

package atc.gui.admin.zk.transition;

import atc.gui.admin.zk.event.EventHandler;
import atc.gui.admin.zk.event.impl.PathChangedEvent;

public interface TransitionManager
{

	/**
	 * Find <code>pathPart</code> inside current Path and select it
	 */
	public void select(PathPart pathPart);

	/**
	 * Add new path part into the current path
	 */
	public void forward(PathPart newPathPart);

	public void refresh();

	public void back();

	/**
	 * Replace current path
	 */
	public void newPlace(String pathStr);
	
	public String getParamValueInTop(String paramName);
	
	public void addPathChangeHandler(EventHandler<PathChangedEvent> handler);
	
	public void removePathChangeHandler(EventHandler<PathChangedEvent> handler);

	public PathPart getParentFor(final PathPart pathpart);
	
	public PathPart getParentPathPart();

	public PathPart getGrandParentPathPart();

	public boolean isCurrentPath(String pathString);

}
