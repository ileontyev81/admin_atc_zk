//package atc.gui.admin.viewmodels.newpath;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import atc.gui.admin.application.transition.PathPart;
//
//public class PathPartTest 
//{
//	private PathPart pathPart = null;
//
//	@Before
//	public void preTest()
//	{
//		pathPart = new PathPart();
//	}
//	
//	@Test
//	public void testParse()
//	{
//		pathPart.parse("{id:somePath}");
//		Assert.assertEquals(pathPart.getParamsCount(), 0);
//		Assert.assertEquals(pathPart.getId(), "somePath");
//		pathPart.parse("{id:somePath,params:{aaa:1,bbb:2}}");
//		Assert.assertEquals(pathPart.getId(), "somePath");
//		Assert.assertNotEquals(pathPart.getParamsCount(), 0);
//		Assert.assertEquals(pathPart.getParamsCount(), 2);
//		
//		Assert.assertTrue(pathPart.hasParam("aaa"));
//		Assert.assertTrue(pathPart.hasParam("bbb"));
//		Assert.assertEquals(pathPart.getValue("aaa"), String.valueOf(1));
//		Assert.assertEquals(pathPart.getValue("bbb"), String.valueOf(2));
//	}
//	
//	@Test
//	public void testAsString()
//	{
//		pathPart.setId("somePath");
//		Assert.assertEquals(pathPart.asString(), "{id:somePath,params:{}}");
//
//		pathPart.setId("somePath");
//		pathPart.setParam("aaa", "1");		
//		pathPart.setParam("bbb", "2");		
//		Assert.assertEquals(pathPart.asString(), "{id:somePath,params:{aaa:1,bbb:2}}");
//	}
//
//	@Test
//	public void isParentFor()
//	{
////		pathPart.parse("extensions");
////		Assert.assertTrue(pathPart.isParentOf(new PathPart("extension")));
////		
////		pathPart.parse("extension");
////		Assert.assertFalse(pathPart.isParentOf(new PathPart("extensions")));
////		
////		pathPart.parse("extension");
////		Assert.assertFalse(pathPart.isParentOf(new PathPart("action")));
////		
////		pathPart.parse("extension");
////		Assert.assertTrue(pathPart.isParentOf(new PathPart("actions")));
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
