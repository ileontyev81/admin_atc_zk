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

package atc.gui.admin.zk.comparator;

import atc.gui.admin.domain.model.appobjects.SelectEntity;

import java.io.Serializable;
import java.util.Comparator;

public class SelectEntryArrayComparator implements Comparator<Object>, Serializable
{
	private static final long serialVersionUID = -2127053833562854322L;

	private boolean asc = true;

	public SelectEntryArrayComparator(boolean asc)
	{
		this.asc = asc;
	}

	@Override
	public int compare(Object o1, Object o2)
	{
		SelectEntity[] array1 = (SelectEntity[]) o1;
		SelectEntity[] array2 = (SelectEntity[]) o2;
		if (array1 == array2 || (array1 == null && array2 == null))
		{
			return basedOnOrder(0);
		}
		if (array1 != null && array2 == null)
		{
			return basedOnOrder(1);
		}
		if (array2 != null && array1 == null)
		{
			return basedOnOrder(-1);
		}

		SelectEntity selected1 = getSelected(array1);
		SelectEntity selected2 = getSelected(array2);
		if (selected1 == null)
		{
			return basedOnOrder(-1);
		}
		if (selected2 == null)
		{
			return basedOnOrder(1);
		}
		return basedOnOrder(selected1.getLabel().compareTo(selected2.getLabel()));
	}

	private int basedOnOrder(int result)
	{
		return asc ? result : -result;
	}
	
	private SelectEntity getSelected(SelectEntity[] array)
	{
		for (SelectEntity selectEntry : array)
		{
			if (selectEntry.getSelected())
			{
				return selectEntry;
			}
		}
		return null;
	}
	
}