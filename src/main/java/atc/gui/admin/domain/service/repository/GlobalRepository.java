package atc.gui.admin.domain.service.repository;

import java.util.List;

import atc.gui.admin.domain.model.VariableEntry;

public interface GlobalRepository
{
	public List<VariableEntry> getVariables();
	public VariableEntry getVariable(int id);
	public void saveVariable(VariableEntry entry);
	public void removeVariable(int id);
}
