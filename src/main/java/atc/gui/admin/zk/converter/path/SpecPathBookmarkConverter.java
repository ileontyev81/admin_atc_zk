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

package atc.gui.admin.zk.converter.path;

import atc.gui.admin.zk.transition.Path;
import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.service.BrowserHistoryChangedHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

@Component("specPathBookmarkConverter")
@Slf4j
@Scope("singleton")
public class SpecPathBookmarkConverter implements BrowserHistoryChangedHandler.PathBookmarkConverter
{
	private static final String PARTS_DIVIDER = "*";
	private static final String PARAM_DIVIDER = "-";

	@Override
	public String toBookmark(String pathValue) throws Exception
	{
        log.debug("toBookmark pathValue: " + pathValue);
		Path path = new Path(pathValue);
		StringBuffer result = new StringBuffer();
		Iterator<PathPart> iterator = path.getParts().iterator();
		PathPart nextPathpart = null;
		while (iterator.hasNext() || nextPathpart == null)
		{
			if (nextPathpart != null)
			{
				result.append(PARTS_DIVIDER);
			}
			nextPathpart = iterator.next();
			result.append(nextPathpart.getId());
			
			Iterator<String> namesIterator = nextPathpart.getNamesIterator();
			while (namesIterator.hasNext())
			{
				String name = namesIterator.next();
				result.append(PARTS_DIVIDER).append(name).append(PARAM_DIVIDER).append(nextPathpart.getValue(name));
			}
		}
		return java.net.URLEncoder.encode(result.toString(), "UTF8");
	}

	@Override
	public String toPath(String bookmark) throws Exception
	{
        log.debug("toPath bookmark: " + bookmark);
		StringTokenizer urlParts = new StringTokenizer(bookmark, PARTS_DIVIDER);
		Path path = new Path();
		Map<String, String> params = null;
		String currentId = "";
		while (urlParts.hasMoreTokens())
		{
			String nextPart = urlParts.nextToken();
			String[] name = nextPart.split(PARAM_DIVIDER);
			if (name.length == 1)
			{
				if (!currentId.isEmpty())
				{
					path.addLast(new PathPart(currentId, params));
				}
				currentId = name[0];
				params = new HashMap<String, String>();
			}
			else 
			{
				params.put(name[0], name[1]);
			}
		}
		if (!currentId.isEmpty())
		{
			path.addLast(new PathPart(currentId, params));
		}
		return path.asString();
	}

}
