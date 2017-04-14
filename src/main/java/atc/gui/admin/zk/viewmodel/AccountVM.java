package atc.gui.admin.zk.viewmodel;

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.AccountEntry;
import atc.gui.admin.domain.model.VariableEntry;
import atc.gui.admin.domain.service.ExternalScriptService;
import atc.gui.admin.domain.service.repository.AccountRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntityVM;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.List;

@Slf4j
@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
public class AccountVM extends BaseEntityVM<AccountEntry>
{
	private AccountRepository repository;
	
	@Getter
	@Setter
	private VariableEntry variableSelected;
	
	@Getter
	private List<VariableEntry> variableList;
	
	@WireVariable("accountRepository")
	public void setRepository(AccountRepository repository)
	{
		this.repository = repository;
	}


	@Init(superclass = true)
	public void init()
	{
		if (getEntity().isPersisted())
		{
			variableList = repository.getVariables(getEntity().getId());
		}
	}
	
	
	@Override
	protected AccountEntry loadEntity(String entityId)
	{
		return repository.getAccount(Integer.valueOf(entityId));
	}

	@Override
	protected void saveEntity(AccountEntry entity)
	{
		clearCache();
		repository.saveAccount(entity);
	}
	
	private ExternalScriptService scriptService;
	@WireVariable("externalScriptService")
	public void setScriptService(ExternalScriptService scriptService)
	{
		this.scriptService = scriptService;
	}
	public void clearCache()
	{
		if (getEntity().isPersisted())
		{
			log.info("clear cache for account " + getEntity().getName());
			scriptService.clearAsteriskCache(getEntity().getName());
		}
	}
	public Class<VariableEntry> getVariableClass()
	{
		return VariableEntry.class;
	}
	
	@Command
	public void goAddVariable()
	{
		navigateTo(new PathPart("account_variable"));
	}

	@Command
	public void editVariable()
	{
		if (variableSelected == null)
			return;
		navigateTo(new EditPathPart("account_variable", variableSelected.getId()));
	}

	@NotifyChange("variableList")
	@Command
	public void deleteVariable()
	{
		if (variableSelected == null)
			return;
		repository.removeVariable(variableSelected.getId());
		variableList = repository.getVariables(getEntity().getId());
	}


}
