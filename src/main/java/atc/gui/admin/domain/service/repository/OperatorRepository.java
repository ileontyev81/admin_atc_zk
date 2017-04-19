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

import atc.gui.admin.domain.model.GroupEntry;
import atc.gui.admin.domain.model.GroupSupervisorEntry;
import atc.gui.admin.domain.model.OperatorEntry;
import atc.gui.admin.domain.model.OperatorQueueEntry;

public interface OperatorRepository
{
	public void saveOperator(OperatorEntry e);
	public OperatorEntry getOperator(int id);
	public List<OperatorEntry> getOperatorList(Boolean export);
	public List<OperatorEntry> searchOperators(String key);
	public void deleteOperator(int id);
	
	
	public void saveQueue(OperatorQueueEntry e);
	public OperatorQueueEntry getQueue(int id, int operatorId);
	public List<OperatorQueueEntry> getQueueList(int operatorId);
	public void deleteQueue(int id);
	
	public void saveGroup(GroupEntry e);
	public GroupEntry getGroup(int id);
	public List<GroupEntry> getGroupList();
	
	public void saveGroupSupervisor(GroupSupervisorEntry e);
	public GroupSupervisorEntry getGroupSupervisor(int id);
	public List<GroupSupervisorEntry> getGroupSupervisorList(int groupId);
	public void deleteGroupSupervisor(int id);
}
