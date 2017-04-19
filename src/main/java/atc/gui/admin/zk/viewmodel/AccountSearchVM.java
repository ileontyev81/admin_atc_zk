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

import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.model.AccountEntry;
import atc.gui.admin.domain.model.SimpleSearchEntry;
import atc.gui.admin.domain.service.repository.AccountRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntitiesVM;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.List;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
@Init(superclass = true)
public class AccountSearchVM extends BaseEntitiesVM<AccountEntry>
{
	@Getter @Setter
	private SimpleSearchEntry searchEntry = new SimpleSearchEntry();
	
	private AccountRepository repository;

	@WireVariable("accountRepository")
	public void setRepository(AccountRepository repository)
	{
		this.repository = repository;
	}

	@Override
	protected PathPart getEditPathPart(AccountEntry entity)
	{
		return new EditPathPart("account", entity.getId());
	}

	@Override
	protected void remove(AccountEntry entity)
	{
		repository.removeAccount(entity.getId());
	}

	@Override
	protected void initDataList(List<AccountEntry> dataList)
	{
		// TODO Auto-generated method stub
	}
	
	@NotifyChange("dataList")
	@Command("search")
	public void search()
	{
		getDataList().clear();
		getDataList().addAll(repository.searchAccounts(searchEntry.getRegex()));
	}

}
