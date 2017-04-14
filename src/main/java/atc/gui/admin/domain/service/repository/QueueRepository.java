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
