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

package atc.gui.admin.zk.transition.impl;

import lombok.EqualsAndHashCode;
import atc.gui.admin.zk.transition.PathPart;

@EqualsAndHashCode(callSuper=true)
public class EditPathPart extends PathPart
{
	public static final String OBJECT_ID = "objectId";

	public EditPathPart(String id, String objectId)
	{
		super(id, OBJECT_ID, objectId);
	}
	
	public EditPathPart(String id, int objectId)
	{
		this(id, String.valueOf(objectId));
	}
	
	public EditPathPart(PathPart pathPart) 
	{
		super(pathPart);
	}

	public String getObjectId()
	{
		return getValue(OBJECT_ID);
	}
	
	public int getIntObjectId()
	{
		return Integer.valueOf(getObjectId());
	}
	
}
