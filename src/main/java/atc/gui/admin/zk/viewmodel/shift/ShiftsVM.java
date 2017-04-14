package atc.gui.admin.zk.viewmodel.shift;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.ShiftEntry;
import atc.gui.admin.domain.service.repository.ShiftRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntitiesVM;

@Slf4j
@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
@Init(superclass = true)
public class ShiftsVM extends BaseEntitiesVM<ShiftEntry>
{
	private ShiftRepository repository;

	@WireVariable("shiftRepository")
	public void setRepository(ShiftRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected PathPart getEditPathPart(ShiftEntry entity)
	{
		return new EditPathPart("shift", entity.getId());
	}

	@Override
	protected void remove(ShiftEntry entity)
	{
		repository.deleteShift(entity.getId());
	}

	@Override
	protected void initDataList(List<ShiftEntry> dataList)
	{
		dataList.addAll(repository.getShiftList());
		log.info("total list " + dataList.size());
	}

}
