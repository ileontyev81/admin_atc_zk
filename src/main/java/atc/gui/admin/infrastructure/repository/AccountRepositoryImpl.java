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

package atc.gui.admin.infrastructure.repository;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import atc.gui.admin.security.SpringSecurityHelper;
import atc.gui.admin.domain.model.AccountEntry;
import atc.gui.admin.domain.model.VariableEntry;
import atc.gui.admin.domain.service.repository.AccountRepository;
import atc.gui.admin.infrastructure.dao.DbRequest;

@Repository("accountRepository")
public class AccountRepositoryImpl implements AccountRepository
{
	private JdbcTemplate template;

	@Autowired
	public AccountRepositoryImpl(JdbcTemplate template)
	{
		this.template = template;
	}

	@Override
	public List<AccountEntry> getAccounts(Boolean export)
	{
		DbRequest<AccountEntry> req = new DbRequest<AccountEntry>(template, "wadm_account_get_list", AccountEntry.class);
		return req.execute(new SqlParameterValue(Types.BOOLEAN, export), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public void saveAccount(AccountEntry account)
	{
		DbRequest<AccountEntry> req = new DbRequest<AccountEntry>(template, "wadm_account_save", AccountEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, account), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public void removeAccount(int accountId)
	{
		DbRequest<AccountEntry> req = new DbRequest<AccountEntry>(template, "wadm_account_delete", AccountEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, accountId), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public AccountEntry getAccount(int accountId)
	{
		DbRequest<AccountEntry> req = new DbRequest<AccountEntry>(template, "wadm_account_get", AccountEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, accountId), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
		return req.singleResult();
	}

	@Override
	public List<AccountEntry> searchAccounts(String key)
	{
		DbRequest<AccountEntry> req = new DbRequest<AccountEntry>(template, "wadm_account_search", AccountEntry.class);
		return req.execute(new SqlParameterValue(Types.VARCHAR, key), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	// VARIABLES
	@Override
	public VariableEntry getVariable(int id, int accountId)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_account_variable_get", VariableEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id), new SqlParameterValue(Types.INTEGER, accountId));
		return req.singleResult();
	}

	@Override
	public List<VariableEntry> getVariables(int accountId)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_account_variable_get_list", VariableEntry.class);
		return req.execute(new SqlParameterValue(Types.INTEGER, accountId));
	}

	@Override
	public void saveVariable(VariableEntry entry)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_account_variable_save", VariableEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, entry), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public void removeVariable(int id)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_account_variable_delete", VariableEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id));
	}
}
