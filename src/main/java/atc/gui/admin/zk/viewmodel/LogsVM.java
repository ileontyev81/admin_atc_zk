/*
 * Copyright (C) 2017 i.leontyev81@gmail.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

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
