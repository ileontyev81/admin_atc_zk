package atc.gui.admin.infrastructure.repository;

import atc.gui.admin.domain.service.repository.LogRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository("logRepository")
public class LogRepositoryImpl implements LogRepository
{
	private static final String ROUTER_LOG_FILE = "/var/log/voip/router.log";
	private static final Integer LINES = 500;
	
	private JdbcTemplate template;

	@Autowired
	public LogRepositoryImpl(JdbcTemplate template)
	{
		this.template = template;
	}

	@Override
	public List<String> getRouterLog()
	{
		List<String> list = new ArrayList<String>();
		ReversedLinesFileReader r = null;
		try
		{
			File f = new File(ROUTER_LOG_FILE);
			r = new ReversedLinesFileReader(f, Charset.forName("UTF8"));

			
			for (int lines = 0; lines < LINES; lines++)
			{
				String line = r.readLine();
				if (line == null)
					break;
				list.add(line);
			}
		} 
		catch (Exception e)
		{
			log.error(e.getMessage());
			list.add(e.getMessage());
		}
		finally
		{
			try
			{
				if (r != null)
				{
					r.close();
				}
			}
			catch (IOException e)
			{
				log.error(e.getMessage());
			}
		}
		return list;
	}

}
