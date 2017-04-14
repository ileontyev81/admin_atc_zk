package atc.gui.admin.domain.service.repository;

import java.util.List;

import atc.gui.admin.domain.model.AccountEntry;
import atc.gui.admin.domain.model.VariableEntry;

public interface AccountRepository
{
	public List<AccountEntry> searchAccounts(String key);

	public List<AccountEntry> getAccounts(Boolean export);

	public void saveAccount(AccountEntry account);

	public abstract void removeAccount(int accountId);

	public AccountEntry getAccount(int accountId);

	// VARIABLE
	public List<VariableEntry> getVariables(int extensionId);

	public VariableEntry getVariable(int id, int extensionId);

	public void saveVariable(VariableEntry entry);

	public void removeVariable(int id);

}
