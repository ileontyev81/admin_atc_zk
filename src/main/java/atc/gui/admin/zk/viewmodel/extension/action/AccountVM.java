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

package atc.gui.admin.zk.viewmodel.extension.action;

import lombok.Getter;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.AccountLinkedEntry;
import atc.gui.admin.domain.service.repository.ExtensionRepository;
import atc.gui.admin.zk.viewmodel.base.BaseVM;

public class AccountVM extends BaseVM
{
	@Getter
	private AccountLinkedEntry account;
	private ExtensionRepository repository;
	
	@Init
	public void init()
	{
		String parentId = getParentAttribute(EditPathPart.OBJECT_ID);
		String id = getAttribute(EditPathPart.OBJECT_ID);
		account = repository.getActionAccount(id != null ? Integer.parseInt(id) : 0, Integer.parseInt(parentId));
	}
	
	@WireVariable("extensionRepository")
	public void setRepository(ExtensionRepository repository)
	{
		this.repository = repository;
	}
	@Command
	public void save()
	{
		repository.saveActionAccount(account);
		navigateBack();
	}
}
