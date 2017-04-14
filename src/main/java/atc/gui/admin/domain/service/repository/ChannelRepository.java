package atc.gui.admin.domain.service.repository;

import java.util.List;

import atc.gui.admin.domain.model.ChannelEntry;

public interface ChannelRepository
{

	public List<ChannelEntry> findChannels();
	
	public void saveChannel(ChannelEntry channel);

	public abstract void removeChannel(int channelId);

	public ChannelEntry getOpenedChannel(int channelId);

}
