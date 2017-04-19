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

import atc.gui.admin.zk.service.BrowserHistoryChangedHandler;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component("simplePathBookmarkConverter")
public class SimplePathBookmarkConverter implements BrowserHistoryChangedHandler.PathBookmarkConverter
{

	@Override
	public String toBookmark(String path) throws UnsupportedEncodingException
	{
		return java.net.URLEncoder.encode(path, "UTF8");
	}

	@Override
	public String toPath(String bookmark) throws UnsupportedEncodingException
	{
		return java.net.URLDecoder.decode(bookmark, "UTF-8");
	}

}
