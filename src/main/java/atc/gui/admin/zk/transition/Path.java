package atc.gui.admin.zk.transition;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

@Slf4j
public class Path
{
	@Getter
	private Deque<PathPart> parts = new ArrayDeque<PathPart>();
	
	public Path() {}
	
	public Path(Path path)
	{
		this(path, path.partsSize());
	}
	
	public Path(Path path, int lastIndex) 
	{
		parts.clear(); 
		CollectionUtils.addAll(parts, path.partsIterator());
		while (parts.size() > lastIndex)
		{
			parts.removeLast();
		}
	}
	
	public Path(String fullPath)
	{
		parse(fullPath);
	}
	
	public void parse(String fullPath)
	{
		if (!fullPath.trim().isEmpty())
		{
			log.info("parse path" + fullPath);
			Path obj = (new Gson()).fromJson(fullPath, Path.class);
			this.parts = obj.parts;
		}
	}
	
	public int partsSize()
	{
		return parts.size();
	}
	
	public boolean isEmpty()
	{
		return (parts.size() == 0);
	}
	
	public Iterator<PathPart> partsIterator()
	{
		return parts.iterator();
	}
	
	public void addFirst(PathPart pathPart)
	{
		parts.addFirst(pathPart);
	}
	
	public PathPart getFirst()
	{
		return parts.getFirst();
	}	
	
	public void addLast(PathPart part)
	{
		parts.addLast(part);
	}
	
	public PathPart getLast()
	{
		return parts.getLast();
	}
	
	public boolean contains(PathPart pathPart)
	{
		return parts.contains(pathPart);
	}

	public String asString() 
	{
		return (new Gson()).toJson(this).replaceAll("\"", "");
	}

	public void clear()
	{
		parts.clear();
	}

	public void select(PathPart pathPart)
	{
		Iterator<PathPart> iterator = parts.descendingIterator();
		PathPart nextPathPart = iterator.next();
		while (iterator.hasNext() && !nextPathPart.equals(pathPart))
		{
			iterator.remove();
			nextPathPart = iterator.next();
		}
	}

	public boolean lastHasNotNullParam(String name)
	{
		return getLast().hasNotNullParam(name);
	}

	public String getValueInLast(String name) 
	{
		return getLast().getValue(name);
	}

	public void replaceLast(PathPart pathPart)
	{
		removeLast();
		addLast(pathPart);
	}
	
	public void removeLast()
	{
		parts.removeLast();
	}
	
	public boolean hasParamInLast(String name)
	{
		return parts.getLast().hasParam(name);
	}

	public String getLastId()
	{
		return parts.getLast().getId();
	}

	public static boolean isPathContent(String fullPath)
	{
		try 
		{
			new Path(fullPath);
			return true;
		} 
		catch (Exception e) 
		{
			log.error("fullPath: " + fullPath, e);
		}
		return false;
	}
	
}
