package atc.gui.admin.domain.service.repository;

import java.util.List;

import atc.gui.admin.domain.model.PeerEntry;

public interface PeerRepository
{

	public List<PeerEntry> findPeers();
	
	public void savePeer(PeerEntry peer);

	public abstract void removePeer(int peerId);

	public PeerEntry getOpenedPeer(int peerId);

}
