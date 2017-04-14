package atc.gui.admin.viewmodels.newpath;

import org.junit.Before;

import atc.gui.admin.zk.viewmodel.ContentVM;

public class ContentVMTest 
{
	private ContentVM vm = null;

	@Before
	public void preTest()
	{
		vm = new ContentVM();
	}
	
//	@Test
//	public void getPathUrlTest()
//	{
//		Path path = new Path();
//		path.parse("extensions");
//		Assert.assertEquals("content/extensions.zul", vm.getPathUrl(path));
//		
//		path.parse("extensions/extension/actions/action");
//		Assert.assertEquals("content/extensions.zul", vm.getPathUrl(path));// cut to top page
//	}
//	
//	@Test
//	public void getPathUrlWithInitPageTest1()
//	{
//		Path vmInitPath = new Path();
//		vmInitPath.parse("extensions");
//		vm.setInitPath(vmInitPath);
//		Path path = new Path();
//		path.parse("extensions/extension/actions/action");
//		Assert.assertEquals("content/extensions/extension.zul", vm.getPathUrl(path));// cut to top page
//	}
//	
//	@Test
//	public void getPathUrlWithInitPageTest2()
//	{
//		Path vmInitPath = new Path();
//		vmInitPath.parse("extensions/extension/actions/action");
//		vm.setInitPath(vmInitPath);
//		Path path = new Path();
//		path = new Path(vmInitPath);
//		Assert.assertEquals("content/extensions/extension/actions/action.zul", vm.getPathUrl(path));
//	}
//	
//	@Test
//	public void getPathUrlWithInitPageTest3()
//	{
//		Path vmInitPath = new Path();
//		vmInitPath.parse("extensions/extension/actions/action");
//		vm.setInitPath(vmInitPath);
//		Path path = new Path();
//		path.parse("extensions/extension");
//		Assert.assertEquals("content/extensions/extension.zul", vm.getPathUrl(path));
//	}
//	
//	@Test
//	public void getPathUrlWithInitPageTest4()
//	{
//		Path vmInitPath = new Path();
//		vmInitPath.parse("");
//		vm.setInitPath(vmInitPath);
//		Path path = new Path();
//		path.parse("extensions/extension");
//		Assert.assertEquals("content/extensions/extension.zul", vm.getPathUrl(path));
//	}
	
}

