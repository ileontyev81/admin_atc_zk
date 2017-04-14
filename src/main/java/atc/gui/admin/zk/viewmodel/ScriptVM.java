package atc.gui.admin.zk.viewmodel;

import lombok.Getter;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.ScriptEntry;
import atc.gui.admin.domain.service.repository.ScriptRepository;
import atc.gui.admin.zk.viewmodel.base.BaseVM;

public class ScriptVM extends BaseVM
{
	private ScriptRepository repository;
	
	@Getter
	private ScriptEntry script;
	
	@Init
	public void init()
	{
		String id = getAttribute(EditPathPart.OBJECT_ID);
		
		script = repository.getScript(id != null ? Integer.parseInt(id) : 0);
	}
	
	@WireVariable("scriptRepository")
	public void setRepository(ScriptRepository repository)
	{
		this.repository = repository;
	}
	@Command
	public void saveScript()
	{
		repository.saveScript(script);
		navigateBack();
	}
}
