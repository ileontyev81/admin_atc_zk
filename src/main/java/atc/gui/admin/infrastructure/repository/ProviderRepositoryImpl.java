package atc.gui.admin.infrastructure.repository;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import atc.gui.admin.security.SpringSecurityHelper;
import atc.gui.admin.domain.model.ProviderEntry;
import atc.gui.admin.domain.model.VariableEntry;
import atc.gui.admin.domain.service.repository.ProviderRepository;
import atc.gui.admin.infrastructure.dao.DbRequest;

@Repository("providerRepository")
public class ProviderRepositoryImpl implements ProviderRepository
{
	private JdbcTemplate template;
	
	@Autowired
	public ProviderRepositoryImpl(JdbcTemplate template)
	{
		this.template = template;
	}

	@Override
	public List<ProviderEntry> getProviders()
	{
		DbRequest<ProviderEntry> req = new DbRequest<ProviderEntry>(template, "wadm_provider_get_list", ProviderEntry.class);
		return req.execute();
	}

	@Override
	public void saveProvider(ProviderEntry provider)
	{
		DbRequest<ProviderEntry> req = new DbRequest<ProviderEntry>(template, "wadm_provider_save", ProviderEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, provider),
							  new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public void removeProvider(int providerId)
	{
		DbRequest<ProviderEntry> req = new DbRequest<ProviderEntry>(template, "wadm_provider_delete", ProviderEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, providerId));
	}

	@Override
	public ProviderEntry getProvider(int providerId)
	{
		DbRequest<ProviderEntry> req = new DbRequest<ProviderEntry>(template, "wadm_provider_get", ProviderEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, providerId));
		return req.singleResult();
	}

	
	
	
	
	@Override
	public List<VariableEntry> getVariables(int providerId)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_provider_variable_get_list", VariableEntry.class);
		return req.execute(new SqlParameterValue(Types.OTHER, providerId));
	}

	@Override
	public void saveVariable(VariableEntry var)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_provider_variable_save", VariableEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, var),
							  new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public void removeVariable(int varId)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_provider_variable_delete", VariableEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, varId));
	}

	@Override
	public VariableEntry getVariable(int varId, int parentId)
	{
		DbRequest<VariableEntry> req = new DbRequest<VariableEntry>(template, "wadm_provider_variable_get", VariableEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, varId), new SqlParameterValue(Types.INTEGER, parentId));
		return req.singleResult();
	}
	
}
