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
