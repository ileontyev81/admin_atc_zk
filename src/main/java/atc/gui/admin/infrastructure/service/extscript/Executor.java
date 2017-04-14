package atc.gui.admin.infrastructure.service.extscript;

import atc.gui.admin.domain.service.ExternalScriptExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author i.leontyev
 */
@Slf4j
@Service("scriptsExecutor")
public class Executor implements ExternalScriptExecutor
{
	private String commandPrefix;
	
	public Executor() {}
	
	@Override
	public void setCommandPrefix(String commandPrefix)
	{
		this.commandPrefix = commandPrefix;
	}
	
    @Override
	public void runScript(final String scriptPath, String...arguments) throws InterruptedException, IOException 
    {
    	List<String> shellCommandParts = new ArrayList<String>();
    	if (!StringUtils.isEmpty(commandPrefix))
    	{
    		shellCommandParts.addAll(Arrays.asList(commandPrefix.split("\\s+")));
    	}
    	shellCommandParts.add(scriptPath);
    	for (String argument : arguments)
    	{
    		shellCommandParts.add(argument);
    	}
    	log.debug("call: " + StringUtils.join(shellCommandParts, " "));
    	ProcessBuilder pb = new ProcessBuilder(shellCommandParts);
    	Process process = pb.start();
        try 
        {
            printStream(process.getErrorStream());
            printStream(process.getInputStream());        		
            if (process.waitFor() != 0) 
            {
            	log.error(String.format("return error code=%s during script %s execution", String.valueOf(process.exitValue()), scriptPath));
            }
		} 
        finally 
		{
    		if (process != null)
    		{
    			process.destroy();
    		}
		}
    }

    private void printStream(InputStream is) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("Cp866")));
        try 
        {
        	if (is.available() > 0)
        	{
        		String line = br.readLine();
        		while (line != null) 
        		{
        			log.trace(line);
        			line = br.readLine();
        		}
        	}
        } 
        finally 
        {
            br.close();
        }
    }

}
