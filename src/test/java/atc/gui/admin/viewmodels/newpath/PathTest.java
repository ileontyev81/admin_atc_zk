//package atc.gui.admin.viewmodels.newpath;
//
//
//import java.util.Iterator;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import atc.gui.admin.application.transition.Path;
//import atc.gui.admin.application.transition.PathPart;
//
//public class PathTest
//{
//	private Path path = null;
//	private final String fullPath = "{parts:[{id:extensions,params:{}},{id:action,params:{aaa:1,bbb:2}},{id:actions,params:{}}]}";
//	
//	@Before
//	public void preTest()
//	{
//		path = new Path();
//	}
//	
//	@Test
//	public void parseTest()
//	{
//		path.parse("{parts:[{id:extensions}]}");
//		Assert.assertTrue(path.contains(new PathPart("{id:extensions}")));
//		Assert.assertEquals(path.partsSize(), 1);
//		
//		path.parse("{parts:[{id:extensions,params:{aaa:1,bbb:2}}]}");
//		Assert.assertEquals(path.partsSize(), 1);
//		Assert.assertEquals(path.getFirst().getParamsCount(), 2);
//		
//		path.parse(fullPath);
//		Assert.assertEquals(path.partsSize(), 3);
//		Iterator<PathPart> iterator = path.partsIterator();
//		PathPart pathPart = iterator.next();
//		Assert.assertEquals(pathPart.getParamsCount(), 0);
//		pathPart = iterator.next();
//		Assert.assertEquals(pathPart.getParamsCount(), 2);
//		Assert.assertTrue(pathPart.hasParam("aaa"));
//		Assert.assertEquals(pathPart.getValue("aaa"), "1");
//		Assert.assertTrue(pathPart.hasParam("bbb"));
//		Assert.assertEquals(pathPart.getValue("bbb"), "2");
//		pathPart = iterator.next();
//		Assert.assertEquals(pathPart.getParamsCount(), 0);
//	}
//
//	@Test
//	public void asStringTest()
//	{
//		PathPart part = new PathPart();
//		part.setId("extensions");
//		path.addLast(part);
//		Assert.assertEquals("{parts:[{id:extensions,params:{}}]}", path.asString(true));
//		
//		path.clear();
//		path.addLast(new PathPart("{id:extensions}"));
//		path.addLast(new PathPart("{id:action,params:{aaa:1,bbb:2}}"));
//		path.addLast(new PathPart("{id:actions}"));
//		Assert.assertEquals(fullPath, path.asString(true));
//	}
//	
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
