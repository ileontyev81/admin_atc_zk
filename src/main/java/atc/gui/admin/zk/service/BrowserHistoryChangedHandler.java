package atc.gui.admin.zk.service;

import atc.gui.admin.zk.event.EventHandler;
import atc.gui.admin.zk.event.impl.PathChangedEvent;
import atc.gui.admin.zk.transition.TransitionManager;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

@Service("historyHandler")
@Scope(value = "session")
@Slf4j
public class BrowserHistoryChangedHandler implements EventListener<Event>, EventHandler<PathChangedEvent>
{
    public interface PathBookmarkConverter
	{
		
		public String toBookmark(String path) throws Exception;
		
		public String toPath(String bookmark) throws Exception;
		
	}
	
	private static final String INDEX_PAGE_ID = "index";

    @Setter
	private TransitionManager transitionManager;

    private PathBookmarkConverter converter;

    @Autowired
    public BrowserHistoryChangedHandler(@Qualifier("specPathBookmarkConverter") PathBookmarkConverter converter)
    {
        this.converter = converter;
    }

    /**
     * Catch of manual URL changes Events to inform TransitionManager about it
     * (BrowserHistoryChangedHandler do not registered on any zul page by noClick and others)
     */
	@Override
	public void onEvent(Event event) throws Exception
	{
		String bookmark = Executions.getCurrent().getDesktop().getBookmark();
		String pathString = converter.toPath(bookmark);
		if (!transitionManager.isCurrentPath(pathString))
		{
			transitionManager.newPlace(pathString);
		}
	}

    /**
     * Catch page change event to set(bookmark) id(name) of new page as hash postfix(#zzz)
     */
	@Override
	public void handleEvent(PathChangedEvent event)
	{
        initBookmarkChangeListener();
		try
		{
            String newBookmark = converter.toBookmark(event.getPath().asString());
            Executions.getCurrent().getDesktop().setBookmark(newBookmark);
		}
		catch (Exception e) 
		{
			log.error("", e);
		}
	}

    /**
     * Register BrowserHistoryChangedHandler as listener of browser row URL changes
     */
	private void initBookmarkChangeListener()
	{
		Page indexPage = Executions.getCurrent().getDesktop().getPage(INDEX_PAGE_ID);
        if (!indexPage.getEventListeners(Events.ON_BOOKMARK_CHANGE).iterator().hasNext())
        {
            indexPage.addEventListener(Events.ON_BOOKMARK_CHANGE, this);
        }
	}

}