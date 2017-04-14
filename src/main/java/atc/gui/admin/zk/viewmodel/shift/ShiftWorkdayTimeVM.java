package atc.gui.admin.zk.viewmodel.shift;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.ShiftWorkdayTimeEntry;
import atc.gui.admin.domain.service.repository.ShiftRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntityVM;

@Init(superclass = true)
public class ShiftWorkdayTimeVM extends BaseEntityVM<ShiftWorkdayTimeEntry>
{
	private ShiftRepository repository;

	@WireVariable("shiftRepository")
	public void setRepository(ShiftRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected ShiftWorkdayTimeEntry loadEntity(String entityId)
	{
		String parentId = getParentAttribute(EditPathPart.OBJECT_ID);
		return repository.getWorkdayTime(entityId != null ? Integer.parseInt(entityId) : 0, Integer.parseInt(parentId));
	}

	@Override
	protected void saveEntity(ShiftWorkdayTimeEntry entity)
	{
		repository.saveWorkdayTime(getEntity());
	}

	
}
