package atc.gui.admin.zk.transition;

import atc.gui.admin.zk.event.EventHandler;
import atc.gui.admin.zk.event.impl.PathChangedEvent;

public interface TransitionManager
{

	/**
	 * Find <code>pathPart</code> inside current Path and select it
	 */
	public void select(PathPart pathPart);

	/**
	 * Add new path part into the current path
	 */
	public void forward(PathPart newPathPart);

	public void refresh();

	public void back();

	/**
	 * Replace current path
	 */
	public void newPlace(String pathStr);
	
	public String getParamValueInTop(String paramName);
	
	public void addPathChangeHandler(EventHandler<PathChangedEvent> handler);
	
	public void removePathChangeHandler(EventHandler<PathChangedEvent> handler);

	public PathPart getParentFor(final PathPart pathpart);
	
	public PathPart getParentPathPart();

	public PathPart getGrandParentPathPart();

	public boolean isCurrentPath(String pathString);

}
