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
import atc.gui.admin.domain.model.ScriptEntry;
import atc.gui.admin.domain.service.repository.ScriptRepository;
import atc.gui.admin.infrastructure.dao.DbRequest;

@Repository("scriptRepository")
public class ScriptRepositoryImpl implements ScriptRepository
{
	private JdbcTemplate template;
	@Autowired
	public ScriptRepositoryImpl(JdbcTemplate template)
	{
		this.template = template;
	}
	@Override
	public List<ScriptEntry> getScripts()
	{
		DbRequest<ScriptEntry> req = new DbRequest<ScriptEntry>(template, "wadm_script_get_list", ScriptEntry.class);
		return req.execute(new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public void saveScript(ScriptEntry e)
	{
		DbRequest<ScriptEntry> req = new DbRequest<ScriptEntry>(template, "wadm_script_save", ScriptEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, e),
							  new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public ScriptEntry getScript(int scriptId)
	{
		DbRequest<ScriptEntry> req = new DbRequest<ScriptEntry>(template, "wadm_script_get", ScriptEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, scriptId), new SqlParameterValue(Types.BOOLEAN, false));
		return req.singleResult();
	}

	@Override
	public void removeScript(int scriptId)
	{
		DbRequest<ScriptEntry> req = new DbRequest<ScriptEntry>(template, "wadm_script_delete", ScriptEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, scriptId));
	}

}
