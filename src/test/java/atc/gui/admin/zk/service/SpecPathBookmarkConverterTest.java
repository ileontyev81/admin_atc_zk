package atc.gui.admin.zk.service;

import atc.gui.admin.zk.converter.path.SpecPathBookmarkConverter;
import org.junit.Assert;
import org.junit.Test;

public class SpecPathBookmarkConverterTest
{

	private SpecPathBookmarkConverter converter = new SpecPathBookmarkConverter();
	
	@Test
	public void toBookmark() throws Exception
	{
		String bookmark = converter.toBookmark("{parts:[{id:extensions}]}");
		Assert.assertEquals(bookmark, "extensions");
		bookmark = converter.toBookmark("{parts:[{id:actions},{id:action,params:{objectId:1}}]}");
		Assert.assertEquals(bookmark, "actions*action*objectId-1");
		bookmark = converter.toBookmark("{parts:[{id:actions},{id:action,params:{objectId:1,type:vasy}}]}");
        Assert.assertTrue(bookmark.startsWith("actions*action*"));
        Assert.assertTrue(bookmark.contains("objectId-1"));
        Assert.assertTrue(bookmark.contains("type-vasy"));
	}
	
	@Test
	public void toPath() throws Exception
	{
		String path = converter.toPath("extensions");
		Assert.assertEquals(path, "{parts:[{id:extensions,params:{}}]}"); 
		path = converter.toPath("actions*action*objectId-1");
		Assert.assertEquals(path, "{parts:[{id:actions,params:{}},{id:action,params:{objectId:1}}]}");
		path = converter.toPath("actions*action*objectId-1*type-vasy");
        Assert.assertTrue(path.startsWith("{parts:[{id:actions,params:{}},{id:action,params:"));
        Assert.assertTrue(path.contains("objectId:1"));
        Assert.assertTrue(path.contains("type:vasy"));
	}
	
}
