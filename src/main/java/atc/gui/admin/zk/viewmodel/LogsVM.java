package atc.gui.admin.zk.viewmodel;

import java.util.List;

import lombok.Getter;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.domain.service.repository.LogRepository;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
public class LogsVM
{
	@Getter
	private List<String> routerLog;
	
	private LogRepository repository;
	
	@WireVariable("logRepository")
	public void setRepository(LogRepository repository)
	{
		this.repository = repository;
	}
	
	@Init
	public void init()
	{
		routerLog = repository.getRouterLog();
	}
	
	@NotifyChange("routeLog")
	@Command
	public void refreshRouterLog()
	{
		routerLog.clear();
		routerLog.addAll(repository.getRouterLog());
	}
}
