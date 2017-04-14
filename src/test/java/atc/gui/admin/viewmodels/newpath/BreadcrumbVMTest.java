package atc.gui.admin.viewmodels.newpath;


import org.junit.Before;

import atc.gui.admin.zk.viewmodel.BreadcrumbVM;

public class BreadcrumbVMTest 
{
	private BreadcrumbVM vm = null;

	@Before
	public void preTest()
	{
		vm = new BreadcrumbVM();
	}

//	@Test
//	public void testAddLastPath()
//	{
//		PathPart pathPart = new PathPart("extensions"); 
//		vm.addLastPath(pathPart);
//		Assert.assertEquals(vm.getPathsVector().size(), 1);
//		Assert.assertTrue(vm.getPathsVector().contains(new PathPart("extensions")));
//		
//		vm.addLastPath(new PathPart("extension"));
//		Assert.assertEquals(vm.getPathsVector().size(), 2);
//		Assert.assertTrue(vm.getPathsVector().contains(new PathPart("extensions")));
//		Assert.assertTrue(vm.getPathsVector().contains(new PathPart("extension")));
//	}
	
//	@Test
//	public void lastPartIsParentFor()
//	{
//		vm.addLastPath(new PathPart("extensions"));
//		Assert.assertTrue(vm.lastPartIsParentFor(new PathPart("extension")));
//		
//		vm.addLastPath(new PathPart("extension"));
//		Assert.assertFalse(vm.lastPartIsParentFor(new PathPart("extensions")));
//		Assert.assertFalse(vm.lastPartIsParentFor(new PathPart("extension")));
//		Assert.assertTrue(vm.lastPartIsParentFor(new PathPart("actions")));
//	}

	//	extensions, extension -> extensions, extension
//	@Test
//	public void testAddNewPathPartParentChild()
//	{
//		vm.addNewPathPart(new PathPart("extensions"));
//		vm.addNewPathPart(new PathPart("extension"));
//		Assert.assertEquals(vm.getPathsVector().size(), 2);
//		Assert.assertTrue(vm.getPathsVector().contains(new PathPart("extension")));		
//		Assert.assertTrue(vm.getPathsVector().contains(new PathPart("extensions")));		
//	}
	
	//	extensions, extensions -> extensions(rewrite)
	//	extensions, extension, extension -> extensions, extension(rewrite)
//	@Test
//	public void testAddNewPathPartSamePath()
//	{
//		vm.addNewPathPart(new PathPart("extensions"));
//		vm.addNewPathPart(new PathPart("extensions"));
//		vm.addNewPathPart(new PathPart("extensions"));
//		Assert.assertEquals(vm.getPathsVector().size(), 1);
//		Assert.assertTrue(vm.getPathsVector().contains(new PathPart("extensions")));
//		
//		vm.addNewPathPart(new PathPart("extension"));
//		vm.addNewPathPart(new PathPart("extension"));
//		vm.addNewPathPart(new PathPart("extension"));
//		Assert.assertEquals(vm.getPathsVector().size(), 2);
//		Assert.assertTrue(vm.getPathsVector().contains(new PathPart("extensions")));
//		Assert.assertTrue(vm.getPathsVector().contains(new PathPart("extension")));
//	}
	
}
