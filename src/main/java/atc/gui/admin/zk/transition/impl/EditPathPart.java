package atc.gui.admin.zk.transition.impl;

import lombok.EqualsAndHashCode;
import atc.gui.admin.zk.transition.PathPart;

@EqualsAndHashCode(callSuper=true)
public class EditPathPart extends PathPart
{
	public static final String OBJECT_ID = "objectId";

	public EditPathPart(String id, String objectId)
	{
		super(id, OBJECT_ID, objectId);
	}
	
	public EditPathPart(String id, int objectId)
	{
		this(id, String.valueOf(objectId));
	}
	
	public EditPathPart(PathPart pathPart) 
	{
		super(pathPart);
	}

	public String getObjectId()
	{
		return getValue(OBJECT_ID);
	}
	
	public int getIntObjectId()
	{
		return Integer.valueOf(getObjectId());
	}
	
}
