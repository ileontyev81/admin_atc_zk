package atc.gui.admin.domain.service.repository;

import java.util.List;

import atc.gui.admin.domain.model.ScriptEntry;


public interface ScriptRepository
{
	public List<ScriptEntry> getScripts();
	
	public void saveScript(ScriptEntry e);
	
	public ScriptEntry getScript(int scriptId);
	public void removeScript(int scriptId);
}
