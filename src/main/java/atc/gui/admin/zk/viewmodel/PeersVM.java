package atc.gui.admin.zk.viewmodel;

import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import atc.gui.admin.domain.service.repository.PeerRepository;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
public class PeersVM
{
	private PeerRepository repository;
	
	@WireVariable("peerRepository")
	public void setRepository(PeerRepository repository)
	{
		this.repository = repository;
	}
	
}
