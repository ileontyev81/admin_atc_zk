package atc.gui.admin.infrastructure.repository;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import atc.gui.admin.security.SpringSecurityHelper;
import atc.gui.admin.domain.model.ChannelEntry;
import atc.gui.admin.domain.service.repository.ChannelRepository;
import atc.gui.admin.infrastructure.dao.DbRequest;

@Repository("channelRepository")
public class ChannelRepositoryImpl implements ChannelRepository
{
	private JdbcTemplate template;
	
	@Autowired
	public ChannelRepositoryImpl(JdbcTemplate template)
	{
		this.template = template;
	}

	@Override
	public List<ChannelEntry> findChannels()
	{
		DbRequest<ChannelEntry> listDbRequest = new DbRequest<ChannelEntry>(template, "wadm_channel_get_list", ChannelEntry.class);
		return listDbRequest.execute();
	}

	@Override
	public void saveChannel(ChannelEntry channel)
	{
		DbRequest<ChannelEntry> saveDbRequest = new DbRequest<ChannelEntry>(template, "wadm_channel_save", ChannelEntry.class);
		saveDbRequest.execute(new SqlParameterValue(Types.OTHER, channel),
							  new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public void removeChannel(int channelId)
	{
		DbRequest<ChannelEntry> saveDbRequest = new DbRequest<ChannelEntry>(template, "wadm_channel_delete", ChannelEntry.class);
		saveDbRequest.execute(new SqlParameterValue(Types.OTHER, channelId));	
	}

	@Override
	public ChannelEntry getOpenedChannel(int channelId)
	{
		DbRequest<ChannelEntry> getDbRequest = new DbRequest<ChannelEntry>(template, "wadm_channel_get", ChannelEntry.class);
		getDbRequest.execute(new SqlParameterValue(Types.INTEGER, channelId), new SqlParameterValue(Types.BOOLEAN, false));
		return getDbRequest.singleResult();
	}

}
