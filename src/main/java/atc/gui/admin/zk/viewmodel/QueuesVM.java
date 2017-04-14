package atc.gui.admin.zk.viewmodel;

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.QueueEntry;
import atc.gui.admin.domain.service.repository.QueueRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntitiesVM;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.List;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
@Init(superclass=true)
public class QueuesVM extends BaseEntitiesVM<QueueEntry>
{

	private QueueRepository repository;
	
	@WireVariable("queueRepository")
	public void setRepository(QueueRepository repository)
	{
		this.repository = repository;
	}
	
	@Override
	protected PathPart getEditPathPart(QueueEntry entity)
	{
		return new EditPathPart("queue", entity.getId());
	}

	@Override
	protected void remove(QueueEntry entity)
	{
		repository.removeQueue(entity.getId());
	}

	@Override
	protected void initDataList(List<QueueEntry> dataList)
	{
		dataList.addAll(repository.getQueues());
	}
    
	
	
}
