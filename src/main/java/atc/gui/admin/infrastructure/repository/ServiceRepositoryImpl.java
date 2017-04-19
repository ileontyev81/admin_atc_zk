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

package atc.gui.admin.infrastructure.repository;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import atc.gui.admin.security.SpringSecurityHelper;
import atc.gui.admin.domain.model.ServiceEntry;
import atc.gui.admin.domain.service.repository.ServiceRepository;
import atc.gui.admin.infrastructure.dao.DbRequest;

@Repository("serviceRepository")
public class ServiceRepositoryImpl implements ServiceRepository
{
	private JdbcTemplate template;

	@Autowired
	public ServiceRepositoryImpl(JdbcTemplate template)
	{
		this.template = template;
	}

	@Override
	public void saveService(ServiceEntry e)
	{
		DbRequest<ServiceEntry> req = new DbRequest<ServiceEntry>(template, "wadm_service_save", ServiceEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, e), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public ServiceEntry getService(int id)
	{
		DbRequest<ServiceEntry> req = new DbRequest<ServiceEntry>(template, "wadm_service_get", ServiceEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id));
		return req.singleResult();
	}

	@Override
	public List<ServiceEntry> getServiceList()
	{
		DbRequest<ServiceEntry> req = new DbRequest<ServiceEntry>(template, "wadm_service_get_list", ServiceEntry.class);
		return req.execute();
	}

	@Override
	public void deleteService(int id)
	{
		DbRequest<ServiceEntry> req = new DbRequest<ServiceEntry>(template, "wadm_service_delete", ServiceEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id));
	}

}
