package atc.gui.admin.mvc.controller;

import atc.gui.admin.domain.model.CallRecordEntry;
import atc.gui.admin.domain.service.repository.ReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("record")
public class RecordLoader
{
	@Autowired
	private ReportRepository repository;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void getFile(@PathVariable("id") String id, HttpServletResponse response) throws IOException
	{
		Long callId = Long.parseLong(id);

		if (repository == null)
		{
			log.error("repository is null");
		}
		CallRecordEntry callRecord = repository.getCallRecord(callId);
		
		try
		{
			if (callRecord.getRecord() == null)
				throw new RuntimeException
                        ("call record does not exists");
			
			File downloadFile = new File(callRecord.getRecord());
	        FileInputStream inputStream = new FileInputStream(downloadFile);
			
			byte[] b = IOUtils.toByteArray(inputStream);
	
			response.setHeader("Content-Type", "audio/mpeg");
			response.setHeader("Content-Length", String.valueOf(b.length));
	
			response.getOutputStream().write(b);
			response.flushBuffer();
		}
		catch(Exception e)
		{
			log.warn("record not found for call " + id +" with reason: " + e.getMessage());
			response.setStatus(404);
			response.flushBuffer();
		}
	}
}
