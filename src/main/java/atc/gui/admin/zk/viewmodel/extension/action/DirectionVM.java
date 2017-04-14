package atc.gui.admin.zk.viewmodel.extension.action;

import lombok.Getter;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.DirectionEntry;
import atc.gui.admin.domain.service.repository.ExtensionRepository;
import atc.gui.admin.zk.viewmodel.base.BaseVM;

public class DirectionVM extends BaseVM
{
	@Getter
	private DirectionEntry direction;
	private ExtensionRepository repository;
	
	@Init
	public void init()
	{
		String parentId = getParentAttribute(EditPathPart.OBJECT_ID);
		String id = getAttribute(EditPathPart.OBJECT_ID);
		direction = repository.getActionDirection(id != null ? Integer.parseInt(id) : 0, Integer.parseInt(parentId));
	}
	
	@WireVariable("extensionRepository")
	public void setRepository(ExtensionRepository repository)
	{
		this.repository = repository;
	}
	@Command
	public void save()
	{
		repository.saveActionDirection(direction);
		navigateBack();
	}
}
