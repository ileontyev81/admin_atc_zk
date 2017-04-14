package atc.gui.admin.zk.viewmodel.shift;

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.ShiftEntry;
import atc.gui.admin.domain.model.ShiftWorkdayEntry;
import atc.gui.admin.domain.model.ShiftWorkdayExcludeEntry;
import atc.gui.admin.domain.service.repository.ShiftRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntityVM;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.List;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
public class ShiftVM  extends BaseEntityVM<ShiftEntry>
{
	private ShiftRepository repository;
	
	@Getter
	private List<ShiftWorkdayEntry> workdayList;
	
	@Getter
	private List<ShiftWorkdayExcludeEntry> excludeList;
	
	@Getter @Setter
	private ShiftWorkdayEntry workdaySelected;

	@Getter @Setter
	private ShiftWorkdayExcludeEntry  excludeSelected;
	
	@Init(superclass = true)
	public void init()
	{
		if (getEntity().isPersisted())
		{
			workdayList = repository.getWorkdayList(getEntity().getId());
			excludeList = repository.getWorkdayExcludeList(getEntity().getId());
		}
	}
	
	public Class<ShiftWorkdayEntry> getWorkdayClass()
	{
		return ShiftWorkdayEntry.class;
	}
	public Class<ShiftWorkdayExcludeEntry> getExcludeClass()
	{
		return ShiftWorkdayExcludeEntry.class;
	}
	
	@WireVariable("shiftRepository")
	public void setRepository(ShiftRepository repository)
	{
		this.repository = repository;
	}
	@Override
	protected ShiftEntry loadEntity(String entityId)
	{
		return repository.getShift(Integer.parseInt(entityId));
	}

	@Override
	protected void saveEntity(ShiftEntry entity)
	{
		repository.saveShift(entity);
	}
	
	
	@Command
	public void goAddWorkday()
	{
		navigateTo(new PathPart("shift_workday"));
	}
	@Command
	public void goAddExclude()
	{
		navigateTo(new PathPart("workday_exclude"));
	}
	@Command
	public void editWorkday()
	{
		if (workdaySelected == null)
			return;
		navigateTo(new EditPathPart("shift_workday", workdaySelected.getId()));
	}
	@Command
	public void editExclude()
	{
		if (excludeSelected == null)
			return;
		navigateTo(new EditPathPart("workday_exclude", excludeSelected.getId()));
	}
	
	@NotifyChange("excludeList")
	@Command
	public void deleteExclude()
	{
		if (excludeSelected == null)
			return;
		repository.deleteWorkdayExclude(excludeSelected.getId());
		excludeList = repository.getWorkdayExcludeList(getEntity().getId());
	}
}
