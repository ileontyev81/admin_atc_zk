package atc.gui.admin.infrastructure.service.extscript;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import atc.gui.admin.domain.service.ExternalScriptExecutor;
import atc.gui.admin.domain.service.ExternalScriptService;

@Slf4j
@Service("externalScriptService")
public class ExternalScriptServiceImpl implements ExternalScriptService 
{
	private enum EXTERNAL_SCRIPT_ID
	{
		CLEAR_CACHE;
	}
	
	private ExternalScriptExecutor executor;
	private java.util.Properties scriptsConfig;
	
	@Autowired
	public ExternalScriptServiceImpl(ExternalScriptExecutor scriptsExecutor, java.util.Properties scriptsConfig)
	{
		this.executor = scriptsExecutor;
		this.scriptsConfig = scriptsConfig;
	}

	@Override
	public void clearAsteriskCache(String sipName)
	{
		try
		{
			log.info("run " + getScriptPath(EXTERNAL_SCRIPT_ID.CLEAR_CACHE) + " " + sipName);
			executor.runScript(getScriptPath(EXTERNAL_SCRIPT_ID.CLEAR_CACHE), sipName);
		}
		catch (Exception e)
		{
			log.error("", e);
		}
	}

	private String getScriptPath(EXTERNAL_SCRIPT_ID scriptId)
	{
		return (String) scriptsConfig.get(scriptId.name());
	}
	
}
