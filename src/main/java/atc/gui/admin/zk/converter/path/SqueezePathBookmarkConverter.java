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
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@Component("gzipBase64PathBookmarkConverter")
public class SqueezePathBookmarkConverter implements BrowserHistoryChangedHandler.PathBookmarkConverter
{
	
	@Override
	public String toBookmark(String path) throws Exception
	{
		return java.net.URLEncoder.encode(compressString(path), "UTF8");
	}

	@Override
	public String toPath(String bookmark) throws Exception
	{
		return java.net.URLDecoder.decode(uncompressString(bookmark), "UTF8");
	}

	public String compressString(String srcTxt) throws IOException
	{
		ByteArrayOutputStream rstBao = new ByteArrayOutputStream();
        try (GZIPOutputStream zos = new GZIPOutputStream(rstBao)) {
		    zos.write(srcTxt.getBytes("UTF8"));
		    return Base64.encodeBase64String(rstBao.toByteArray());
        }
	}

	public static String uncompressString(String zippedBase64Str) throws IOException
	{
		String result = "";
		byte[] bytes = Base64.decodeBase64(zippedBase64Str);
		try (GZIPInputStream zi = new GZIPInputStream(new ByteArrayInputStream(bytes));)
		{
			result = IOUtils.toString(zi, Charset.forName("UTF8"));
		}
		return result;
	}
	
}
