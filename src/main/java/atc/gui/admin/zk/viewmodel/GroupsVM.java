package atc.gui.admin.zk.viewmodel;

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.GroupEntry;
import atc.gui.admin.domain.service.repository.OperatorRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntitiesVM;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.List;

@Init(superclass=true)
@Slf4j
public class GroupsVM extends BaseEntitiesVM<GroupEntry>
{
	private OperatorRepository repository;

	@WireVariable("operatorRepository")
	public void setRepository(OperatorRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected PathPart getEditPathPart(GroupEntry entity)
	{
		log.debug("entity: " + entity.toString());
		return new EditPathPart("group", entity.getId());
	}

	@Override
	protected void remove(GroupEntry entity)
	{
	}

	@Override
	protected void initDataList(List<GroupEntry> dataList)
	{
		dataList.addAll(repository.getGroupList());
	}

}
