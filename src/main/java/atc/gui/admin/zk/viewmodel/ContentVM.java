package atc.gui.admin.zk.viewmodel;

import atc.gui.admin.zk.event.EventHandler;
import atc.gui.admin.zk.event.impl.PathChangedEvent;
import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.viewmodel.base.BaseVM;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Default;
import org.zkoss.bind.annotation.Init;

import java.util.Deque;
@Slf4j
public class ContentVM extends BaseVM implements EventHandler<PathChangedEvent>
{
	private String pathPattern;
	
    @Getter
    private String url;
    
	@Init
	public void init(@BindingParam("pathPattern") @Default("subpage/%s.zul") String pathPattern)
	{
		this.pathPattern = pathPattern;
		getTransitionManager().addPathChangeHandler(this);
	}
	
	@Override
	public void handleEvent(PathChangedEvent event)
	{
		Deque<PathPart> pathParts = event.getPathParts();
		this.url = constructUrl(pathParts);
		log.debug("new content " + url);
		BindUtils.postNotifyChange(null, null, this, "url");
	}

    private String constructUrl(Deque<PathPart> pathParts)
    {
        PathPart last = pathParts.getLast();
        return String.format(pathPattern, last.getId());
    }

}