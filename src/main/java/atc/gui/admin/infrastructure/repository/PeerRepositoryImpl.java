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
import atc.gui.admin.domain.model.PeerEntry;
import atc.gui.admin.domain.service.repository.PeerRepository;
import atc.gui.admin.infrastructure.dao.DbRequest;

@Repository("peerRepository")
public class PeerRepositoryImpl implements PeerRepository
{
	private JdbcTemplate template;
	
	@Autowired
	public PeerRepositoryImpl(JdbcTemplate template)
	{
		this.template = template;
	}

	@Override
	public List<PeerEntry> findPeers()
	{
		DbRequest<PeerEntry> listDbRequest = new DbRequest<PeerEntry>(template, "wadm_peer_get_list", PeerEntry.class);
		return listDbRequest.execute();
	}

	@Override
	public void savePeer(PeerEntry peer)
	{
		DbRequest<PeerEntry> saveDbRequest = new DbRequest<PeerEntry>(template, "wadm_peer_save", PeerEntry.class);
		saveDbRequest.execute(new SqlParameterValue(Types.OTHER, peer),
							  new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public void removePeer(int peerId)
	{
		DbRequest<PeerEntry> saveDbRequest = new DbRequest<PeerEntry>(template, "wadm_peer_delete", PeerEntry.class);
		saveDbRequest.execute(new SqlParameterValue(Types.OTHER, peerId));
	}

	@Override
	public PeerEntry getOpenedPeer(int peerId)
	{
		DbRequest<PeerEntry> getDbRequest = new DbRequest<PeerEntry>(template, "wadm_peer_get", PeerEntry.class);
		getDbRequest.execute(new SqlParameterValue(Types.INTEGER, peerId), new SqlParameterValue(Types.BOOLEAN, false));
		return getDbRequest.singleResult();
	}
	
}
