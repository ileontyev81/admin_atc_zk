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

package atc.gui.admin.domain.service.repository;

import java.util.List;

import atc.gui.admin.domain.model.QueueEntry;
import atc.gui.admin.domain.model.QueueMemberEntry;

public interface QueueRepository
{

	public List<QueueEntry> getQueues();
	public void saveQueue(QueueEntry queue);

	public abstract void removeQueue(int queueId);

	public QueueEntry getQueue(int queueId);

	
	
	public List<QueueMemberEntry> getMembers(int queueId);
	public QueueMemberEntry getMember(int id, int queueId);
	public void saveMember(QueueMemberEntry entry);
	public void deleteMember(int id);
	
	
	
}
