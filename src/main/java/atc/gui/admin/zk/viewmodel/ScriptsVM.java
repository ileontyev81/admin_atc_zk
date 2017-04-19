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
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.ScriptEntry;
import atc.gui.admin.domain.service.repository.ScriptRepository;
import atc.gui.admin.zk.viewmodel.base.BaseVM;

@Slf4j
public class ScriptsVM extends BaseVM
{
	//@Getter
	//public ScriptEntry entity = new ScriptEntry();

	private ScriptRepository repository;
	@Getter
	@Setter
	private ScriptEntry scriptSelected;
	@Getter
	private List<ScriptEntry> scriptList;
	
	@Init
	public void init()
	{
		scriptList = repository.getScripts();
	}
	
	@WireVariable("scriptRepository")
	public void setRepository(ScriptRepository repository)
	{
		this.repository = repository;
	}
	
	public Class<ScriptEntry> getScriptClass()
	{
		return ScriptEntry.class;
	}
	@Command
	public void editScript()
	{
		if (scriptSelected != null)
		{
			navigateTo(new EditPathPart("script", scriptSelected.getId()));
		}
	}

	@Command
	public void create()
	{
		navigateTo(new EditPathPart("script", null));
	}
	@Command
	public void deleteScript()
	{

	}
	
	/*@Command("saveScript")
	public void saveScript()
	{
		log.debug("script sent to server:" + entity.getCode());
	}*/
}
