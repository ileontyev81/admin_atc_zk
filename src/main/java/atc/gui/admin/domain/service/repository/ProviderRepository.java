package atc.gui.admin.domain.service.repository;

import java.util.List;

import atc.gui.admin.domain.model.ProviderEntry;
import atc.gui.admin.domain.model.VariableEntry;

public interface ProviderRepository
{
	public List<ProviderEntry> getProviders();
	public void saveProvider(ProviderEntry provider);
	public void removeProvider(int providerId);
	public ProviderEntry getProvider(int providerId);

	
	public List<VariableEntry> getVariables(int providerId);
	public void saveVariable(VariableEntry var);
	public void removeVariable(int varId);
	public VariableEntry getVariable(int varId, int parentId);
}
