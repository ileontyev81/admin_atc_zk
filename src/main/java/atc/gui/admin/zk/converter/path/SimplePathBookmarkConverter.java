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
