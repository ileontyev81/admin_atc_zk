package atc.gui.admin.zk.event.impl;

import atc.gui.admin.zk.transition.Path;
import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.event.Event;

import java.util.Deque;

public class PathChangedEvent implements Event
{
	private final Path path;

	public PathChangedEvent(Path path)
	{
		this.path = path;
	}
	
	public Path getPath()
	{
		return path;
	}
	
	public Deque<PathPart> getPathParts()
	{
		return path.getParts();
	}

	public PathPart getFirstPathPart()
	{
		return getPathParts().getFirst();
	}
	public PathPart getLastPathPart()
	{
		return getPathParts().getLast();
	}
	
	public boolean pathIsEmpty()
	{
		return getPathParts().isEmpty();
	}

}
