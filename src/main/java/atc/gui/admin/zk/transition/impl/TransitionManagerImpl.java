package atc.gui.admin.zk.transition.impl;

import atc.gui.admin.zk.event.EventHandler;
import atc.gui.admin.zk.event.impl.PathChangedEvent;
import atc.gui.admin.zk.transition.Path;
import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.TransitionManager;
import atc.gui.admin.zk.service.BrowserHistoryChangedHandler;
import com.google.common.base.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.GlobalCommandEvent;
import org.zkoss.bind.sys.BinderCtrl;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Service("transitionManager")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TransitionManagerImpl implements TransitionManager, EventListener<Event>
{
    private static final String START_PATH_POINT = "{parts:[{id:greeting}]}";// {parts:[{id:cdr}]}
	private static final String OPEN = "open";
	private static final String PATH_PARAM_NAME = "path";

    private Set<EventHandler<PathChangedEvent>> handlers = new CopyOnWriteArraySet<EventHandler<PathChangedEvent>>();
	private Path path = new Path();

    @Autowired
    private void setHistoryChangedHandler(BrowserHistoryChangedHandler historyChangedHandler) {
        historyChangedHandler.setTransitionManager(this);
        addPathChangeHandler(historyChangedHandler);
    }

	@Override
	public void back()
	{
		path.removeLast();
		refresh();
	}

	@Override
	public void select(PathPart pathPart)
	{
		path.select(pathPart);
		refresh();
	}

	@Override
	public void forward(PathPart newPathPart)
	{
        log.debug("forward to: " + newPathPart.asString());
		path.addLast(newPathPart);
		refresh();
	}
	
	@Override
	public void addPathChangeHandler(final EventHandler<PathChangedEvent> handler)
	{
		initGeneralHandler();
		Optional<EventHandler<PathChangedEvent>> existedHandler = findHandlerByClass(handler.getClass());
		if (existedHandler.isPresent())
		{
            log.debug("remove already registered handler: " + existedHandler.get().getClass());
			handlers.remove(existedHandler.get());
		}
        log.debug("add path change handler: " + handler.getClass());
		handlers.add(handler);
	}

    /**
     * Subscribe TransitionManagerImpl as listener of global events on default(application) queue
     */
	private void initGeneralHandler()
	{
		if (Executions.getCurrent() != null)
		{
			final EventQueue<Event> appEventQueue = EventQueues.lookup(BinderCtrl.DEFAULT_QUEUE_NAME, BinderCtrl.DEFAULT_QUEUE_SCOPE, false);
			if (!appEventQueue.isSubscribed(this))
			{
				appEventQueue.subscribe(this);
			}
		}
	}
	
	private Optional<EventHandler<PathChangedEvent>> findHandlerByClass(Class<?> handlerType)
	{
		Iterator<EventHandler<PathChangedEvent>> handlersIterator = handlers.iterator();
		while (handlersIterator.hasNext())
		{
			EventHandler<PathChangedEvent> handler = handlersIterator.next();
			if (handler.getClass() == handlerType)
			{
				return Optional.of(handler);
			}
		}
		return Optional.absent();
	}
	
	private void firePathChangedEvent(final PathChangedEvent event)
	{
		Iterator<EventHandler<PathChangedEvent>> handlersIterator = handlers.iterator();
		while (handlersIterator.hasNext())
		{
			EventHandler<PathChangedEvent> handler = handlersIterator.next();
			handler.handleEvent(event);
		}
	}

	@Override
	public void refresh()
	{
		if (path.isEmpty())
		{
			path = new Path(START_PATH_POINT);
		}
		log.debug("choose new : " + path.asString());
		BindUtils.postGlobalCommand(BinderCtrl.DEFAULT_QUEUE_NAME,  BinderCtrl.DEFAULT_QUEUE_SCOPE, OPEN, pathWrappedIntoMap(path.asString()));
	}

//    @AfterCompose
//    public void chooseGretingPath() {
//        choose(new PathPart("greeting"));
//    }

	@Override
	public void newPlace(String pathString)
	{
		if (Path.isPathContent(pathString))
		{
			this.path = new Path(pathString);
			refresh();
		}
	}

	@Override
	public String getParamValueInTop(String paramName)
	{
		return path.getValueInLast(paramName);
	}

	private Map<String, Object> pathWrappedIntoMap(String pathStr)
	{
	    Map<String, Object> map = new HashMap<String, Object>();
		map.put(PATH_PARAM_NAME, pathStr);
	    return map;
	}

	@Override
	public PathPart getParentPathPart()
	{
		return getParentFor(path.getParts().getLast());
	}
	
	@Override
	public PathPart getGrandParentPathPart()
	{
		return getParentFor(getParentPathPart());
	}

	@Override
	public PathPart getParentFor(final PathPart pathpart)
	{
		Iterator<PathPart> descendingIterator = path.getParts().descendingIterator();
		PathPart nextPathpart = null;
		while (descendingIterator.hasNext() || nextPathpart == null)
		{
			nextPathpart = descendingIterator.next();
			if (nextPathpart.equals(pathpart) && descendingIterator.hasNext())
			{
				return descendingIterator.next();
			}
		}
		throw new IllegalArgumentException("can't find parent for pathpart: " + pathpart.asString());
	}
	
	@Override
	public void removePathChangeHandler(EventHandler<PathChangedEvent> handler)
	{
		handlers.remove(handler);
	}

    /**
     * TransitionManager subscribed(above) to listen events on default(application) queue
     */
	@Override
	public void onEvent(Event event) throws Exception 
	{
		if (event instanceof GlobalCommandEvent) 
		{
			String commandName = ((GlobalCommandEvent) event).getCommand();
			if (commandName.equals(OPEN))
			{
				String pathStr = (String) (((GlobalCommandEvent) event).getArgs().get(PATH_PARAM_NAME));
				TransitionManagerImpl.this.path = new Path(pathStr);
				firePathChangedEvent(new PathChangedEvent(path));
			}
		}
	}

	@Override
	public boolean isCurrentPath(String pathString) 
	{
		return path.asString().equals(pathString);
	}

}
