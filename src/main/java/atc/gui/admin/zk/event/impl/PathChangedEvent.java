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

package atc.gui.admin.zk.event.impl;

import atc.gui.admin.zk.transition.Path;
import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.event.Event;

import java.util.Deque;

public class PathChangedEvent implements Event
{
	private final Path path;

	public PathChangedEvent(Path path)
	{
		this.path = path;
	}
	
	public Path getPath()
	{
		return path;
	}
	
	public Deque<PathPart> getPathParts()
	{
		return path.getParts();
	}

	public PathPart getFirstPathPart()
	{
		return getPathParts().getFirst();
	}
	public PathPart getLastPathPart()
	{
		return getPathParts().getLast();
	}
	
	public boolean pathIsEmpty()
	{
		return getPathParts().isEmpty();
	}

}
