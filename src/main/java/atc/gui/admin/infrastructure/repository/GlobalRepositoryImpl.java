package atc.gui.admin.infrastructure.repository;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import atc.gui.admin.security.SpringSecurityHelper;
import atc.gui.admin.domain.model.VariableEntry;
import atc.gui.admin.domain.service.repository.GlobalRepository;
import atc.gui.admin.infrastructure.dao.DbRequest;

@Repository("globalRepository")
//@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class GlobalRepositoryImpl implements GlobalRepository
{
	private JdbcTemplate template;
	
	@Autowired
	public GlobalRepositoryImpl(JdbcTemplate template)
	{
		this.template = template;
	}
	@Override
	public List<VariableEntry> getVariables()
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_global_variable_get_list", VariableEntry.class);
		return req.execute();
	}

	@Override
	public VariableEntry getVariable(int id)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_global_variable_get", VariableEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id));
		return req.singleResult();
	}

	@Override
	public void saveVariable(VariableEntry entry)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_global_variable_save", VariableEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, entry),
				new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public void removeVariable(int id)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_global_variable_delete", VariableEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id));
	}

}
