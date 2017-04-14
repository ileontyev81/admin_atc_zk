package atc.gui.admin.zk.viewmodel;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.domain.service.repository.ChannelRepository;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
@Init(superclass=true)
public class ChannelsVM
{
	private ChannelRepository repository;
	
	@WireVariable("channelRepository")
	public void setRepository(ChannelRepository repository)
	{
		this.repository = repository;
	}

}
