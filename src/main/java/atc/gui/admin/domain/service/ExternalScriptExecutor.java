package atc.gui.admin.domain.service;

import java.io.IOException;

public interface ExternalScriptExecutor
{

	public abstract void runScript(final String scriptPath, String...arguments) throws InterruptedException, IOException;

	public abstract void setCommandPrefix(String commandPrefix);

}
